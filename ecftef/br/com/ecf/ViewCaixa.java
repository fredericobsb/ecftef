/*
 * ViewCaixa.java
 *
 * Created on 11 de Agosto de 2016, 23:30
 */

package br.com.ecf;

import br.com.ecf.control.Controle;
import br.com.ecf.model.AcessoBean;
import br.com.ecf.model.AcessoDAO;
import br.com.ecf.model.CabecalhoVendaBean;
import br.com.ecf.model.DetalheVendaBean;
import br.com.ecf.model.FuncionarioBean;
import br.com.ecf.model.FuncionarioDAO;
import br.com.ecf.model.ProdutoBean;
import br.com.ecf.model.ProdutoDAO;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bemajava.BemaString;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author  Frederico
 */
public class ViewCaixa extends javax.swing.JFrame {
    
    /** Creates new form ViewCaixa */
    private static String descontoConcedido = "0";
    private static DefaultListModel model = new DefaultListModel();
    private static DefaultListModel model_resultado_busca = new DefaultListModel();
    private static double totalGeral = 0;
    private static CabecalhoVendaBean cVendaBean;
    private static Controle controle;
    private static ArrayList<DetalheVendaBean> arrayDVendaBean;
    JList jList1 = new JList();
    private DetalheVendaBean dVendaBean;
    private ProdutoBean produtoBean;
    static double valorTotalProduto = 0;
    static int contador_de_itens_vendidos = 1;
    static List guardadorDeValorDoItem = new ArrayList();
    static JLabel label_cupom_aberto = new JLabel("CUPOM ABERTO!");
    static List<ProdutoBean>lista = new ArrayList<ProdutoBean>();
    static JLabel label_senha_adm = new JLabel("Senha do Administrador para concessão de desconto:");
    static String nomeLogado = "";
    //static JLabel label_usuario_logado = new JLabel("TESTE USUARIO FRED");
    static JLabel label_usuario_logado = new JLabel("Usuário logado: ");
    static JLabel label_data_hora = new JLabel("New label");
    static JLabel label_so_login = new JLabel("");
    static ViewCaixa view = new ViewCaixa();
    private static boolean cupomAberto;
    static int opcao;
    Data mostra_data;
    private org.netbeans.examples.lib.timerbean.Timer timer1;
    JLabel label_hora = new JLabel("New label");
    public ViewCaixa() 
    {
    	setTitle("FR Sistemas - Frente de Caixa V.1.0");
    	getContentPane().setPreferredSize(new Dimension(1100, 700));
        initComponents();
        //para teclas de atalho
        Toolkit.getDefaultToolkit().addAWTEventListener( new AWTEventListener()
        {
            public void eventDispatched(AWTEvent e)
	    {
	    }
        }, AWTEvent.KEY_EVENT_MASK);
        controle = new Controle();
        viewPadrao();
        arrayDVendaBean = new ArrayList<DetalheVendaBean>();
        cVendaBean = new  CabecalhoVendaBean();
        jPanel2.setVisible(false);
        label_cupom_cancelado.setVisible(false);
        painel_navega_resultado_busca.setVisible(false);
        mostra_data = new Data();
        mostra_data.le_data();
        //label_data.setText(mostra_data.dia+", "+mostra_data.dia_semana+
              //  " de "+mostra_data.mes+" de "+mostra_data.ano);
             
        timer1.start();
        this.setLocationRelativeTo(null);
    }

    public void chama_login()
    {
    	int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema ?", null, JOptionPane.YES_NO_OPTION);
    	if(opcao == 0)
    	{
    		String [] args = new String[1];
    		args[0] = "Frente de caixa";
    		this.hide();
    		Login.main(args);
    		//grava a acao no banco de dados
        	AcessoDAO ad = new AcessoDAO();
        	AcessoBean ac = new AcessoBean();
        	ac.setLogin(label_so_login.getText());
        	String horaDaAcao = "";
        	java.util.Date acao =  new java.util.Date();
        	horaDaAcao = String.valueOf(acao.getHours()) + ":" + String.valueOf(acao.getMinutes());
        	ac.setHora(horaDaAcao);
        	int dia = acao.getDate();
        	int mes = acao.getMonth() ;
        	int ano = acao.getYear();
        	ac.setData(new java.sql.Date(ano,mes,dia));
        	ac.setAcao("SAÍDA");
        	ac.setNome(label_so_login.getText());
        	ad.incluir(ac);
    	}
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents()
    {

    	timer1 = new org.netbeans.examples.lib.timerbean.Timer();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem_cancelaItemGenerico = new javax.swing.JMenuItem();
        jMenuItem_cancelaUltimoItem = new javax.swing.JMenuItem();
        jMenuItem_cancelaCupom = new javax.swing.JMenuItem();
        jMenuItem_concederDesconto = new javax.swing.JMenuItem();
        jMenuItem_abreCupom = new javax.swing.JMenuItem();
        jMenuItem_fechaCupom = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        tf_descricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        quantidade = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel3.setBorder(null);
        jPanel4 = new javax.swing.JPanel();
        jPanel4.setBorder(null);
        jLabel5 = new javax.swing.JLabel();
        jLabel5.setForeground(new Color(128, 128, 128));
        jLabel6 = new javax.swing.JLabel();
        jLabel6.setForeground(new Color(128, 128, 128));
        codigo_da_venda = new javax.swing.JLabel();
        label_total_geral = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
        jLabel9 = new javax.swing.JLabel();
        tf_dinheiro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_cheque = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_cheque_pre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_ticket = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_cartao = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel6.setBorder(new LineBorder(Color.GRAY));
        jLabel18 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        tf_desconto = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jMenu1.setText("Comandos de Inicialização");

        jMenuItem1.setText("Programação do horário de verão");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Programa truncamento");
        jMenu1.add(jMenuItem2);

        jMenuItem18.setText("Programa horário de verão");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem18);
        
        jMenuItem3.setText("Programa alíquota ICMS");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jPopupMenu1.add(jMenu1);

        jMenu2.setText("Comandos do cupom fiscal");

        jMenuItem_cancelaItemGenerico.setText("F6 - Cancela item genérico");
        jMenuItem_cancelaItemGenerico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cancelaItemGenericoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_cancelaItemGenerico);

