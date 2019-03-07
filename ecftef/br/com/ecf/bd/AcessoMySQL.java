package br.com.ecf.bd;

import java.sql.*;

public class AcessoMySQL {
	
	Connection con;
	
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //localhost = 127.0.0.1
            //ecftef = nome do banco 
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ecftef?user=root&password=");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível encontrar o Driver!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível conectar ao banco!");
        }
        return con;
    }
    
    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }	

}
