package reports;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

import data.FactoryConexion;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


public class ReporteVentasGen {
	
	
 public void generate(java.util.Date fecha, java.util.Collection<Integer> vendedores) {
  HashMap hm = null;
  // System.out.println("Usage: ReportGenerator ....");

  try {
   System.out.println("Start ....");
   // Get jasper report
   String jrxmlFileName = "C:/Reportes/Files/ReporteVentas.jrxml";
   String jasperFileName = "C:/Reportes/Files/ReporteVentas.jasper";
  
   // Get the connection
   Connection conn = FactoryConexion.getInstancia().getConn();

   // Create arguments
   // Map params = new HashMap();
   hm = new HashMap();
   hm.put("fecha", fecha);
   hm.put("vendedores", vendedores);

   // Generate jasper print
   JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);

   // Show report
   JasperViewer jv = new JasperViewer(jprint);
   jv.setVisible(true);
   
   DateFormat df = new SimpleDateFormat("dd/MM/yyyy");   
   String reportDate = df.format(fecha);
   
   jv.setTitle("Ventas " + reportDate);
      
   
  } catch (Exception e) {
   System.out.print("Exception" + e);
   e.printStackTrace();
  }
 }

}