        jMenuItem_cancelaUltimoItem.setText("F7 - Cancela último item");
        jMenuItem_cancelaUltimoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cancelaUltimoItemActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_cancelaUltimoItem);

        jMenuItem_cancelaCupom.setText("F8 - Cancela cupom");
        jMenuItem_cancelaCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cancelaCupomActionPerformed(evt);
            }
        });
        
        jMenuItem15.addActionListener(new java.awt.event.ActionListener()
        {
        	public void actionPerformed(java.awt.event.ActionEvent evt)
        	{
        		String [] args = new String[1];
        		args[0] = "Login";
        		chama_login();
        		//Login.main(args);
        	}

        });
        
        jMenu2.add(jMenuItem_cancelaCupom);

        jMenuItem_concederDesconto.setText("F10 - Conceder desconto");
        jMenuItem_concederDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_concederDescontoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_concederDesconto);

        jMenuItem_abreCupom.setText("F11 - Abre cupom");
        jMenuItem_abreCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_abreCupomActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_abreCupom);

        jMenuItem_fechaCupom.setText("F12 - Fecha cupom");
        jMenuItem_fechaCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_fechaCupomActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem_fechaCupom);

        jPopupMenu1.add(jMenu2);

        jMenu3.setText("Comandos de relatórios fiscais");

        jMenuItem9.setText("Leitura X");
        jMenuItem9.setToolTipText("");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem10.setText("Redução Z");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuItem16.setText("Memória por faixa de data");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem16);

        jMenuItem17.setText("Memória por faixa de redução");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem17);

        jPopupMenu1.add(jMenu3);

        jMenu4.setText("Outras opções");

        jMenuItem11.setText("F1 - Ajuda");
        jMenu4.add(jMenuItem11);

        jMenuItem12.setText("F2 - Calculadora");
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("F3 - Retorna");
        jMenu4.add(jMenuItem13);

        jMenuItem14.setText("F5 - Dados da venda");
        jMenu4.add(jMenuItem14);

        jMenuItem15.setText("F9 - Logout");
        jMenu4.add(jMenuItem15);

        jPopupMenu1.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new Color(245, 245, 245));
        jPanel1.setBorder(null);
        jPanel1.setComponentPopupMenu(jPopupMenu1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new Color(105, 105, 105));
        jLabel1.setText("Código:");

        tf_descricao.setBackground(new java.awt.Color(255, 255, 204));
        tf_descricao.setEditable(false);
        tf_descricao.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setForeground(new Color(128, 128, 128));
        jLabel2.setText("Quantidade:");

        /*
        quantidade.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeActionPerformed(evt);
            }
        });
        */
        quantidade.addFocusListener(new java.awt.event.FocusAdapter()
        {
           
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                quantidadeFocusLost(evt);
            }
            
        });
        
        codigo.addFocusListener(new java.awt.event.FocusAdapter()
        {
           
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                codigoFocusLost(evt);
            }
            
        });
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter()
        {
        	public void focusLost(java.awt.event.FocusEvent evt)
        	{
        		jTextField9FocusLost(evt);
        	}
        });
        
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter()
        {
        	public void focusLost(java.awt.event.FocusEvent evt)
        	{
        		jTextField14FocusLost(evt);
        	}
        });
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        label_cupom_aberto.setForeground(Color.BLUE);
        label_cupom_aberto.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        
        label_cupom_aberto.setVisible(false);
        
        label_cupom_cancelado = new JLabel("CUPOM CANCELADO!");
        label_cupom_cancelado.setForeground(Color.RED);
        label_cupom_cancelado.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        painel_navega_resultado_busca = new JPanel();
        
        valor_unitario = new JTextField();
        valor_unitario.setVisible(false);
        
        valor_total = new JTextField();
        valor_total.setVisible(false);
        label_hora.setForeground(Color.BLUE);
        
        
        label_hora.setFont(new Font("Tahoma", Font.BOLD, 18));
        
       
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel1)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(codigo, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(jLabel2)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(quantidade)))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(tf_descricao, 312, 312, 312)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addComponent(painel_navega_resultado_busca, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        									.addGap(50)
        									.addComponent(valor_total, 0, 0, Short.MAX_VALUE))
        								.addComponent(label_cupom_cancelado, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(label_hora, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(23)
        					.addComponent(label_cupom_aberto, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(350)
        					.addComponent(valor_unitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18)
        			.addComponent(panel, GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel1)
        						.addComponent(codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(tf_descricao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(jLabel2)
        							.addComponent(quantidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        						.addComponent(painel_navega_resultado_busca, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(valor_unitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(valor_total, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        							.addComponent(label_hora)))
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(label_cupom_aberto)
        						.addComponent(label_cupom_cancelado))))
        			.addContainerGap())
        );
        
        botao_voltar = new JButton("<");
        botao_voltar.setEnabled(false);
        botao_voltar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0)//botao voltar 
        {
        	int tamanho = lista.size();
        	int contador = tamanho - 2;
        	for(int i = 1; i <= tamanho; i++)
        	{
        		codigo.setText(lista.get(contador).getCodigo());
        		tf_descricao.setText(lista.get(contador).getDescricao());
        		if(contador == 0)
        		{
        			botao_voltar.setEnabled(false);
        		    botao_avancar.setEnabled(true);
        		}
        		if(contador > 0)
        			--contador;
        	}
        }
        });
        painel_navega_resultado_busca.add(botao_voltar);
        
        botao_avancar = new JButton(">");
        botao_avancar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0)//botao avancar
        	{
        		int contador = 1;
        		for(ProdutoBean p : lista)
        		{
        			codigo.setText(p.getCodigo());
        			tf_descricao.setText(p.getDescricao());
        			++contador;
        			if(contador == lista.size())
        				botao_avancar.setEnabled(false);
        				botao_voltar.setEnabled(true);
        		}
        	}
        });
        painel_navega_resultado_busca.add(botao_avancar);
        
        label_imagem = new JLabel("");
        panel.add(label_imagem);
        label_imagem.setLabelFor(label_imagem);
        jPanel1.setLayout(jPanel1Layout);

        jPanel2.setBackground(new Color(0, 0, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, null));
        
        lblCompraFinalizadaObrigado = new JLabel("Compra Finalizada! Obrigado e volte sempre!");
        lblCompraFinalizadaObrigado.setFont(new Font("Verdana", Font.BOLD, 20));
        lblCompraFinalizadaObrigado.setForeground(new Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addGap(275)
        			.addComponent(lblCompraFinalizadaObrigado)
        			.addContainerGap(442, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(lblCompraFinalizadaObrigado)
        			.addContainerGap())
        );
        jPanel2.setLayout(jPanel2Layout);

        jPanel3.setBackground(new Color(245, 245, 245));

        jPanel4.setBackground(new Color(245, 245, 245));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel5.setText("Código da Venda:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setText("Total Geral:");

        codigo_da_venda.setFont(new Font("Tahoma", Font.BOLD, 26));
        codigo_da_venda.setForeground(new Color(105, 105, 105));
        codigo_da_venda.setText("0000000000");

        label_total_geral.setFont(new Font("Tahoma", Font.BOLD, 26));
        label_total_geral.setForeground(new Color(0, 0, 205));
        //label_total_geral.setText("323,56");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4Layout.setHorizontalGroup(
        	jPanel4Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(jPanel4Layout.createSequentialGroup()
        			.addGap(32)
        			.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel5)
        				.addComponent(codigo_da_venda))
        			.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
        			.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel6)
        				.addComponent(label_total_geral))
        			.addGap(20))
        );
        jPanel4Layout.setVerticalGroup(
        	jPanel4Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel4Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel4Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel5)
        				.addComponent(jLabel6))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel4Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(label_total_geral)
        				.addComponent(codigo_da_venda))
        			.addContainerGap())
        );
        jPanel4.setLayout(jPanel4Layout);

        label_total_geral.getAccessibleContext().setAccessibleName("");

        jPanel5.setBackground(new Color(245, 245, 245));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel9.setForeground(new Color(105, 105, 105));
        jLabel9.setText("1 - Dinheiro:");

        tf_dinheiro.setFont(new java.awt.Font("Tahoma", 1, 11));
        tf_dinheiro.setText("                         0,00");
        tf_dinheiro.setMinimumSize(new java.awt.Dimension(6, 21));
        tf_dinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_dinheiroActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel10.setForeground(new Color(105, 105, 105));
        jLabel10.setText("2 - Cheque:");

        tf_cheque.setFont(new java.awt.Font("Tahoma", 1, 11));
        tf_cheque.setText("                        0,00");
        tf_cheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_chequeActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel11.setForeground(new Color(105, 105, 105));
        jLabel11.setText("3 - Cheque- Pré:");

        tf_cheque_pre.setFont(new java.awt.Font("Tahoma", 1, 11));
        tf_cheque_pre.setText("                 0,00");
        tf_cheque_pre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cheque_preActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel12.setForeground(new Color(105, 105, 105));
        jLabel12.setText("4 - Ticket:");

        tf_ticket.setFont(new java.awt.Font("Tahoma", 1, 11));
        tf_ticket.setText("                         0,00");
        tf_ticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ticketActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel13.setForeground(new Color(105, 105, 105));
        jLabel13.setText("5 - Cartão:");

        tf_cartao.setFont(new java.awt.Font("Tahoma", 1, 11));
        tf_cartao.setText("                        0,00");
        tf_cartao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_cartaoActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel14.setForeground(new Color(105, 105, 105));
        jLabel14.setText("6 - Vale:");

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField6.setText("                          0,00");
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel15.setForeground(new Color(105, 105, 105));
        jLabel15.setText("7 - Convênio:");

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField7.setText("                        0,00");
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel16.setForeground(new Color(105, 105, 105));
        jLabel16.setText("8 - Contra-Vale:");

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField8.setText("                       0,00");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel17.setForeground(new Color(105, 105, 105));
        jLabel17.setText("9 - Crediário:");

        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField9.setText("                       0,00");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField9, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_dinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_cheque, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_cheque_pre, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_ticket, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_cartao, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField7, 0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(10, 10, 10)
                        .addComponent(jTextField8, 0, 0, Short.MAX_VALUE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tf_dinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tf_cheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tf_cheque_pre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tf_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tf_cartao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new Color(245, 245, 245));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel18.setForeground(new Color(105, 105, 105));
        jLabel18.setText("Total a pagar:");

        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 11));
        //jTextField10.setText(String.valueOf(valorTotalProduto));//<--TESTE**********
       
        jTextField10.setMinimumSize(new java.awt.Dimension(6, 21));
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel19.setForeground(new Color(105, 105, 105));
        jLabel19.setText("Desconto:");

        tf_desconto.setFont(new java.awt.Font("Tahoma", 1, 11));
        tf_desconto.setText("                                0,00");
        tf_desconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_descontoActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel20.setForeground(new Color(105, 105, 105));
        jLabel20.setText("Subtotal:");
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel21.setForeground(new Color(105, 105, 105));
        jLabel21.setText("Valor Recebido:");

        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField13.setText("0,00");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel22.setForeground(new Color(105, 105, 105));
        jLabel22.setText("Troco:");

        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 11));
        jTextField14.setText("                               0,00");
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6Layout.setHorizontalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel6Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel20)
        				.addComponent(jLabel19)
        				.addComponent(jLabel21)
        				.addComponent(jLabel22)
        				.addGroup(jPanel6Layout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(jTextField12, Alignment.LEADING)
        					.addComponent(jTextField13, Alignment.LEADING)
        					.addComponent(jTextField10, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(jLabel18, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(tf_desconto, Alignment.LEADING)
        					.addComponent(jTextField14, Alignment.LEADING)))
        			.addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
        	jPanel6Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel6Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jLabel18)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jTextField10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel19)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(tf_desconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel20)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jTextField13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel21)
        			.addGap(11)
        			.addComponent(jTextField14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jLabel22)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jTextField12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel6.setLayout(jPanel6Layout);
        
       

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3Layout.setHorizontalGroup(
        	jPanel3Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel3Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING, false)
        				.addComponent(jPanel4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, jPanel3Layout.createSequentialGroup()
        					.addComponent(jPanel6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
        	jPanel3Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel3Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jPanel6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(18)
        			.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        			.addGap(13))
        );
        jPanel3.setLayout(jPanel3Layout);
        jTextPane1 = new javax.swing.JTextPane();
        jTextPane1.setText("  <F2> Calculadora | <F4> Importa Produto | <F6> Cancela Item Genérico | <F7> Cancela Último Item | <F8> Cancela Cupom | <F9> Logout  <F10> Desconto        <F11> Abre Cupom | <F12> Fecha Cupom");
        
                jTextPane1.setBackground(new Color(245, 245, 245));
                jTextPane1.setEditable(false);
                jTextPane1.setFont(new java.awt.Font("Tahoma", 1, 14));
                jTextPane1.setForeground(new Color(128, 128, 128));
                jTextPane1.setAutoscrolls(false);
                jTextPane1.setSelectedTextColor(new java.awt.Color(255, 255, 204));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
       
        label_senha_adm.setForeground(Color.RED);
        label_senha_adm.setFont(new Font("Tahoma", Font.BOLD, 12));
        label_senha_adm.setVisible(false);
        campo_senha_desconto = new JPasswordField();
        campo_senha_desconto.addFocusListener(new FocusAdapter()
        {
        	@Override
        	public void focusLost(FocusEvent arg0)
        	{
        		concede_desconto_operacao();
        	}
        });
        campo_senha_desconto.setVisible(false);
        
       
        
       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(22)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(label_usuario_logado, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, 551, Short.MAX_VALUE)
        					.addComponent(label_data_hora, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
        				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
        				.addComponent(jTextPane1, GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
        				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1057, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(label_senha_adm)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(campo_senha_desconto, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
        						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 496, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(191))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(label_senha_adm)
        						.addComponent(campo_senha_desconto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(jTextPane1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
        			.addGap(3)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(label_usuario_logado, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
        				.addComponent(label_data_hora))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        			.addGap(62))
        );
        scrollPane.setViewportView(jList1);
        jList1.setValueIsAdjusting(true);
        jList1.setModel(model);
        
        
        jList1.setBackground(new Color(255, 255, 153));
        getContentPane().setLayout(layout);

        pack();
        timer1.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer1OnTime(evt);
            }
        });
    }// init components

    private void timer1OnTime(java.awt.event.ActionEvent evt) {
        mostra_data.le_hora();
        label_hora.setText(mostra_data.hora);
    }
    
    private void tf_dinheiroActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
}                                           

    private void tf_chequeActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
}                                         

    private void tf_cheque_preActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
}                                             

    private void tf_ticketActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
}                                         

    private void tf_cartaoActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
}                                         

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void tf_descontoActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
}                                           

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        BemaECF.leituraX();
    }                                          

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        BemaECF.reducaoZ();
    }                                           

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String data1 = JOptionPane.showInputDialog("Insira a data inicial(ddmmaaaa)");
        String data2 = JOptionPane.showInputDialog("Insira a data final(ddmmaaaa)");
        BemaECF.memoriaFiscalPorData(data1, data2);
    }                                           

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        String reducao1 = JOptionPane.showInputDialog("Insira o número da redução inicial(4 dígitos)");
        String reducao2 = JOptionPane.showInputDialog("Insira o número da redução final(4 dígitos)");
        BemaECF.memoriaFiscalPorReducao(reducao1, reducao2);
    }                                           

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {                                            
       BemaECF.horarioVerao();
    }                                           

    private void jMenuItem_cancelaItemGenericoActionPerformed(java.awt.event.ActionEvent evt) {                                                              
        String item = JOptionPane.showInputDialog("Qual item deseja cancelar ?");
        BemaECF.cancelaItemGenerico(item);
        insereItemCancelado(item);
       
}                                                             

    private void jMenuItem_cancelaUltimoItemActionPerformed(java.awt.event.ActionEvent evt) {                                                            
       BemaECF.cancelaItemAnterior();
        insereItemCancelado("Anterior");//mostra na tela o item cancelado
    }                                                           

    private void jMenuItem_cancelaCupomActionPerformed(java.awt.event.ActionEvent evt) {                                                       
       cancela_cupom();
    }

    private static void cancela_cupom()
    {
    	BemaECF.cancelaCupom();
        model.clear();
        label_total_geral.setText("0,00");
        valorTotalProduto = 0;
       // tf_dinheiro.setBackground(Color.getColor("#dcdcdc"));
        //tf_dinheiro.setEnabled(false);
        guardadorDeValorDoItem = new ArrayList();
        contador_de_itens_vendidos = 1;
        jPanel2.setVisible(false);
        label_cupom_aberto.setVisible(false);
        label_cupom_cancelado.setVisible(true);
    }

    private void jMenuItem_concederDescontoActionPerformed(java.awt.event.ActionEvent evt) {                                                           
       concede_desconto();
    }                                                          

    private void jMenuItem_abreCupomActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	valida_cupom_aberto();
    }                                                   

    private void jMenuItem_fechaCupomActionPerformed(java.awt.event.ActionEvent evt) {                                                     
       fechaCupom_joga_para_tf_dinheiro();
    //System.out.println("ONDE ESSA PORRA TA FECHANDO?");
    }                                                    

    private void quantidadeFocusGained(java.awt.event.FocusEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt)
    {
    	jPanel6.setVisible(true);
    	jPanel6.setBackground(Color.LIGHT_GRAY);
        verificaTotais();
        tf_desconto.setEditable(false);
        jTextField12.setEditable(false);
        jTextField14.setText("");
        jTextField14.requestFocus();
        
    }

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt)
    {
    	//calculo do troco -> se quantidade recebida for > qtd a pagar, então troco = qtd recebida - qtd a pagar, senao, exibir mssg falta grana.
    	String quantiaR = "";
    	double quantiaRd = 0;
    	double qtdRecebida = 0;
    	if(jTextField14.getText().equals(""))
    	{
    		quantiaR = JOptionPane.showInputDialog(null, "Informe a quantia recebida.");
    	    if(quantiaR.contains(","))
    	    	quantiaR = quantiaR.replace(",", ".");
    		quantiaRd = Double.parseDouble(quantiaR);
    		qtdRecebida = quantiaRd;
    	}
    	else
    		qtdRecebida = Double.valueOf(jTextField14.getText().replace(",", "."));
    	double qtdAPagar = Double.valueOf(jTextField10.getText().replace(",", "."));
    	double trocoADevolver = 0;
    	if(qtdRecebida >= qtdAPagar)
    	{
    		trocoADevolver = qtdRecebida - qtdAPagar;
    		trocoADevolver = conferidorDeCasasDecimais(trocoADevolver);
    		String trocoFormatado = String.valueOf(trocoADevolver);
    		int marcador = trocoFormatado.indexOf(".");
    		int tamanho = trocoFormatado.length();
    		if(tamanho - (marcador + 1) == 1)
    			trocoFormatado += "0";
    		jTextField12.setText(trocoFormatado.replace(".",","));
    		jPanel2.setVisible(true);//obrigado volte sempre
    		cupomAberto = false;
    	}
    	else
    	{
    		JOptionPane.showMessageDialog(null, "A quantia recebida é insuficiente.");
    		jTextField14.setText("");
    		jTextField14.requestFocus();
    	}
    	label_cupom_aberto.setVisible(false);
    }

    private void quantidadeFocusLost(java.awt.event.FocusEvent evt)
    {
    	painel_navega_resultado_busca.setVisible(false);
        BemaECF.vendeItem(
                codigo.getText(), 
                tf_descricao.getText(), 
                "1700", 
                "I", 
                quantidade.getText(),
                2, 
                valor_unitario.getText(), 
               "$", 
                "0");
        atualizaTotais();
        insereItemVendido();
    }                                    

    String totalDoCupomFormatado = "";
    public void atualizaTotais()
    {
        int qtd = Integer.parseInt(quantidade.getText()); 
        double valorUnitario = Double.parseDouble(valor_unitario.getText());
        double resultado = qtd * valorUnitario;//TOTAL DA COMPRA DO CUPOM
        resultado = verificaMultiplicacao(resultado);
        valorTotalProduto += resultado;
        valorTotalProduto = verificaMultiplicacao(valorTotalProduto);
        //Formatacao do valor total da compra 
        String count1 = String.valueOf(valorTotalProduto);
        int indiceDoPonto = count1.indexOf(".");
        int tamanhoDoTotalDoCupom = count1.length();
        if(tamanhoDoTotalDoCupom - (indiceDoPonto + 1) == 1)
        	totalDoCupomFormatado = count1 + "0";
        totalDoCupomFormatado = totalDoCupomFormatado.replace(".", ",");
        label_total_geral.setText(String.valueOf(totalDoCupomFormatado));
        //totalGeral = totalGeral + valorTotalProduto;
        totalGeral = qtd * valorUnitario;//total no visualizador do cupom, apos multiplicar quantaidade pelo valor unitario.
        totalGeral = verificaMultiplicacao(totalGeral);
        //valor_total.setText(String.valueOf(valorTotalProduto));//QUEM É ESSE?
        
    }

    public double verificaMultiplicacao(Double numero)
    {
    	String check = String.valueOf(numero);
    	String cortador = "";
    	int check2 = check.length();
    	int check3 = check.indexOf(".");
    	if(check2 > ((check3 + 2)))
    		numero = Math.nextUp(numero);
    	if(check2 > ((check3 + 2)))
    	{
    		cortador = String.valueOf(numero);
    		cortador = cortador.substring(0, (check3 + 2));
    		numero = Double.valueOf(cortador);
    	}
    	return numero;		
    }

    public static double conferidorDeCasasDecimais(Double numero)
    {
    	String check = String.valueOf(numero);
    	String cortador = "";
    	int check2 = check.length();
    	int check3 = check.indexOf(".");
    	if(check2 > ((check3 + 2)))
    	{
    		cortador = String.valueOf(numero);
    		cortador = cortador.substring(0, (check3 + 3));
    		//checando cortador
    		int t1 = cortador.length();
    		int t2 = cortador.indexOf(".") + 1;
    		double cortadorDouble = Double.valueOf(cortador);
    		if(t1 - t2 > 2)
    			cortadorDouble = Math.nextUp(cortadorDouble);
    		numero = cortadorDouble;
    	}
    	return numero;	
    }
 
    public static String formatoPontoXVirgula(double numero)
    {
    	String numeroRecebido = String.valueOf(numero);
    	int indicePonto = numeroRecebido.indexOf(".") + 1;
    	int tamanho = numeroRecebido.length();
    	if(tamanho - indicePonto < 2)
    		numeroRecebido += "0";
    	numeroRecebido = numeroRecebido.replace(".",",");
    	return numeroRecebido;
    }

    @SuppressWarnings("unchecked")
	public void insereItemVendido()
    {
    	//System.out.println(contador_de_itens_vendidos);
        model.addElement("     " + codigo.getText() +  "             " + tf_descricao.getText());//add(" " + codigo.getText() +  "   " + descricao.getText());
        String qtd_x_valorUnitario = "";
        qtd_x_valorUnitario = qtd_x_valorUnitario.valueOf(totalGeral).replace(".",",");
        int contador1 = qtd_x_valorUnitario.indexOf(",");
        int tamanhoDoTotalDoPreco= qtd_x_valorUnitario.length();
        if(tamanhoDoTotalDoPreco - (contador1 + 1) == 1)
        	qtd_x_valorUnitario += "0";
        model.addElement("     Item: " + contador_de_itens_vendidos + "          " + quantidade.getText()+ "  X  " + valor_unitario.getText().replace(".", ",") + "           " + qtd_x_valorUnitario);
        ++ contador_de_itens_vendidos;
        dVendaBean = new DetalheVendaBean();
		dVendaBean.setCodigoCabecalhoVenda(Integer.parseInt(codigo_da_venda.getText()));
		dVendaBean.setCodigoProduto(codigo.getText());
		dVendaBean.setQuantidade(Integer.parseInt(quantidade.getText()));
		dVendaBean.setValorUnitario(Double.parseDouble(valor_unitario.getText()));
		dVendaBean.setValorTotal(totalGeral);
		arrayDVendaBean.add(dVendaBean);
		//Limpa os campos para inserir outro produto
		codigo.setText("");
		valor_unitario.setText("");
		valor_total.setText("");
		quantidade.setText("1");
		tf_descricao.setText("");
		codigo.requestFocus();
		guardadorDeValorDoItem.add(totalGeral);
	}
    
    @SuppressWarnings("unchecked")
	public static void insereItemCancelado(String item)
    {
        model.addElement("*************************");
        model.addElement("Item cancelado: " + item);
        model.addElement("*************************");
        int v = Integer.parseInt(item) - 1;
        valorTotalProduto = subtraiItemCancelado(valorTotalProduto, Double.parseDouble(String.valueOf(guardadorDeValorDoItem.get(v))));
        valorTotalProduto = conferidorDeCasasDecimais(valorTotalProduto);
        String check = String.valueOf(valorTotalProduto);
        int ponto = check.indexOf(".");
        int tamanho = check.length();
        String verificacao = "";
        if(tamanho - (ponto +1)== 1)
        {
        	check += "0";
        	verificacao = check.replace(".", ",");
        	label_total_geral.setText(verificacao);
        }
        else
        	label_total_geral.setText(String.valueOf(valorTotalProduto));
    }

    public static double subtraiItemCancelado(double total, double itemASerCancelado)
    {
    	if(total > itemASerCancelado)
		{
			double resultado = total - itemASerCancelado;
			resultado = conferidorDeCasasDecimais(resultado);
			//resultado = arredondador(resultado);
			/*
			String conversor = String.valueOf(resultado);
			int tamanho = conversor.length();
			int posicaoDoPonto = conversor.indexOf(".") + 1;
			int posicaoDoPontoMaisDois = posicaoDoPonto + 3;
			String checaSeTemDuasCasasAposPonto = conversor.substring(posicaoDoPonto, conversor.length());
			int tamanhoDosCentavos = checaSeTemDuasCasasAposPonto.length();
			String solucao = "";
			String resultadoFinal = String.valueOf(resultado);
			double d = 0;
			if(conversor.length() != posicaoDoPonto + 2 && tamanho > 3 && tamanhoDosCentavos > 1)//se o java NAO subtraiu correto
			{
				solucao = String.valueOf(resultado).substring(posicaoDoPonto - 1,posicaoDoPontoMaisDois);
				d = arredondador(Double.parseDouble(solucao));
			}
				
			return d;
			*/
			return resultado;
		}
		else
			return 0;
    	
    }

    public static double arredondador(double solucao)
    {
    	double resultado = 0;
    	String resultadoFinal = "";
    	String queMerda = "";
    	queMerda = String.valueOf(solucao);
    	int indicePonto = queMerda.indexOf(".");
		String centena = queMerda.substring(indicePonto + 1, indicePonto + 2);
		String dezena = queMerda.substring(indicePonto + 2, indicePonto + 3);
		String unidade = queMerda.substring(indicePonto + 3, indicePonto + 4);
		if(unidade.equals("9") && !dezena.equals("9"))
		{
			int d = Integer.parseInt(dezena) + 1;
			dezena = String.valueOf(d);
			
		}
		if(unidade.equals("9") && dezena.equals("9"))
		{
			//centena ganha mais 1 e dezena ganha valor 0;
			int cent = Integer.parseInt(centena) + 1;
			centena = String.valueOf(cent);
			dezena = "0";
			
		}
		if(dezena.equals("9"))
		{
			int c = Integer.parseInt(dezena) + 1;
			centena = String.valueOf(c);
		}
		if(!unidade.equals("0"))
		{
			//resultadoFinal = String.valueOf(resultado).substring(0,3) + centena + dezena;
			String corte = String.valueOf(resultado);
			int c = corte.indexOf(".");
			//resultadoFinal = corte.substring(0, c + 3);
			resultadoFinal = String.valueOf(resultado).substring(0,2) + centena + dezena;
		}
		if(unidade.equals("0"))
		{
			String corte = String.valueOf(resultado);
			int c = corte.indexOf(".");
			resultadoFinal = corte.substring(0, c + 3);
		}
		return Double.parseDouble(resultadoFinal);
    }
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        String aliquota = JOptionPane.showInputDialog(null, "Informe a alíquota.");
        BemaECF.programaAliquota(aliquota);
    }                                          
    /*
    private void quantidadeActionPerformed(java.awt.event.ActionEvent evt) 
    {                                           
        // TODO add your handling code here:
    }                                          
    */
    

    public void fechaCupom_joga_para_tf_dinheiro()
    {
    	tf_dinheiro.setEnabled(true);
    	tf_dinheiro.requestFocus();
    	tf_dinheiro.setText("");
    	tf_dinheiro.setBackground(Color.yellow);
    	tf_cheque.setEnabled(true);
    	tf_cheque.setText("");
    	tf_cheque_pre.setEnabled(true);
    	tf_cheque_pre.setText("");
    	tf_ticket.setEnabled(true);
    	tf_ticket.setText("");
    	tf_cartao.setEnabled(true);
    	tf_cartao.setText("");
    	jTextField6.setEnabled(true);
    	jTextField6.setText("");
    	jTextField7.setEnabled(true);
    	jTextField7.setText("");
    	jTextField8.setEnabled(true);
    	jTextField8.setText("");
    	jTextField9.setEnabled(true);
    	jTextField9.setText("");
    	contador_de_itens_vendidos = 1;
    	
    }
    
    //mesmo metodo para o atalho F12
    public static void fechaCupom_joga_para_tf_dinheiro_estatico()
    {
    	tf_dinheiro.setEnabled(true);
    	tf_dinheiro.requestFocus();
    	tf_dinheiro.setText("");
    	tf_dinheiro.setBackground(Color.yellow);
    	tf_cheque.setEnabled(true);
    	tf_cheque.setText("");
    	tf_cheque_pre.setEnabled(true);
    	tf_cheque_pre.setText("");
    	tf_ticket.setEnabled(true);
    	tf_ticket.setText("");
    	tf_cartao.setEnabled(true);
    	tf_cartao.setText("");
    	jTextField6.setEnabled(true);
    	jTextField6.setText("");
    	jTextField7.setEnabled(true);
    	jTextField7.setText("");
    	jTextField8.setEnabled(true);
    	jTextField8.setText("");
    	jTextField9.setEnabled(true);
    	jTextField9.setText("");
    	contador_de_itens_vendidos = 1;
    }
    
    private void fechaCupom()
    {
        ArrayList<FormasPagamento> a = new ArrayList<FormasPagamento>();
        if(!tf_dinheiro.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Dinheiro");
            f.setValor(tf_dinheiro.getText());
            a.add(f);
        }
        
        if(!tf_cheque.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Cheque");
            f.setValor(tf_cheque.getText());
            a.add(f);
        }
        
        if(!tf_cheque_pre.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Cheque-Pré");
            f.setValor(tf_cheque_pre.getText());
            a.add(f);
        }
        
        if(!tf_ticket.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Ticket");
            f.setValor(tf_ticket.getText());
            a.add(f);
        }
        
        if(!tf_cartao.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Cartão");
            f.setValor(tf_cartao.getText());
            a.add(f);
        }
        if(!jTextField6.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Vale");
            f.setValor(jTextField6.getText());
            a.add(f);
        }
        if(!jTextField7.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Convênio");
            f.setValor(jTextField7.getText());
            a.add(f);
        }
        if(!jTextField8.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Contra-Vale");
            f.setValor(jTextField8.getText());
            a.add(f);
        }
        if(!jTextField9.getText().equals("0,00"))
        {
            FormasPagamento f = new FormasPagamento();
            f.setDescricao("Crediário");
            f.setValor(jTextField9.getText());
            a.add(f);
        }
             BemaECF.fechaCupom(
                            "D",
                            "$",
                            descontoConcedido, 
                            "Número da venda: " + codigo_da_venda.getText(),
                            a);
        //tf_desconto.setText("0,00"); ???
        cVendaBean.setCodigo(Integer.parseInt(codigo_da_venda.getText()));
        cVendaBean.setValorVenda(Double.parseDouble(jTextField13.getText()));
        cVendaBean.setDescontoVenda(Double.parseDouble(descontoConcedido));
        cVendaBean.setTotalVenda(Double.parseDouble(label_total_geral.getText()));
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d = new java.util.Date();
		Date data = Date.valueOf(formato.format(d));
		cVendaBean.setDataVenda(data);
		controle.fechaVenda(cVendaBean, arrayDVendaBean);
		arrayDVendaBean = new ArrayList<DetalheVendaBean>();//null??????
		viewPadrao();
		jPanel2.setVisible(true);
		label_cupom_aberto.setVisible(false);
    }//fechaCupom
    
    public void verificaTotais()
    {
		double totais = 0;
		if(tf_dinheiro.getText().contains(","))
			tf_dinheiro.setText(tf_dinheiro.getText().replace(",", "."));
		if(!tf_dinheiro.getText().equals(""))
			totais = Double.parseDouble(tf_dinheiro.getText()); 
		
		if(tf_cheque.getText().contains(","))
			tf_cheque.setText(tf_cheque.getText().replace(",", "."));
		if(!tf_cheque.getText().equals(""))
			totais += Double.parseDouble(tf_cheque.getText());
		
		if(tf_cheque_pre.getText().contains(","))
			tf_cheque_pre.setText(tf_cheque_pre.getText().replace(",", "."));
		if(!tf_cheque_pre.getText().equals(""))
			totais += Double.parseDouble(tf_cheque_pre.getText()); 
		
		if(tf_ticket.getText().contains(","))
			tf_ticket.setText(tf_ticket.getText().replace(",", "."));
		if(!tf_ticket.getText().equals(""))
			totais += Double.parseDouble(tf_ticket.getText()); 
		
		if(tf_cartao.getText().contains(","))
			tf_cartao.setText(tf_cartao.getText().replace(",", "."));
		if(!tf_cartao.getText().equals(""))
			totais += Double.parseDouble(tf_cartao.getText());
		
		if(jTextField6.getText().contains(","))
			jTextField6.setText(jTextField6.getText().replace(",", "."));
		if(!jTextField6.getText().equals(""))
			totais += Double.parseDouble(jTextField6.getText()); //vale
		
		if(jTextField7.getText().contains(","))
			jTextField7.setText(jTextField7.getText().replace(",", "."));
		if(!jTextField7.getText().equals(""))
			totais += Double.parseDouble(jTextField7.getText());//convenio
		
		if(jTextField8.getText().contains(","))
			jTextField8.setText(jTextField8.getText().replace(",", "."));
		if(!jTextField8.getText().equals(""))
			totais += Double.parseDouble(jTextField8.getText());//contra vale
		
		if(jTextField9.getText().contains(","))
			jTextField9.setText(jTextField9.getText().replace(",", "."));
		if(!jTextField9.getText().equals(""))
			totais += Double.parseDouble(jTextField9.getText());//crediario
		
		if (totais != valorTotalProduto) {
			JOptionPane.showMessageDialog(null, "Totais Divergentes. Corrija!");
			tf_dinheiro.requestFocus();
		} else {
			terminaFechamentoCupom();
		}
	}

    public static void concede_desconto()
    {
    	label_senha_adm.setVisible(true);
    	campo_senha_desconto.setVisible(true);
    	campo_senha_desconto.requestFocus();
      //  concede_desconto_operacao();
    }

    public static void concede_desconto_operacao()
    {
    	String senhaDeGerente = campo_senha_desconto.getText();
        //pesquisar a senha do gerente no banco de dados
         FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
         FuncionarioBean funcionarioBean = new FuncionarioBean();
         funcionarioBean = funcionarioDAO.buscaFuncionarioAdmin(campo_senha_desconto.getText());
         if(funcionarioBean.getSenha().equals(campo_senha_desconto.getText()) && funcionarioBean.getCargo().equals("admin"))
        {
            descontoConcedido = JOptionPane.showInputDialog(null, "Insira o valor do desconto.");
            tf_desconto.setText(descontoConcedido);
            if(tf_desconto.getText().contains(","))
            	tf_desconto.setText(tf_desconto.getText().replace(",","."));
            BemaECF.desconto(tf_desconto.getText());
            valorTotalProduto = valorTotalProduto - Double.parseDouble(tf_desconto.getText());//aplica o desconto na tela
            label_senha_adm.setVisible(false);
         	campo_senha_desconto.setVisible(false);
         	campo_senha_desconto.setText("");
         	String numeroOK = formatoPontoXVirgula(valorTotalProduto);
         	label_total_geral.setText(numeroOK);
        }
        else
        {
        	 JOptionPane.showMessageDialog(null, "Você não tem permissão para conceder desconto");
        	 label_senha_adm.setVisible(false);
         	campo_senha_desconto.setVisible(false);
         	campo_senha_desconto.setText("");
         	codigo.requestFocus();
        }
           
    }
    
    public static void main(String args[]) 
    {
        final ViewCaixa v = new ViewCaixa();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCaixa().setVisible(true);
            }
        });
        //teclas de atalho
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
							// COMENTADO PORQUE ESTAVA ATRAPALHANDO O DEBUG
							switch (Integer.parseInt(tecla[1]))
							{
								case 112: //F1
									JOptionPane.showMessageDialog(null, "Manual de ajuda em desenvolvimento!");
									break;
								case 113: //F2
									try {
											Runtime.getRuntime().exec("calc.exe");
										} catch (IOException e) {
											e.printStackTrace();
										}
									break;
								case 114: //F3
									//JOptionPane.showMessageDialog(null, "Pressionou F3!");
									break;
								case 115: //F4
									if(cupomAberto == true)
									{
										String descricao = JOptionPane.showInputDialog("Qual o nome do Produto? ");
										pesquisaProduto(descricao);
									}
									else
										JOptionPane.showMessageDialog(null, "Não há cupom aberto.");
									
									break;
								case 116: //F5
									//JOptionPane.showMessageDialog(null, "Pressionou F5!");
									break;
								case 117: //F6
									if(cupomAberto == true)
									{
										String item = JOptionPane.showInputDialog("Qual item deseja cancelar? ");
										BemaECF.cancelaItemGenerico(item);
										v.insereItemCancelado(item);
									}
									else
										JOptionPane.showMessageDialog(null, "Não há cupom aberto.");
									
									break;
								case 118: //F7
									if(cupomAberto == true)
									{
										BemaECF.cancelaItemAnterior();
										v.insereItemCancelado(String.valueOf(contador_de_itens_vendidos - 1));
									}
									else
										JOptionPane.showMessageDialog(null, "Não há cupom aberto.");
									
									break;
								case 119: //F8
									if(cupomAberto == true)
									{
										cancela_cupom();
										cupomAberto = false;
									}
									else
										//cancela_cupom();//<!--APENAS PARA TESTES *******
										JOptionPane.showMessageDialog(null,"O cupom já foi cancelado.");
									break;
								case 120: //F9
									logoutViaF9();
									break;
								case 121: //F10
									if(cupomAberto == true)
									{
										concede_desconto();
									}
									else
										JOptionPane.showMessageDialog(null, "Não há cupom aberto.");
									
									break;
								case 122: //F11
									cupomAberto = true;
									valida_cupom_aberto();
									break;
								case 123: //F12
									if(cupomAberto == true && valorTotalProduto > 0)
									{
										fechaCupom_joga_para_tf_dinheiro_estatico();
									}
									else if(cupomAberto == true && valorTotalProduto == 0)
									{
										opcao = JOptionPane.showConfirmDialog(null, "O cupom está vazio. Deseja cancelá-lo ?",null,  JOptionPane.YES_NO_OPTION);
										if(opcao == 0)
										{
											BemaECF.cancelaCupomJaConfirmado();
											cupomAberto = false;
											label_cupom_cancelado.setVisible(true);
											label_cupom_aberto.setVisible(false);
										}
									}
									else
										JOptionPane.showMessageDialog(null, "O cupom já está cancelado.");
									break;
								default:
									break;
							}
							//*/
						}
					}
					
			}
		};
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(queue);
    }

    public static void valida_cupom_aberto()
    {
    	String retorno = BemaECF.abreCupom();
		if(retorno.equals(""))
			iniciaVenda();
		if(retorno.equals("cupomAberto"))
			JOptionPane.showMessageDialog(null, "Um cupom já está aberto na impressora.");
    }
    
    public static void iniciaVenda()
    {
    	guardadorDeValorDoItem = new ArrayList();
        cVendaBean = controle.iniciaVenda();
        codigo_da_venda.setText(String.valueOf(cVendaBean.getCodigo()));
        label_total_geral.setText("0,00");
        arrayDVendaBean = new ArrayList<DetalheVendaBean>();
        codigo.requestFocus();
        model.clear();
        model.addElement("    " + " Código   " +  "                           Descrição  " );
        zerarPainelDoTroco();
        valorTotalProduto = 0;
        viewPadrao();
        jPanel2.setVisible(false);
       // panel_cupom_aberto.setVisible(true);
        label_cupom_aberto.setVisible(true);
        label_cupom_cancelado.setVisible(false);
    }

    public static void pesquisaProduto(String descricao)
    {
    	painel_navega_resultado_busca.setVisible(true);
    	botao_avancar.requestFocus();
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	lista = produtoDAO.listarProdutos(descricao);
    	//DefaultTableModel dtmProdutos = (DefaultTableModel) table.getModel();
    	codigo.setText(lista.get(0).getCodigo());
    	tf_descricao.setText(lista.get(0).getDescricao());
    }

    
	private void codigoFocusLost(java.awt.event.FocusEvent evt)
    {
    	if(!codigo.getText().equals(""))
    	{
    		produtoBean = controle.retornaProdutoPeloCodigo(codigo.getText());
    		if(produtoBean.getDescricao().length() > 30)
    			produtoBean.setDescricao(produtoBean.getDescricao().substring(0,29));
        	tf_descricao.setText(produtoBean.getDescricao());//limita descricao a 30 caracteres. padrão bematech.
        	valor_unitario.setText(String.valueOf(produtoBean.getValorVenda()));
        	//Tratamento para erro do ZERO. Ex: 0,7 --> 0,07
        	int a = valor_unitario.getText().indexOf(".") + 1;
        	int b = valor_unitario.getText().length();
        	if(b - a ==1)
        		valor_unitario.setText(valor_unitario.getText().concat("0"));
    	}
    	
    }

    private static void viewPadrao()
    {
		codigo.setText("");
		tf_descricao.setText("");
		quantidade.setText("1");
		//
		model.clear();
		//
		//jPanel5.setEnabled(false);
		//jPanel6.setVisible(false);
		//
		//codigo_da_venda.setText("0000000000");
		label_total_geral.setText("0,00");
        valorTotalProduto = 0;
		jTextField10.setText("0,00");//total em dinheiro
		tf_desconto.setText("0,00");
		jTextField14.setText("0,00");//valor recebido
		jTextField12.setText("0,00"); // troco
		tf_dinheiro.setText("0,00");
		tf_cheque.setText("0,00");
		tf_cheque_pre.setText("0,00");
		tf_ticket.setText("0,00");
		tf_cartao.setText("0,00");
		jTextField6.setText("0,00");
		jTextField7.setText("0,00");
		jTextField8.setText("0,00");
		jTextField9.setText("0,00");
		valorTotalProduto = 0;
		desabilitarCamposPanelFechamento();
		mostrarImagem("logomarca.png");
		//jPanel6.setVisible(false);//desabilita o painel do troco
		guardadorDeValorDoItem = new ArrayList();
		contador_de_itens_vendidos = 1;
		//panel_cupom_aberto.setVisible(false);
	}

    private static void mostrarImagem(String nome)
    {
    	String caminho = System.getProperty("user.dir") + "\\src\\imagens\\";
    	String imagem = caminho + "logomarca.png";
    	ImageIcon img = new ImageIcon(imagem);
    	label_imagem.setIcon(img);
    	//label_teste = new JLabel(imagem);
    }

    private static void desabilitarCamposPanelFechamento() {
		tf_dinheiro.setEnabled(false);
		tf_cheque.setEnabled(false);
		tf_cheque_pre.setEnabled(false);
		tf_ticket.setEnabled(false);
		tf_cartao.setEnabled(false);
		jTextField6.setEnabled(false);
		jTextField7.setEnabled(false);
		jTextField8.setEnabled(false);
		jTextField9.setEnabled(false);
		jTextField10.setEnabled(false);
		tf_desconto.setEnabled(false);
		jTextField12.setEnabled(false);//verifique
		jTextField14.setEnabled(false);
		jTextField13.setEnabled(false);
	}

    private static void habilitarCamposPanelFechamento() {
    	tf_dinheiro.setEnabled(true);
    	tf_cheque.setEnabled(true);
    	tf_cheque_pre.setEnabled(true);
    	tf_ticket.setEnabled(true);
    	tf_cartao.setEnabled(true);
    	jTextField6.setEnabled(true);
    	jTextField7.setEnabled(true);
    	jTextField8.setEnabled(true);
    	jTextField9.setEnabled(true);
    	jTextField10.setEnabled(true);
    	tf_desconto.setEnabled(true);
    	jTextField12.setEnabled(true);
    	jTextField14.setEnabled(true);
		//jFormattedTextField14.setEnabled(true);
	}

    public static void zerarPainelDoTroco()
    {
    	jTextField10.setText("0,00");
    	jTextField10.setEnabled(false);
    	
    	tf_desconto.setText("0,00");
    	jTextField10.setEnabled(false);
    	
    	jTextField13.setText("0,00");
    	jTextField13.setEnabled(false);
    	
    	jTextField14.setText("0,00");
    	jTextField14.setEnabled(false);
    	
    	jTextField12.setText("0,00");
    	jTextField12.setEnabled(false);
    	
    	jPanel6.setBackground(Color.getColor("#CCCCCC"));
    }

    private static void terminaFechamentoCupom() 
    {
    	ArrayList<FormasPagamento> a = new ArrayList<FormasPagamento>();
    	if (!tf_dinheiro.getText().equals(""))
    	{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Dinheiro");
			f.setValor(tf_dinheiro.getText());
			a.add(f);
		}
		if (!tf_cheque.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Cheque");
			f.setValor(tf_cheque.getText());
			a.add(f);
		}
		if (!tf_cheque_pre.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Cheque-Pré");
			f.setValor(tf_cheque_pre.getText());
			a.add(f);
		}
		if (!tf_ticket.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Ticket");
			f.setValor(tf_ticket.getText());
			a.add(f);
		}
		if (!tf_cartao.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Cartão");
			f.setValor(tf_cartao.getText());
			a.add(f);
		}
		if (!jTextField6.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Vale");
			f.setValor(jTextField6.getText());
			a.add(f);
		}
		if (!jTextField7.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Convênio");
			f.setValor(jTextField7.getText());
			a.add(f);
		}
		if (!jTextField8.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Contra-Vale");
			f.setValor(jTextField8.getText());
			a.add(f);
		}
		if (!jTextField9.getText().equals(""))
		{
			FormasPagamento f = new FormasPagamento();
			f.setDescricao("Crediário");
			f.setValor(jTextField9.getText());
			a.add(f);
		}
		BemaECF.fechaCupom(
                "D",
                "$",
                descontoConcedido, 
                "Número da venda: " + codigo_da_venda.getText(),
                a);
				//tf_desconto.setText("0,00"); ???
				cVendaBean.setCodigo(Integer.parseInt(codigo_da_venda.getText()));
				String s = jTextField13.getText().replace(",", ".");
				cVendaBean.setValorVenda(Double.parseDouble(s));
				if(descontoConcedido.contains(","))
					descontoConcedido = descontoConcedido.replace(",",".");
				cVendaBean.setDescontoVenda(Double.parseDouble(descontoConcedido));
				String s2 = label_total_geral.getText().replace(",", ".");
				cVendaBean.setTotalVenda(Double.parseDouble(s2));
				
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date d = new java.util.Date();
				Date data = Date.valueOf(formato.format(d));
				cVendaBean.setDataVenda(data);
				controle.fechaVenda(cVendaBean, arrayDVendaBean);
				arrayDVendaBean = new ArrayList<DetalheVendaBean>();//null??????
				//viewPadrao();
				//valorTotalProduto = 0.00;
				tf_dinheiro.setBackground(Color.white);
				//jPanel6.setVisible(true);//mostra painel do troco
				//jPanel5.setVisible(false);//esconde o painel que soma as formas pagamento
				jTextField10.setEnabled(true);//TOTAL EM DINHEIRO
				//valorTotalProduto = valorTotalProduto - Double.parseDouble(tf_desconto.getText());
				//label_total_geral.setText(String.valueOf(valorTotalProduto));
				String t = String.valueOf(valorTotalProduto);
				t = t.replace(".", ",");
				int contador1 = t.length();
				int contador2 = t.indexOf(",");
				if(contador1 - (contador2 + 1) == 1)
					t = t + "0";
				jTextField10.setText(String.valueOf(t));
				tf_desconto.setEnabled(true);
		    	jTextField12.setEnabled(true);//subtotal
		    	jTextField13.setEnabled(true);//valor recebido
		    	jTextField14.setEnabled(true);//troco
		    	contador_de_itens_vendidos = 1;
		    	descontoConcedido = "0";//zera desconto para nao aparecer em outro cupom.
    }

    public static void setarUsuarioLogado(String nome)
    {
    	//nomeLogado = nome;
    	//label_usuario_logado.setVisible(true);
    	//label_usuario_logado.setText("Usuário logado: " + "pqp" + nomeLogado);
    }
    
    public static void logoutViaF9()
    {
    	jMenuItem15.doClick();//quem diria que seria tao simples? hahaha.
    }
    
    // Variables declaration - do not modify
    private static javax.swing.JTextField codigo;
    private static javax.swing.JLabel codigo_da_venda;
    private static javax.swing.JTextField tf_descricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private static javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItem_abreCupom;
    private javax.swing.JMenuItem jMenuItem_cancelaCupom;
    private javax.swing.JMenuItem jMenuItem_cancelaItemGenerico;
    private javax.swing.JMenuItem jMenuItem_cancelaUltimoItem;
    private javax.swing.JMenuItem jMenuItem_concederDesconto;
    private javax.swing.JMenuItem jMenuItem_fechaCupom;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private static javax.swing.JPanel jPanel5;
    private static javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private static javax.swing.JTextField jTextField10;
    private static javax.swing.JTextField jTextField12;
    private static javax.swing.JTextField jTextField13;
    private static javax.swing.JTextField jTextField14;
    private static javax.swing.JTextField jTextField6;
    private static javax.swing.JTextField jTextField7;
    private static javax.swing.JTextField jTextField8;
    private static javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPane1;
    private static javax.swing.JLabel label_total_geral;
    private static javax.swing.JTextField quantidade;
    private static javax.swing.JTextField tf_cartao;
    private static javax.swing.JTextField tf_cheque;
    private static javax.swing.JTextField tf_cheque_pre;
    private static javax.swing.JTextField tf_desconto;
    private static javax.swing.JTextField tf_dinheiro;
    private static javax.swing.JTextField tf_ticket;
    private static JLabel label_imagem;
    private JLabel lblCompraFinalizadaObrigado;
    private static JLabel label_cupom_cancelado;
    private static JPanel painel_navega_resultado_busca;
    private static JButton botao_avancar;
    private JButton botao_voltar;
    private JTextField valor_unitario;
    private JTextField valor_total;
    private static JPasswordField campo_senha_desconto  = new JPasswordField();
}
//MAPEAR COM TABELAS DO BANCO!!
class FormasPagamento{
    private String descricao;
    private String valor;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
