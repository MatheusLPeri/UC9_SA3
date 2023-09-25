package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexao.ConexaoBanco;
import model.Usuario;

public class LoginRepository
{
	private Connection connection;
	
	public LoginRepository()
	{
		connection = ConexaoBanco.getConnection();
	}
	
	public Boolean ValidarLogin(Usuario usuario) throws Exception
	{
		String sql = "SELECT * FROM usuario WHERE usuario = ? and senha = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, usuario.getUsuario());
		stmt.setString(2, usuario.getSenha());
		
		ResultSet rst = stmt.executeQuery();
		if(rst.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
