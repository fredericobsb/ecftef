package br.com.ecf.model;

import java.sql.Date;

public class EntradaSaidaOperadoresBean
{
	private String id;
	private String nome;
	private Date data;
	private String horaLogin;
	private String horaLogout;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getHoraLogin() {
		return horaLogin;
	}
	public void setHoraLogin(String horaLogin) {
		this.horaLogin = horaLogin;
	}
	public String getHoraLogout() {
		return horaLogout;
	}
	public void setHoraLogout(String horaLogout) {
		this.horaLogout = horaLogout;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
