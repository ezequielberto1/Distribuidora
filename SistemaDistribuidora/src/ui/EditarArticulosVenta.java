package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;

import java.awt.SystemColor;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.ButtonGroup;

import data.DataVentas;
import entidades.Articulo_Venta;
import entidades.Venta;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ListSelectionModel;

import utils.NumberRenderer;

import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class EditarArticulosVenta {

	private JFrame frame;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtCantidad;
	private JTextField txtCostoU;
	private JTextField txtPrecioU;
	private JTable tblArticulosVenta;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private int nro_venta;
	private ABMVentas abmv;
	private boolean changeSelected = true;
	private ArrayList<Articulo_Venta> articulos_agregar = new ArrayList<Articulo_Venta>();
	private ArrayList<Articulo_Venta> articulos_modificar = new ArrayList<Articulo_Venta>();
	private ArrayList<Integer> articulos_eliminar = new ArrayList<Integer>();
	private int cantOriginal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarArticulosVenta window = new EditarArticulosVenta();
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
	public EditarArticulosVenta() {
		try 
		{ 
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
		} 
		catch(Exception e){ 
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 760, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos art\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.inactiveCaption);

		JButton button_5 = new JButton("Ordenar...");

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);

		txtCantidad = new JTextField();

		txtCantidad.setText("");
		txtCantidad.setColumns(10);

		txtCostoU = new JTextField();
		txtCostoU.setEditable(false);
		txtCostoU.setText("");
		txtCostoU.setColumns(10);

		JButton btnBuscar = new JButton("Buscar articulo...");

		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblCostoU = new JLabel("Costo U");
		lblCostoU.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblPrecioU = new JLabel("Precio U");
		lblPrecioU.setHorizontalAlignment(SwingConstants.CENTER);

		txtPrecioU = new JTextField();
		txtPrecioU.setEditable(false);
		txtPrecioU.setColumns(10);

		JButton btnAgregar = new JButton("Agregar");
		buttonGroup.add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarArticulo();
				btnEliminar.setEnabled(false);
			}
		});
		buttonGroup.add(btnEliminar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		
		buttonGroup.add(btnModificar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				show(false);
			}
		});

		JLabel label = new JLabel("");

		JButton btnGuardarCambios = new JButton("Guardar");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarCambios();
				btnGuardarCambios.setEnabled(false);
			}
		});
		btnGuardarCambios.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addGap(14)
						.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnGuardarCambios, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(button_5)
								.addContainerGap(649, Short.MAX_VALUE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(4)
						.addComponent(button_5)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addGap(11)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAgregar)
								.addComponent(btnEliminar)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnModificar)
										.addComponent(btnVolver)
										.addComponent(btnGuardarCambios))
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
				);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(4)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
										.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(txtNombre, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
										.addGap(10)
										.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(txtCostoU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(txtPrecioU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
												.addComponent(lblCdigo, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
												.addGap(10)
												.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(lblCostoU, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
												.addGap(10)
												.addComponent(lblPrecioU, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
												.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
												.addGap(7))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(11)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCostoU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPrecioU, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(8)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCdigo)
										.addComponent(lblNombre)
										.addComponent(lblCantidad)
										.addComponent(lblCostoU)
										.addComponent(lblPrecioU))
										.addGap(11)
										.addComponent(btnBuscar))
				);
		panel_1.setLayout(gl_panel_1);

		tblArticulosVenta = new JTable();
		tblArticulosVenta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblArticulosVenta.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				seleccionarArticulo();
				if (btnEliminar.isEnabled()==false)
					btnEliminar.setEnabled(true);
			}
		});
		txtCantidad.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				btnModificar.setEnabled(true);
			}
			public void removeUpdate(DocumentEvent e) {
				if(txtCantidad.getText().isEmpty())
					btnModificar.setEnabled(false);
				else
					if(!(txtCantidad.getText().equals(Integer.toString(cantOriginal))))
						btnModificar.setEnabled(true);
					else
						btnModificar.setEnabled(false);
			}
			public void insertUpdate(DocumentEvent e) {
				if(!(txtCantidad.getText().equals(Integer.toString(cantOriginal))))
					btnModificar.setEnabled(true);
				else
					btnModificar.setEnabled(false);
			}
		});
		scrollPane.setViewportView(tblArticulosVenta);
		panel.setLayout(gl_panel);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean exito = modificarArticulo();
				if(exito){
					btnModificar.setEnabled(false);
					btnGuardarCambios.setEnabled(true);
				}
			}
		});
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exito = agregarArticulo();
				if(exito)
					btnGuardarCambios.setEnabled(true);
			}
		});
	}

	public void setNroVenta(int nro){
		nro_venta = nro;
	}

	public void show(boolean b){
		if (b){
			DataVentas dv = new DataVentas();
			dv.cargarDetalleVenta(tblArticulosVenta, nro_venta);
		}
		this.frame.setVisible(b);
	}

	public void setCaller(ABMVentas abm){
		abmv = abm;
	}

	public ABMVentas getCaller(){
		return abmv;
	}

	public void seleccionarArticulo(){
		if(changeSelected){
			Articulo_Venta av = getSeleccionado();
			mapearAFormulario(av);
		}
	}

	public Articulo_Venta getSeleccionado(){
		DataVentas dv = new DataVentas();
		Articulo_Venta av = null;
		boolean modificado = false;
		int cod_art;
		Vector<Object> v = new Vector<Object>();
		cod_art = (Integer)(tblArticulosVenta.getValueAt(tblArticulosVenta.getSelectedRow(), 0));
		for (int i = 0; i < articulos_modificar.size(); i++) {
			if(articulos_modificar.get(i).getCodigo()==cod_art){
				av = articulos_modificar.get(i);
				modificado = true;
				break;
			}
		}
		if (modificado==false){
			v = dv.getArticuloVenta(nro_venta, cod_art);
			av = new Articulo_Venta(v);
		}
		return av;

	}

	public Articulo_Venta mapearDeFormulario(){
		Articulo_Venta art = new Articulo_Venta();

		art.setCodigo(Integer.parseInt(txtCodigo.getText()));
		art.setNombre(txtNombre.getText());
		art.setCantidad(Integer.parseInt(txtCantidad.getText()));
		art.setCosto(Float.parseFloat(txtCostoU.getText()));
		art.setPrecio(Float.parseFloat(txtPrecioU.getText()));
		art.setSubtotalcosto(art.getCosto()*art.getCantidad());
		art.setSubtotalventa(art.getPrecio()*art.getCantidad());

		return art;
	}

	public void mapearAFormulario(Articulo_Venta av){
		txtCodigo.setText(Integer.toString(av.getCodigo()));
		txtNombre.setText(av.getNombre());
		txtCantidad.setText(Integer.toString(av.getCantidad()));
		cantOriginal = av.getCantidad();
		txtCostoU.setText(Float.toString(av.getCosto()));
		txtPrecioU.setText(Float.toString(av.getPrecio()));

	}

	public boolean agregarArticulo(){
		boolean exito = false;
		if(!(txtCodigo.getText().matches(""))){
			if(esNumero(txtCantidad.getText())){
				int cod = Integer.parseInt(txtCodigo.getText());
				int fila = 0;
				if(estaCargado(cod)){
					for (int i = 0; i < tblArticulosVenta.getRowCount(); i++) {
						if((Integer)(tblArticulosVenta.getValueAt(i, 0))==cod){
							fila = i;
							JOptionPane.showMessageDialog(null, "El artículo ya está cargado. La cantidad ingresada se sumará a la ya cargada.");
							break;
						}
					}
					int cantidadActual = Integer.parseInt(tblArticulosVenta.getModel().getValueAt(fila, 4).toString());
					Vector<Object> v = new Vector<Object>();
					Articulo_Venta av = mapearDeFormulario();
					av.setCantidad(av.getCantidad()+cantidadActual);
					av.setSubtotalcosto(av.getCantidad()*av.getCosto());
					av.setSubtotalventa(av.getCantidad()*av.getPrecio());
					articulos_modificar.add(av);
					v.add(av.getCodigo());
					v.add(av.getNombre());
					v.add(av.getCosto());
					v.add(av.getPrecio());
					v.add(av.getCantidad());
					v.add(av.getSubtotalcosto());
					v.add(av.getSubtotalventa());
					v.add(av.getSubtotalventa()-av.getSubtotalcosto());
					modificarFila(v, fila);
					exito = true;
				}
				else {
					if(esNumero(txtCantidad.getText())){
						Articulo_Venta av = mapearDeFormulario();
						articulos_agregar.add(av);
						Vector<Object> v = new Vector<Object>();
						v.add(av.getCodigo());
						v.add(av.getNombre());
						v.add(av.getCosto());
						v.add(av.getPrecio());
						v.add(av.getCantidad());
						v.add(av.getSubtotalcosto());
						v.add(av.getSubtotalventa());
						v.add(av.getSubtotalventa()-av.getSubtotalcosto());
						addFila(v);
						JOptionPane.showMessageDialog(null, "Artículo agregado con éxito.");
						exito = true;
					}
					else
						JOptionPane.showMessageDialog(null, "Cantidad inválida.");
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Cantidad inválida.");
		}

		else{
			JOptionPane.showMessageDialog(null, "Seleccione un artículo.");
		}
		return exito;
	}

	public boolean modificarArticulo(){
		boolean exito = false;
		if(esNumero(txtCantidad.getText())) {
			Vector<Object> v = new Vector<Object>();
			Articulo_Venta av = mapearDeFormulario();
			av.setSubtotalcosto(av.getCantidad()*av.getCosto());
			av.setSubtotalventa(av.getCantidad()*av.getPrecio());
			if(fueModificado(av)==false)
				articulos_modificar.add(av);
			else{
				for (int i = 0; i < articulos_modificar.size(); i++) {
					if(av.getCodigo()==articulos_modificar.get(i).getCodigo())
						articulos_modificar.get(i).setCantidad(av.getCantidad());
				}
			}
			v.add(av.getCodigo());
			v.add(av.getNombre());
			v.add(av.getCosto());
			v.add(av.getPrecio());
			v.add(av.getCantidad());
			v.add(av.getSubtotalcosto());
			v.add(av.getSubtotalventa());
			modificarFila(v, tblArticulosVenta.getSelectedRow());
			JOptionPane.showMessageDialog(null, "Modificación exitosa.");
			exito = true;
		}
		else
			JOptionPane.showMessageDialog(null, "Cantidad inválida.");
		return exito;
	}

	public void eliminarArticulo(){
		int cod_art = (Integer)(tblArticulosVenta.getValueAt(tblArticulosVenta.getSelectedRow(), 0));
		articulos_eliminar.add(cod_art);
		//Eliminar de modificados
		for (int i = 0; i < articulos_modificar.size(); i++) {
			if(cod_art==articulos_modificar.get(i).getCodigo())
				articulos_modificar.remove(i);
			}
		//Eliminar de agregados
		for (int i = 0; i < articulos_agregar.size(); i++) {
			if(cod_art==articulos_agregar.get(i).getCodigo())
				articulos_agregar.remove(i);
			}
		removeFila();
		JOptionPane.showMessageDialog(null, "Eliminación exitosa.");
	}

	public void addFila(Vector<Object> v){
		DefaultTableModel model = (DefaultTableModel)tblArticulosVenta.getModel();
		model.addRow(v);
		actualizarTabla(model);
		int n = tblArticulosVenta.getRowCount();
		tblArticulosVenta.setRowSelectionInterval(n, n);
	}

	public void modificarFila(Vector<Object> v, int n){
		DefaultTableModel model = (DefaultTableModel)tblArticulosVenta.getModel();
		Vector<Vector<Object>> data = model.getDataVector();
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> w = new Vector<Vector<Object>>();
		for (int i = 0; i < data.size(); i++) {
			if(i!=n)
				w.add(data.get(i));
			else
				w.add(v);
		}
		for (int i = 0; i < model.getColumnCount(); i++) {
			columnNames.add(model.getColumnName(i));
		}
		changeSelected = false;
		model.setDataVector(w, columnNames);
		actualizarTabla(model);
		changeSelected = true;
		tblArticulosVenta.setRowSelectionInterval(n, n);
	}

	public void removeFila(){
		DefaultTableModel model = (DefaultTableModel)tblArticulosVenta.getModel();
		changeSelected = false;
		model.removeRow(tblArticulosVenta.getSelectedRow());
		actualizarTabla(model);
		changeSelected = true;

	}

	public void actualizarTabla(DefaultTableModel model){
		tblArticulosVenta.setModel(model);
		TableColumnModel m = tblArticulosVenta.getColumnModel();
		m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());
	}

	public void guardarCambios(){
		if(!articulos_agregar.isEmpty())
			abmv.setArticulosAgregar(articulos_agregar);
		if(!articulos_modificar.isEmpty())
			abmv.setArticulosModificar(articulos_modificar);
		if(!articulos_eliminar.isEmpty())
			abmv.setArticulosEliminar(articulos_eliminar);
	}

	public JTable getTabla(){
		return tblArticulosVenta;
	}

	public boolean estaCargado(int cod){
		boolean b = false;
		for (int i = 0; i < tblArticulosVenta.getRowCount(); i++) {
			if((Integer)(tblArticulosVenta.getValueAt(i, 0))==cod){
				b=true;
			}
		}
		return b;
	}

	public boolean fueModificado(Articulo_Venta av){
		boolean b = false;
		for (int i = 0; i < articulos_modificar.size(); i++) {
			if(av.getCodigo()==articulos_modificar.get(i).getCodigo())
				b = true;
		}
		return b;
	}

	public boolean esNumero(String s){
		boolean b=false;
		if (s.matches("[0-9]+"))
			b=true;
		return b;			
	}
}
