package client.control;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import com.MyTools;
import com.MyTools.Flag;

import client.common.MyLabel;
import client.frame.LoginFrame;
import client.frame.RegisterFrame;
import client.socket.CS_TCP;
import com.sun.awt.AWTUtilities;

//登录窗体，继承自LoginFrame
public class Login extends LoginFrame 
{
	private static final long serialVersionUID = 5055331246882705423L;
	int width=430;
	int height=305;
	private Point lastPoint = null;//存放按下鼠标时的坐标点
	public CS_TCP cs_TCP=null;//声明一个客户端TCP
	public Main main; //声明一个主窗体
	public RegisterFrame registerFrame=null;
	
	//程序主路口
	public static void main(String[] args)
	{
		new Login();
	}
	
    //登录窗体构造
	public Login()
	{
		init();
	}

	//初始化
	public void init()
	{
		MyTools.setWindowsMiddleShow(this,width,height);//设置窗体居中显示
		new MyLabel(loginButton, "../img/button/button_login", "png").addEvent();
		new MyLabel(registerButton).addEvent();
		addEvent();
		this.setVisible(true);
		main=new Main();
	}

	//登录到服务器
	public void login()
	{
		//获取用户名与密码
		String name=textFieldUserName.getText();
		String password=new String(UserPwd.getPassword());
		
		if(name.equals("")||password.equals(""))
			JOptionPane.showMessageDialog(this, "用户名和密码不能为空！","错误",JOptionPane.ERROR_MESSAGE);
		else 
		{
			try
    		{
    			if(cs_TCP==null)
    				cs_TCP=new CS_TCP(MyTools.QQServerIP, MyTools.QQServerPort,this,main);
    			System.out.println("开始检测用户名和密码……");
    			
    			cs_TCP.sendMessage(Flag.LOGIN+MyTools.FLAGEND+name+MyTools.SPLIT1+password+MyTools.SPLIT1+main.getServerPort()+MyTools.SPLIT1+1);
    		}
    		catch (Exception e)
    		{
    			JOptionPane.showMessageDialog(null, "连接服务器失败！请检查网络连接或确保QQ服务器已启动！");
    		}
		}
	}
	
	//窗体鼠标事件
	public void mousePress(MouseEvent e)
	{
		lastPoint = e.getLocationOnScreen();
	}
	
	//窗口拖拽事件
	public void mouseDrag(MouseEvent e)
	{
		Point point = e.getLocationOnScreen();
		int offsetX = point.x - lastPoint.x;
		int offsetY = point.y - lastPoint.y;
		Rectangle bounds = this.getBounds();
		bounds.x += offsetX;
		bounds.y += offsetY;
		this.setBounds(bounds);
		lastPoint = point;
	}
	
    //为窗体添加事件
	public void addEvent()
	{
		loginButton.addMouseListener(new MouseAdapter()
		{
			//登录按钮的单击事件
			@Override
			public void mouseClicked(MouseEvent e)
			{
				login();
			}
		});
		this.addMouseListener(new MouseAdapter()
		{
			//窗体的鼠标按下事件
			@Override
			public void mousePressed(MouseEvent e)
			{
				mousePress(e);
			}
		});
		this.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				mouseDrag(e);
			};
		});
		
		registerButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				new Register();
			}
		});
	}
}
