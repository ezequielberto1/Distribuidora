package ui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class OrdenarLista {

	private int nivel = 1;
	private JFrame frmOrdenarLista;
	private ABMVentas abmv;
	private Vector<String> items;
	private JComboBox cmbOrdenar3;
	private JComboBox cmbOrdenar3AD;
	private JComboBox cmbOrdenar2AD;
	private JComboBox cmbOrdenar2;
	private JComboBox cmbOrdenar1AD;
	private JComboBox cmbOrdenar1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdenarLista window = new OrdenarLista();
					window.frmOrdenarLista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OrdenarLista() {
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

		frmOrdenarLista = new JFrame();
		frmOrdenarLista.setResizable(false);
		frmOrdenarLista.setTitle("Ordenar");
		frmOrdenarLista.setBounds(100, 100, 312, 327);
		frmOrdenarLista.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Image logo = (new ImageIcon(this.getClass().getResource("/Logo INT.png")).getImage());
		frmOrdenarLista.setIconImage(logo);

		JButton btnAgregarNivel = new JButton("Agregar nivel");

		btnAgregarNivel.setIcon(new ImageIcon(OrdenarLista.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnAgregarNivel.setBounds(10, 11, 118, 23);

		JButton btnQuitarNivel = new JButton("Quitar nivel");

		btnQuitarNivel.setEnabled(false);
		btnQuitarNivel.setIcon(new ImageIcon(OrdenarLista.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnQuitarNivel.setBounds(177, 11, 118, 23);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setBounds(10, 56, 63, 14);

		cmbOrdenar1 = new JComboBox();
		cmbOrdenar1.setBounds(10, 76, 149, 21);
		cmbOrdenar1.addItem("Nº venta");
		cmbOrdenar1.addItem("Nº cliente");
		cmbOrdenar1.addItem("Nombre cliente");
		cmbOrdenar1.addItem("Localidad");
		cmbOrdenar1.addItem("Zona");
		
		cmbOrdenar1AD = new JComboBox();
		cmbOrdenar1AD.setBounds(177, 76, 118, 21);
		cmbOrdenar1AD.setMaximumRowCount(2);
		cmbOrdenar1AD.addItem("Ascendente");
		cmbOrdenar1AD.addItem("Descendente");


		JLabel lblLuegoPor = new JLabel("luego por:");
		lblLuegoPor.setBounds(10, 115, 49, 14);
		lblLuegoPor.setEnabled(false);

		cmbOrdenar2 = new JComboBox();
		cmbOrdenar2.setBounds(10, 135, 149, 21);
		cmbOrdenar2.setEnabled(false);
		cmbOrdenar2.addItem("Nº venta");
		cmbOrdenar2.addItem("Nº cliente");
		cmbOrdenar2.addItem("Nombre cliente");
		cmbOrdenar2.addItem("Localidad");
		cmbOrdenar2.addItem("Zona");


		cmbOrdenar2AD = new JComboBox();
		cmbOrdenar2AD.setBounds(177, 135, 118, 21);
		cmbOrdenar2AD.setEnabled(false);
		cmbOrdenar2AD.setMaximumRowCount(2);
		cmbOrdenar2AD.addItem("Ascendente");
		cmbOrdenar2AD.addItem("Descendente");

		JLabel label = new JLabel("luego por:");
		label.setBounds(10, 174, 49, 14);
		label.setEnabled(false);

		cmbOrdenar3 = new JComboBox();
		cmbOrdenar3.setBounds(10, 199, 149, 21);
		cmbOrdenar3.setEnabled(false);
		cmbOrdenar3.addItem("Nº venta");
		cmbOrdenar3.addItem("Nº cliente");
		cmbOrdenar3.addItem("Nombre cliente");
		cmbOrdenar3.addItem("Localidad");
		cmbOrdenar3.addItem("Zona");

		cmbOrdenar3AD = new JComboBox();
		cmbOrdenar3AD.setBounds(177, 199, 118, 21);
		cmbOrdenar3AD.setEnabled(false);
		cmbOrdenar3AD.setMaximumRowCount(2);
		cmbOrdenar3AD.addItem("Ascendente");
		cmbOrdenar3AD.addItem("Descendente");
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarOrden();
			}
		});
		btnConfirmar.setBounds(128, 265, 79, 23);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(217, 265, 79, 23);
		frmOrdenarLista.getContentPane().setLayout(null);
		frmOrdenarLista.getContentPane().add(lblOrdenarPor);
		frmOrdenarLista.getContentPane().add(cmbOrdenar1);
		frmOrdenarLista.getContentPane().add(cmbOrdenar1AD);
		frmOrdenarLista.getContentPane().add(lblLuegoPor);
		frmOrdenarLista.getContentPane().add(cmbOrdenar2);
		frmOrdenarLista.getContentPane().add(cmbOrdenar2AD);
		frmOrdenarLista.getContentPane().add(label);
		frmOrdenarLista.getContentPane().add(cmbOrdenar3);
		frmOrdenarLista.getContentPane().add(cmbOrdenar3AD);
		frmOrdenarLista.getContentPane().add(btnAgregarNivel);
		frmOrdenarLista.getContentPane().add(btnQuitarNivel);
		frmOrdenarLista.getContentPane().add(btnVolver);
		frmOrdenarLista.getContentPane().add(btnConfirmar);

		btnAgregarNivel.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				switch (nivel) {
				case 1: {
					cmbOrdenar2.setEnabled(true);
					cmbOrdenar2AD.setEnabled(true);
					btnQuitarNivel.setEnabled(true);
					nivel = 2;
					break;
				}
				case 2: {
					cmbOrdenar3.setEnabled(true);
					cmbOrdenar3AD.setEnabled(true);
					btnAgregarNivel.setEnabled(false);
					nivel = 3;
					break;
				}
				}
			}
		});

		btnQuitarNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (nivel) {
				case 2: {
					cmbOrdenar2.setEnabled(false);
					cmbOrdenar2AD.setEnabled(false);
					btnQuitarNivel.setEnabled(false);
					nivel = 1;
					break;
				}
				case 3: {
					cmbOrdenar3.setEnabled(false);
					cmbOrdenar3AD.setEnabled(false);
					btnAgregarNivel.setEnabled(true);
					nivel = 2;
					break;
				}
				}
			}
		});
	}

	public void show(boolean b){
		this.frmOrdenarLista.setVisible(b);
	}

	public void setCaller(ABMVentas abm){
		abmv = abm;
	}

	public ABMVentas getCaller(){
		return abmv;
	}

	public void confirmarOrden(){
		if (cmbVacio()==false){
			String orden = new String();
			switch (nivel) {
			case 1: {
				orden = getColumna(cmbOrdenar1.getSelectedItem().toString())+" "+getCriterio(cmbOrdenar1AD.getSelectedItem().toString());
				break;
			}
			case 2: {
				orden = getColumna(cmbOrdenar1.getSelectedItem().toString())+" "+getCriterio(cmbOrdenar1AD.getSelectedItem().toString())+", "
						+ getColumna(cmbOrdenar2.getSelectedItem().toString())+" "+getCriterio(cmbOrdenar2AD.getSelectedItem().toString());
				break;
			}
			case 3: {
				orden = getColumna(cmbOrdenar1.getSelectedItem().toString())+" "+getCriterio(cmbOrdenar1AD.getSelectedItem().toString())+", "
				+ getColumna(cmbOrdenar2.getSelectedItem().toString())+" "+getCriterio(cmbOrdenar2AD.getSelectedItem().toString())+", "
				+ getColumna(cmbOrdenar3.getSelectedItem().toString())+" "+getCriterio(cmbOrdenar3AD.getSelectedItem().toString());
				break;
			}
			}
			abmv.setOrden(orden);
			System.out.println(orden);
			abmv.cargarListaVentas();
			show(false);
		}
	}
	
	public String getColumna(String cmb){
		String columna = new String();
		
		switch (cmb){
		case "Nº venta": {
			columna = "ventasfinal.[numventa]";
			break;
		}
		case "Nº cliente": {
			columna = "ventasfinal.[codcli]";
			break;
		}
		case "Nombre cliente": {
			columna = "ventasfinal.[apenom]";
			break;
		}
		case "Localidad": {
			columna = "ventasfinal.[localidad]";
			break;
		}
		case "Zona": {
			columna = "ventasfinal.[iva]";
			break;
		}
		}
	return columna;
	
	}
	
	public String getCriterio(String cmb){
		String criterio = new String();
		if(cmb=="Ascendente")
			criterio = "ASC";
		if(cmb=="Descendente")
			criterio = "DESC";
		
		return criterio;
	}
	
	public boolean cmbVacio(){
		boolean vacio = false;
		switch (nivel) {
		case 1: {
			if(cmbOrdenar1.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 1º columna de orden.");
			}
			if(cmbOrdenar1AD.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 1º criterio.");
			}
			break;		
		}
		case 2: {
			if(cmbOrdenar1.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 1º columna de orden.");
			}
			if(cmbOrdenar1AD.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 1º criterio.");
			}
			if(cmbOrdenar2.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 2º columna de orden.");
			}
			if(cmbOrdenar2AD.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 2º criterio.");
			}
			break;
		}
		case 3: {
			if(cmbOrdenar1.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 1º columna de orden.");
			}
			if(cmbOrdenar1AD.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 1º criterio.");
			}
			if(cmbOrdenar2.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 2º columna de orden.");
			}
			if(cmbOrdenar2AD.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 2º criterio.");
			}
			if(cmbOrdenar3.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 3º columna de orden.");
			}
			if(cmbOrdenar3AD.getSelectedItem().toString().matches("")){
				vacio = true;
				JOptionPane.showMessageDialog(frmOrdenarLista, "Seleccione 3º criterio.");
			}
			break;
		}
		}
		return vacio;
	}
	
	public void volver(){
		show(false);
	}
}
