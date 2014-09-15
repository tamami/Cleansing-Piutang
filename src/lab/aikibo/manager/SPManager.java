package lab.aikibo.manager;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import lab.aikibo.util.BoneCPDSOracle;
import lab.aikibo.util.ConnectorUtil;

public class SPManager {
	BoneCPDSOracle boneCP;
	
	public void init() {
		boneCP = new BoneCPDSOracle();
	}
	
	public void finish() {
		boneCP.shutdownBoneCP();
	}
	
	public void callUpdateSpptKeBelumBayar(String nop, String thn) throws SQLException {
		CallableStatement cs = boneCP.getBoneCPConn().
				prepareCall("{call update_sppt_ke_belum_bayar(?,?)}");
		cs.setString(1, nop);
		cs.setString(2, thn);
		cs.executeQuery();
	}
	
	public void callUpdatePiutang(String nop, String thn) throws SQLException {
		
		CallableStatement cs = boneCP.getBoneCPConn().
				prepareCall("{call update_piutang(?,?)}");
		cs.setString(1, nop);
		cs.setString(2, thn);
		cs.executeQuery();
	}
	
	public void callUpdateSpptKeBatal(String nop, String thn) throws SQLException {
		CallableStatement cs = boneCP.getBoneCPConn().
				prepareCall("{call update_sppt_ke_batal(?,?)}");
		cs.setString(1, nop);
		cs.setString(2, thn);
		cs.executeQuery();
	}
	
	public void callSesuaikanPembayaran(String nop, String thn, BigDecimal nilai, BigDecimal denda, Calendar tglTransaksi, Calendar tglCatat) throws SQLException {
		CallableStatement cs = boneCP.getBoneCPConn().prepareCall("{call sesuaikan_pembayaran(?,?,?,?,?,?)}");
		cs.setString(1, nop);
		cs.setString(2, thn);
		cs.setBigDecimal(3, nilai);
		//java.sql.Date d = 
		cs.setDate(4, (java.sql.Date) tglTransaksi.getTime());
		cs.setDate(5, (java.sql.Date) tglCatat.getTime());
		cs.setBigDecimal(6, denda);
		cs.executeQuery();
	}

}