package client.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import client.common.MyLabel;
import client.control.Chat;
import javax.swing.JScrollPane;

public class PublicMessageFrame extends JDialog
{
	private JPanel contentPane;
	public JTextArea textArea公告;
	//打开、关闭按钮
	public JLabel closeButton;
	public JButton openButton;
	public JButton closeButton1;
	
	public Chat chat=null;
	private JScrollPane scrollPane;
//	/**
//	 *启动应用程序。.
//	 */
//	public static void main(String[] args)
//	{
//		new PublicMessageFrame("您有未读消息","你好",null);
//	}

	public PublicMessageFrame(String title,String publicMessage)
	{
		init(title, publicMessage);
	}
	
	public PublicMessageFrame(String title,String publicMessage,Chat chat)
	{
		this.chat=chat;
		init(title, publicMessage);
	}
	//初始化界面
	public void init(String title,String publicMessage)
	{
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 352, 267);
		contentPane = new MyPanel("../img/QQ_Login.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel(title);
		Title.setFont(new Font("宋体", Font.BOLD, 16));
		Title.setBounds(15, 5, 237, 21);
		contentPane.add(Title);
		
		closeButton1 = new JButton("关闭(c)");
		closeButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		closeButton1.setBounds(247, 234, 83, 27);
		contentPane.add(closeButton1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 107, 345, 126);
		contentPane.add(scrollPane);
		
		textArea公告 = new JTextArea();
		scrollPane.setViewportView(textArea公告);
		textArea公告.setLineWrap(true);
		textArea公告.setFont(new Font("微软雅黑", Font.BOLD, 18));
		textArea公告.setBackground(new Color(255, 250, 250));
		textArea公告.setEditable(false);
		textArea公告.setText(publicMessage);
		
		closeButton = new JLabel("");
		closeButton.setIcon(new ImageIcon(PublicMessageFrame.class.getResource("/client/img/button/login_exit_1.png")));
		closeButton.setBounds(312, 0, 38, 18);
		contentPane.add(closeButton);
		
		openButton = new JButton("\u6253\u5F00");
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				showChat();
			}
		});
		openButton.setBounds(165, 234, 83, 27);
		contentPane.add(openButton);
		
		addEvent();
		showFrame();
	}
	
	/**
	 * 添加事件
	 */
	public void addEvent()
	{
		new MyLabel(closeButton).addEvent();
		closeButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				dispose();
			}
		});
	}
	/**
	 * 显示窗体
	 */
	public void showFrame()
	{
		this.setAlwaysOnTop(true);
		if(chat==null)
			openButton.setVisible(false);
		int width=350;
		int height=266;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(screenSize.width-width-3,screenSize.height,width,height);
		setVisible(true);
		Timer timer=new Timer(10, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				setLocation(getLocation().x, getLocation().y-10);
			}
		});
		timer.start();
		while(true)
		{
			System.out.println("当前高度："+getLocation().y+"，屏幕高度："+screenSize.height);
			if(getLocation().y+height<screenSize.height-25)
			{
				timer.stop();
				break;
			}
		}
	}
	/**
	 * 显示聊天窗体
	 */
	public void showChat()
	{
		if(chat!=null)
		{
			chat.setVisible(true);
			this.dispose();
		}
	}
}
