package br.com.ecf;

import java.math.BigDecimal;

public class Teste {

	/*
	 * valores testados: 
	 * 49.35 - 49.28 = 0,07
	 * 49.35 - 49.00 = 0.35
	 * 49.35 - 48.35 = 1.0
	 * 49.35 - 40.00 = 9.35
	 * 40.00 - 39.95 = 0.05
	 * 49.35 - 39.95 = 9.40
	 * 100.23 - 0.01 = 100.22
	 * 100.23 - 9.99 = 90.24 --> faltou o ponto. arrumado
	 * 495.35 - 6.79 = 488.56
	 * 1009.99 - 5.09 = 1004.9
	 * 5001.09 - 9.99 = 4991.1
	 * 12349.99 - 9.99 = 12340;0
	 * 
	 * 
	 * */
	public static void main(String[] args) {
		double valor1 = 90;
		double valor2 = 81.9;
		BigDecimal valor3 = new BigDecimal(90.8);
		BigDecimal valor4 = new BigDecimal(81.9);
		if(valor1 > valor2)
		{
			double resultado = valor1 - valor2;
			String conversor = String.valueOf(resultado);
			
			BigDecimal result = new BigDecimal(0);
			result = valor3.subtract(valor4);
			
			int tamanho = conversor.length();
			int posicaoDoPonto = conversor.indexOf(".") + 1;
			int posicaoDoPontoMaisDois = posicaoDoPonto + 3;
			String checaSeTemDuasCasasAposPonto = conversor.substring(posicaoDoPonto, conversor.length());
			int tamanhoDosCentavos = checaSeTemDuasCasasAposPonto.length();
			String solucao = "";
			String resultadoFinal = String.valueOf(resultado);
			if(conversor.length() != posicaoDoPonto + 2 && tamanho > 3 && tamanhoDosCentavos > 1)//se o java NAO subtraiu correto
			{
				solucao = String.valueOf(resultado).substring(posicaoDoPonto - 1,posicaoDoPontoMaisDois);
				int indicePonto = solucao.indexOf(".");
				String centena = solucao.substring(indicePonto + 1, indicePonto + 2);
				String dezena = solucao.substring(indicePonto + 2, indicePonto + 3);
				String unidade = solucao.substring(indicePonto + 3, indicePonto + 4);
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
			}
				
			System.out.println(resultadoFinal);
		}
		else
			System.out.println(0);
	}
		

}
