package client.socket;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import client.control.Chat;
import client.control.Login;
import client.control.Main;
import client.control.SendTread;
import client.frame.PublicMessageFrame;
import client.frame.SendFileFrame;

import com.MyTools;
import com.MyTools.Flag;
import com.socket.TCP;

/**
 * 客户端与QQ服务器之间的TCP通讯
 */
public class CS_TCP extends TCP
{
	Login login = null;//登录窗体，从构造方法中传过来
	Main main=null;//主窗体
	public CS_TCP(String serverIP, int serverPort, Login login,Main main) throws Exception
	{
		super(serverIP, serverPort);
		this.login = login;
		this.main=main;
	}
	/**
	 * 此构造方法仅供注册用
	 */
	public CS_TCP(String serverIP, int serverPort) throws Exception
	{
		super(serverIP, serverPort);
	}


	@Override
	public void dealWithMessage(Flag flag, String message)
	{
		System.out.println("测试"+flag.toString()+","+message);
		switch(flag)
		{
			case LOGIN:doLogin(message);break;//如果是登录
			case REGISTER:doRegister(message);break;//如果是注册
			case CHANGE:doChange(message);break;//如果是个人修改事件
			case USERINFO:doUsersInfo(message);break;//如果是用户信息
			case GET_FRIEND_INFO:doGetFriendInfo(message);break;//如果是服务器发来的指定好友信息
			case FRIENDS_LIST:doFriendList(message);break;//接收服务器发来的刷新好友列表信息
			case QUN_CHAT:doQunChat(message);break;//处理群消息
			case UNDERLINE_MESSAGE:doUnreadMessage(message);break;//接收服务器发来的未读消息
			default:break;
		}
	}

	@Override
	public void dealWithExit()
	{
	}

	//处理登录相关事件
	public void doLogin(String message)
	{
		//注意一定要给Flag加toString()
		if (message.split(MyTools.SPLIT1)[0].equals(Flag.SUCCESS.toString()))// 如果登录成功
		{
			login.dispose();//销毁登录窗体
			main.setCS_TCP(login.cs_TCP);
			main.setVisible(true);
			doUsersInfo(message.split(MyTools.SPLIT1)[1]);
		}
		else if (message.split(MyTools.SPLIT1)[0].equals(Flag.FAILED.toString()))// 如果登录失败
		{
			JOptionPane.showMessageDialog(null,
					message.split(MyTools.SPLIT1)[1]);//显示登录失败的原因
		}
	}
	
    //处理注册相关事件
	public void doRegister(String message)
	{
		if(message.split(MyTools.SPLIT1)[0].equals(Flag.SUCCESS.toString()))
			JOptionPane.showMessageDialog(null,message.split(MyTools.SPLIT1)[1]);
		else if(message.split(MyTools.SPLIT1)[0].equals(Flag.FAILED.toString()))
			JOptionPane.showMessageDialog(null, message.split(MyTools.SPLIT1)[1],"失败",JOptionPane.ERROR_MESSAGE);
	}
	
	//处理个人信息修改事件
	public void doChange(String message)
	{
		if(message.split(MyTools.SPLIT1)[0].equals(Flag.SUCCESS.toString()))
			JOptionPane.showMessageDialog(null,message.split(MyTools.SPLIT1)[1]);
		else if(message.split(MyTools.SPLIT1)[0].equals(Flag.FAILED.toString()))
			JOptionPane.showMessageDialog(null, message.split(MyTools.SPLIT1)[1],"失败",JOptionPane.ERROR_MESSAGE);
	}
	
	// 处理服务器发回来用户信息
	public void doUsersInfo(String message)
	{
		String[] temp=message.split(MyTools.SPLIT2);
		String userName=temp[0];//用户名
		int userState=Integer.parseInt(temp[1]);
		String signature=temp[2];//个性签名
		String headImage=temp[3];//头像
		if(main!=null)
		{
			main.userName.setText(userName);
			main.comboBoxState.setSelectedIndex(userState);
			main.signate.setText(signature);
			main.headImg.setIcon(MyTools.getIcon("../img/headImage/middle/"+headImage+"_64.jpg"));
		}
	}
	
	//处理获取好友信息
	public void doGetFriendInfo(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	//处理好友列表
	public void doFriendList(String message)
	{
		System.out.println("客户端已接收到好友列表！");
		System.out.println(message);
		String[] groupNames=message.split(MyTools.SPLIT1)[0].split(MyTools.SPLIT2);
		ArrayList<String[]> friendNames=new ArrayList<String[]>();
		for(int i=0;i<groupNames.length;i++)
		{
			String temp=message.split(MyTools.SPLIT1)[i+1];
			if(!temp.equals("无") && !"".equals(temp))
				friendNames.add(temp.split(MyTools.SPLIT2));
			else
				friendNames.add(new String[]{});
		}
		
		main.initjTree(groupNames, friendNames);
	}
	
	//处理群聊
	public void doQunChat(String message)
	{
		if(main.qunChat!=null)
			main.qunChat.receiveMessage(message);
		else
		{
			String[] temp=message.split(MyTools.SPLIT1);
			new PublicMessageFrame("聊天室未读消息", temp[0]+"说("+temp[1]+"):\n"+temp[2]);
		}
			
	}
	
	//处理离线消息
	public void doUnreadMessage(String message)
	{
		new PublicMessageFrame("您有未读离线消息", message);
	}
}
