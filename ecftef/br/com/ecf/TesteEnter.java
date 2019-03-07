package br.com.ecf;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TesteEnter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteEnter frame = new TesteEnter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
								
								case 23: 
									JOptionPane.showMessageDialog(null, "23");
									break;
								case 22: 
									JOptionPane.showMessageDialog(null, "22!");
									break;
								case 21: 
									JOptionPane.showMessageDialog(null, "21!");
									break;
								case 20: 
									JOptionPane.showMessageDialog(null, "20");
									break;
								case 19: 
									JOptionPane.showMessageDialog(null, "19");
									break;
								case 18: 
									JOptionPane.showMessageDialog(null, "18");
									break;
								case 17: 
									JOptionPane.showMessageDialog(null, "17");
									break;
								case 16: 
									JOptionPane.showMessageDialog(null, "16");
									break;
								case 15: 
									JOptionPane.showMessageDialog(null, "15");
									break;
								case 14: 
									JOptionPane.showMessageDialog(null, "14");
									break;
								case 13: 
									JOptionPane.showMessageDialog(null, "13");
									break;
								case 12: 
									JOptionPane.showMessageDialog(null, "12");
									break;
								case 11: 
									JOptionPane.showMessageDialog(null, "11");
									break;
								case 10: 
									JOptionPane.showMessageDialog(null, "10");
									break;
								case 9: 
									JOptionPane.showMessageDialog(null, "9");
									break;
								case 8: 
									JOptionPane.showMessageDialog(null, "8");
									break;
								case 7: 
									JOptionPane.showMessageDialog(null, "7");
									break;
								case 6: 
									JOptionPane.showMessageDialog(null, "6");
									break;
								case 5: 
									JOptionPane.showMessageDialog(null, "5");
									break;
								case 4: 
									JOptionPane.showMessageDialog(null, "4");
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

	/**
	 * Create the frame.
	 */
	public TesteEnter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
