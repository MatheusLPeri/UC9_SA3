package controller;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserRepository;
import dao.ClienteRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Usuario;

public class ClienteServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private ClienteRepository clienteRepository = new ClienteRepository();

	public ClienteServlet()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			String acao = request.getParameter("acao");
			if (acao != null && !acao.isEmpty() & acao.equalsIgnoreCase("deletar"))
			{

				String clienteId = request.getParameter("id");

				clienteRepository.deletarCliente(clienteId);
				request.setAttribute("msg", "Excluído com Sucesso!!!");
				request.getRequestDispatcher("painel/clientes.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax"))
			{

				String clienteId = request.getParameter("id");

				clienteRepository.deletarCliente(clienteId);

				response.getWriter().write("Excluído com Ajax");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("consultarAjax"))
			{

				String nome = request.getParameter("nomeBusca");
				List<Cliente> dadosCliente = clienteRepository.consultarClienteLista(nome);
				ObjectMapper mapa = new ObjectMapper();
				String json = mapa.writeValueAsString(dadosCliente);
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar"))
			{

				String id = request.getParameter("id");
				Cliente newCliente = clienteRepository.consultarClienteID(id);

				request.setAttribute("msg", "Cliente em Edição!!!");
				request.setAttribute("newCliente", newCliente);
				request.getRequestDispatcher("painel/clientes.jsp").forward(request, response);

			} else
			{
				request.getRequestDispatcher("painel/clientes.jsp").forward(request, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("painel/clientes.jsp");//
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
			String nome = request.getParameter("nome");
			String endereco = request.getParameter("endereco");
			String modalidade = request.getParameter("modalidade");
			String cpf = request.getParameter("cpf");

			Cliente newCliente = new Cliente();

			newCliente.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			newCliente.setNome(nome);
			newCliente.setEndereco(endereco);
			newCliente.setModalidade(modalidade);
			newCliente.setCpf(cpf);

			if (clienteRepository.verificaCliente(newCliente.getNome()) && newCliente.getId() == null)
			{

				mensagem = "Cliente já cadastrado, informe outro cliente!!!";

			} else
			{

				if (newCliente.novo())
				{

					mensagem = "Gravado com Sucesso!!";

				} else
				{

					mensagem = "Atualizado com Sucesso!!";

				}
				newCliente = clienteRepository.insereCliente(newCliente);
			}

			request.setAttribute("msg", mensagem);
			request.setAttribute("newCliente", newCliente);
			request.getRequestDispatcher("painel/clientes.jsp").forward(request, response);

		} catch (Exception e)
		{
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("painel/clientes.jsp");//
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);
		}
	}

}
