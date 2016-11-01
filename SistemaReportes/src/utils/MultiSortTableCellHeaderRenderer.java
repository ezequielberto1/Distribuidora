package utils;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class MultiSortTableCellHeaderRenderer extends DefaultTableCellRenderer {

	    protected SortIcon sortIcon = new SortIcon(8);

	    public MultiSortTableCellHeaderRenderer()
	    {
	        setHorizontalAlignment(0);
	        setHorizontalTextPosition(10);
	    }


	    public Component getTableCellRendererComponent(JTable table, Object value,
	                          boolean isSelected, boolean hasFocus, int row, int column)
	    {
	        JTableHeader tableHeader = table.getTableHeader();
	        Color fg = null;
	        Color bg = null;
	        Border border = null;
	        Icon icon = null;

	        if (hasFocus)
	        {
	            fg = UIManager.getColor("TableHeader.focusCellForeground");
	            bg = UIManager.getColor("TableHeader.focusCellBackground");
	            border = UIManager.getBorder("TableHeader.focusCellBorder");
	        }

	        if (fg == null)
	            fg = tableHeader.getForeground();
	        if (bg == null)
	            bg = tableHeader.getBackground();
	        if (border == null)
	            border = UIManager.getBorder("TableHeader.cellBorder");
	        if (!tableHeader.isPaintingForPrint() && table.getRowSorter() != null)
	            icon = getSortIcon(table, table.convertColumnIndexToModel(column));

	        setFont(tableHeader.getFont());
	        setText(value != null && value != "" ? value.toString() : " ");
	        setBorder(border);
	        setIcon(icon);

	        return this;
	    }


	    protected Icon getSortIcon(JTable table, int column)
	    {
	        List<? extends SortKey> sortKeys = table.getRowSorter().getSortKeys();
	        if (sortKeys == null || sortKeys.size() == 0)
	            return null;

	        int priority = 0;
	        for (SortKey sortKey : sortKeys)
	        {
	            if (sortKey.getColumn() == column)
	            {
	                sortIcon.setPriority(priority);
	                sortIcon.setSortOrder(sortKey.getSortOrder());
	                return sortIcon;
	            }

	            priority++;
	        }

	        return null;
	    }
	}
