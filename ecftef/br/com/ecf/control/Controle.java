package br.com.ecf.control;

import java.util.ArrayList;

import br.com.ecf.model.CabecalhoVendaBean;
import br.com.ecf.model.CabecalhoVendaDAO;
import br.com.ecf.model.DetalheVendaBean;
import br.com.ecf.model.DetalheVendaDAO;
import br.com.ecf.model.ProdutoBean;
import br.com.ecf.model.ProdutoDAO;

public class Controle {

	DetalheVendaBean dVendaBean;
	DetalheVendaDAO dVendaDAO = new DetalheVendaDAO();
	
	CabecalhoVendaBean cVendaBean;
	CabecalhoVendaDAO cVendaDAO = new CabecalhoVendaDAO();
	
	ProdutoBean produtoBean;
	ProdutoDAO produtoDAO = new ProdutoDAO();

	public CabecalhoVendaBean iniciaVenda() {
		cVendaBean = new CabecalhoVendaBean();
		cVendaBean = cVendaDAO.inserirCVenda(cVendaBean);
		return cVendaBean;
	}

	public void fechaVenda(CabecalhoVendaBean cVendaBean, ArrayList<DetalheVendaBean> arrayDVendaBean)
	{
		cVendaDAO.alterarCVenda(cVendaBean);
		for (int i = 0; i < arrayDVendaBean.size(); i++)
		{
			dVendaDAO.inserirDVenda(arrayDVendaBean.get(i));
		}
	}

	public ProdutoBean retornaProdutoPeloCodigo(String codigo)
	{
		produtoBean = new ProdutoBean();
		produtoBean = produtoDAO.listarProdutosPeloCodigo(codigo);
		return produtoBean;
	}
}
