/*
 * ListaDeCompras.java
 *
 * Created on 24 de Setembro de 2016, 10:36
 */

package br.com.ecf;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import br.com.ecf.model.FornecedorBean;
import br.com.ecf.model.ListaDeCompraDAO;
import br.com.ecf.model.ProdutoBean;
import br.com.ecf.model.ProdutoDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author  Frederico
 */
public class ListaDeCompras extends javax.swing.JFrame {

	 static javax.swing.JTable jTable1;
	
    public ListaDeCompras()
    {
    	
    	Dimension dim = new Dimension();
        dim.width = 900;
        dim.height = 600;
        this.setPreferredSize(dim);
        
        initComponents();
        this.setLocationRelativeTo(null);
        chamaLista();
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
        jTable1 = new javax.swing.JTable();
        jTable1.addMouseListener(new MouseAdapter()
        {
        	//EVENTOS NO JTABLE
        	@Override
        	public void mouseClicked(MouseEvent arg0)
        	{
        		int linha = jTable1.getSelectedRow();
        		String codigo = (String) jTable1.getValueAt(linha, 0);
        		//String descricao = (String) jTable1.getValueAt(linha, 1);
        		String quantidadeDisponivel = String.valueOf(jTable1.getValueAt(linha, 2));
        		/*
        		String nomeFornecedor = (String) jTable1.getValueAt(linha, 3);
        		String celularFornecedor = (String) jTable1.getValueAt(linha, 4);
        		*/
        		String [] args = new String[1];
        		args[0] = "chamando";
        		DecisaoListaCompras.transportador(codigo, quantidadeDisponivel);
        		DecisaoListaCompras.main(args);
        		dispose();
        	}
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Compras");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24));
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Lista de Compras");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Código", "Descrição", "Qtd. disponível", "Nome Fornecedor", "Tel. Fornecedor"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaDeCompras().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    
    // End of variables declaration

    public static void chamaLista()
    {
    	DefaultTableModel modelo2 = (DefaultTableModel)jTable1.getModel();
    	List<ProdutoBean>lista = new ArrayList<ProdutoBean>();
    	//ProdutoDAO p = new ProdutoDAO();
    	ListaDeCompraDAO p = new ListaDeCompraDAO();
    	lista = p.mostarListaCompras();
    	List<ProdutoBean>listaTemp = new ArrayList<ProdutoBean>();
    	for(ProdutoBean b: lista)
    	{
    		modelo2.addRow(new Object[]{
    				b.getCodigo(),
    				b.getDescricao(),
    				b.getEstoqueCritico(),
    				b.getNomeFornecedor(),
    				b.getTelefoneFornecedor()
    		});
    	}
    }

    public static void fecharForm()
    {
    	
    }
}

