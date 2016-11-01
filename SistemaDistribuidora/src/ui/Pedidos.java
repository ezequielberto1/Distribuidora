package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class Pedidos {

	private JFrame frmListaDePedidos;
	private JTable tblListaPedidos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pedidos window = new Pedidos();
					window.frmListaDePedidos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pedidos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListaDePedidos = new JFrame();
		frmListaDePedidos.setTitle("Lista de pedidos");
		frmListaDePedidos.setBounds(100, 100, 450, 345);
		frmListaDePedidos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frmListaDePedidos.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JButton btnMarcarTodas = new JButton("Marcar todas");
		
		JButton btnDesmarcarTodas = new JButton("Desmarcar todas");
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Zonas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMarcarTodas, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addComponent(btnDesmarcarTodas, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnMarcarTodas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDesmarcarTodas)
					.addContainerGap())
		);
		
		JCheckBox checkBox = new JCheckBox("1");
		
		JCheckBox checkBox_1 = new JCheckBox("2");
		
		JCheckBox checkBox_2 = new JCheckBox("3");
		
		JCheckBox checkBox_3 = new JCheckBox("4");
		
		JCheckBox checkBox_4 = new JCheckBox("5");
		
		JCheckBox checkBox_5 = new JCheckBox("6");
		
		JCheckBox checkBox_6 = new JCheckBox("7");
		
		JCheckBox checkBox_7 = new JCheckBox("8");
		
		JCheckBox checkBox_8 = new JCheckBox("9");
		
		JCheckBox checkBox_9 = new JCheckBox("10");
		
		JCheckBox checkBox_10 = new JCheckBox("11");
		
		JCheckBox checkBox_11 = new JCheckBox("12");
		
		JCheckBox checkBox_12 = new JCheckBox("13");
		
		JCheckBox checkBox_13 = new JCheckBox("14");
		
		JScrollBar scrollBar = new JScrollBar();
		
		JCheckBox checkBox_14 = new JCheckBox("15");
		
		JCheckBox checkBox_15 = new JCheckBox("16");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(checkBox)
							.addGap(18)
							.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(checkBox_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_5, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(checkBox_6, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_7, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(checkBox_8, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkBox_9))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(checkBox_14, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(checkBox_12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(checkBox_10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(12)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(checkBox_13, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
								.addComponent(checkBox_11, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(checkBox_15, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox)
						.addComponent(checkBox_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_2)
						.addComponent(checkBox_3))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_4)
						.addComponent(checkBox_5))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_6)
						.addComponent(checkBox_7))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_8)
						.addComponent(checkBox_9))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_10)
						.addComponent(checkBox_11))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_12)
						.addComponent(checkBox_13))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_14)
						.addComponent(checkBox_15))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(scrollBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
		);
		panel_2.setLayout(gl_panel_2);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnImprimir = new JButton("Imprimir");
		
		JButton btnOrdenar = new JButton("Ordenar...");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnOrdenar)
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addComponent(btnImprimir)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(0)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnImprimir)
						.addComponent(btnOrdenar))
					.addGap(10))
		);
		
		tblListaPedidos = new JTable();
		tblListaPedidos.setBackground(SystemColor.window);
		tblListaPedidos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Fecha", "Cod. Art.", "Descripci\u00F3n", "Cantidad"
			}
		));
		scrollPane_1.setViewportView(tblListaPedidos);
		panel_1.setLayout(gl_panel_1);
	}
}
