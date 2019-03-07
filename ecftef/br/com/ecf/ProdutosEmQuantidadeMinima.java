/*
 * QuantidadeMinima.java
 *
 * Created on 17 de Setembro de 2016, 18:29
 */

package br.com.ecf;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.ecf.model.ProdutoBean;
import br.com.ecf.model.ProdutoDAO;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

/**
 *
 * @author  Frederico
 */
public class ProdutosEmQuantidadeMinima extends javax.swing.JFrame
{
	private static javax.swing.JTable jTable1 = new javax.swing.JTable();
	static DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
    public ProdutosEmQuantidadeMinima()
    {
        initComponents();
        chamaQtdMinima();//ta fucionando???
        this.setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        
       

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos em quantidade mínima");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Produtos em quantidade mínima");
        jTable1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0)
        	{
        		int linha = jTable1.getSelectedRow();
        		String codigo = (String) jTable1.getValueAt(linha, 0);
        		String descricao = (String) jTable1.getValueAt(linha, 1);
        		String quantidade = String.valueOf(jTable1.getValueAt(linha, 2));
        		Date data =  new Date();
        		//chamando forulario q adiciona na lista de compras
        		String [] args = new String[1];
        		args[0] = "chamando";
        		AdicionarNaListaCompras.transportador(
    				codigo, 
    				descricao, 
    				quantidade, 
    				data
        		);
        		AdicionarNaListaCompras.main(args);
        	}
        });

        jTable1.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Código", "Descrição", "Qtd em Estoque", "Qtd Mínima"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(30)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(153)
        			.addComponent(jLabel1)
        			.addContainerGap(287, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(24)
        			.addComponent(jLabel1)
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(20, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProdutosEmQuantidadeMinima().setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
   
   
    // End of variables declaration
  
    public static void chamaQtdMinima()
    {
    	DefaultTableModel modelo2 = (DefaultTableModel)jTable1.getModel();
    	List<ProdutoBean>lista = new ArrayList<ProdutoBean>();
    	ProdutoDAO p = new ProdutoDAO();
    	lista = p.listarQuantidadeMinima();
    	
    	for(ProdutoBean b: lista)
    	{
    		modelo2.addRow(new Object[]{
    				b.getCodigo(),
    				b.getDescricao(),
    				b.getEstoque(),
    				b.getEstoqueCritico()
    		});
    	}
    }

    //para mostrar se há quantidade minima atingida no formulario painel.
    public boolean chamaQtdMinimaPainel()
    {
    	DefaultTableModel modelo2 = (DefaultTableModel)jTable1.getModel();
    	List<ProdutoBean>lista = new ArrayList<ProdutoBean>();
    	ProdutoDAO p = new ProdutoDAO();
    	lista = p.listarQuantidadeMinima();
    	if(lista.size() > 0)
    		return true;
    	else
    		return false;
    }
}
