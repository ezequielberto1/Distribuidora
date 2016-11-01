package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.DataMarcas;
import data.DataVendedores;
import data.DataVentas;

public class FiltrarListaVentas {

	private JFrame frmFiltrarListaVentas;
	private JTable tblMarcas;
	private JTable tblVendedores;
	private DataVendedores dataVendedores;
	private DataMarcas dataMarcas;
	private ArrayList<String> marcas = new ArrayList<String>();
	private ArrayList<String> vendedores = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FiltrarListaVentas window = new FiltrarListaVentas();
					window.frmFiltrarListaVentas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FiltrarListaVentas() {
		initialize();
		
		dataVendedores= new DataVendedores();

		dataVendedores.cargarTablaVendedores(tblVendedores);
		
		dataMarcas= new DataMarcas();
		
		dataMarcas.cargarTablaMarcas(tblMarcas);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFiltrarListaVentas = new JFrame();
		frmFiltrarListaVentas.setBounds(100, 100, 202, 407);
		frmFiltrarListaVentas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFiltrarListaVentas.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmFiltrarListaVentas.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Por marcas", null, panel, null);
		
		JButton btnAplicarFiltrosM = new JButton("Aplicar y cerrar");
		btnAplicarFiltrosM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				

				if (getSeleccionados(tblMarcas).size()==0)
					JOptionPane.showMessageDialog(null, "Seleccione al menos una marca.");
				else 
					if (getSeleccionados(tblVendedores).size()==0)
						JOptionPane.showMessageDialog(null, "Seleccione al menos un vendedor.");
					else {
						marcas = getSeleccionados(tblMarcas);
						vendedores = getSeleccionados(tblVendedores);
						frmFiltrarListaVentas.setVisible(false);
					}
			}
		});
		
		JButton btnDesmarcarTodasM = new JButton("Desmarcar todas");
		
		JButton btnMarcarTodasM = new JButton("Marcar todas");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		
		tblMarcas = new JTable();
		scrollPane.setViewportView(tblMarcas);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(12))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(btnMarcarTodasM, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(22))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(btnDesmarcarTodasM, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(22))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(38)
					.addComponent(btnAplicarFiltrosM, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(38))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnMarcarTodasM)
					.addGap(6)
					.addComponent(btnDesmarcarTodasM)
					.addGap(6)
					.addComponent(btnAplicarFiltrosM)
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Por vendedores", null, panel_1, null);
		
		JButton btnAplicarFiltrosV = new JButton("Aplicar y cerrar");
		btnAplicarFiltrosV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				if (getSeleccionados(tblMarcas).size()==0)
					JOptionPane.showMessageDialog(null, "Seleccione al menos una marca.");
				else 
					if (getSeleccionados(tblVendedores).size()==0)
						JOptionPane.showMessageDialog(null, "Seleccione al menos un vendedor.");
					else 
						marcas = getSeleccionados(tblMarcas);
						vendedores = getSeleccionados(tblVendedores);
			}
		});
		
		JButton btnDesmarcarTodasV = new JButton("Desmarcar todas");
		
		JButton btnMarcarTodasV = new JButton("Marcar todas");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		
		tblVendedores = new JTable();
		scrollPane_1.setViewportView(tblVendedores);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
					.addGap(12))
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addComponent(btnMarcarTodasV, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(22))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addComponent(btnDesmarcarTodasV, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(22))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(38)
					.addComponent(btnAplicarFiltrosV, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(38))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnMarcarTodasV)
					.addGap(6)
					.addComponent(btnDesmarcarTodasV)
					.addGap(6)
					.addComponent(btnAplicarFiltrosV)
					.addGap(10))
		);
		panel_1.setLayout(gl_panel_1);
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
	
	public void cargarSeleccionados (JTable tabla, ArrayList<String> items) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			if (tabla.getModel().getValueAt(i, 0).toString()==items.get(i).toString()) 
				tabla.getModel().setValueAt(Boolean.TRUE, i, 1);
			else
				tabla.getModel().setValueAt(Boolean.FALSE, i, 1);
		}	
	};
	
	public ArrayList<String> getMarcas() {
		return marcas;
	}
	
	public void setMarcas(ArrayList<String> m) {
		marcas = m;
	}
	
	public ArrayList<String> getVendedores() {
		return vendedores;
	}
	
	public void setVendedores(ArrayList<String> v) {
		marcas = v;
	}
	
	public void runFiltro() {
		frmFiltrarListaVentas.setVisible(true);
		
			
		
		
	}
}
