package ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

import data.DataVendedores;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.commons.lang.StringUtils;
//import org.eclipse.birt.report.engine.api.EngineException;

//import reports.ReporteVentasGen;




import reports.ReporteVentasGen;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerarReporteVentas {

	private JFrame frmGenerarReporte;
	private JTable tblVendedores;
	private DataVendedores dataVendedores;
	




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarReporteVentas window = new GenerarReporteVentas();
					window.frmGenerarReporte.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GenerarReporteVentas() {
		initialize();
		
		dataVendedores= new DataVendedores();

		dataVendedores.cargarTablaVendedores(tblVendedores);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGenerarReporte = new JFrame();
		frmGenerarReporte.setTitle("Generar reporte de ventas");
		frmGenerarReporte.setResizable(false);
		frmGenerarReporte.setBounds(100, 100, 285, 373);
		frmGenerarReporte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		
		
		btnGenerarReporte.setBounds(158, 277, 111, 23);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(10, 11, 33, 14);
		
		JDateChooser dateFecha = new JDateChooser();
		dateFecha.setBounds(10, 36, 87, 20);
		
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Date fecha = dateFecha.getDate();
				
				ArrayList<String> vendedores = new ArrayList<String>();
				vendedores = getSeleccionados(tblVendedores);

				ArrayList<Integer> codVendedores = new ArrayList<Integer>();
				codVendedores = new DataVendedores().getCodVendedores(vendedores);
				System.out.println(codVendedores);
				if (fecha != null && codVendedores.size()!=0) {
					ReporteVentasGen rvg = new ReporteVentasGen();
					rvg.generate(fecha, codVendedores);
				}
				else {
					if (fecha == null) 
						JOptionPane.showMessageDialog(null, "Seleccione una fecha.");
					if (codVendedores.size()==0)
						JOptionPane.showMessageDialog(null, "Seleccione al menos un vendedor.");
				}
			}
		});
		
		JLabel lblVendedores = new JLabel("Vendedores:");
		lblVendedores.setBounds(10, 69, 61, 14);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 259, 106);
		
		tblVendedores = new JTable();
		scrollPane.setViewportView(tblVendedores);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmGenerarReporte.setVisible(false);
			}
		});
		btnCerrar.setBounds(158, 311, 111, 23);
		
		JButton btnDesmarcarTodos = new JButton("Desmarcar todos");
		btnDesmarcarTodos.setBounds(151, 211, 118, 23);
		
		JButton btnMarcarTodos = new JButton("Marcar todos");
		btnMarcarTodos.setBounds(10, 211, 118, 23);
		frmGenerarReporte.getContentPane().setLayout(null);
		frmGenerarReporte.getContentPane().add(btnGenerarReporte);
		frmGenerarReporte.getContentPane().add(lblFecha);
		frmGenerarReporte.getContentPane().add(dateFecha);
		frmGenerarReporte.getContentPane().add(lblVendedores);
		frmGenerarReporte.getContentPane().add(scrollPane);
		frmGenerarReporte.getContentPane().add(btnCerrar);
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
}
