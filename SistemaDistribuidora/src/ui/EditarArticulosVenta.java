package ui;

import java.awt.EventQueue;
import java.awt.Image;

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
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
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

import utils.NonEditableTableModel;
import utils.NumberRenderer;


public class EditarArticulosVenta {

	private JFrame frmEditarArticulos;
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
	private ArrayList<Articulo_Venta> articulos_venta = new ArrayList<Articulo_Venta>();
	private ArrayList<Articulo_Venta> av_original = new ArrayList<Articulo_Venta>();

	private float cantOriginal;
	private JButton btnBorrarCambios;
	private boolean guardado = false;
	private boolean modificado = false;
	private boolean modificadoBD;
	private int filaSeleccionada;
	private boolean automatico = true;
	private JButton btnModificar;
	private BuscarArticulo ba;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarArticulosVenta window = new EditarArticulosVenta();
					window.frmEditarArticulos.setVisible(true);
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
		frmEditarArticulos = new JFrame();
		frmEditarArticulos.setTitle("Editar articulos");
		frmEditarArticulos.setBounds(100, 100, 760, 491);
		frmEditarArticulos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Image logo = (new ImageIcon(this.getClass().getResource("/Logo INT.png")).getImage());
		frmEditarArticulos.setIconImage(logo);

		JPanel panel = new JPanel();
		frmEditarArticulos.getContentPane().add(panel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos art\u00EDculo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.inactiveCaption);

		JButton button_5 = new JButton("Ordenar...");

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		txtCantidad = new JTextField();

		txtCantidad.setText("");
		txtCantidad.setColumns(10);

		txtCostoU = new JTextField();
		txtCostoU.setEditable(false);
		txtCostoU.setText("");
		txtCostoU.setColumns(10);

