package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

import data.DataMarcas;
import data.DataZonas;
import data.DataVentas;
import entidades.Articulo_Venta;
import entidades.Venta;

import javax.swing.UIManager;

import utils.FormatRenderer;
import utils.NumberRenderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;

public class ABMVentas {

	private JFrame frmGestionVentas;
	private JTextField txtNroVenta;
	private JTextField txtCliente;
	private JComboBox cmbZona;
	private JTable tblArticulosVenta;
	private JTable tblListaVentas;
	private DataVentas dataVentas;
	private ArrayList<String> zonas = new ArrayList<String>();
	private ArrayList<String> marcas = new ArrayList<String>();
	private JLabel label;
	private JDateChooser dateFechaDesde;
	private JLabel label_1;
	private JDateChooser dateFechaHasta;
	private JButton btnAplicarFecha;
	private JScrollPane scrListaClientes;
	private JPanel panel;
	private JButton button_5;
	private JButton button_6;
	private JButton btnFiltrar;
	private JButton btnGuardarCambios;
	private JLabel lblCodigo;
	private JLabel lblFecha;
	private JDateChooser dateFechaVenta;
	private JLabel lblCliente;
	private JLabel lblZona;
	private JScrollPane scrollPane;
	private JLabel lblCampoSlo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private DataZonas dataZonas;
	private ArrayList<Articulo_Venta> articulos_venta = new ArrayList<Articulo_Venta>();

	private int seleccionado;
	private int ventaActual;
	private boolean modificado = false;
	private String orden = "ventasfinal.[numventa] DESC";
	private String filtro = "";
	private boolean changeSelected = false;
	
	private EditarArticulosVenta eav;
	private FiltrarListaVentas flv;
	private OrdenarLista ord;
	private MenuPrincipal mp;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMVentas window = new ABMVentas();
					window.frmGestionVentas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ABMVentas() {
		try 
	    { 
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
	    } 
	    catch(Exception e){ 
	    }
		initialize();
		cargarZonas();
		cargarListaVentas();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionVentas = new JFrame();
		frmGestionVentas.setTitle("Gesti\u00F3n de ventas");
		frmGestionVentas.setBounds(100, 100, 752, 594);
		frmGestionVentas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Detalle venta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.inactiveCaption);
		
		lblZona = new JLabel("Zona");
		lblZona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZona.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblCodigo = new JLabel("N\u00BA Vta.(*)");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtNroVenta = new JTextField();
		txtNroVenta.setColumns(10);
		
		txtCliente = new JTextField();
		txtCliente.setColumns(10);
		
		cmbZona = new JComboBox();
		
		lblCampoSlo = new JLabel("(*) Campo s\u00F3lo para b\u00FAsqueda.");
		lblCampoSlo.setFont(new Font("Tahoma", Font.ITALIC, 8));
		
		scrListaClientes = new JScrollPane();
		
		btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarVenta();
			}
		});
		buttonGroup_1.add(btnGuardarCambios);
		
		button_5 = new JButton("Limpiar campos");
		
		label = new JLabel("Desde:");
		
		dateFechaDesde = new JDateChooser();
		java.util.Date fd = new java.util.Date();
		dateFechaDesde.setDate(fd);
		
		label_1 = new JLabel("hasta:");
		
		dateFechaHasta = new JDateChooser();
		java.util.Date fh = new java.util.Date();
		dateFechaHasta.setDate(fh);
		
		btnAplicarFecha = new JButton("Aplicar");
		btnAplicarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarListaVentas();
			}
		});
		
		tblListaVentas = new JTable();
		tblListaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrListaClientes.setViewportView(tblListaVentas);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		dateFechaVenta = new JDateChooser();
		
		scrollPane = new JScrollPane();
		
		tblArticulosVenta = new JTable();
		tblArticulosVenta.setFont(new Font("Tahoma", Font.PLAIN, 9));
		scrollPane.setViewportView(tblArticulosVenta);
		
		btnFiltrar = new JButton("Filtrar...");
		buttonGroup.add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				filtrarListaVentas();
			}
		});
		
		button_6 = new JButton("Ordenar...");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ordenar();
			}
		});
		buttonGroup.add(button_6);
		
		JButton button_2 = new JButton("Eliminar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarVenta();
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmGestionVentas.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrListaClientes, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
							.addGap(6))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button_6)
							.addGap(10)
							.addComponent(btnFiltrar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addGap(158)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(button_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnGuardarCambios, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(4)
					.addComponent(dateFechaDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(label_1)
					.addGap(4)
					.addComponent(dateFechaHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAplicarFecha)
					.addContainerGap(386, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateFechaDesde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateFechaHasta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAplicarFecha, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrListaClientes, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_5)
								.addComponent(btnGuardarCambios))))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button_6)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnFiltrar)
							.addComponent(button_2)
							.addComponent(btnVolver)))
					.addContainerGap())
		);
		
		JButton btnEditar = new JButton("Editar...");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editarArticulosVenta();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(0, 0, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addComponent(lblCodigo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtNroVenta, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(dateFechaVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(46)
							.addComponent(lblCliente, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(46)
							.addComponent(lblZona, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cmbZona, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblCampoSlo)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnEditar))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 358, GroupLayout.PREFERRED_SIZE))))
					.addGap(4))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCodigo))
						.addComponent(txtNroVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblFecha))
						.addComponent(dateFechaVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblCliente))
						.addComponent(txtCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblZona))
						.addComponent(cmbZona, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEditar)
						.addComponent(lblCampoSlo, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		frmGestionVentas.getContentPane().setLayout(groupLayout);
		
		tblListaVentas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	seleccionarVenta();
	        	btnEditar.setEnabled(true);
	        }
	    });

	}
	
	public void editarArticulosVenta(){
		if (eav==null)
			eav = new EditarArticulosVenta();
		DataVentas dv = new DataVentas();
		eav.setNroVenta(ventaActual);
		eav.setArticulosVenta(articulos_venta);
		eav.setTablaArticulosVenta(tblArticulosVenta);
		eav.cargarTabla();
		if(modificado)
			eav.enableBorrarCambios(true);
		eav.setCaller(this);
		eav.show(true);
	}
	
	public void cargarDetalleVenta(){
		DataVentas dv = new DataVentas();
    	int nro_venta;
    	if(tblListaVentas.getSelectedRow()>=0){
	        nro_venta = (Integer)(tblListaVentas.getValueAt(tblListaVentas.getSelectedRow(), 1));
	        ventaActual = nro_venta;
	        Venta v = dv.getVenta(nro_venta);
	        txtNroVenta.setText(Integer.toString(v.getNro()));
	        txtCliente.setText(v.getCliente());
	        dateFechaVenta.setDate(v.getFecha());
	        cmbZona.setSelectedItem(v.getZona());
	        dv.cargarDetalleVenta(tblArticulosVenta, v.getNro());
			copiarArrayList(articulos_venta, dv.getArticulosVenta(nro_venta));
    	}
	}
	
	
	public void setArticulosVenta(ArrayList<Articulo_Venta> av){
		copiarArrayList(articulos_venta, av);
	}
	
	public void seleccionarVenta() {
		if(changeSelected=true)
			if (modificado==false){
				cargarDetalleVenta();
				seleccionado = tblListaVentas.getSelectedRow();
			}
			else {
				if(JOptionPane.showConfirmDialog(frmGestionVentas, "Si selecciona otra venta perder� los cambios hechos. �Seleccionar de todos modos?")==JOptionPane.YES_OPTION){
					cargarDetalleVenta();
					seleccionado = tblListaVentas.getSelectedRow();
					modificado=false;
					eav=null;
				}
				else {
					tblListaVentas.setRowSelectionInterval(seleccionado, seleccionado);
				}
			}
	}
	
	public void modificarVenta() {
		if(JOptionPane.showConfirmDialog(frmGestionVentas, "�Desea guardar los cambios realizados?")==JOptionPane.YES_OPTION){
			Venta v = mapearDeFormulario();
			DataVentas dv = new DataVentas();
			dv.updateVenta(v, articulos_venta);
			modificado=false;
			eav = null;
		}
	}
	
	public void eliminarVenta() {
		DataVentas dv = new DataVentas();
		if(tblListaVentas.getSelectedRow()>=0){
			Venta v = mapearDeFormulario();
			if(JOptionPane.showConfirmDialog(frmGestionVentas, "�Desea eliminar venta N� " + v.getNro() + "?") == JOptionPane.YES_OPTION){
				dv.deleteVenta(v);
				cargarListaVentas();
			}
		}
		else
			JOptionPane.showMessageDialog(frmGestionVentas, "Seleccione una venta.");
	}
	
	public Venta mapearDeFormulario(){
		Venta venta = new Venta();
		
		venta.setNro(ventaActual);
		venta.setCliente(txtCliente.getText());
		venta.setFecha(dateFechaVenta.getDate());
		venta.setZona((String)cmbZona.getSelectedItem());
		
		return venta;
	}
	
	public boolean esNumero(String s){
		boolean b=false;
		if (s.matches("[0-9]+"))
			b=true;
		return b;			
	}
	
	public void setTablaArticulosVenta(JTable tabla){
		TableModel tm = tabla.getModel();
		tblArticulosVenta.setModel(tm);
		TableColumnModel m = tblArticulosVenta.getColumnModel();
		m.getColumn(2).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(3).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(5).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(6).setCellRenderer(NumberRenderer.getCurrencyRenderer());
		m.getColumn(7).setCellRenderer(NumberRenderer.getCurrencyRenderer());
	}
	
	public void setModificado(boolean b){
		modificado = b;
	}
	
	public void copiarArrayList(ArrayList<Articulo_Venta> copia, ArrayList<Articulo_Venta> av){
		copia.clear();
		for (int i = 0; i < av.size(); i++) {
			copia.add(new Articulo_Venta(av.get(i)));
		}
	}
	
	public void ordenar(){
		if (ord==null)
			ord = new OrdenarLista();
		ord.setCaller(this);
		ord.show(true);
	}
	
	public void cargarZonas(){
		dataZonas = new DataZonas();
		Vector<Object> zonas = dataZonas.getZonas();
		
		for (int i = 0; i < zonas.size(); i++) {
			cmbZona.addItem(zonas.get(i).toString());
		}
	}
	
	public void cargarListaVentas(){
		dataVentas = new DataVentas();
		
		Date fd = dateFechaDesde.getDate();
		Date fh = dateFechaHasta.getDate();
		if(fd!=null && fh!=null){
			changeSelected=false;
			dataVentas.cargarListaVentas(tblListaVentas, fd, fh, orden, filtro);
			changeSelected=true;
		}
		else
			JOptionPane.showMessageDialog(frmGestionVentas, "Ingrese un rango de fechas v�lido.");
	}
	
	public void setOrden(String ord){
		orden = ord;
	}
	
	public void filtrarListaVentas(){
		if(flv==null)
			flv = new FiltrarListaVentas();
		flv.setCaller(this);
		flv.show(true);
	}
	
	public void setFiltro(String f){
		filtro = f;
	}
	
	public void show(boolean b){
		this.frmGestionVentas.setVisible(false);
	}
	
	public void setCaller(MenuPrincipal m){
		mp = m;
	}
	
	public void volver(){
		mp.setNullABMV();
		show(false);
	}
}