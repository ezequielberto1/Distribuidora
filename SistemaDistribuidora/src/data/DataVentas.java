package data;
//
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;




import org.apache.commons.lang.StringUtils;

import entidades.Articulo_Venta;
import entidades.Venta;
import utils.ApplicationException;
import utils.NumberRenderer;

public class DataVentas {
	

	public void cargarDetalleVenta(JTable tabla, int nro_venta) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		DefaultTableModel model;

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[codart] as Codigo, ventasfinal.[descripcion] as Descripcion, "
					+ "ventasfinal.[costo] as CostoU, ventasfinal.[monto] as PrecioU, ventasfinal.[subtotal]/ventasfinal.[monto] as Cantidad, "
					+ "ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as SubtotalCosto, "
					+ "ventasfinal.[subtotal] as SubtotalVenta, ventasfinal.subtotal-ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as Ganancia "
					+ "FROM ventasfinal "
					+ "WHERE ventasfinal.numventa = ?");
			stmt.setInt(1, nro_venta);
			rs = stmt.executeQuery();
			ArrayList<String> columnnames = new ArrayList<String>(Arrays.asList("Codigo","Descripcion","CostoU","PrecioU","Cantidad","Subtotal Costo","Subtotal Venta","Ganancia"));
			model = buildTableModel(rs, columnnames);
			tabla.setModel(model);
			TableColumnModel m = tabla.getColumnModel();
			m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());

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
	
	public Vector<Vector<Object>> cargarDetalleVenta(int nro_venta) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Vector<Vector<Object>> w = new Vector<Vector<Object>>();

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[codart] as Codigo, ventasfinal.[descripcion] as Descripcion, "
					+ "ventasfinal.[costo] as CostoU, ventasfinal.[monto] as PrecioU, ventasfinal.[subtotal]/ventasfinal.[monto] as Cantidad, "
					+ "ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as SubtotalCosto, "
					+ "ventasfinal.[subtotal] as SubtotalVenta, ventasfinal.subtotal-ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as Ganancia "
					+ "FROM ventasfinal "
					+ "WHERE ventasfinal.numventa = ?");
			stmt.setInt(1, nro_venta);
			rs = stmt.executeQuery();
			while (rs.next()){
				Vector<Object> v = new Vector<Object>();
				v.add(rs.getObject(1));
				v.add(rs.getObject(2));
				v.add(rs.getObject(3));
				v.add(rs.getObject(4));
				v.add(rs.getObject(5));
				v.add(rs.getObject(6));
				v.add(rs.getObject(7));
				w.add(v);
			}


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

		return w;

	}
	
	public ArrayList<Articulo_Venta> getArticulosVenta(int nro_venta) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Articulo_Venta> av = new ArrayList<Articulo_Venta>();

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[codart] as Codigo, ventasfinal.[descripcion] as Descripcion, "
					+ "ventasfinal.[costo] as CostoU, ventasfinal.[monto] as PrecioU, ventasfinal.[subtotal]/ventasfinal.[monto] as Cantidad, "
					+ "ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as SubtotalCosto, "
					+ "ventasfinal.[subtotal] as SubtotalVenta, ventasfinal.subtotal-ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as Ganancia "
					+ "FROM ventasfinal "
					+ "WHERE ventasfinal.numventa = ?");
			stmt.setInt(1, nro_venta);
			rs = stmt.executeQuery();
			while (rs.next()){
				Articulo_Venta art = new Articulo_Venta();
				art.setCodigo(rs.getInt(1));
				art.setNombre(rs.getString(2));
				art.setCosto(rs.getFloat(3));
				art.setPrecio(rs.getFloat(4));
				art.setCantidad(rs.getFloat(5));
				art.setSubtotalcosto(rs.getFloat(6));
				art.setSubtotalventa(rs.getFloat(7));
				av.add(art);
			}


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

		return av;

	}

	public void cargarListaVentas(JTable tabla, java.util.Date fechaDesde, java.util.Date fechaHasta, String orden, String filtroZonas) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		DefaultTableModel model;
		java.sql.Date fd = new java.sql.Date(fechaDesde.getTime());
		java.sql.Date fh = new java.sql.Date(fechaHasta.getTime());

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[fecha] as Fecha, ventasfinal.[numventa] as Vta, "
					+ "ventasfinal.[codcli] as CodCli, ventasfinal.[apenom] as Cliente, ventasfinal.[localidad] as Localidad, ventasfinal.[iva] as Zona, "
					+ "sum(ventasfinal.[costo]*ventasfinal.[subtotal]/ventasfinal.[monto]) as SubtotalCosto, sum(ventasfinal.[subtotal]) as SubtotalVenta, "
					+ "sum(ventasfinal.[subtotal]-ventasfinal.[costo]*ventasfinal.[subtotal]/ventasfinal.[monto]) as Ganancia "
					+ "FROM ventasfinal "
					+ "WHERE ((ventasfinal.[fecha] BETWEEN ? AND ?) " + filtroZonas +") "
					+ "GROUP BY ventasfinal.[fecha], ventasfinal.[numventa], "
					+ "ventasfinal.[codcli], ventasfinal.[apenom], ventasfinal.[localidad], ventasfinal.[iva] "
					+ "ORDER BY " + orden);
			stmt.setDate(1, fd);
			stmt.setDate(2, fh);
	
			rs = stmt.executeQuery();
			
			ArrayList<String> columnnames = new ArrayList<String>(Arrays.asList("Fecha","Nº Vta.","Nº Cli.","Cliente","Localidad","Zona","Subtotal Costo", "Subtotal Venta", "Ganancia"));
			model = buildTableModel(rs, columnnames);
			tabla.setModel(model);
			TableColumnModel m = tabla.getColumnModel();
			m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());
			m.getColumn(8).setCellRenderer(NumberRenderer.getCurrencyRenderer());
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

	public Venta getVenta(int nro_venta) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Venta v = new Venta();

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[fecha], ventasfinal.[numventa], "
					+ "ventasfinal.[codcli], ventasfinal.[apenom], ventasfinal.[domicilio], ventasfinal.[localidad], ventasfinal.[iva] "
					+ "FROM ventasfinal "
					+ "WHERE ventasfinal.numventa = ?");
			stmt.setInt(1, nro_venta);
			rs = stmt.executeQuery();
			while(rs.next()){
				v.setFecha(rs.getDate(1));
				v.setNro(rs.getInt(2));
				v.setCodcli(rs.getInt(3));
				v.setCliente(rs.getString(4));
				v.setDomicilio(rs.getString(5));
				v.setLocalidad(rs.getString(6));
				v.setZona(rs.getString(7));
			}
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
		return v;

	}

	

	public Vector<Object> getArticuloVenta(int nro_venta, int codigo) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Vector<Object> v = new Vector<Object>();

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[codart] as Codigo, ventasfinal.[descripcion] as Descripcion, "
					+ "ventasfinal.[costo] as CostoU, ventasfinal.[monto] as PrecioU, ventasfinal.[subtotal]/ventasfinal.[monto] as Cantidad, "
					+ "ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as SubtotalCosto, "
					+ "ventasfinal.[subtotal] as SubtotalVenta, ventasfinal.subtotal-ventasfinal.costo*ventasfinal.subtotal/ventasfinal.monto as Ganancia "
					+ "FROM ventasfinal "
					+ "WHERE ventasfinal.numventa = ? AND ventasfinal.codart = ?");
			stmt.setInt(1, nro_venta);
			stmt.setInt(2, codigo);
			rs = stmt.executeQuery();
			while (rs.next()){
				v.add(rs.getObject(1));
				v.add(rs.getObject(2));
				v.add(rs.getObject(3));
				v.add(rs.getObject(4));
				v.add(rs.getObject(5));
				v.add(rs.getObject(6));
				v.add(rs.getObject(7));
			}


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

		return v;

	}
	
	public void updateVenta(Venta v, ArrayList<Articulo_Venta> av){
		PreparedStatement stmt=null;
		ResultSet rs = null;

		try {
			
			//Eliminar articulos 
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement("delete * from ventasfinal "
					+ "where numventa=?");
			stmt.setInt(1, v.getNro());
			stmt.execute();
			
			//Agregar articulos
			System.out.println(av.size());
			for (int i = 0; i < av.size(); i++) {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement("insert into ventasfinal "
						+ "(numventa, fecha, codcli, apenom, domicilio, localidad, iva, codart, descripcion, costo, monto, "
						+ "subtotalcosto, subtotal, ganancia, cantidad) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, v.getNro());
				stmt.setDate(2, new java.sql.Date(v.getFecha().getTime()));
				stmt.setInt(3, v.getCodcli());
				stmt.setString(4, v.getCliente());
				stmt.setString(5, v.getDomicilio());
				stmt.setString(6, v.getLocalidad());
				stmt.setString(7, v.getZona());
				stmt.setInt(8, av.get(i).getCodigo());
				stmt.setString(9, av.get(i).getNombre());
				stmt.setFloat(10, av.get(i).getCosto());
				stmt.setFloat(11, av.get(i).getPrecio());
				stmt.setFloat(12,av.get(i).getSubtotalcosto());
				stmt.setFloat(13,av.get(i).getSubtotalventa());
				stmt.setFloat(14, (av.get(i).getSubtotalventa()-av.get(i).getSubtotalcosto()));
				stmt.setFloat(15, av.get(i).getCantidad());
				stmt.executeUpdate();
			}	

		} 
		catch (SQLException e) {

			e.printStackTrace();
		} 

		finally {
			FactoryConexion.getInstancia().releaseConn(); 
		}
	}
	
	
	public void deleteVenta(Venta v) {
		PreparedStatement stmt=null;

		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement("delete * from ventasfinal "
					+ "where numventa=?");
			stmt.setInt(1, v.getNro());
			stmt.execute();

		} 
		catch (SQLException e) {

			e.printStackTrace();
		} 

		finally {
			FactoryConexion.getInstancia().releaseConn(); 
		}
		
	}

	public static DefaultTableModel buildTableModel(ResultSet rs, ArrayList<String> col)
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

		return new DefaultTableModel(data, columnNames);

	}

}
