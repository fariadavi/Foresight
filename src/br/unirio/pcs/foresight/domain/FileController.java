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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.unirio.pcs.foresight.domain.dto.Score;

import com.thoughtworks.xstream.XStream;

public class FileController {
	
	private static final String FILE = "score.xml";
	private static final int MAX_LINHAS_ARQUIVO = 10;

	public static void addScore(List<Score> scores) {
		
		CheckIfFileExists();
		
		try {
			Collections.sort(scores);
			
			XStream xstream = new XStream();
			xstream.alias("score", Score.class);
			String scoresInXML = xstream.toXML(scores);
			
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true)));
			bufferedWriter.write(scoresInXML);
			bufferedWriter.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		
	}

	public static void writeScore(String[] lines) {
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
	
	public static List<Score> recoversScore(){
		
		CheckIfFileExists();
		
		try(BufferedReader fileRead = new BufferedReader(new InputStreamReader(new FileInputStream(FILE)))) {
			
			XStream xstream = new XStream();
			xstream.alias("score", Score.class);
			
			StringBuffer linhas = new StringBuffer();
			String linhaAtual;
			while((linhaAtual = fileRead.readLine()) != null){
				linhas.append(linhaAtual);
			}
			
			System.out.println(linhas);
			
			@SuppressWarnings("unchecked")
			List<Score> scores = (List<Score>) xstream.fromXML(linhas.toString());
			Collections.sort(scores);
			return scores;
			
		} catch (FileNotFoundException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		
		return Arrays.asList();
	}
	
	public static void CheckIfFileExists() {
		try {
			File file = new File(FILE);
			if(!file.exists()){
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
