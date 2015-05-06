package com.getLunch.backend.utils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.getLunch.backend.message.Restaurante;

public class FileManager {
	
	public static File createFileRestaurantes() throws IOException{
		
		File f1 = new File("DBGetLunch");
		
		if(!f1.exists()){ 
			JOptionPane.showMessageDialog(null, "Foi verificado que não existe o repositório, dos dados do programa, será criado um.");
			f1.mkdir();
		}
		
		File f2 = new File("DBGetLunch/Restaurantes.db");
		
		if(!f1.exists()){ 
			f2.createNewFile();
		}
		
		return f2;
		
	}
	
	public static File createFileVotos() throws IOException{
		
		File f1 = new File("DBGetLunch");
		
		if(!f1.exists()){ 
			JOptionPane.showMessageDialog(null, "Foi verificado que não existe o repositório, dos dados do programa, será criado um.");
			f1.mkdir();
		}
		
		File f2 = new File("DBGetLunch/Votos.db");
		
		if(!f2.exists()){ 
			f2.createNewFile();
		}
		
		return f2;
		
	}
	
	public static void writeRestaurantes(Restaurante[] restaurantes) {
		File f = null;
		
		try {

				f = createFileRestaurantes();

				FileOutputStream fout = new FileOutputStream(f);
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				for (int i = 0; i < restaurantes.length; i++) {
					oout.writeObject(restaurantes[i]);
					oout.flush();
				}
				
				oout.close();

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo \""+f+
				"\" não existe.");
		} catch (IOException e) {
			e.getMessage();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<Restaurante> getSavedRestaurantes() {
		
		ArrayList<Restaurante> res = new ArrayList<Restaurante>();
		File f = null;
		
		try {
				
			f = new File("DBGetLunch/Restaurantes.db");
			
			if(!f.exists()){ 
				return res;
			}

			FileInputStream fin = new FileInputStream(f);
			ObjectInputStream oin = new ObjectInputStream(fin);
			Object obj = oin.readObject();
			while(obj != null){
				res.add((Restaurante) obj);
				obj = oin.readObject();
			}

			oin.close();
		
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Arquivo \""+f+
				"\" não existe.");
		} catch (EOFException e){
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public static void writeVotos(Votacao[] votos){
		
		File f = null;
		
		try {

				f = createFileVotos();

				FileOutputStream fout = new FileOutputStream(f);
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				for (int i = 0; i < votos.length; i++) {
					oout.writeObject(votos[i]);
					oout.flush();
				}
				
				oout.close();

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo \""+f+
				"\" não existe.");
		} catch (IOException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Votacao[] getSavedVotos() {
		
		ArrayList<Votacao> vts = new ArrayList<Votacao>();
		File f = null;
		
		try {
				
			f = new File("DBGetLunch/Votos.db");
			
			if(!f.exists()){ 
				return Arrays.copyOf(vts.toArray(), vts.toArray().length, Votacao[].class);
			}

			FileInputStream fin = new FileInputStream(f);
			ObjectInputStream oin = new ObjectInputStream(fin);
			Object obj = oin.readObject();
			while(obj != null){
				vts.add((Votacao) obj);
				obj = oin.readObject();
			}

			oin.close();
		
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Arquivo \""+f+
				"\" não existe.");
		} catch (EOFException e){
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Arrays.copyOf(vts.toArray(), vts.toArray().length, Votacao[].class);
	}
}
