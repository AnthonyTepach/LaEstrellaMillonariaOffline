package com.tepach.service;

import com.tepach.bean.Boleto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JOptionPane;

public class LeerBase {

	public Boleto guardaInfoArray(String Ruta) throws Exception {
		Boleto bol = new Boleto();
		List<String> lines = Files.readAllLines(Paths.get(Ruta));
		for (String line : lines) {
			String[] array = line.split("\t");

			String folio = array.length > 0 ? array[0] : "";
			String kit = array.length > 2 ? array[2] : "";
			String cant = array.length > 3 ? array[3] : "";
			bol.getFolio().add(folio);
			bol.getKit().add(kit);
			bol.getCantidad().add(cant);
			// System.out.printf("Folio: %s%nKit: %s%nCantidad: %s%n", viewcode, entry,
			// account);
			// System.out.println("*******************");
		}
		return bol;
	}

	public void ExportarError(Boleto boleto, String gusrdar) {
		File file = new File(gusrdar);
		BufferedWriter bw;

		try {
			if (file.exists()) {
				bw = new BufferedWriter(new FileWriter(file));
				bw.write("El fichero de texto ya estaba creado.");
			} else {
				bw = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < boleto.getRepetido().size(); i++) {
					bw.write(boleto.getRepetido().get(i));
					bw.newLine();
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Guardado", "OK", JOptionPane.INFORMATION_MESSAGE);
	}

	public void ExportarEscaneados(Boleto boleto, String gusrdar) {
		File file = new File(gusrdar);
		BufferedWriter bw;

		try {
			if (file.exists()) {
				bw = new BufferedWriter(new FileWriter(file));
				bw.write("El fichero de texto ya estaba creado.");
			} else {
				bw = new BufferedWriter(new FileWriter(file));
				for (int i = 0; i < boleto.getEscaneo().size(); i++) {
					bw.write(boleto.getEscaneo().get(i));
					bw.newLine();
				}
			}
			bw.close();
			Runtime obj = Runtime.getRuntime();
			obj.exec("notepad "+gusrdar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Guardado", "OK", JOptionPane.INFORMATION_MESSAGE);
	}

}
