package client.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import client.common.MyLabel;
import client.control.Chat;

public class FaceFrame extends JWindow
{

	private JPanel contentPane;

	GridLayout gridLayout1 = new GridLayout();
	public static JLabel[] ico = new JLabel[20];
	Chat chat;
	int width=240;
	int height=192;
	public FaceFrame(Chat chat)
	{
		super(chat);
		this.chat = chat;
		try
		{
			jbInit();
			int left=chat.getLocation().x+20;
			int top=chat.getLocation().y+chat.getHeight()-200-height;
			this.setBounds(left, top, width, height);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{
		getContentPane().setLayout(gridLayout1);
		gridLayout1.setColumns(5);
		gridLayout1.setHgap(1);
		gridLayout1.setRows(4);
		gridLayout1.setVgap(1);
		String fileName = " ";
		for (int i = 0; i < ico.length; i++)
		{
			if(i<10)
        	{
        		fileName= "/client/img/face/f00"+i+".png";//图片路径 
        	}
        	else
        	{
        		fileName= "/client/img/face/f0"+i+".png";
			}
			ico[i]=new   JLabel(new   ImageIcon(FaceFrame.class.getResource(fileName)));
			ico[i].setToolTipText("点此添加QQ表情哟^_^");
			new MyLabel(ico[i],Color.black).addEvent();
			final Icon img = ico[i].getIcon();
			final int count=i;
			ico[i].addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					chat.jTextPaneSendMessage.insertIcon(img);
					chat.faceIdx=count;
					getObj().dispose();// 这里最好用单例模式
				}
			});
			this.getContentPane().add(ico[i]);
		}
		this.getContentPane().setBackground(SystemColor.text);
	}

	private JWindow getObj()
	{
		return this;
	}

}
