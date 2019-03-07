package br.com.ecf.model;

public class DetalheVendaBean {

	String codigoProduto;
	Integer quantidade;
	Double valorUnitario;
	Double valorTotal;
	Integer codigoCabecalhoVenda;
	
	public Integer getCodigoCabecalhoVenda() {
		return codigoCabecalhoVenda;
	}
	public void setCodigoCabecalhoVenda(Integer codigoCabecalhoVenda) {
		this.codigoCabecalhoVenda = codigoCabecalhoVenda;
	}
	public String getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}

