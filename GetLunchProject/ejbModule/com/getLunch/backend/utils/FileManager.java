package com.getLunch.backend.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;

public class FileManager {
	public void update() {

		try {

			JFileChooser fchooser = new JFileChooser();
			if( fchooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
				File f = fchooser.getSelectedFile();

				FileOutputStream fout = new FileOutputStream(f);
				ObjectOutputStream oout = new ObjectOutputStream(fout);
				oout.writeObject(null);
				oout.flush();
				oout.writeObject(null);
				oout.flush();
				oout.close();

				FileInputStream fin = new FileInputStream(f);
				ObjectInputStream oin = new ObjectInputStream(fin);
				//Pessoa p2 = (Pessoa) oin.readObject();
				System.out.println("Dados gravados em disco: ");
				//p2 = (Pessoa) oin.readObject();
				System.out.println("Dados gravados em disco: ");
				oin.close();


			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
