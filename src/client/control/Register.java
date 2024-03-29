﻿package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.MyTools;
import com.MyTools.Flag;
import client.frame.LoginFrame;
import client.frame.RegisterFrame;
import client.socket.CS_TCP;

import server.common.JDBC;
import server.dao.UserDao;
import server.entity.Users;

/**
 *注册最后得到的是一个user对象
 *user对象内容为用户名,密码,头像,生日,签名,E-mail.
 */
public class Register extends RegisterFrame
{
	public CS_TCP cs_TCP;//客户端与服务器的TCP连接
	
	public Register()
	{
		
		MyTools.setWindowsMiddleShow(this);
		initHeadImage();
		addEvent();
		setVisible(true);
		
	}
	/**
	 * 初始化头像
	 */
	public void initHeadImage()
	{
		for(int i=0;i<=15;i++)
		{
			comboBoxHeadImage.addItem(MyTools.getIcon("../img/headImage/big/"+i+"_100.jpg"));
		}
	}
	public void addEvent()
	{
		//注册按钮监听
		btnRegister.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(txtName.getText().equals(""))
					JOptionPane.showMessageDialog(null, "用户名不能为空！","错误",JOptionPane.ERROR_MESSAGE);
				else if(new String(pwd.getPassword()).equals(""))
					JOptionPane.showMessageDialog(null, "密码不能为空！","错误",JOptionPane.ERROR_MESSAGE);
				else if(!new String(pwd.getPassword()).equals(new String(pwdRe.getPassword())))
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入！","错误",JOptionPane.ERROR_MESSAGE);
				else
				{
					//建立TCP连接，形成传注册的数据的通道。 
					try
    				{
    					if(cs_TCP==null)
    						cs_TCP=new CS_TCP(MyTools.QQServerIP, MyTools.QQServerPort);
    					String sex="男";
    					if(comGender.getSelectedIndex()==1)
    						sex="女";
    					cs_TCP.sendMessage(Flag.REGISTER+MyTools.FLAGEND//注册标志
    							+txtName.getText()+MyTools.SPLIT1//用户名
    							+new String(pwd.getPassword())+MyTools.SPLIT1//密码
    							+sex+MyTools.SPLIT1//性别
    							+txtEmail.getText()+MyTools.SPLIT1//电子邮件
    							+txtbirthday.getText()+MyTools.SPLIT1//生日
    							+txtSignat.getText()+MyTools.SPLIT1//个性签名
    							+comboBoxHeadImage.getSelectedIndex());//头像索引
    				}
    				catch (Exception ee)
    				{
    					JOptionPane.showMessageDialog(null, "连接服务器失败！","错误",JOptionPane.ERROR_MESSAGE);
    				}
				}
				
			}
		});
	}
	/**
	 * 在窗体关闭之前需要做的事
	 * 关闭连接
	 */
	@Override
	public void beforeClose()
	{
		if(cs_TCP!=null)
			cs_TCP.closeSocket();
	}
	/**
	 * 取消按钮事件
	 */
	@Override
	public void cancle()
	{
		if(cs_TCP!=null)
			cs_TCP.closeSocket();
		this.dispose();
	}
	public void actionPerformed(ActionEvent e) 
	{
		
	}
}
