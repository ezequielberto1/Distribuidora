package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import data.*;

import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JTabbedPane;

import negocio.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;

public class GestionVentas {

	private JFrame frmGestinDeVentas;
	private JTable tblListaVentas;
	private JTable tblVendedores;
	
	private DataVentas dataVentas;
	private DataVendedores dataVendedores;
	private DataMarcas dataMarcas;
	
	
	private TableRowSorter<TableModel> sorterLV;
	private JTable tblMarcas;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionVentas window = new GestionVentas();
					window.frmGestinDeVentas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GestionVentas() {
		//int columnIndexToSort;
		//List<RowSorter.SortKey> sortKeys = new Arraylist<>();
		
		initialize();
		
		dataVentas= new DataVentas();

		Date fd = new java.util.Date();
		Date fh = new java.util.Date();
		
		dataVendedores= new DataVendedores();

		dataVendedores.cargarTablaVendedores(tblVendedores);
		
		dataMarcas= new DataMarcas();
		
		dataMarcas.cargarTablaMarcas(tblMarcas);
		
		//dataVentas.cargarTablaVentas(tblListaVentas, fd, fh, getSeleccionados(tblMarcas), getSeleccionados(tblVendedores));
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestinDeVentas = new JFrame();
		frmGestinDeVentas.setTitle("Gesti\u00F3n de ventas");
		frmGestinDeVentas.setBounds(100, 100, 739, 416);
		frmGestinDeVentas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		frmGestinDeVentas.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnReporte = new JButton("Reporte...");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerarReporteVentas.main(null);
			}
		});
		
		JButton btnOrdenar = new JButton("Ordenar...");
		
		JButton btnAdministrar = new JButton("Administrar...");
		
		tblListaVentas = new JTable();
		tblListaVentas.setCellSelectionEnabled(true);
		tblListaVentas.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(tblListaVentas);
		
		JLabel label = new JLabel("Desde:");
		
		JDateChooser dateFechaDesde = new JDateChooser();
		java.util.Date fd = new java.util.Date();
		dateFechaDesde.setDate(fd);
		
		JLabel label_1 = new JLabel("hasta:");
		
		JDateChooser dateFechaHasta = new JDateChooser();
		java.util.Date fh = new java.util.Date();
		dateFechaHasta.setDate(fh);
		
		JButton btnAplicarFecha = new JButton("Aplicar");
		btnAplicarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date fechaDesde = dateFechaDesde.getDate();
				Date fechaHasta = dateFechaHasta.getDate();
				DataVentas dv = new DataVentas();
				//dv.cargarTablaVentas(tblListaVentas, fechaDesde, fechaHasta, getSeleccionados(tblMarcas), getSeleccionados(tblVendedores));
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(btnOrdenar)
					.addGap(238)
					.addComponent(btnAdministrar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnReporte, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dateFechaDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dateFechaHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAplicarFecha)
					.addContainerGap(201, Short.MAX_VALUE))
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(dateFechaHasta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAplicarFecha, 0, 0, Short.MAX_VALUE))
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateFechaDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOrdenar)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnReporte)
							.addComponent(btnAdministrar)))
					.addGap(10))
		);
		panel.setLayout(gl_panel);
		
		JTabbedPane tabFiltrar = new JTabbedPane(JTabbedPane.TOP);
		tabFiltrar.setBorder(new TitledBorder(null, "Filtrar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setLeftComponent(tabFiltrar);
		
		JPanel pnPorVendedor = new JPanel();
		tabFiltrar.addTab("Por vendedor", null, pnPorVendedor, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		
		tblVendedores = new JTable();
		tblVendedores.setBorder(null);
		tblVendedores.setShowGrid(false);
		tblVendedores.setBackground(SystemColor.menu);
		scrollPane_1.setViewportView(tblVendedores);
		
		JButton btnDesmarcarVendedores = new JButton("Desmarcar todas");
		
		JButton btnMarcarVendedores = new JButton("Marcar todas");
		
		JButton btnAplicarFiltros1 = new JButton("Aplicar filtros");
		btnAplicarFiltros1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> marcas = new ArrayList<String>();
				ArrayList<String> vendedores = new ArrayList<String>();
				DataVentas dv = new DataVentas();
				
				marcas = getSeleccionados(tblMarcas);
				vendedores = getSeleccionados (tblVendedores);
				
				if (marcas.size()==0)
					JOptionPane.showMessageDialog(null, "Seleccione al menos una marca.");
				else 
					if (vendedores.size()==0)
						JOptionPane.showMessageDialog(null, "Seleccione al menos un vendedor.");
					//else 
						//dv.cargarTablaVentas(tblListaVentas, fd, fh, marcas, vendedores);
			}
		});
		GroupLayout gl_pnPorVendedor = new GroupLayout(pnPorVendedor);
		gl_pnPorVendedor.setHorizontalGroup(
			gl_pnPorVendedor.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
				.addGroup(gl_pnPorVendedor.createSequentialGroup()
					.addGap(10)
					.addComponent(btnMarcarVendedores, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_pnPorVendedor.createSequentialGroup()
					.addGap(10)
					.addComponent(btnDesmarcarVendedores, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_pnPorVendedor.createSequentialGroup()
					.addGap(28)
					.addComponent(btnAplicarFiltros1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(26))
		);
		gl_pnPorVendedor.setVerticalGroup(
			gl_pnPorVendedor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnPorVendedor.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnMarcarVendedores)
					.addGap(6)
					.addComponent(btnDesmarcarVendedores)
					.addGap(6)
					.addComponent(btnAplicarFiltros1)
					.addGap(5))
		);
		pnPorVendedor.setLayout(gl_pnPorVendedor);
		
		JPanel pnPorMarca = new JPanel();
		tabFiltrar.addTab("Por marca", null, pnPorMarca, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportBorder(null);
		
		JButton btnMarcarMarcas = new JButton("Marcar todas");
		
		JButton btnDesmarcarMarcas = new JButton("Desmarcar todas");
		
		JButton btnAplicarFiltros2 = new JButton("Aplicar filtros");
		btnAplicarFiltros2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> marcas = new ArrayList<String>();
				ArrayList<String> vendedores = new ArrayList<String>();
				DataVentas dv = new DataVentas();
				
				marcas = getSeleccionados(tblMarcas);
				vendedores = getSeleccionados (tblVendedores);
				
				//dv.cargarTablaVentas(tblListaVentas, fd, fh, marcas, vendedores);
			}
		});
		
		tblMarcas = new JTable();
		tblMarcas.setShowGrid(false);
		tblMarcas.setBackground(SystemColor.menu);
		scrollPane_2.setViewportView(tblMarcas);
		GroupLayout gl_pnPorMarca = new GroupLayout(pnPorMarca);
		gl_pnPorMarca.setHorizontalGroup(
			gl_pnPorMarca.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
				.addGroup(gl_pnPorMarca.createSequentialGroup()
					.addGap(10)
					.addComponent(btnMarcarMarcas, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_pnPorMarca.createSequentialGroup()
					.addGap(10)
					.addComponent(btnDesmarcarMarcas, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(gl_pnPorMarca.createSequentialGroup()
					.addGap(28)
					.addComponent(btnAplicarFiltros2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(26))
		);
		gl_pnPorMarca.setVerticalGroup(
			gl_pnPorMarca.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnPorMarca.createSequentialGroup()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(btnMarcarMarcas)
					.addGap(6)
					.addComponent(btnDesmarcarMarcas)
					.addGap(6)
					.addComponent(btnAplicarFiltros2)
					.addGap(5))
		);
		pnPorMarca.setLayout(gl_pnPorMarca);
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
