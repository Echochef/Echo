package client.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JTree;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.tree.TreeModel;

import com.MyTools;
import com.MyTools.Flag;

import client.common.MyTextPane;
import client.common.MyTreeIcon;
import client.frame.QunChatFrame;
import client.socket.CS_TCP;

public class QunChat extends QunChatFrame
{
	CS_TCP cs_TCP=null;
	Main main=null;
	
	public QunChat(Main main)
	{
		this.main=main;
		this.cs_TCP=main.cs_TCP;
		MyTools.setWindowsMiddleShow(this);
		this.setTitle("聊天室(当前用户："+main.userName.getText()+")");
		addEvent();
		this.setVisible(true);
	}
	public void addEvent()
	{
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nowTime= DateFormat.getTimeInstance().format(new Date());
				cs_TCP.sendMessage(Flag.QUN_CHAT+MyTools.FLAGEND+main.userName.getText()+MyTools.SPLIT1+nowTime+MyTools.SPLIT1+textPaneSend.getText());
				textPaneSend.setText("");
			}
		});
		closeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();//关闭聊天室
			}
		});
	}
	
	//接收消息
	public void receiveMessage(String message)
	{
		String[] temp=message.split(MyTools.SPLIT1);
		if(temp[0].equals(main.userName.getText()))
		{
			new MyTextPane(textPaneGet).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(textPaneGet).addText(temp[2]+"\n", MyTextPane.getMyAttribute());
		}
		else
		{
			new MyTextPane(textPaneGet).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(textPaneGet).addText(temp[2]+"\n", MyTextPane.getFriendAttribute());
		}
	}
	
}
