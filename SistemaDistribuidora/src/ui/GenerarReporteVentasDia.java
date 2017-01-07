package ui;

import java.awt.EventQueue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import data.DataZonas;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.lang.StringUtils;

import reports.ReporteVentasGen;
import utils.ApplicationException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.SystemColor;

public class GenerarReporteVentasDia {

	private JFrame frmGenerarReporte;
	private JTable tblZonas;
	private DataZonas DataZonas;
	private JTextField txtVendedor;
	private JTextField txtComision;
	private MenuReportes menur;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarReporteVentasDia window = new GenerarReporteVentasDia();
					window.frmGenerarReporte.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ApplicationException 
	 */
	public GenerarReporteVentasDia() {
		
		try 
	    { 
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	    } 
	    catch(Exception e){ 
	    }
		
		initialize();
		
		DataZonas= new DataZonas();

		DataZonas.cargarTablaZonas(tblZonas);
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGenerarReporte = new JFrame();
		frmGenerarReporte.setTitle("Reporte de ventas");
		frmGenerarReporte.setResizable(false);
		frmGenerarReporte.setBounds(100, 100, 160, 455);
		frmGenerarReporte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblVendedor = new JLabel("Vendedor:");
		lblVendedor.setBounds(10, 67, 69, 14);
		frmGenerarReporte.getContentPane().add(lblVendedor);
		
		txtVendedor = new JTextField();
		txtVendedor.setBounds(10, 92, 134, 20);
		frmGenerarReporte.getContentPane().add(txtVendedor);
		txtVendedor.setColumns(10);
		
		JLabel lblComisin = new JLabel("Comisi\u00F3n (%):");
		lblComisin.setBounds(10, 123, 69, 18);
		frmGenerarReporte.getContentPane().add(lblComisin);
		
		txtComision = new JTextField();
		txtComision.setBounds(89, 123, 55, 20);
		frmGenerarReporte.getContentPane().add(txtComision);
		txtComision.setColumns(10);
		
		JButton btnGenerarReporte = new JButton("Generar");
		
		btnGenerarReporte.setBounds(36, 362, 80, 23);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 11, 33, 14);
		
		JDateChooser dateFecha = new JDateChooser();
		dateFecha.setBounds(10, 36, 87, 20);
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				java.util.Date fecha = dateFecha.getDate();
				java.util.ArrayList<String> zonas = new ArrayList<String>();
				String zonasString = "";
				String vendedor;				
				
				vendedor = txtVendedor.getText();
				
				zonas = getSeleccionados(tblZonas);
				
				if (fecha != null && !(zonas.isEmpty()) && !(txtComision.getText().matches(""))) {
					for (String s : zonas)
					{
					    zonasString += s.substring(5) + ", ";
					}
					zonasString = zonasString.substring(0, zonasString.length()-2);
					try{
						BigDecimal porcentaje_comision = new BigDecimal(txtComision.getText());
						porcentaje_comision = porcentaje_comision.divide(new BigDecimal(100));
						ReporteVentasGen rvg = new ReporteVentasGen();
						rvg.generate(fecha, zonas, zonasString, vendedor, porcentaje_comision);
					}
					catch (Exception e1){
						JOptionPane.showMessageDialog(null, "Formato de comisión inválido.");
					}
					
				}
				else {
					if (fecha == null)
						JOptionPane.showMessageDialog(null, "Seleccione una fecha.");
					if (zonas.isEmpty())
						JOptionPane.showMessageDialog(null, "Seleccione al menos una zona.");
					if (txtComision.getText().matches(""))
						JOptionPane.showMessageDialog(null, "Ingrese comisión del vendedor.");
					}
				
			}
		});
		
		JLabel lblZonas = new JLabel("Zonas:");
		lblZonas.setBounds(10, 152, 61, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 177, 134, 106);
		
		tblZonas = new JTable();
		tblZonas.setShowVerticalLines(false);
		tblZonas.setBackground(SystemColor.menu);
		scrollPane.setViewportView(tblZonas);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGenerarReporte.setVisible(false);
			}
		});
		btnVolver.setBounds(36, 396, 80, 23);
		
		JButton btnDesmarcarTodos = new JButton("Desmarcar todos");
		btnDesmarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desmarcarTodo(tblZonas);
			}
		});
		btnDesmarcarTodos.setBounds(10, 328, 134, 23);
		
		JButton btnMarcarTodos = new JButton("Marcar todos");
		btnMarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				marcarTodo(tblZonas);
			}
		});
		btnMarcarTodos.setBounds(10, 294, 134, 23);
		frmGenerarReporte.getContentPane().setLayout(null);
		frmGenerarReporte.getContentPane().add(btnGenerarReporte);
		frmGenerarReporte.getContentPane().add(lblFecha);
		frmGenerarReporte.getContentPane().add(dateFecha);
		frmGenerarReporte.getContentPane().add(lblZonas);
		frmGenerarReporte.getContentPane().add(scrollPane);
		frmGenerarReporte.getContentPane().add(btnVolver);
		frmGenerarReporte.getContentPane().add(btnDesmarcarTodos);
		frmGenerarReporte.getContentPane().add(btnMarcarTodos);
	}
	
	public ArrayList<String> getSeleccionados (JTable tabla) {
		ArrayList<String> items = new ArrayList<String>();
		for (int i = 0; i < tabla.getRowCount(); i++) {
			if (tabla.getModel().getValueAt(i, 1).toString()==Boolean.TRUE.toString()) {
				items.add(tabla.getModel().getValueAt(i,0).toString());
			}	
		}
		return items;
	}
	
	public void marcarTodo (JTable tabla) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			tabla.getModel().setValueAt(Boolean.TRUE, i, 1);
		}	
		return;
	}
	
	public void desmarcarTodo (JTable tabla) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			tabla.getModel().setValueAt(Boolean.FALSE, i, 1);
		}	
		return;
	}
	
	public void show(boolean b){
		this.frmGenerarReporte.setVisible(b);
	}

	public void setCaller(MenuReportes menu){
		menur = menu;
	}

	public MenuReportes getCaller(){
		return menur;
	}
	
}