package model;

public class Cliente
{
	private Long id;
	private String nome;
	private String endereco;
	private String modalidade;
	private String cpf;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}

	public String getModalidade()
	{
		return modalidade;
	}

	public void setModalidade(String modalidade)
	{
		this.modalidade = modalidade;
	}
	
	public String getCpf()
	{
		return cpf;
	}

	public void setCpf(String cpf)
	{
		this.cpf = cpf;
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
