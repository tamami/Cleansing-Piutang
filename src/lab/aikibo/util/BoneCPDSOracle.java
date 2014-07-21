package lab.aikibo.util;

import java.sql.Connection;
import java.sql.SQLException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 *  BoneCPDSPostgre is BoneCP Data Source for Connection to Postgre Database
 *  
 * @author tamami
 *
 */
public class BoneCPDSOracle {
	// the connection pool using BoneCP
	private BoneCP connectionPool;
	// this is the connection that can use to CRUD data in database
	private Connection connection;
	
	public BoneCPDSOracle() {
		init();
	}
	
	/*
	 * Get connection that was setup with BoneCP Connection Pool 
	 */
	public void init() {
		connectionPool = null;
		connection = null;
 
		try {
			// load the database driver (make sure this is in your classpath!)
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			// setup the connection pool
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:oracle:thin:PBB/RAHASIAPBB@192.168.2.7:1521/SISMIOP"); // jdbc url specific to your database, eg jdbc:mysql://127.0.0.1/yourdb
			config.setUsername("PBB"); 
			config.setPassword("RAHASIAPBB");
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(1);
			connectionPool = new BoneCP(config); // setup the connection pool
			
			connection = connectionPool.getConnection(); // fetch a connection
			//connectionPool.shutdown(); // shutdown connection pool.
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getBoneCPConn() {
		return connection;
	}
	
	public void shutdownBoneCP() {
		connectionPool.shutdown();
	}

}
