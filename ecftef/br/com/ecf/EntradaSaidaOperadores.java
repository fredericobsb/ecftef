package br.com.ecf;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import br.com.ecf.model.AcessoBean;
import br.com.ecf.model.AcessoDAO;
import br.com.ecf.model.FuncionarioDAO;

public class EntradaSaidaOperadores extends javax.swing.JFrame {
    
    /** Creates new form EntradaSaidaOperadores */
    public EntradaSaidaOperadores()
    {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        label_entrada_saida = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_pesquisa_data = new javax.swing.JTextField();
        tf_pesquisa_nome = new javax.swing.JTextField();
        botao_pesquisar = new javax.swing.JButton();
        botao_pesquisar.addActionListener(new ActionListener() {
        
        //botao pesquisar
        public void actionPerformed(ActionEvent arg0)
        {
        	@SuppressWarnings("unused")
			String dataPesquisada = tf_pesquisa_data.getText();
        	String nomePesquisado = tf_pesquisa_nome.getText();
        	//pesquisar no banco por data ou nome e preencher jtable
        	//tabela_login_logout.getColumnModel().getColumn(0).setPreferredWidth(20);
        	//tabela_login_logout.getColumnModel().getColumn(1).setPreferredWidth(150);
        	//tabela_login_logout.getColumnModel().getColumn(2).setPreferredWidth(20);
        	AcessoBean ab = new AcessoBean();
        	AcessoDAO f = new AcessoDAO();
        	List<AcessoBean>lista = new ArrayList<AcessoBean>();
        	DefaultTableModel modelo = (DefaultTableModel)tabela_login_logout.getModel();
            modelo.setNumRows(0);
        	if(!tf_pesquisa_data.getText().equals(""))
            {
            	String dataInserida = tf_pesquisa_data.getText();
            	String dia = dataInserida.substring(0,2);
            	String mes = dataInserida.substring(2,4);
            	String ano = dataInserida.substring(4,8);
            	Date dataSQL = new Date(Integer.parseInt(ano) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
            	ab.setData(dataSQL);
            	lista = f.pesquisarPorData(ab);
            	for(AcessoBean o : lista)
            	{
            		modelo.addRow(new Object []
            		{
            			o.getNome(),
            			o.getLogin(),
            			o.getData().getDate() + "/" + (o.getData().getMonth() + 1) + "/" + (o.getData().getYear() + 1900),
            			o.getHora(),
            			o.getAcao()
            		});
            	}
            	tf_pesquisa_data.setText("");
            }
        	if(!tf_pesquisa_nome.getText().equals(""))
        	{
        		ab.setNome(tf_pesquisa_nome.getText());
        		lista = f.pesquisarPorNome(ab);
        		for(AcessoBean o : lista)
            	{
            		modelo.addRow(new Object []
            		{
            			o.getNome(),
            			o.getLogin(),
            			o.getData().getDate() + "/" + (o.getData().getMonth() -1) + "/" + (o.getData().getYear() + 1900),
            			o.getHora(),
            			o.getAcao()
            		});
            	}
        		tf_pesquisa_nome.setText("");
        	}
        }
        });

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_login_logout = new javax.swing.JTable();
        botao_gerar_relatorio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label_entrada_saida.setFont(new java.awt.Font("Verdana", 1, 36));
        label_entrada_saida.setForeground(new java.awt.Color(0, 0, 204));
        label_entrada_saida.setText("Entrada / Saída de Operadores");

        jLabel1.setText("Pesquisar por data:");

        jLabel2.setText("Pesquisar por nome:");

        botao_pesquisar.setPreferredSize(new java.awt.Dimension(0, 0));

        tabela_login_logout.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        			"Nome","Login", "Data", "Hora","A\u00E7\u00E3o"
        	}
        ));
        jScrollPane1.setViewportView(tabela_login_logout);

        botao_gerar_relatorio.setText("Gerar Relatório");
        botao_gerar_relatorio.setVisible(false);
        botao_gerar_relatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botao_gerar_relatorioActionPerformed(evt);
            }
        });
        
        JLabel lblExemplo = new JLabel("Exemplo: 03052016");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(62, Short.MAX_VALUE)
        			.addComponent(label_entrada_saida, GroupLayout.PREFERRED_SIZE, 636, GroupLayout.PREFERRED_SIZE)
        			.addGap(26))
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(14)
        							.addComponent(jLabel1))
        						.addGroup(layout.createSequentialGroup()
        							.addContainerGap()
        							.addComponent(jLabel2)))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(tf_pesquisa_data, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(lblExemplo))
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(tf_pesquisa_nome, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(botao_pesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addComponent(botao_gerar_relatorio))))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(label_entrada_saida, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(tf_pesquisa_data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblExemplo))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(tf_pesquisa_nome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel2)
        				.addComponent(botao_pesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(botao_gerar_relatorio))
        			.addGap(18)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(24, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>

    private void botao_gerar_relatorioActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntradaSaidaOperadores().setVisible(true);
            }
        });
      //verificacao se apertou tecla enter
        EventQueue queue = new EventQueue()
		{
			protected void dispatchEvent(AWTEvent event)
			{
				super.dispatchEvent(event);          
					String a[];
					String tecla[];
					if (!event.paramString().equals("")) 
					{
						if  (event.paramString().substring(0, 5).equals("KEY_P"))
						{
							a = event.paramString().split(",");
							tecla = a[1].split("=");
							switch (Integer.parseInt(tecla[1]))
							{
								case 10: 
									botao_pesquisar.doClick();
									break;
								default:
									break;
							}
						}
					}
			}
		};
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(queue);
        //fim verificacao apertou enter
    }

    

    // Variables declaration - do not modify
    private javax.swing.JButton botao_gerar_relatorio;
    private static javax.swing.JButton botao_pesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela_login_logout;
    private javax.swing.JLabel label_entrada_saida;
    private javax.swing.JTextField tf_pesquisa_data;
    private javax.swing.JTextField tf_pesquisa_nome;
}