package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import utils.NonEditableTableModel;
import utils.NumberRenderer;

public class DataArticulos {
	public void cargarArticulos(JTable tabla, String rubro) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		NonEditableTableModel model;

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT codigo AS Codigo, descripcion AS Descripcion, "
					+ "costo AS Costo, lista1 AS PVenta FROM articulos "
					+ "WHERE rubro = ? ORDER BY codigo");
			stmt.setString(1, rubro);
			rs = stmt.executeQuery();
			ArrayList<String> columnnames = new ArrayList<String>(Arrays.asList("Codigo","Descripcion","CostoU","PrecioU"));
			model = buildTableModel(rs, columnnames);
			tabla.setModel(model);
			TableColumnModel m = tabla.getColumnModel();
			m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());

		} catch (SQLException ex) {

			System.out.println("SQLException: " + ex.getMessage());
			ex.printStackTrace();


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
	
	public static NonEditableTableModel buildTableModel(ResultSet rs, ArrayList<String> col)
			throws SQLException {

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = col.size();
		for (int column = 0; column < columnCount; column++) {
			columnNames.add(col.get(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new NonEditableTableModel(data, columnNames);

	}

}
