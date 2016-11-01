package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class OrdenarLista {

	private int nivel;
	private JFrame frmOrdenarLista;

	/**
	 * Launch the application.
	 */
	public static void main(JTable lista) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrdenarLista window = new OrdenarLista(lista);
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
	public OrdenarLista(JTable lista) {
		nivel = 1;
		Vector<String> columnNames = new Vector<String>();
		int columnCount = lista.getColumnCount();
		for (int column = 0; column <= columnCount-1; column++) {
			columnNames.add(lista.getColumnName(column));
		}	
		initialize(columnNames);

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Vector<String> items) {
		
		frmOrdenarLista = new JFrame();
		frmOrdenarLista.setResizable(false);
		frmOrdenarLista.setTitle("Ordenar");
		frmOrdenarLista.setBounds(100, 100, 312, 327);
		frmOrdenarLista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnAgregarNivel = new JButton("Agregar nivel");
		
		btnAgregarNivel.setIcon(new ImageIcon(OrdenarLista.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		btnAgregarNivel.setBounds(10, 11, 118, 23);
		
		JButton btnQuitarNivel = new JButton("Quitar nivel");
		
		btnQuitarNivel.setEnabled(false);
		btnQuitarNivel.setIcon(new ImageIcon(OrdenarLista.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		btnQuitarNivel.setBounds(177, 11, 118, 23);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setBounds(10, 56, 63, 14);
		
		JComboBox cmbOrdenar1 = new JComboBox();
		cmbOrdenar1.setBounds(10, 76, 149, 21);
		setCMBOrdenar(cmbOrdenar1, items);
		
		JComboBox cmbOrdenar1AD = new JComboBox();
		cmbOrdenar1AD.setBounds(177, 76, 118, 21);
		cmbOrdenar1AD.setMaximumRowCount(2);
		cmbOrdenar1AD.addItem("Ascendente");
		cmbOrdenar1AD.addItem("Descendente");


		JLabel lblLuegoPor = new JLabel("luego por:");
		lblLuegoPor.setBounds(10, 115, 49, 14);
		lblLuegoPor.setEnabled(false);

		JComboBox cmbOrdenar2 = new JComboBox();
		cmbOrdenar2.setBounds(10, 135, 149, 21);
		cmbOrdenar2.setEnabled(false);
		setCMBOrdenar(cmbOrdenar2, items);


		JComboBox cmbOrdenar2AD = new JComboBox();
		cmbOrdenar2AD.setBounds(177, 135, 118, 21);
		cmbOrdenar2AD.setEnabled(false);
		cmbOrdenar2AD.setMaximumRowCount(2);
		cmbOrdenar2AD.addItem("Ascendente");
		cmbOrdenar2AD.addItem("Descendente");

		JLabel label = new JLabel("luego por:");
		label.setBounds(10, 174, 49, 14);
		label.setEnabled(false);

		JComboBox cmbOrdenar3 = new JComboBox();
		cmbOrdenar3.setBounds(10, 199, 149, 21);
		cmbOrdenar3.setEnabled(false);
		setCMBOrdenar(cmbOrdenar3, items);


		JComboBox cmbOrdenar3AD = new JComboBox();
		cmbOrdenar3AD.setBounds(177, 199, 118, 21);
		cmbOrdenar3AD.setEnabled(false);
		cmbOrdenar3AD.setMaximumRowCount(2);
		cmbOrdenar3AD.addItem("Ascendente");
		cmbOrdenar3AD.addItem("Descendente");
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(128, 265, 79, 23);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCerrar.setBounds(217, 265, 79, 23);
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
		frmOrdenarLista.getContentPane().add(btnCerrar);
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
	
	public void setCMBOrdenar(JComboBox cmb, Vector<String> items) {
		for (int i = 0; i < items.size(); i++) {
			cmb.addItem(items.get(i));
		}
	}
}
