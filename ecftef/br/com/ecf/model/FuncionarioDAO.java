package br.com.ecf.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.ecf.EntradaSaidaOperadores;
import br.com.ecf.bd.AcessoMySQL;

public class FuncionarioDAO {

    PreparedStatement pstm;
    ResultSet rs;
    String consultaFuncionario = "SELECT * FROM FUNCIONARIO WHERE DESCRICAO LIKE ?";
    String consultaFuncionarioCodigo = "SELECT * FROM FUNCIONARIO WHERE CODIGO=?";
    String verificaSenhaAdmin = "SELECT * FROM FUNCIONARIO WHERE senha = ? and cargo = ?";
    String login = "SELECT * FROM FUNCIONARIO WHERE LOGIN = ? AND SENHA = ?";
    String cadastro = "INSERT INTO FUNCIONARIO (NOME, LOGIN, SENHA, CELULAR, CARGO) VALUES(?,?,?,?,?)";
    String horaLogin = "INSERT INTO ENTRADASAIDAOPERADORES (NOME, DATA, HORALOGIN) VALUES(?,?,?)";
    String horaLogout = "INSERT INTO ENTRADASAIDAOPERADORES (HORALOGOUT) VALUES(?) WHERE CODIGO = ? AND DATE = ?";
    AcessoMySQL bd = new AcessoMySQL();	
	
    public List<FuncionarioBean> listarFuncionarios(String descricao)
    {
        List<FuncionarioBean> funcionarios = new ArrayList<FuncionarioBean>();
        try{
            pstm = bd.conectar().prepareStatement(consultaFuncionario);
            pstm.setString(1, "%" + descricao + "%");//SELECT * FROM FUNCIONARIO WHERE DESCRICAO LIKE caneta
            rs = pstm.executeQuery();
            FuncionarioBean funcionario;
            while (rs.next()){
            	funcionario = new FuncionarioBean();
            	funcionario.setNome(rs.getString("nome"));
            	funcionario.setEndereco(rs.getString("endereco"));
            	funcionario.setEmail(rs.getString("email"));
            	funcionario.setBairro(rs.getString("bairro"));
            	funcionario.setCidade(rs.getString("cidade"));
            	funcionario.setCep(rs.getString("cep"));
            	funcionario.setEstado(rs.getString("estado"));
            	funcionario.setTelefone(rs.getString("telefone"));
            	funcionario.setCelular(rs.getString("celular"));
            	funcionarios.add(funcionario);
            }
            bd.desconectar();
        } catch(Exception e){
            e.printStackTrace();
        }
        return funcionarios;
    }
	
	public FuncionarioBean listarFuncionariosPeloCodigo(String codigo)
	{
		FuncionarioBean funcionario = new FuncionarioBean();
		try 
		{
			pstm = bd.conectar().prepareStatement(consultaFuncionarioCodigo);
			pstm.setString(1, codigo);
			rs = pstm.executeQuery();
			rs.first();
			funcionario.setCodigo(rs.getString("codigo"));
			funcionario.setNome(rs.getString("nome"));
        	funcionario.setEndereco(rs.getString("endereco"));
        	funcionario.setEmail(rs.getString("email"));
        	funcionario.setBairro(rs.getString("bairro"));
        	funcionario.setCidade(rs.getString("cidade"));
        	funcionario.setCep(rs.getString("cep"));
        	funcionario.setEstado(rs.getString("estado"));
        	funcionario.setTelefone(rs.getString("telefone"));
        	funcionario.setCelular(rs.getString("celular"));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			bd.desconectar();
		}
		return funcionario;
	}

	public FuncionarioBean buscaFuncionarioAdmin(String senha)
	{
		FuncionarioBean funcionario = new FuncionarioBean();
		try
		{
			pstm = bd.conectar().prepareStatement(verificaSenhaAdmin);
			pstm.setString(1, senha);
			pstm.setString(2, "admin");
			rs = pstm.executeQuery();
			if(rs.first() == true)
			{
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setCargo(rs.getString("cargo"));
			}
			else
			{
				funcionario.setSenha("");
				funcionario.setCargo("");
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
		return funcionario;
	}

	public FuncionarioBean login(FuncionarioBean f)
	{
		
		FuncionarioBean funcionario = new FuncionarioBean();
		try
		{
			pstm = bd.conectar().prepareStatement(login);
			pstm.setString(1, f.getLogin());
			pstm.setString(2, f.getSenha());
			rs = pstm.executeQuery();
			if(rs.first() == true)
			{
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setNome(rs.getString("nome"));
			}
			else
			{
				funcionario.setLogin("");
				funcionario.setSenha("");
				funcionario.setCargo("");
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
		return funcionario;
	}

	public void incluir(FuncionarioBean f)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(cadastro);
			pstm.setString(1, f.getNome());
			pstm.setString(2, f.getLogin());
			pstm.setString(3, f.getSenha());
			pstm.setString(4, f.getCelular());
			pstm.setString(5, f.getCargo());
			int rs = pstm.executeUpdate();//executeQuery();
			if(rs > 0)
			{
				JOptionPane.showMessageDialog(null,"Incluído com sucesso!");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Um erro ocorreu. Tente novamente.");
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
	}

	public void incluirHoraLogin(EntradaSaidaOperadoresBean o)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(horaLogin);
			pstm.setString(1, o.getNome());
			pstm.setDate(2, o.getData());
			pstm.setString(3, o.getHoraLogin());
			int rs = pstm.executeUpdate();
			if(rs > 0)
			{
				JOptionPane.showMessageDialog(null,"Incluído com sucesso!");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Um erro ocorreu. Tente novamente.");
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
	}

	public void incluirHoraLogout(EntradaSaidaOperadoresBean o)
	{
		try
		{
			pstm = bd.conectar().prepareStatement(horaLogout);
			pstm.setString(1, o.getHoraLogout());
			pstm.setString(2, o.getId());
			pstm.setDate(3, o.getData());
			int rs = pstm.executeUpdate();
			if(rs > 0)
			{
				JOptionPane.showMessageDialog(null, "Voce saiu do sistema.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Erro ao fazer logout...");
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
	}
}

