package br.com.ecf.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ecf.bd.AcessoMySQL;

public class CabecalhoVendaDAO {

    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    
    String insereCVenda = "INSERT INTO C_VENDA (CODIGO) VALUES (NULL)";
    String ultimoCodigo = "SELECT MAX(CODIGO) AS CODIGO FROM C_VENDA";
    String alteraCVenda = "UPDATE C_VENDA SET DATAVENDA = ?, VALOR = ?, DESCONTO = ?, TOTAL = ? "+
    "where CODIGO = ?";
    AcessoMySQL bd = new AcessoMySQL();
    
    public CabecalhoVendaBean inserirCVenda(CabecalhoVendaBean cVenda){
    	CabecalhoVendaBean cVendaBean = new CabecalhoVendaBean(); 
        try{
            pstm = bd.conectar().prepareStatement(insereCVenda);
            pstm.executeUpdate();
            cVendaBean.setCodigo(retornaUltimoCodigo());
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            bd.desconectar();
        }
        return cVendaBean;
    }
	
	public int retornaUltimoCodigo() 
	{
		try 
		{
			stm = bd.conectar().createStatement();
			rs = stm.executeQuery(ultimoCodigo);
			rs.first();
			return rs.getInt(1);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			bd.desconectar();
		}
		return 0;
	}
	
	public void alterarCVenda(CabecalhoVendaBean cVenda)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(alteraCVenda);
			pstm.setDate(1, (Date) cVenda.getDataVenda());
			pstm.setDouble(2, cVenda.getValorVenda());
			pstm.setDouble(3, cVenda.getDescontoVenda());
			pstm.setDouble(4, cVenda.getTotalVenda());
			pstm.setDouble(5, cVenda.getCodigo());
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
}
