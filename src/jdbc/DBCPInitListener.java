package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


//SeveletContextListener�� ó�� �����ø����̼� ������ �� ����Ǵ� Ŭ����
public class DBCPInitListener implements ServletContextListener{
	
	@Override //�̰� SeveletContextListener �� �޼ҵ�
	public void contextInitialized(ServletContextEvent sce){
		
		//poolConfig�� web.xml�� ����� �Ķ���͵�
		String poolConfig=
				sce.getServletContext().getInitParameter("poolConfig");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadJDBCDriver(prop); //JDBC����̹��� �ε���
		initConnectionPool(prop); //Ŀ�ؼ�Ǯ ����
	}
	
	//JDBC����̹��� �ε���
	private void loadJDBCDriver(Properties prop){
		String driverClass=prop.getProperty("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Ŀ�ؼ�Ǯ ����
	private void initConnectionPool(Properties prop){
		String jdbcUrl = prop.getProperty("jdbcUrl");
		String username = prop.getProperty("dbUser");
		String pw = prop.getProperty("dbPass");
		
		ConnectionFactory connFactory =
				new DriverManagerConnectionFactory(jdbcUrl, username, pw);
		
		PoolableConnectionFactory poolableConnFactory =
				new PoolableConnectionFactory(connFactory, null);
		poolableConnFactory.setValidationQuery("select 1"); //�׽�Ʈ ����
		
		//Ŀ�ؼ�Ǯ ����
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinIdle(4);
		poolConfig.setMaxTotal(50);
		
		GenericObjectPool<PoolableConnection> connectionPool =
				new GenericObjectPool<>(poolableConnFactory, poolConfig);
		poolableConnFactory.setPool(connectionPool);
		try {
			Class.forName("org.apache.commons.dbcp2.PoolongDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			PoolingDriver driver = (PoolingDriver)
					DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connectionPool);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}