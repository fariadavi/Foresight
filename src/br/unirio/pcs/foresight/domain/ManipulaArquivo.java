package br.unirio.pcs.foresight.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManipulaArquivo {
	
	private static final String FILE = "pontuacao.dat";
	private static final int MAX_LINHAS_ARQUIVO = 10;

	public static void adicionaPontuacao(String nomeEntrado, int pts) {
		try {
			File file = new File(FILE);
			if (!file.exists())
				file.createNewFile();
//			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true)));
			bufferedWriter.append(nomeEntrado + "|" + pts + "\n");
			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		
		organiza();
	}

	public static void escreve(String[] lines) {
		try {
			if (lines.length > MAX_LINHAS_ARQUIVO)
				lines = Arrays.copyOfRange(lines, 0, MAX_LINHAS_ARQUIVO);

			BufferedWriter file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, false)));
			for (String string : lines) {
				file.append(string);
				file.append("\n");
			}
			
			file.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}
	
	public static void organiza() {
		List<String> lines = leitura();
		int size = lines.size();
		String[] linhas = lines.toArray(new String[size]);
		
		for (int i = 0; i < size; i++) {
			int pontosA = Integer.parseInt(linhas[i].split("\\|")[1]);
			
			for(int j = i+1; j < size; j++) {
				int pontosB = Integer.parseInt(linhas[j].split("\\|")[1]);
				
				if(pontosB > pontosA) {
					String temp = linhas[j];
					linhas[j] = linhas[i];
					linhas[i] = temp;
					
					int temp2 = pontosB;
					pontosB = pontosA;
					pontosA = temp2;
				}
			}
		}
		escreve(linhas);
	}
	
	public static String[] getNomes() {
		List<String> leitura = leitura();
		
		String[] nomes = new String[leitura.size()];
		int i = 0;
		for (String linha : leitura) {
			nomes[i] = linha.split("\\|")[0];
			i++;
		}
		
		return nomes;
	}

	public static int[] getPts() {
		List<String> leitura = leitura();
		
		int[] pontos = new int[leitura.size()];
		int i = 0;
		for (String linha : leitura) {
			pontos[i] = Integer.parseInt(linha.split("\\|")[1]);
			i++;
		}
		
		return pontos;
	}

	public static List<String> leitura() {
		List<String> linhas = new ArrayList<String>();
		
		try {
			BufferedReader fileread = new BufferedReader(new InputStreamReader(new FileInputStream(FILE)));
			String linha;
			while((linha = fileread.readLine()) != null)
				linhas.add(linha);
			
			fileread.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		
		return linhas;
	}
	
	public static int menorPont() {
		int[] pts = getPts();
		return pts[pts.length-1];
	}
}
