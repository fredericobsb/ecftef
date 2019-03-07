package br.com.ecf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.ecf.bd.AcessoMySQL;

public class DetalheVendaDAO {

    PreparedStatement pstm;
    ResultSet rs;
    String insereDVenda = "INSERT INTO D_VENDA (PRODUTO_CODIGO, C_VENDA_CODIGO, " +
    	"QTDE, VLRUNITARIO, VLRTOTAL) VALUES(?,?,?,?,?)";

	AcessoMySQL bd = new AcessoMySQL();
    
    public void inserirDVenda(DetalheVendaBean dVenda){
        try{
            pstm = bd.conectar().prepareStatement(insereDVenda);
            pstm.setString(1, dVenda.getCodigoProduto());
            pstm.setInt(2, dVenda.getCodigoCabecalhoVenda());
            pstm.setInt(3, dVenda.getQuantidade());
            pstm.setDouble(4, dVenda.getValorUnitario());
            pstm.setDouble(5, dVenda.getValorTotal());
            pstm.executeUpdate();
            bd.desconectar();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	
}
