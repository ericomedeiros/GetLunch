package com.getLunch.test.unit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;

import org.junit.Test;

import com.getLunch.backend.message.Restaurante;
import com.getLunch.backend.utils.FileManager;
import com.getLunch.backend.utils.Votacao;

public class FileManagerTest {

	@Test
	public void testCreateFileRestaurantes() {
		
		try {
			File f = FileManager.createFileRestaurantes();
			
			assertTrue(f.exists());
			
		} catch (IOException e) {
			fail("Ocorreu testando função \"createFileRestaurantes\"");
			e.printStackTrace();
		}
		
	}

	@Test
	public void testCreateFileVotos() {

		try {
			File f = FileManager.createFileVotos();
			
			assertTrue(f.exists());
			
		} catch (IOException e) {
			fail("Ocorreu testando função \"createFileVotos\"");
			e.printStackTrace();
		}
		
	}

	@Test
	public void testWriteRestaurantes() {

		Restaurante[] res = {new Restaurante("Test a", 1)};
		Restaurante[] resTest;
		ArrayList<Restaurante> 	ArrayRes = new ArrayList<Restaurante>();
		
		FileManager.writeRestaurantes(res);
		
		File			f 	= new File("DBGetLunch/Restaurantes.db");
		FileInputStream fin;
		
		try {
			fin = new FileInputStream(f);
			ObjectInputStream 	oin = new ObjectInputStream(fin);
			Object 				obj = oin.readObject();
			ArrayRes.add((Restaurante) obj);
		} catch (FileNotFoundException e) {
			fail("Ocorreu testando função \"writeRestaurantes\"");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Ocorreu testando função \"writeRestaurantes\"");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			fail("Ocorreu testando função \"writeRestaurantes\"");
			e.printStackTrace();
		}
		
		resTest = Arrays.copyOf(ArrayRes.toArray(), ArrayRes.toArray().length, Restaurante[].class);
		
		assertArrayEquals(res, resTest);
	}

	@Test
	public void testGetSavedRestaurantes() {
		
		Restaurante[] res = {new Restaurante("Test a", 1)};
		Restaurante[] resTest;
		ArrayList<Restaurante> 	ArrayRes;
		
		try {
			
			File f1 = new File("DBGetLunch");
			
			if(!f1.exists()){ 
				JOptionPane.showMessageDialog(null, "Foi verificado que não existe o repositório, dos dados do programa, será criado um.");
				f1.mkdir();
			}
			
			File f2 = new File("DBGetLunch/Restaurantes.db");
			
			if(!f1.exists()){ 
					f2.createNewFile();
			}
			
			FileOutputStream 	fout = new FileOutputStream(f2);
			ObjectOutputStream 	oout = new ObjectOutputStream(fout);
			oout.writeObject(res[0]);
			oout.flush();
			oout.close();
			
			ArrayRes = FileManager.getSavedRestaurantes();
			resTest  = Arrays.copyOf(ArrayRes.toArray(), ArrayRes.toArray().length, Restaurante[].class);
			
			assertArrayEquals(res, resTest);
			
		} catch (IOException e) {
			fail("Ocorreu testando função \"getSavedRestaurantes\"");
			e.printStackTrace();
		}
	}

	@Test
	public void testWriteVotos() {
		
		Restaurante 	   re 	  	= new Restaurante("Test a", 1);
		Votacao[] 	  	   vts 	  	= {new Votacao(re, new Date())};
		ArrayList<Votacao> ArrayVts = new ArrayList<Votacao>();
		Votacao[] 	  	   vtsTest;
		
		
		FileManager.writeVotos(vts);
		
		File			f 	= new File("DBGetLunch/Votos.db");
		FileInputStream fin;
		
		try {
			fin = new FileInputStream(f);
			ObjectInputStream 	oin = new ObjectInputStream(fin);
			Object 				obj = oin.readObject();
			ArrayVts.add((Votacao) obj);
		} catch (FileNotFoundException e) {
			fail("Ocorreu testando função \"writeRestaurantes\"");
			e.printStackTrace();
		} catch (IOException e) {
			fail("Ocorreu testando função \"writeRestaurantes\"");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			fail("Ocorreu testando função \"writeRestaurantes\"");
			e.printStackTrace();
		}
		
		vtsTest = Arrays.copyOf(ArrayVts.toArray(), ArrayVts.toArray().length, Votacao[].class);
		
		assertEquals(vts[0].getRestaurante(), vtsTest[0].getRestaurante());
		assertEquals(vts[0].getVotos(), vtsTest[0].getVotos());
		assertEquals(vts[0].getData(), vtsTest[0].getData());
	
	}

	@Test
	public void testGetSavedVotos() {
		
		Restaurante 	   re 	  	= new Restaurante("Test a", 1);
		Votacao[] 	  	   vts 	  	= {new Votacao(re, new Date())};
		Votacao[] 	  	   vtsTest;
		
		try {
			File f1 = new File("DBGetLunch");
			
			if(!f1.exists()){ 
				JOptionPane.showMessageDialog(null, "Foi verificado que não existe o repositório, dos dados do programa, será criado um.");
				f1.mkdir();
			}
			
			File f2 = new File("DBGetLunch/Votos.db");
			
			if(!f2.exists()){ 
				f2.createNewFile();
			}
			
			FileOutputStream 	fout = new FileOutputStream(f2);
			ObjectOutputStream 	oout = new ObjectOutputStream(fout);
			
			oout.writeObject(vts[0]);
			oout.flush();
			oout.close();
			
			vtsTest = FileManager.getSavedVotos();
			
			assertEquals(vts[0].getRestaurante(), vtsTest[0].getRestaurante());
			assertEquals(vts[0].getVotos(), vtsTest[0].getVotos());
			assertEquals(vts[0].getData(), vtsTest[0].getData());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
