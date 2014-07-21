package lab.aikibo.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectorUtil {
	
	private Configuration oracleConfig;
	private SessionFactory oracleSessionFactory;;
	
	public ConnectorUtil() {
		init();
	}
	
	private void init() {
		// prepare for Annotated Class from oracle db
		oracleConfig = new Configuration();
		//oracleConfig.addAnnotatedClass(User.class);
		//oracleConfig.addAnnotatedClass(Sppt.class);
		//oracleConfig.addAnnotatedClass(RefPropinsi.class);
		// why this is cannot used in production site
		//oracleSessionFactory = oracleConfig.configure("oracleconfig.cfg.xml").buildSessionFactory();
		oracleSessionFactory = oracleConfig.configure().buildSessionFactory();
		
		
		// prepare for Annotated Class from postgre db
		//postgreConfig = new AnnotationConfiguration();
		// - this must be in Oracle Configuration
		//postgreConfig.addAnnotatedClass(User.class);
		// - end: this must be in Oracle COnfiguration
		//postgreSessionFactory = postgreConfig.configure("postgreconfig.cfg.xml").buildSessionFactory();
		
		
	}
	
	public SessionFactory getOracleSF() {
		return oracleSessionFactory;
	}
	

}
