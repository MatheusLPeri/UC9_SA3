package controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

public class UserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private UserRepository userRepository = new UserRepository();

	public UserServlet()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() & acao.equalsIgnoreCase("deletar"))
			{

				String userId = request.getParameter("id");

				userRepository.deletarUsuario(userId);
				request.setAttribute("msg", "Excluído com Sucesso!!!");
				request.getRequestDispatcher("painel/inicio.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax"))
			{

				String userId = request.getParameter("id");

				userRepository.deletarUsuario(userId);

				response.getWriter().write("Excluído com Ajax");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("consultarAjax"))
			{

				String nome = request.getParameter("nomeBusca");
				List<Usuario> dadosUsuario = userRepository.consultarUsuarioLista(nome);
				ObjectMapper mapa = new ObjectMapper();
				String json = mapa.writeValueAsString(dadosUsuario);
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar"))
			{

				String id = request.getParameter("id");
				Usuario newUser = userRepository.consultarUsuarioID(id);

				request.setAttribute("msg", "Usuário em Edição!!!");
				request.setAttribute("newUser", newUser);
				request.getRequestDispatcher("painel/inicio.jsp").forward(request, response);

			} else
			{
				request.getRequestDispatcher("painel/inicio.jsp").forward(request, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("painel/error.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String mensagem = "Cadastro Realizado com Sucesso!!";
			String id = request.getParameter("id");
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");

			Usuario newUser = new Usuario();

			newUser.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			newUser.setUsuario(usuario);
			newUser.setSenha(senha);

			if (userRepository.verificaUsuario(newUser.getUsuario()) && newUser.getId() == null)
			{

				mensagem = "Usuário já cadastrado, informe outro usuário!!!";

			} else
			{

				if (newUser.novo())
				{

					mensagem = "Gravado com Sucesso!!";

				} else
				{

					mensagem = "Atualizado com Sucesso!!";

				}
				newUser = userRepository.insereUsuario(newUser);
			}

			request.setAttribute("msg", mensagem);
			request.setAttribute("newUser", newUser);
			request.getRequestDispatcher("painel/inicio.jsp").forward(request, response);

		} catch (Exception e)
		{
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erros.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

}
