package client.common;

import java.awt.Component;
import java.awt.Image;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import client.control.Main;

import com.MyTools;

public class MyTreeIcon extends DefaultTreeCellRenderer 
{
	//如果是：用户名;图片路径，那么就是好友节点
	ArrayList<String> nodeImages=null;
    public MyTreeIcon(ArrayList<String> nodeImages)
    {
    	this.nodeImages=nodeImages;
    }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) 
    {
        super.getTreeCellRendererComponent(tree, value, sel,expanded,
        		leaf, row, hasFocus);
        setForeground(SystemColor.BLACK);// 设置文字的颜色
        setBackgroundSelectionColor(SystemColor.CYAN);// 设置选中时的背景色
        setBackgroundNonSelectionColor(SystemColor.controlHighlight);// 设置没选中时的背景色
        for(String str:nodeImages)
        {
        	String[] temp=str.split(MyTools.SPLIT1);
        	if(value.toString().startsWith(temp[0])&&!temp[0].equals(""))
        	{
        		 try
				{
					if(temp.length==2)//如果是：用户名;图片路径，那么就是好友节点
	        			//this.setIcon(new ImageIcon(grayImage));
						this.setIcon(MyTools.getIcon(temp[1]));
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
        		
        	}
        }
        return this;
    }
}

