package utils;

import javax.swing.*;
import javax.swing.plaf.basic.*;
import java.awt.*;

public class SortIcon implements Icon, SwingConstants
{
    private int baseSize;
    private int size;
    private int direction;
    private BasicArrowButton iconRenderer;
    private double[] sizePercentages = {1.0, .85, .70, .55, .40, .25, .10};

    public SortIcon(int size)
    {
        this.baseSize = this.size = size;
        iconRenderer = new BasicArrowButton(direction);
    }


    public void setPriority(int priority)
    {
        size = (int) (baseSize * sizePercentages[priority]);
    }


    public void setSortOrder(SortOrder sortOrder)
    {
        direction = sortOrder == SortOrder.ASCENDING ? NORTH : SOUTH;
    }


    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        iconRenderer.paintTriangle(g, x, y, size, direction, true);
    }


    public int getIconWidth()
    {
        return size;
    }



    public int getIconHeight()
    {
        return size / 2;
    }
}
