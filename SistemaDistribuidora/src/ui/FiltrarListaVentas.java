package ui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import data.DataMarcas;
import data.DataVentas;
import data.DataZonas;

import javax.swing.JLabel;

public class FiltrarListaVentas {

	private JFrame frmFiltrarListaVentas;
	private DataMarcas dataMarcas;
	private ArrayList<String> marcas = new ArrayList<String>();
	private ArrayList<String> vendedores = new ArrayList<String>();
	private JTable tblZonas;
	private ABMVentas abmv;

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
		
		DataZonas dz= new DataZonas();

		dz.cargarTablaZonas(tblZonas);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFiltrarListaVentas = new JFrame();
		frmFiltrarListaVentas.setTitle("Filtrar");
		frmFiltrarListaVentas.setBounds(100, 100, 171, 336);
		frmFiltrarListaVentas.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmFiltrarListaVentas.getContentPane().setLayout(new BorderLayout(0, 0));
		Image logo = (new ImageIcon(this.getClass().getResource("/Logo INT.png")).getImage());
		frmFiltrarListaVentas.setIconImage(logo);
		
		JPanel panel = new JPanel();
		frmFiltrarListaVentas.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Zonas:");
		label.setBounds(10, 11, 61, 14);
		panel.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 134, 106);
		panel.add(scrollPane);
		
		tblZonas = new JTable();
		scrollPane.setViewportView(tblZonas);
		
		JButton btnMarcarTodos = new JButton("Marcar todos");
		btnMarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				marcarTodo(tblZonas);
			}
		});
		btnMarcarTodos.setBounds(10, 153, 134, 23);
		panel.add(btnMarcarTodos);
		
		JButton btnDesmarcarTodos = new JButton("Desmarcar todos");
		btnDesmarcarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desmarcarTodo(tblZonas);
			}
		});
		btnDesmarcarTodos.setBounds(10, 187, 134, 23);
		panel.add(btnDesmarcarTodos);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmarFiltro();
			}
		});
		btnConfirmar.setBounds(36, 221, 80, 23);
		panel.add(btnConfirmar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(36, 255, 80, 23);
		panel.add(btnVolver);
	}
	
	public void cargarSeleccionados (JTable tabla, ArrayList<String> items) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			if (tabla.getModel().getValueAt(i, 0).toString()==items.get(i).toString()) 
				tabla.getModel().setValueAt(Boolean.TRUE, i, 1);
			else
				tabla.getModel().setValueAt(Boolean.FALSE, i, 1);
		}	
	};
	
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
		this.frmFiltrarListaVentas.setVisible(b);
	}
			
	public void confirmarFiltro(){
		java.util.ArrayList<String> zonas = new ArrayList<String>();
		String zonasString = "";				
		
		zonas = getSeleccionados(tblZonas);
		
		if (!(zonas.isEmpty())) {
			for (String s : zonas){
			    zonasString += "'" + s + "', ";
			}
			zonasString = "AND (ventasfinal.[iva] IN (" + zonasString.substring(0, zonasString.length()-2) +"))";

			abmv.setFiltro(zonasString);
			abmv.cargarListaVentas();
			show(false);
		}
		else {
			JOptionPane.showMessageDialog(null, "Seleccione al menos una zona.");
		}
	}
	
	public void volver(){
		show(false);
	}

	public void setCaller(ABMVentas abm){
		abmv = abm;
	}

	public ABMVentas getCaller(){
		return abmv;
	}
}
