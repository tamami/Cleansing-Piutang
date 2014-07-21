package lab.aikibo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

import lab.aikibo.manager.SPManager;

public class PenembakJitu {
	
	public static boolean processFile(File dataFile) throws FileNotFoundException {
		Scanner scnFile = new Scanner(dataFile);
		SPManager callSP = new SPManager();
		callSP.init();
		int lineNumber = 0;
		while(scnFile.hasNextLine()) {
			scnFile.nextLine();
			lineNumber++;
		}
		scnFile.close();
		scnFile = new Scanner(dataFile);
		int i=1;
		while(scnFile.hasNextLine()) {
			Scanner scnData = new Scanner(scnFile.nextLine()).useDelimiter(Pattern.compile("\\|"));
			String nop = scnData.next();
			String thn = scnData.next();
			String flag = scnData.next();
			System.out.println("Proses " + i + "/" + lineNumber + 
					" NOP: " + nop + " -> " + thn + "(" + flag + ")");
			
			// panggil sp update_sppt_ke_belum_bayar
			if(flag.equals("1")) {
				try {
					callSP.callUpdateSpptKeBelumBayar(nop, thn);
				} catch(SQLException sqle) {
					System.err.println("Kenapa bisa ada SQL Exception saat mengembalikan SPPT jadi belum bayar?");
					sqle.printStackTrace();
					return false;
				}
			}
			// panggil sp update_piutang 
			if(flag.equals("2")) {
				try {
					callSP.callUpdatePiutang(nop, thn);
				} catch(SQLException sqle) {
					System.err.println("Ada eror SQL Exception saat update piutang?");
					sqle.printStackTrace();
					return false;
				}
			}
			// panggil sp update_sppt_ke_batal
			if(flag.equals("3")) {
				try {
					callSP.callUpdateSpptKeBatal(nop, thn);
				} catch(SQLException sqle) {
					System.err.println("Error SQL Exception saat mengubah status SPPT jadi batal");
					sqle.printStackTrace();
					return false;
				}
			}
			i++;
		}
		callSP.finish();
		return true;
	}

}
