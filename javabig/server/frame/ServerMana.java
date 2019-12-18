package server.frame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import server.QQServer;

import client.frame.MyPanel;

import com.MyTools;

import java.util.Calendar;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;


public class ServerMana extends JPanel implements Runnable
{
	QQServer qqServer=null; //QQ服务器
	
	JButton btnStart;    //启动服务器按钮
	
	Calendar calendar=Calendar.getInstance();
	
	private JLabel lblStateShow;//当前服务器状态
	public JLabel lblState;
	
	public boolean isRun=false;
	
	public ServerMana() 
	{
		setLayout(null);
		
		//启动服务器按钮
		btnStart = new JButton("启动服务器");
		btnStart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				startOrCloseServer();
			}
		});
		btnStart.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnStart.setBounds(29, 30, 301, 76);
		add(btnStart);
		
		//当前服务器状态
		lblStateShow = new JLabel("当前服务器状态：");
		lblStateShow.setBounds(28, 132, 107, 15);
		add(lblStateShow);
		
		lblState = new JLabel("服务器未运行");
		lblState.setBounds(153, 132, 135, 15);
		add(lblState);
	
		Thread thread=new Thread(this);
		thread.start();
		
	}
	
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void startOrCloseServer()
	{
		if(btnStart.getText().equals("启动服务器"))
		{
			//启动服务器
			qqServer=new QQServer(MyTools.QQServerPort);
			
			isRun=true;
			lblState.setText("服务器正在运行中...");
			btnStart.setText("停止服务器");
		}
		else if(btnStart.getText().equals("停止服务器"))
		{
			if(qqServer!=null)
			{
				qqServer.closeServer();
				isRun=false;
				lblState.setText("服务器已关闭。");
				btnStart.setText("启动服务器");
			}
		}
	}
	
}
