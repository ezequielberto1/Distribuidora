package utils;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ChecklistTableModel extends DefaultTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChecklistTableModel(Vector<Vector<Object>> data,
			Vector<String> columnNames) {
		this.setDataVector(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
	    if (columnIndex == 1)
	        return Boolean.class;
	    return super.getColumnClass(columnIndex);
	}
	
	
 
	@Override
	public boolean isCellEditable(int row, int col) {
	    return (col == 1); 
	}

}
