package data;

import java.sql.*;

import javax.swing.JOptionPane;

public class FactoryConexion {
private Connection conn;
	
	private int cantConn=0;
	
	private String dbType="ucanaccess";
	private String dbDriver="net.ucanaccess.jdbc.UcanaccessDriver";
	//private String host="C:/Proyectos/gerardo/base.mdb";
	private String host="C:/Users/Ezequiel/Desktop/Nueva carpeta/base.mdb";
	private String user="";
	private String pass="";
	private String db="";
	
	//Usamos patron Singleton, es decir, que solo pueda haber una sola instancia de esta clase (no quiero que hayan varios drivers de la bd)
	
	FactoryConexion(){
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error de base de datos.");
		}
	}
	
	private static FactoryConexion instancia; //variable de clase para el singleton
	
	public static FactoryConexion getInstancia(){
		if (instancia==null){
			instancia = new FactoryConexion();
		}
		return instancia;
	}
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){
				try {
					conn = DriverManager.getConnection(
							"jdbc:"+dbType+"://"+host);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void releaseConn(){
		cantConn--;
		if(cantConn==0)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}


