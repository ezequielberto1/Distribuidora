package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

import data.DataArticulos;
import data.DataMarcas;
import data.DataVentas;
import data.DataZonas;
import entidades.Articulo_Venta;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarArticulo {

	private JFrame frame;
	private JTable tblArticulos;
	private JComboBox cmbMarcas;
	private String rubro;
	private EditarArticulosVenta eav;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscarArticulo window = new BuscarArticulo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BuscarArticulo() {
		initialize();
		cargarMarcas();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 753, 374);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 40, 737, 251);
		panel.add(scrollPane);
		
		tblArticulos = new JTable();
		scrollPane.setViewportView(tblArticulos);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarArticulo();
			}
		});
		btnAgregar.setBounds(479, 302, 119, 23);
		panel.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Volver");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show(false);
			}
		});
		btnCancelar.setBounds(608, 302, 119, 23);
		panel.add(btnCancelar);
		
		cmbMarcas = new JComboBox();
		cmbMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rubro = (String)cmbMarcas.getSelectedItem();
				cargarTablaArticulos();
			}
		});
		cmbMarcas.setBounds(66, 12, 315, 20);
		panel.add(cmbMarcas);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(10, 15, 46, 14);
		panel.add(lblMarca);
		
	}
	
	public void cargarMarcas(){
		DataMarcas dataMarcas = new DataMarcas();
		Vector<Object> marcas = dataMarcas.getMarcas();
		for (int i = 0; i < marcas.size(); i++) {
			cmbMarcas.addItem(marcas.get(i).toString());
		}
	}
	
	public void cargarTablaArticulos(){
		DataArticulos da = new DataArticulos();
		da.cargarArticulos(tblArticulos, rubro);
	}
	
	public void show(boolean b){
		this.frame.setVisible(b);
	}

	public void setCaller(EditarArticulosVenta ea){
		eav = ea;
	}

	public EditarArticulosVenta getCaller(){
		return eav;
	}
	
	public Articulo_Venta getSeleccionado(){
		Articulo_Venta art = new Articulo_Venta();

		art.setCodigo((Integer)(tblArticulos.getValueAt(tblArticulos.getSelectedRow(), 0)));
		art.setNombre((String)(tblArticulos.getValueAt(tblArticulos.getSelectedRow(), 1)));
		art.setCantidad(0);
		art.setCosto(Float.parseFloat((tblArticulos.getValueAt(tblArticulos.getSelectedRow(), 2)).toString()));
		art.setPrecio(Float.parseFloat((tblArticulos.getValueAt(tblArticulos.getSelectedRow(), 3).toString())));
		art.setSubtotalcosto(art.getCosto()*art.getCantidad());
		art.setSubtotalventa(art.getPrecio()*art.getCantidad());
		
		return art;
	}
	
	public void agregarArticulo(){
		Articulo_Venta art = getSeleccionado();
		eav.mapearAFormulario(art);
		this.show(false);
	}
}
