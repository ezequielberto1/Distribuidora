package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import utils.ChecklistTableModel;

public class DataMarcas {
	public void cargarTablaMarcas(JTable tabla) {
		Statement stmt=null;
		ResultSet rs=null;
		ChecklistTableModel model;

		try {

			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select rubro from rubro");
			model = buildTableModel(rs);
			tabla.setModel(model);			//tabla.setModel(model);
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
	
	public Vector<Object> getMarcas(){
			
			Statement stmt=null;
			ResultSet rs=null;
			Vector<Object> marcas = new Vector<Object>();
			
			try {
	
				stmt = FactoryConexion.getInstancia().getConn().createStatement();
				rs = stmt.executeQuery("select rubro from rubro order by rubro asc");
				while(rs.next())
					marcas.add(rs.getObject(1));
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
			
			return marcas;
			
		}
	
	public void getMarcas(JTable tabla) {
		Statement stmt=null;
		ResultSet rs=null;
		ChecklistTableModel model;

		try {

			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("select rubro from rubro");
			model = buildTableModel(rs);
			tabla.setModel(model);			//tabla.setModel(model);
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

}
