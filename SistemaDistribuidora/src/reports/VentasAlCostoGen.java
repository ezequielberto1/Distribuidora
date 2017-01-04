package reports;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.Level;

import utils.SuperLogger;
import data.FactoryConexion;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


public class VentasAlCostoGen {
	
	
 public void generate(java.util.Date fecha) {
  HashMap hm = null;
  // System.out.println("Usage: ReportGenerator ....");

  try {
   System.out.println("Start ....");
   // Get jasper report
   String jrxmlFileName = "C:/IntReports/Files/VentasAlCosto.jrxml";
   String jasperFileName = "C:/IntReports/Files/VentasAlCosto.jasper";
  
   // Get the connection
   Connection conn = FactoryConexion.getInstancia().getConn();

   // Create arguments
   // Map params = new HashMap();
   hm = new HashMap();
   hm.put("fecha", fecha);

   // Generate jasper print
   JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);

   // Show report
   JasperViewer jv = new JasperViewer(jprint);
   jv.setVisible(true);
   
   DateFormat df = new SimpleDateFormat("dd/MM/yyyy");   
   String reportDate = df.format(fecha);
   
   jv.setTitle("Ventas al costo " + reportDate);
      
   
  }  catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
	  	SuperLogger.logger.log(Level.ERROR, e.getMessage(), e);
  }
 }
}