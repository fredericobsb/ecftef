package br.com.ecf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ecf.bd.AcessoMySQL;

public class AcessoDAO {

	AcessoMySQL bd = new AcessoMySQL();	
	PreparedStatement pstm;
    ResultSet rs;
	String incluiAcao = "INSERT INTO ACESSO (nome, login, data, hora, acao) VALUES(?,?,?,?,?)";
	String pesquisaPorNome = "SELECT * FROM ACESSO WHERE NOME LIKE ? ORDER BY DATA DESC";
	String pesquisaPorData = "SELECT * FROM ACESSO WHERE DATA LIKE ? ORDER BY DATA DESC";

	public void incluir(AcessoBean a)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(incluiAcao);
			pstm.setString(1, a.getNome());
			pstm.setString(2,a.getLogin());//SELECT * FROM PRODUTO WHERE DESCRICAO LIKE caneta
			pstm.setDate(3,a.getData());
			pstm.setString(4,a.getHora());
			pstm.setString(5,a.getAcao());
			pstm.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			bd.desconectar();
		}
	}

	public List<AcessoBean> pesquisarPorNome(AcessoBean a)
	{
		List<AcessoBean>lista = new ArrayList<AcessoBean>();
		try
		{
			pstm = bd.conectar().prepareStatement(pesquisaPorNome);
			pstm.setString(1, a.getNome());
			rs = pstm.executeQuery();
			//rs.first();
			while(rs.next())
			{
				AcessoBean ac = new AcessoBean();
				ac.setNome(rs.getString("nome"));
				ac.setLogin(rs.getString("login"));
				ac.setData(rs.getDate("data"));
				ac.setHora(rs.getString("hora"));
				ac.setAcao(rs.getString("acao"));
				lista.add(ac);
			}
			// }
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

	public List<AcessoBean> pesquisarPorData(AcessoBean a)
	{
		List<AcessoBean>lista = new ArrayList<AcessoBean>();
		try
		{
			pstm = bd.conectar().prepareStatement(pesquisaPorData);
			pstm.setDate(1, a.getData());
			 rs = pstm.executeQuery();
			rs.first();
			while(rs.next())
			{
				AcessoBean ac = new AcessoBean();
				ac.setNome(rs.getString("nome"));
				ac.setLogin(rs.getString("login"));
				ac.setData(rs.getDate("data"));
				ac.setHora(rs.getString("hora"));
				ac.setAcao(rs.getString("acao"));
				lista.add(ac);
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
