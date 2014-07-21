package lab.aikibo.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

import lab.aikibo.util.PenembakJitu;

public class ProsesFile {
	
	public static void main(String args[]) {
		if(args.length != 1) {
			System.err.println("Gunakan : java -jar clean.jar namafile.txt");
			return;
		} else {
			File data = null;
			Scanner scanner = null;
			try {
				data = new File(args[0]);
				if(PenembakJitu.processFile(data)) {
					System.out.println("Sukses..");
				} else {
					System.out.println("Ada kesalahan saat baca data");
				}
			} catch(FileNotFoundException ex) {
				System.err.println("File tidak ditemukan!");
			} 
		}
	}

}
