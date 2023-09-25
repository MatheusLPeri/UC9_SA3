package filter;

import java.io.IOException;
import java.sql.Connection;

import conexao.ConexaoBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/painel/*" })
public class FilterPainel extends HttpFilter implements Filter
{
	private static Connection connection;

	public FilterPainel()
	{

	}

	public void destroy()
	{
		try
		{
			connection.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		try
		{
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession sessao = req.getSession();

			String usuarioLogado = (String) sessao.getAttribute("usuario");
			String urlAutenticar = req.getServletPath();

			if (usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/painel/LoginServlet"))
			{
				RequestDispatcher redireciona = request.getRequestDispatcher("/login");
				request.setAttribute("msg", "Efetue o login!");
				redireciona.forward(request, response);
				return;
			} else
			{
				chain.doFilter(request, response);
			}
			connection.commit();

		} catch (Exception e)
		{
			e.printStackTrace();
			RequestDispatcher redireciona = request.getRequestDispatcher("erros.jsp");
			request.setAttribute("msg", e.getMessage());
			redireciona.forward(request, response);

			try
			{
				connection.rollback();
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException
	{
		connection = ConexaoBanco.getConnection();
	}

}
