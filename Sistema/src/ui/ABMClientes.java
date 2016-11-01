package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ABMClientes {

	private JFrame frmGestinDeClientes;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	@SuppressWarnings("rawtypes")
	private JComboBox textField_4;
	private JTable tblListaClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMClientes window = new ABMClientes();
					window.frmGestinDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ABMClientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frmGestinDeClientes = new JFrame();
		frmGestinDeClientes.setTitle("Gesti\u00F3n de clientes");
		frmGestinDeClientes.setBounds(100, 100, 752, 468);
		frmGestinDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Datos cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.inactiveCaption);
		
		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocalidad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblLocalidad.setBounds(52, 190, 48, 14);
		panel.add(lblLocalidad);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDomicilio.setBounds(52, 114, 48, 14);
		panel.add(lblDomicilio);
		
		JLabel lblCodigo = new JLabel("C\u00F3digo(*)");
		lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCodigo.setBounds(33, 38, 67, 14);
		panel.add(lblCodigo);
		
		JLabel label_3 = new JLabel("Nombre");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(52, 76, 48, 14);
		panel.add(label_3);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelfono.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTelfono.setBounds(33, 152, 67, 14);
		panel.add(lblTelfono);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(118, 35, 44, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(118, 73, 209, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(118, 111, 209, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(118, 149, 209, 20);
		panel.add(textField_3);
		
		textField_4 = new JComboBox();
		textField_4.setBounds(118, 187, 209, 20);
		panel.add(textField_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setBounds(118, 225, 209, 20);
		panel.add(comboBox);
		
		JLabel lblZona = new JLabel("Zona");
		lblZona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZona.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblZona.setBounds(54, 228, 46, 14);
		panel.add(lblZona);
		
		JLabel label_6 = new JLabel("(*) Campo s\u00F3lo para b\u00FAsqueda. \r\nAl agregar un cliente, el N\u00BA es autogenerado.");
		label_6.setFont(new Font("Tahoma", Font.ITALIC, 8));
		label_6.setBounds(33, 256, 317, 14);
		panel.add(label_6);
		
		JButton btnOrdenar = new JButton("Ordenar...");
		
		JScrollPane scrListaClientes = new JScrollPane();
		
		JButton button_1 = new JButton("Buscar");
		
		JButton button_2 = new JButton("Agregar");
		
		JButton button_3 = new JButton("Eliminar");
		
		JButton button_4 = new JButton("Modificar");
		
		JButton button_5 = new JButton("Limpiar campos");
		
		JButton button = new JButton("Filtrar...");
		GroupLayout groupLayout = new GroupLayout(frmGestinDeClientes.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOrdenar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrListaClientes, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addGap(25)
									.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGap(14)
									.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))))
					.addGap(14))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrdenar)
						.addComponent(button))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrListaClientes, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_1)
								.addComponent(button_5))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button_2)
								.addComponent(button_3)
								.addComponent(button_4))))
					.addGap(10))
		);
		
		tblListaClientes = new JTable();
		tblListaClientes.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Domicilio", "Localidad", "Zona"
			}
		));
		scrListaClientes.setViewportView(tblListaClientes);
		frmGestinDeClientes.getContentPane().setLayout(groupLayout);
	}
}
