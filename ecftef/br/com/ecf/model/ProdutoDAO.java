package br.com.ecf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ecf.bd.AcessoMySQL;

public class ProdutoDAO {

    PreparedStatement pstm;
    ResultSet rs;
    String consultaProduto = "SELECT P.CODIGO, P.UNIDADE_CODIGO, P.FORNECEDOR_CODIGO, P.DESCRICAO, P.VLRCOMPRA, P.VLRVENDA, P.ESTOQUE, P.CRITICO, P.AUDITORIA, P.EXCLUIDO, Q.NOME FROM PRODUTO P, FORNECEDOR Q WHERE DESCRICAO LIKE ?";
    String consultaProdutoCodigo = "SELECT * FROM PRODUTO WHERE CODIGO=?";
    String quantidadeMinima = "SELECT P.CODIGO, P.UNIDADE_CODIGO, P.FORNECEDOR_CODIGO, P.DESCRICAO, P.VLRCOMPRA, P.VLRVENDA, P.ESTOQUE, P.CRITICO, P.AUDITORIA, P.EXCLUIDO, Q.NOME, Q.CELULAR FROM PRODUTO P, FORNECEDOR Q WHERE P.ESTOQUE = P.CRITICO GROUP BY P.CODIGO";
    String buscaNomeFornecedor = "SELECT NOME FROM FORNECEDOR WHERE CODIGO = ?"; 
    String alterarQuantidadeEstoque = "UPDATE PRODUTO SET ESTOQUE = ? WHERE CODIGO = ?";
    
	AcessoMySQL bd = new AcessoMySQL();	
	
    public List<ProdutoBean> listarProdutos(String descricao)
    {
        List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();
        try{
            pstm = bd.conectar().prepareStatement(consultaProduto);
            pstm.setString(1, "%" + descricao + "%");//SELECT * FROM PRODUTO WHERE DESCRICAO LIKE caneta
            rs = pstm.executeQuery();
            ProdutoBean prod;
            while (rs.next()){
                prod = new ProdutoBean();
                prod.setCodigo(rs.getString("codigo"));
                prod.setAuditoria(rs.getString("auditoria"));
                prod.setCodigoFornecedor(rs.getInt("fornecedor_codigo"));
                prod.setCodigoUnidade(rs.getInt("unidade_codigo"));
                prod.setEstoque(rs.getInt("estoque"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setEstoqueCritico(rs.getInt("critico"));
                prod.setValorCompra(rs.getDouble("vlrcompra"));
                prod.setValorVenda(rs.getDouble("vlrvenda"));
                prod.setNomeFornecedor(rs.getString("nome"));
                produtos.add(prod);
            }
            bd.desconectar();
        } catch(Exception e){
            e.printStackTrace();
        }
        return produtos;
    }
	
	public ProdutoBean listarProdutosPeloCodigo(String codigo)
	{
		ProdutoBean prod = new ProdutoBean();
		try 
		{
			pstm = bd.conectar().prepareStatement(consultaProdutoCodigo);
			pstm.setString(1, codigo);
			rs = pstm.executeQuery();
			rs.first();
			prod.setCodigo(rs.getString("codigo"));
			prod.setAuditoria(rs.getString("auditoria"));
			prod.setCodigoFornecedor(rs.getInt("fornecedor_codigo"));
			prod.setCodigoUnidade(rs.getInt("unidade_codigo"));
			prod.setEstoque(rs.getInt("estoque"));
			prod.setDescricao(rs.getString("descricao"));
			prod.setEstoqueCritico(rs.getInt("critico"));
			prod.setValorCompra(rs.getDouble("vlrcompra"));
			prod.setValorVenda(rs.getDouble("vlrvenda"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			bd.desconectar();
		}
		return prod;
	}

	public List<ProdutoBean> listarQuantidadeMinima()
	{
		List<ProdutoBean> produtos = new ArrayList<ProdutoBean>();
		try
		{
			pstm = bd.conectar().prepareStatement(quantidadeMinima);
			rs = pstm.executeQuery();
			ProdutoBean prod;
			while (rs.next())
			{
				prod = new ProdutoBean();
				prod.setCodigo(rs.getString("codigo"));
				prod.setAuditoria(rs.getString("auditoria"));
				prod.setCodigoFornecedor(rs.getInt("fornecedor_codigo"));
				prod.setCodigoUnidade(rs.getInt("unidade_codigo"));
				prod.setEstoque(rs.getInt("estoque"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setEstoqueCritico(rs.getInt("critico"));
				prod.setValorCompra(rs.getDouble("vlrcompra"));
				prod.setValorVenda(rs.getDouble("vlrvenda"));
				prod.setNomeFornecedor(rs.getString("nome"));
				prod.setTelefoneFornecedor(rs.getString("celular"));
				produtos.add(prod);
			}
			bd.desconectar();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return produtos;
	}

	public void alterarQtdEstoque(String codigo, String quantidade)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(alterarQuantidadeEstoque);
			pstm.setString(1, quantidade);
			pstm.setString(2, codigo);
			int rs = pstm.executeUpdate();
			if(rs > 0)
				JOptionPane.showMessageDialog(null, "Alterado com sucesso");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro no ProdutoDAO - alterarQtdEstoque. Informe este erro ao administrador do sistema.");
		}
		finally
		{
			bd.desconectar();
		}
	}
}
