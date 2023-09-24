package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class ServletLogin extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ServletLogin()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String nomeLogin = request.getParameter("name");
		String matriculaLogin = request.getParameter("matricula");
		String enderecoLogin = request.getParameter("endereco");
		String senhaLogin = request.getParameter("senha");

		if (nomeLogin != null && !nomeLogin.isEmpty() && matriculaLogin != null && !matriculaLogin.isEmpty())
		{
			Usuario newUser = new Usuario();
			newUser.setUsuario(nomeLogin);
			newUser.setSenha(senhaLogin);
		} else
		{
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Utilize informações válidas!");
			redirecionar.forward(request, response);
		}
	}

}
