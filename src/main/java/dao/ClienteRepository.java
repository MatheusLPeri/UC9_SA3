package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoBanco;
import model.Cliente;
import model.Usuario;

public class ClienteRepository
{
	private Connection conn;

	public ClienteRepository()
	{
		conn = ConexaoBanco.getConnection();
	}

	public Cliente insereCliente(Cliente objeto) throws Exception
	{
		if (objeto.novo())
		{
			String sql = "INSERT INTO cliente(nome, endereco, modalidade, cpf) VALUES(?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, objeto.getNome());
			stmt.setString(2, objeto.getEndereco());
			stmt.setString(3, objeto.getModalidade());
			stmt.setString(4, objeto.getCpf());

			stmt.execute();

			conn.commit();
		} else
		{
			String sql = "UPDATE cliente SET nome=?, endereco=?, modalidade=?, cpf=? WHERE id = " + objeto.getId() + ";";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, objeto.getNome());
			stmt.setString(2, objeto.getEndereco());
			stmt.setString(3, objeto.getModalidade());
			stmt.setString(4, objeto.getCpf());

			stmt.executeUpdate();

			conn.commit();
		}
		return this.consultarCliente(objeto.getNome());
	}

	public Cliente consultarCliente(String cliente) throws Exception
	{
		Cliente newCliente = new Cliente();

		String sql = "SELECT * FROM cliente WHERE nome = '" + cliente + "'";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rst = stmt.executeQuery();

		while (rst.next())
		{
			newCliente.setId(rst.getLong("id"));
			newCliente.setNome(rst.getString("nome"));
			newCliente.setEndereco(rst.getString("endereco"));
			newCliente.setModalidade(rst.getString("modalidade"));
			newCliente.setCpf(rst.getString("cpf"));
		}

		return newCliente;
	}

	public boolean verificaCliente(String cliente) throws Exception
	{
		String sql = "SELECT COUNT(1) > 0 AS EXISTE FROM cliente where nome = '" + cliente + "';";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet res = stmt.executeQuery();

		res.next();
		return res.getBoolean("existe");
	}

	public void deletarCliente(String clienteId) throws Exception
	{
		String sql = "DELETE FROM cliente WHERE id = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(clienteId));
		stmt.executeUpdate();
		conn.commit();

	}

	public List<Cliente> consultarClienteLista(String nome) throws Exception
	{
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		String sql = "SELECT * FROM cliente WHERE nome like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + nome + "%");
		ResultSet rst = stmt.executeQuery();
		while (rst.next())
		{
			Cliente newCliente = new Cliente();
			newCliente.setId(rst.getLong("id"));
			newCliente.setNome(rst.getString("usuario"));
			newCliente.setEndereco(rst.getString("endereco"));
			newCliente.setModalidade(rst.getString("modalidade"));
			newCliente.setCpf(rst.getString("cpf"));

			listaCliente.add(newCliente);
		}
		return listaCliente;
	}

	public Cliente consultarClienteID(String id) throws Exception
	{
		Cliente newCliente = new Cliente();
		String sql = "SELECT * FROM cliente WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setLong(1, Long.parseLong(id));
		ResultSet rst = stmt.executeQuery();
		while (rst.next())
		{
			newCliente.setId(rst.getLong("id"));
			newCliente.setNome(rst.getString("usuario"));
			newCliente.setEndereco(rst.getString("endereco"));
			newCliente.setModalidade(rst.getString("modalidade"));
			newCliente.setCpf(rst.getString("cpf"));
		}
		return newCliente;
	}
}
