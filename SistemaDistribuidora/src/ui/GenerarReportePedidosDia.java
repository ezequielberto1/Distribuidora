package ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import data.DataZonas;

import javax.swing.JScrollPane;
import javax.swing.JTable;


import reports.ReportePedidosGen;
import utils.ApplicationException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerarReportePedidosDia {

	private JFrame frmGenerarReporte;
	private JTable tblZonas;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarReportePedidosDia window = new GenerarReportePedidosDia();
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
	public GenerarReportePedidosDia() {
		
		try 
	    { 
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	    } 
	    catch(Exception e){ 
	    }
		
		initialize();
		
		DataZonas dz= new DataZonas();

		dz.cargarTablaZonas(tblZonas);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGenerarReporte = new JFrame();
		frmGenerarReporte.setTitle("Reporte de pedidos");
		frmGenerarReporte.setResizable(false);
		frmGenerarReporte.setBounds(100, 100, 160, 374);
		frmGenerarReporte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton btnGenerarReporte = new JButton("Generar");
		
		btnGenerarReporte.setBounds(36, 277, 80, 23);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 11, 33, 14);
		
		JDateChooser dateFecha = new JDateChooser();
		dateFecha.setBounds(10, 36, 87, 20);
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				java.util.Date fecha = dateFecha.getDate();
				java.util.ArrayList<String> zonas = new ArrayList<String>();
				String zonasString = "";				
				
				zonas = getSeleccionados(tblZonas);
				
				if (fecha != null && !(zonas.isEmpty())) {
					for (String s : zonas)
					{
					    zonasString += s.substring(5) + ", ";
					}
					zonasString = zonasString.substring(0, zonasString.length()-2);
					ReportePedidosGen rvg = new ReportePedidosGen();
					rvg.generate(fecha, zonas, zonasString);
				}
				else {
					if (fecha == null)
						JOptionPane.showMessageDialog(null, "Seleccione una fecha.");
					if (zonas.isEmpty())
						JOptionPane.showMessageDialog(null, "Seleccione al menos una zona.");
					}
				
			}
		});
		
		JLabel lblZonas = new JLabel("Zonas:");
		lblZonas.setBounds(10, 67, 61, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 92, 134, 106);
		
		tblZonas = new JTable();
		scrollPane.setViewportView(tblZonas);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGenerarReporte.setVisible(false);
			}
		});
		btnVolver.setBounds(36, 311, 80, 23);
		
		JButton btnDesmarcarTodos = new JButton("Desmarcar todos");
		btnDesmarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desmarcarTodo(tblZonas);
			}
		});
		btnDesmarcarTodos.setBounds(10, 243, 134, 23);
		
		JButton btnMarcarTodos = new JButton("Marcar todos");
		btnMarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				marcarTodo(tblZonas);
			}
		});
		btnMarcarTodos.setBounds(10, 209, 134, 23);
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
}