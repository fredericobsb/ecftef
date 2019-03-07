/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecf.model;

import java.util.Date;

/**
 *
 * @author Frederico
 */
public class CabecalhoVendaBean {

        Integer codigo;
	Integer codigoFuncionario;
	Integer codigoCliente;
	Integer codigoCartao;
	Date dataVenda;
	Double valorVenda;
	Double descontoVenda;
	Double totalVenda;
	Integer numeroParcelas;
	Date primeiroVencimento;
	Integer numeroNota;
	String auditoria;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getCodigoFuncionario() {
		return codigoFuncionario;
	}
	public void setCodigoFuncionario(Integer codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
	public Integer getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public Integer getCodigoCartao() {
		return codigoCartao;
	}
	public void setCodigoCartao(Integer codigoCartao) {
		this.codigoCartao = codigoCartao;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Double getDescontoVenda() {
		return descontoVenda;
	}
	public void setDescontoVenda(Double descontoVenda) {
		this.descontoVenda = descontoVenda;
	}
	public Double getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}
	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}
	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	public Date getPrimeiroVencimento() {
		return primeiroVencimento;
	}
	public void setPrimeiroVencimento(Date primeiroVencimento) {
		this.primeiroVencimento = primeiroVencimento;
	}
	public Integer getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}
	public String getAuditoria() {
		return auditoria;
	}
	public void setAuditoria(String auditoria) {
		this.auditoria = auditoria;
	}
}
