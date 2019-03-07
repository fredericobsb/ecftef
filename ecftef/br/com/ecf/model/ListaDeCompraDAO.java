package br.com.ecf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ecf.bd.AcessoMySQL;

public class ListaDeCompraDAO
{
	String inserir = "INSERT INTO lista_compras (codigo, descricao, quantidade, flag_ja_comprou, data) VALUES (?, ?, ?, ?, ?)";
	String excluir = "UPDATE lista_compras set flag_ja_comprou = 1 WHERE codigo = ?";
	String listar = "SELECT * FROM lista_compras WHERE flag_ja_comprou = 0";

	PreparedStatement pstm;
	ResultSet rs;
	AcessoMySQL bd = new AcessoMySQL();	

	public void inserir(ListaDeCompraBean l)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(inserir);
			pstm.setString(1, l.getCodigo());
			pstm.setString(2,  l.getDescricao());
			pstm.setString(3, l.getQuantidade());
			pstm.setString(4, "0");
			pstm.setDate(5, l.getData());
			int rs = pstm.executeUpdate();
			if(rs > 0)
				JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Um erro ocorreu. Contacte o administrador do sistema.");
		}
		finally
		{
			bd.desconectar();
		}
	}

	public void excluir(String codigo)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(excluir);
			pstm.setString(1, codigo);
			pstm.executeUpdate();
			//JOptionPane.showMessageDialog(null, "Alterado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro na ListaDeCompraDAO. Informe este erro ao administrador do sistema.");
		}
		finally
		{
			bd.desconectar();
		}
	}

	public List<ProdutoBean>mostarListaCompras()
	{
		List<ProdutoBean>lista = new ArrayList<ProdutoBean>();
		try
		{
			pstm = bd.conectar().prepareStatement(listar);
			rs = pstm.executeQuery();
			while(rs.next())
			{
				ProdutoBean prod = new ProdutoBean();
				prod.setCodigo(rs.getString("codigo"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setEstoque(Integer.parseInt(rs.getString("estoque")));
				lista.add(prod);				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			bd.desconectar();
		}
		return lista;
	}
}
