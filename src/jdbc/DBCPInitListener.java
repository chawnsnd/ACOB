package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
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


//SeveletContextListener는 처음 웹어플리케이션 실행할 때 실행되는 클래스
public class DBCPInitListener implements ServletContextListener{
	
	@Override //이게 SeveletContextListener 주 메소드
	public void contextInitialized(ServletContextEvent sce){
		
		//poolConfig는 web.xml에 등록한 파라미터들
		String poolConfig=
				sce.getServletContext().getInitParameter("poolConfig");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loadJDBCDriver(prop); //JDBC드라이버를 로딩함
		initConnectionPool(prop); //커넥션풀 만듬
	}
	
	//JDBC드라이버를 로딩함
	private void loadJDBCDriver(Properties prop){
		String driverClass=prop.getProperty("jdbcdriver");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//커넥션풀 생성
	private void initConnectionPool(Properties prop){
		try{
		String jdbcUrl = prop.getProperty("jdbcUrl");
		String username = prop.getProperty("dbUser");
		String pw = prop.getProperty("dbPass");
		
		ConnectionFactory connFactory =
				new DriverManagerConnectionFactory(jdbcUrl, username, pw);
		
		PoolableConnectionFactory poolableConnFactory =
				new PoolableConnectionFactory(connFactory, null);
		poolableConnFactory.setValidationQuery("select 1"); //테스트 쿼리
		
		//커넥션풀 설정
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinIdle(4);
		poolConfig.setMaxTotal(50);
		
		GenericObjectPool<PoolableConnection> connectionPool =
				new GenericObjectPool<>(poolableConnFactory, poolConfig);
		poolableConnFactory.setPool(connectionPool);

		Class.forName("org.apache.commons.dbcp2.PoolingDriver");

		PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		String poolName = prop.getProperty("poolName");
		driver.registerPool(poolName, connectionPool);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