		JButton btnBuscar = new JButton("Buscar articulo...");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarArticulo();
			}
		});

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

		buttonGroup.add(btnEliminar);

		btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);

		buttonGroup.add(btnModificar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});

		JLabel label = new JLabel("");

		btnBorrarCambios = new JButton("Borrar cambios");

		btnBorrarCambios.setEnabled(false);

		JButton btnGuardar = new JButton("Guardar");

		btnGuardar.setEnabled(false);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(392)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
						.addGap(113)
						.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(button_5)
								.addContainerGap(649, Short.MAX_VALUE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
										.addContainerGap()
										.addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
										.addGap(14)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
										.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnBorrarCambios)
										.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(4)
						.addComponent(button_5)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAgregar)
								.addComponent(btnEliminar)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnModificar)
										.addComponent(btnBorrarCambios)
										.addComponent(btnGuardar)))
										.addGap(11)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnVolver))
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
					if((estaCargado(Integer.parseInt(txtCodigo.getText()))==true) && (!(txtCantidad.getText().equals(Float.toString(cantOriginal)))))
						btnModificar.setEnabled(true);
					else
						btnModificar.setEnabled(false);
			}
			public void insertUpdate(DocumentEvent e) {
				if((estaCargado(Integer.parseInt(txtCodigo.getText()))==true) && (!(txtCantidad.getText().equals(Float.toString(cantOriginal)))))
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
					btnBorrarCambios.setEnabled(modificado);
					if(cambiosNoGuardados() == true)
						btnGuardar.setEnabled(true);
					else
						btnGuardar.setEnabled(false);
				}
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarArticulo();
				btnEliminar.setEnabled(false);
				btnBorrarCambios.setEnabled(modificado);
				if(cambiosNoGuardados() == true)
					btnGuardar.setEnabled(true);
				else
					btnGuardar.setEnabled(false);			
			}
		});

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean exito = agregarArticulo();

				if(exito){
					btnBorrarCambios.setEnabled(modificado);
					if(cambiosNoGuardados() == true)
						btnGuardar.setEnabled(true);
					else
						btnGuardar.setEnabled(false);				
				}
			}
		});

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
				JOptionPane.showMessageDialog(frmEditarArticulos, "Cambios guardados.");
				btnGuardar.setEnabled(false);
			}
		});

		btnBorrarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frmEditarArticulos, "Se restaurarán los artículos originales. ¿Continuar?")==JOptionPane.YES_OPTION){
					changeSelected = false;
					borrarCambios();
					JOptionPane.showMessageDialog(frmEditarArticulos, "Artículos originales restaurados.");
					btnBorrarCambios.setEnabled(false);
					if(cambiosNoGuardados() == true)
						btnGuardar.setEnabled(true);
					else
						btnGuardar.setEnabled(false);
					changeSelected = true;
				}
			}
		});
	}

	public void setNroVenta(int nro){
		nro_venta = nro;
	}

	public void show(boolean b){
		this.frmEditarArticulos.setVisible(b);
	}

	public void setCaller(ABMVentas abm){
		abmv = abm;
	}

	public ABMVentas getCaller(){
		return abmv;
	}

	public void volver(){
		if(cambiosNoGuardados() == true){
			if(JOptionPane.showConfirmDialog(frmEditarArticulos, "Los cambios no fueron guardados. Si vuelve, se cancelarán. ¿Volver de todos modos?")==JOptionPane.YES_OPTION){
				changeSelected=false;
				volverSinGuardar();
				changeSelected = true;
				show(false);
			}
		}
		else
			show(false);
	}

	public void guardar(){
		copiarArrayList(av_original, articulos_venta);
		abmv.setArticulosVenta(articulos_venta);
		abmv.setTablaArticulosVenta(tblArticulosVenta);
		if (modificado)
			abmv.setModificado(true);
		else
			abmv.setModificado(false);
		guardado = true;
	}

	public void volverSinGuardar(){
		copiarArrayList(articulos_venta, av_original);
		cargarTabla();
		modificado = fueModificado();
		abmv.setArticulosVenta(articulos_venta);
		abmv.setTablaArticulosVenta(tblArticulosVenta);
		abmv.setModificado(modificado);

	}
	public void borrarCambios(){
		DataVentas dv = new DataVentas();
		dv.cargarDetalleVenta(tblArticulosVenta, nro_venta);
		copiarArrayList(articulos_venta, dv.getArticulosVenta(nro_venta));
		modificado = fueModificado();
	}

	public void seleccionarArticulo(){
		if(changeSelected){
			Articulo_Venta av = getSeleccionado();
			mapearAFormulario(av);
		}
	}

	public Articulo_Venta getSeleccionado(){
		Articulo_Venta av = null;
		if (automatico==true){
			if(tblArticulosVenta.getSelectedRow()>=0)
				filaSeleccionada = tblArticulosVenta.getSelectedRow();
		}

		int cod_art = (Integer)(tblArticulosVenta.getValueAt(filaSeleccionada, 0));
		for (int i = 0; i < articulos_venta.size(); i++) {
			if(articulos_venta.get(i).getCodigo()==cod_art)
				av = articulos_venta.get(i);
		}
		return av;
	}

	public Articulo_Venta mapearDeFormulario(){
		Articulo_Venta art = new Articulo_Venta();

		art.setCodigo(Integer.parseInt(txtCodigo.getText()));
		art.setNombre(txtNombre.getText());
		art.setCantidad(Float.parseFloat(txtCantidad.getText()));
		art.setCosto(Float.parseFloat(txtCostoU.getText()));
		art.setPrecio(Float.parseFloat(txtPrecioU.getText()));
		art.setSubtotalcosto(art.getCosto()*art.getCantidad());
		art.setSubtotalventa(art.getPrecio()*art.getCantidad());

		return art;
	}

	public void mapearAFormulario(Articulo_Venta av){
		txtCodigo.setText(Integer.toString(av.getCodigo()));
		txtNombre.setText(av.getNombre());
		txtCantidad.setText(Float.toString(av.getCantidad()));
		cantOriginal = av.getCantidad();
		txtCostoU.setText(Float.toString(av.getCosto()));
		txtPrecioU.setText(Float.toString(av.getPrecio()));
	}

	public boolean agregarArticulo(){
		boolean exito = false;

		if(!(txtCodigo.getText().matches(""))){
			if(!(txtNombre.getText().matches(""))){
				if(esNumero(txtCantidad.getText())){
					int cod = Integer.parseInt(txtCodigo.getText());
					float cant = Float.parseFloat(txtCantidad.getText());
					if(estaCargado(cod)){
						JOptionPane.showMessageDialog(null, "El artículo ya está cargado. La cantidad ingresada se sumará a la ya cargada.");
						for (int i = 0; i < articulos_venta.size(); i++) {
							if(articulos_venta.get(i).getCodigo()==cod){
								float cantidadActual = articulos_venta.get(i).getCantidad();
								articulos_venta.get(i).setCantidad(cantidadActual + cant);
								articulos_venta.get(i).setSubtotalcosto((cantidadActual + cant)*articulos_venta.get(i).getCosto());
								articulos_venta.get(i).setSubtotalventa((cantidadActual + cant)*articulos_venta.get(i).getPrecio());
								articulos_venta.get(i).setNombre(txtNombre.getText());
								cargarTabla();
								exito = true;
								modificado = fueModificado();
								break;
							}
						}
					}
					else{
						Articulo_Venta av = mapearDeFormulario();
						articulos_venta.add(av);
						cargarTabla();
						JOptionPane.showMessageDialog(null, "Artículo agregado con éxito.");
						btnModificar.setEnabled(false);
						exito = true;
						modificado = fueModificado();
					}
				}
				else
					JOptionPane.showMessageDialog(frmEditarArticulos, "Cantidad inválida.");
			}
			else
				JOptionPane.showMessageDialog(frmEditarArticulos, "Escriba un nombre para el artículo.");
		}
		else
			JOptionPane.showMessageDialog(frmEditarArticulos, "Seleccione un artículo.");

		return exito;
	}

	public boolean modificarArticulo(){
		boolean exito = false;

		if(!(txtNombre.getText().matches(""))){
			if(esNumero(txtCantidad.getText())){
				int cod = Integer.parseInt(txtCodigo.getText());
				float cant = Float.parseFloat(txtCantidad.getText());
				for (int i = 0; i < articulos_venta.size(); i++) {
					if(articulos_venta.get(i).getCodigo()==cod){
						articulos_venta.get(i).setCantidad(cant);
						articulos_venta.get(i).setSubtotalcosto(cant*articulos_venta.get(i).getCosto());
						articulos_venta.get(i).setSubtotalventa(cant*articulos_venta.get(i).getPrecio());
						articulos_venta.get(i).setNombre(txtNombre.getText());
						break;
					}
				}
				cargarTabla();
				JOptionPane.showMessageDialog(null, "Modificación exitosa.");
				exito = true;
				modificado = fueModificado();
			}
			else
				JOptionPane.showMessageDialog(null, "Cantidad inválida.");
		}
		else
			JOptionPane.showMessageDialog(null, "Escriba un nombre para el artículo.");
		automatico = true;
		
		return exito;
	}

	public void eliminarArticulo(){
		filaSeleccionada = tblArticulosVenta.getSelectedRow();
		if(filaSeleccionada>=0){
			automatico = false;
	
			int cod_art = (Integer)(tblArticulosVenta.getValueAt(filaSeleccionada, 0));
			for (int i = 0; i < articulos_venta.size(); i++) {
				if(articulos_venta.get(i).getCodigo()==cod_art)
					articulos_venta.remove(i);
			};
			modificado = fueModificado();
			cargarTabla();
			JOptionPane.showMessageDialog(null, "Eliminación exitosa.");
			automatico = true;
		}
		else
			JOptionPane.showMessageDialog(frmEditarArticulos, "Seleccione una fila.");
	}

	public void actualizarTabla(NonEditableTableModel model){
		tblArticulosVenta.setModel(model);
		TableColumnModel m = tblArticulosVenta.getColumnModel();
		m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());
	}

	public void setTablaArticulosVenta(JTable tabla){
		NonEditableTableModel tm = (NonEditableTableModel)tabla.getModel();
		tblArticulosVenta.setModel(tm);		
		TableColumnModel m = tblArticulosVenta.getColumnModel();
		m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());
	}

	public boolean estaCargado(int cod){
		boolean b = false;
		for (int i = 0; i < articulos_venta.size(); i++) {
			if(articulos_venta.get(i).getCodigo()==cod){
				b=true;
			}
		}
		return b;
	}

	public boolean esNumero(String s){
		boolean b=false;
		if (s.matches("[0-9]*\\.?[0-9]+"))
			b=true;
		return b;			
	}

	public void setArticulosVenta(ArrayList<Articulo_Venta> av){
		copiarArrayList(articulos_venta, av);
		copiarArrayList(av_original, av);
	}

	public void buscarArticulo(){
		if(ba==null){
			ba = new BuscarArticulo();
		}
		ba.setCaller(this);
		ba.show(true);
	}

	public void enableBorrarCambios(boolean b){
		btnBorrarCambios.setEnabled(b);
	}

	public void cargarTabla(){
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		NonEditableTableModel model = (NonEditableTableModel)tblArticulosVenta.getModel();
		Vector<String> columnNames = new Vector<String>();
		automatico = false;

		for (int i = 0; i < articulos_venta.size(); i++) {
			Vector<Object> v = new Vector<Object>();
			Articulo_Venta av = articulos_venta.get(i);
			v.add(av.getCodigo());
			v.add(av.getNombre());
			v.add(av.getCosto());
			v.add(av.getPrecio());
			v.add(av.getCantidad());
			v.add(av.getSubtotalcosto());
			v.add(av.getSubtotalventa());
			v.add(av.getSubtotalventa()-av.getSubtotalcosto());
			data.add(v);
		}

		for (int i = 0; i < model.getColumnCount(); i++) {
			columnNames.add(model.getColumnName(i));
		}
		changeSelected = false;

		model.setDataVector(data, columnNames);
		tblArticulosVenta.setModel(model);

		TableColumnModel m = tblArticulosVenta.getColumnModel();
		m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());		

		int n = tblArticulosVenta.getRowCount();
		if(n>=0){
			changeSelected = true;
		}
		automatico = true;
	}

	public boolean fueModificado(){
		boolean modificado = false;
		boolean encontrado;
		DataVentas dv = new DataVentas();

		ArrayList<Articulo_Venta> original = dv.getArticulosVenta(nro_venta);
		if(original.size()!=articulos_venta.size())
			modificado = true;
		else {
			for (int i = 0; i < articulos_venta.size(); i++) {
				encontrado = false;
				for (int j = 0; j < original.size(); j++) {
					if(articulos_venta.get(i).getCodigo()==original.get(j).getCodigo()){
						encontrado = true;
						if(articulos_venta.get(i).getCantidad()!=original.get(j).getCantidad())
							modificado = true;
						if(!(articulos_venta.get(i).getNombre().matches(original.get(j).getNombre())))
							modificado = true;
					}
				}
				if(encontrado == false){
					modificado = true;
					break;
				}
			}
		}
		return modificado;
	}

	public boolean cambiosNoGuardados(){
		boolean cambios = false;
		boolean encontrado;

		if(av_original.size()!=articulos_venta.size())
			cambios = true;
		else {
			for (int i = 0; i < av_original.size(); i++) {
				encontrado = false;
				for (int j = 0; j < articulos_venta.size(); j++) {
					if(articulos_venta.get(j).getCodigo()==av_original.get(i).getCodigo()){
						encontrado = true;
						if(articulos_venta.get(j).getCantidad()!=av_original.get(i).getCantidad()){
							cambios = true;
							break;	
						}
						if(!(articulos_venta.get(i).getNombre().matches(av_original.get(j).getNombre())))
							modificado = true;
					}
				}
				if(encontrado == false || cambios == true){
					cambios = true;
					break;
				}
			}
		}
		return cambios;
	}

	public void copiarArrayList(ArrayList<Articulo_Venta> copia, ArrayList<Articulo_Venta> av){
		copia.clear();
		for (int i = 0; i < av.size(); i++) {
			copia.add(new Articulo_Venta(av.get(i)));
		}
	}
	
	public void setNullBuscarArticulo(){
		ba=null;
	}
}