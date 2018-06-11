package com.unitunes.model.usuario;

import com.unitunes.pagamento.model.Comprador;

public class Academico implements Comprador {
	
	private Long id;
	private String primeiroNome, segundoNome, email, senha, cpf;
	private Long matricula;
	private TipoVinculo tipoVinculo;
	
	public Academico() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSegundoNome() {
		return segundoNome;
	}

	public void setSegundoNome(String segundoNome) {
		this.segundoNome = segundoNome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public TipoVinculo getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(TipoVinculo tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public enum TipoVinculo {
		Aluno(1), Professor(2), Funcionario(3);
		
		Integer tipo;
		
		TipoVinculo(Integer tipo) {
			this.tipo = tipo;
		}
	}

	@Override
	public String getName() {
		return String.format("%s %s", this.getPrimeiroNome(), this.getSegundoNome());
	}

	@Override
	public String getCPF() {
		return this.cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}

