package br.com.ecf;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class TeclaEnter
{
	private static boolean enter;
	public boolean apertouEnter()
	{
		return enter;
	}
	public static void main(String[] args)
	{
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
								case 22: 
									enter = true;
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
}
