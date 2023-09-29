package model;

public class Usuario
{
	private Long id;
	private String usuario;
	private String senha;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUsuario()
	{
		return usuario;
	}

	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public boolean novo()
	{
		if (this.id == null)
		{
			return true;
		} else if (this.id != null && this.id > 0)
		{
			return false;
		}
		return id == null;
	}
}
