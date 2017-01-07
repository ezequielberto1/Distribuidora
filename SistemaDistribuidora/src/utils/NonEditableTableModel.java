package utils;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel{
	
	public NonEditableTableModel(Vector data, Vector columnnames){
		super(data, columnnames);
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
        return false;
	}
}
