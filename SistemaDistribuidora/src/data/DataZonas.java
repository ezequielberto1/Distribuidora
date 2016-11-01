package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang.StringUtils;

import utils.ChecklistTableModel;


public class DataZonas {
	public void cargarTablaZonas(JTable tabla) {
		Statement stmt=null;
		ResultSet rs=null;
		ChecklistTableModel model;

		try {

			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select distinct iva from clientes order by iva asc");
			model = buildTableModel(rs);
			tabla.setModel(model);
		} catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());

		}
		finally{
			try {
				if (rs!=null)
					rs.close();
				if (stmt!=null)stmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public Vector<Object> getZonas(){
		
		Statement stmt=null;
		ResultSet rs=null;
		Vector<Object> zonas = new Vector<Object>();
		
		try {

			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select distinct iva from clientes order by iva asc");
			while(rs.next())
				zonas.add(rs.getObject(1));
		} catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());

		}
		finally{
			try {
				if (rs!=null)
					rs.close();
				if (stmt!=null)stmt.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return zonas;
		
	}

	public static ChecklistTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		// names of columns
		Vector<String> columnNames = new Vector<String>();

		columnNames.add("Nombre");
		columnNames.add("");

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			vector.add(rs.getObject(1));
			vector.add(Boolean.TRUE);
			data.add(vector);
		}

		return new ChecklistTableModel(data, columnNames);

	}
}

