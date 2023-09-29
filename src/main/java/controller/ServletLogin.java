package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;

import dao.LoginRepository;

@WebServlet("/LoginServlet")
public class ServletLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private LoginRepository loginRepository = new LoginRepository();

	public ServletLogin()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String acao = request.getParameter("acao");
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout"))
		{
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("login.jsp");
			redirecionar.forward(request, response);
		}
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nomeLogin = request.getParameter("nome");
		//String matriculaLogin = request.getParameter("matricula");
		//String enderecoLogin = request.getParameter("endereco");
		String senhaLogin = request.getParameter("senha");

		String url = request.getParameter("url");

		try
		{
			if (nomeLogin != null && !nomeLogin.isEmpty() && senhaLogin != null && !senhaLogin.isEmpty())
			{
				Usuario newUser = new Usuario();
				newUser.setUsuario(nomeLogin);
				newUser.setSenha(senhaLogin);

				if (loginRepository.ValidarLogin(newUser))
				{
					request.getSession().setAttribute("usuario", newUser.getUsuario());
					if (url == null || url.equals("null"))
					{
						url = "painel/inicio.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else
				{
					RequestDispatcher redirecionar = request.getRequestDispatcher("login.jsp");
					request.setAttribute("msg", "Nome de usuário ou senha incorretos.");
					redirecionar.forward(request, response);
				}

			} else
			{
				RequestDispatcher redirecionar = request.getRequestDispatcher("login.jsp");
				request.setAttribute("msg", "Utilize informações válidas!");
				redirecionar.forward(request, response);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
