package data;
//
import java.sql.*;
import java.util.ArrayList;
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
	public void cargarListaVentas(JTable tabla, java.util.Date fechaDesde, java.util.Date fechaHasta, ArrayList<String> marcas, ArrayList<String> zonas) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		DefaultTableModel model;
		java.sql.Date fd = new java.sql.Date(fechaDesde.getTime());
		java.sql.Date fh = new java.sql.Date(fechaHasta.getTime());
		String marcasString = StringUtils.join(marcas.iterator(),"','");
		marcasString = "'".concat(marcasString.concat("'"));
		String zonasString = StringUtils.join(zonas.iterator(),"','");
		zonasString = "'".concat(zonasString.concat("'"));
		System.out.println(marcasString);
		System.out.println(zonasString);

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[fecha] as Fecha, ventasfinal.[numventa] as Vta, "
					+ "ventasfinal.[codcli] as CodCli, ventasfinal.[apenom] as Cliente, ventasfinal.[localidad] as Localidad, ventasfinal.[iva] as Zona, "
					+ "sum(ventasfinal.[subtotalcosto]) as SubtotalCosto, sum((ventasfinal.[cantidad]*articulos.[lista1])) as SubtotalVenta, sum(ventasfinal.[ganancia]) as Ganancia "
					+ "FROM ventasfinal INNER JOIN articulos ON ventasfinal.[codart]=articulos.[codigo] "
					+ "WHERE ((ventasfinal.[fecha]) BETWEEN ? AND ?) AND (articulos.[rubro] IN (?)) AND (ventasfinal.[iva] IN (?)) "
					+ "GROUP BY ventasfinal.[fecha], ventasfinal.[numventa], vendedores.[nombre], "
					+ "ventasfinal.[codcli], ventasfinal.[apenom], ventasfinal.[localidad], ventasfinal.[iva]");
			stmt.setDate(1, fd);
			stmt.setDate(2, fh);
			stmt.setString(3, marcasString);
			stmt.setString(4, zonasString);
			rs = stmt.executeQuery();
			model = buildTableModel(rs);
			tabla.setModel(model);
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

	public void cargarDetalleVenta(JTable tabla, int nro_venta) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		DefaultTableModel model;

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[codart] as Codigo, ventasfinal.[descripcion] as Descripcion, "
					+ "ventasfinal.[costo] as CostoU, articulos.[lista1] as PrecioU, ventasfinal.[cantidad] as Cantidad, ventasfinal.[subtotalcosto], "
					+ "ventasfinal.[cantidad]*articulos.[lista1] as SubtotalVenta, ventasfinal.[ganancia] "
					+ "FROM ventasfinal "
					+ "INNER JOIN articulos ON ventasfinal.[codart]=articulos.[codigo] "
					+ "WHERE ventasfinal.numventa = ?");
			stmt.setInt(1, nro_venta);
			rs = stmt.executeQuery();
			model = buildTableModel(rs);
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
					+ "ventasfinal.[costo] as CostoU, articulos.[lista1] as PrecioU, ventasfinal.[cantidad] as Cantidad, ventasfinal.[subtotalcosto], "
					+ "ventasfinal.[cantidad]*articulos.[lista1] as SubtotalVenta, ventasfinal.[ganancia] "
					+ "FROM ventasfinal "
					+ "INNER JOIN articulos ON ventasfinal.[codart]=articulos.[codigo] "
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

	public void cargarListaVentasDefault(JTable tabla, java.util.Date fechaDesde, java.util.Date fechaHasta) {
		PreparedStatement stmt=null;
		ResultSet rs=null;
		DefaultTableModel model;
		java.sql.Date fd = new java.sql.Date(fechaDesde.getTime());
		java.sql.Date fh = new java.sql.Date(fechaHasta.getTime());

		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT ventasfinal.[fecha] as Fecha, ventasfinal.[numventa] as Vta, "
					+ "ventasfinal.[codcli] as CodCli, ventasfinal.[apenom] as Cliente, ventasfinal.[localidad] as Localidad, ventasfinal.[iva] as Zona, "
					+ "sum(ventasfinal.[subtotalcosto]) as SubtotalCosto, sum((ventasfinal.[cantidad]*articulos.[lista1])) as SubtotalVenta, sum(ventasfinal.[ganancia]) as Ganancia "
					+ "FROM ventasfinal INNER JOIN articulos ON ventasfinal.[codart]=articulos.[codigo] "
					+ "WHERE ventasfinal.[fecha] BETWEEN ? AND ? "
					+ "GROUP BY ventasfinal.[numventa], ventasfinal.[fecha], "
					+ "ventasfinal.[codcli], ventasfinal.[apenom], ventasfinal.[localidad], ventasfinal.[iva]");
			stmt.setDate(1, fd);
			stmt.setDate(2, fh);

			rs = stmt.executeQuery();
			model = buildTableModel(rs);
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
					+ "ventasfinal.[apenom], ventasfinal.[iva] "
					+ "FROM ventasfinal "
					+ "WHERE ventasfinal.numventa = ?");
			stmt.setInt(1, nro_venta);
			rs = stmt.executeQuery();
			while(rs.next()){
				v.setFecha(rs.getDate(1));
				v.setNro(rs.getInt(2));
				v.setCliente(rs.getString(3));
				v.setZona(rs.getString(4));
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
					+ "ventasfinal.[costo] as CostoU, articulos.[lista1] as PrecioU, ventasfinal.[cantidad] as Cantidad, ventasfinal.[subtotalcosto], "
					+ "ventasfinal.[cantidad]*articulos.[lista1] as SubtotalVenta, ventasfinal.[ganancia] "
					+ "FROM ventasfinal "
					+ "INNER JOIN articulos ON ventasfinal.[codart]=articulos.[codigo] "
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

	public void updateVenta(Venta v, ArrayList<Articulo_Venta> aa, ArrayList<Articulo_Venta> am, ArrayList<Integer> ae){
		PreparedStatement stmt=null;
		ResultSet rs = null;
		String domicilio = new String(); 
		String localidad = new String();

		try {
			stmt= FactoryConexion.getInstancia().getConn().prepareStatement("select distinct domicilio, localidad from ventasfinal "
					+ "where numventa=?");
			stmt.setInt(1, v.getNro());
			rs = stmt.executeQuery();
			while(rs.next()){ //NO ENTRA ACÁ; rs NO DEVUELVE NADA
				domicilio = rs.getString(1);
				localidad = rs.getString(2);
			}
			//Eliminar articulos 
			for (int i = 0; i < ae.size(); i++) {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement("delete * from ventasfinal "
						+ "where numventa=? and codart=?");
				stmt.setInt(1, v.getNro());
				stmt.setInt(2, ae.get(i));
				stmt.execute();
			}
			
			//Agregar articulos
			for (int i = 0; i < aa.size(); i++) {
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement("insert into ventasfinal "
						+ "(numventa, fecha, apenom, domicilio, localidad, iva, codart, descripcion, costo "
						+ "subtotalcosto, ganancia) values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, v.getNro());
				stmt.setDate(2, new java.sql.Date(v.getFecha().getTime()));
				stmt.setString(3, v.getCliente());
				stmt.setString(4, domicilio);
				stmt.setString(5, localidad);
				stmt.setString(6, v.getZona());
				stmt.setInt(7, aa.get(i).getCodigo());
				stmt.setString(8, aa.get(i).getNombre());
				stmt.setFloat(9, aa.get(i).getCosto());
				stmt.setFloat(10, aa.get(i).getPrecio());
				stmt.setFloat(11,aa.get(i).getSubtotalcosto());
				stmt.setFloat(12, (aa.get(i).getSubtotalventa()-aa.get(i).getSubtotalcosto()));
			}
			
			//Modificar articulos
			for (int i = 0; i < am.size(); i++) {
				//Primero los elimino
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement("delete * from ventasfinal "
						+ "where numventa=? and codart=?");
				stmt.setInt(1, v.getNro());
				stmt.setInt(2, ae.get(i));
				stmt.execute();
				
				//Luego los vuelvo a cargar
				stmt= FactoryConexion.getInstancia().getConn().prepareStatement("insert into ventasfinal "
						+ "(numventa, fecha, apenom, domicilio, localidad, iva, codart, descripcion, costo "
						+ "subtotalcosto, ganancia) values(?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, v.getNro());
				stmt.setDate(2, new java.sql.Date(v.getFecha().getTime()));
				stmt.setString(3, v.getCliente());
				stmt.setString(4, domicilio);
				stmt.setString(5, localidad);
				stmt.setString(6, v.getZona());
				stmt.setInt(7, am.get(i).getCodigo());
				stmt.setString(8, am.get(i).getNombre());
				stmt.setFloat(9, am.get(i).getCosto());
				stmt.setFloat(10, am.get(i).getPrecio());
				stmt.setFloat(11,am.get(i).getSubtotalcosto());
				stmt.setFloat(12, (am.get(i).getSubtotalventa()-am.get(i).getSubtotalcosto()));
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

	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		/**/for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
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
