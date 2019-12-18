package client.frame;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import client.common.MyLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class ChatFrame extends JFrame
{
	//关闭、发送按钮
	private JButton jButtonClose;
	public JButton jButtonSend;
	
	//基本按钮
	public JLabel jLabelQQ空间;
	public JLabel jLabelAddFriends;
	public JLabel jLabelSendFile;
	public JLabel jLabelSendImg;
	private JLabel jLabelHeadImg;
	public JLabel jLabel我的好友;
	public JLabel jLabelChatHistory;
	public JLabel jLabelSmileImg;
	public JLabel jLabelVideo;
	
	//页面面板，发送、左侧、聊天、顶部
	private JPanel jPanelSendMode;
	private JPanel jPanelLeftMode;
	private JPanel jPanelChatMode;
	private JPanel jPanelTopMode;
	
	//滚动框
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane3;
	public JTextPane jTextPaneSendMessage;
	public JTextPane jTextPaneGetMessage;
	
//	public static void main(String[] args) {
//		new ChatFrame().setVisible(true);
//	}
	
	public ChatFrame()
	{
		initComponents();
	}

	private void initComponents()
	{
		jPanelLeftMode = new JPanel();
		jPanelTopMode = new JPanel();
		jPanelChatMode = new JPanel();
		jLabelHeadImg = new JLabel();
		jLabelQQ空间 = new JLabel();
		jLabel我的好友 = new JLabel();
		jLabelVideo = new JLabel();
		jLabelAddFriends = new JLabel();
		
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setBackground(SystemColor.controlHighlight);
		
		jTextPaneGetMessage = new JTextPane();
		jTextPaneGetMessage.setBackground(SystemColor.controlHighlight);
		
		jPanelSendMode = new JPanel();
		jButtonSend = new JButton();
		jButtonClose = new JButton();
		
		jLabelSmileImg = new JLabel();
		jLabelSmileImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				selectFace();
			}
		});
		jLabelSendImg = new JLabel();
		jLabelSendImg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				sendImg();
			}
		});
		jLabelChatHistory = new JLabel();
		jLabelChatHistory.setBackground(SystemColor.controlHighlight);
		
		jScrollPane3 = new JScrollPane();
		jScrollPane3.setBackground(SystemColor.controlHighlight);
		
		jTextPaneSendMessage = new JTextPane();
		jTextPaneSendMessage.setBackground(SystemColor.controlHighlight);
		jTextPaneSendMessage.setBorder(null);
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u804a\u5929");
		setBackground(SystemColor.controlHighlight);
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				formWindowClosing(evt);
			}
		});

		jPanelLeftMode.setBackground(SystemColor.controlHighlight);

		jPanelTopMode.setBackground(SystemColor.controlHighlight);

		jLabelHeadImg.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/QQ_64.png"))); // NOI18N

		jLabelQQ空间.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_qzone_54.png"))); // NOI18N

		jLabel我的好友.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_myfeeds_54.png"))); // NOI18N

		jLabelVideo.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_video_54.png"))); // NOI18N
		jLabelVideo.setToolTipText("\u5f00\u59cb\u89c6\u9891");

		jLabelAddFriends.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_add_54.png"))); // NOI18N

		GroupLayout jPanelTopModeLayout = new GroupLayout(
				jPanelTopMode);
		jPanelTopModeLayout.setHorizontalGroup(
				jPanelTopModeLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanelTopModeLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabelHeadImg)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jLabelVideo)
					.addGap(18)
					.addComponent(jLabelQQ空间)
					.addGap(18)
					.addComponent(jLabel我的好友)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jLabelAddFriends)
					.addContainerGap(140, Short.MAX_VALUE))
		);
		jPanelTopModeLayout.setVerticalGroup(
				jPanelTopModeLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanelTopModeLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanelTopModeLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jLabelHeadImg)
						.addGroup(jPanelTopModeLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(jLabelVideo)
							.addComponent(jLabelQQ空间)
							.addComponent(jLabel我的好友)
							.addComponent(jLabelAddFriends)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		jPanelTopMode.setLayout(jPanelTopModeLayout);

		jPanelChatMode
		.setLayout(new java.awt.BorderLayout());

		jTextPaneGetMessage.setEditable(false);
		jScrollPane1.setViewportView(jTextPaneGetMessage);

		jPanelChatMode.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		jPanelSendMode.setBackground(SystemColor.controlHighlight);

		jButtonSend.setText("发送");
		jButtonSend.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jButtonSendActionPerformed(evt);
			}
		});
		
		jButtonClose.setText("关闭");
		jButtonClose.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();//关闭聊天室
			}
		});

		
		JPanel jPanelToolMode = new JPanel();
		jPanelToolMode.setBackground(SystemColor.controlHighlight);

		jLabelSmileImg.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_face_32.png"))); // NOI18N
		jLabelSmileImg.setToolTipText("\u8868\u60c5");

		jLabelSendImg.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_picture_32.png"))); // NOI18N
		jLabelSendImg.setToolTipText("\u53d1\u9001\u56fe\u7247");

		jLabelChatHistory.setIcon(new ImageIcon(getClass().getResource(
				"/client/img/chat/fun_message_history_32.png"))); // NOI18N
		jLabelChatHistory.setText("\u804a\u5929\u8bb0\u5f55");
		jLabelChatHistory
				.setToolTipText("\u6253\u5f00\u6211\u7684\u804a\u5929\u8bb0\u5f55");
		jLabelSendFile = new JLabel();
		
		jLabelSendFile.setIcon(new ImageIcon(getClass().getResource(
						"/client/img/chat/fun_sendfile_54.png"))); // NOI18N
		jLabelSendFile.setToolTipText("\u53d1\u9001\u6587\u4ef6");
		jLabelSendFile.addMouseListener(new java.awt.event.MouseAdapter()
				{
					public void mouseClicked(java.awt.event.MouseEvent evt)
					{
						jLabelSendFileMouseClicked(evt);
					}
				});

		GroupLayout jPanelToolModeLayout = new GroupLayout(
				jPanelToolMode);
		jPanelToolModeLayout.setHorizontalGroup(
				jPanelToolModeLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanelToolModeLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabelSmileImg)
					.addGap(18)
					.addComponent(jLabelSendImg)
					.addGap(18)
					.addComponent(jLabelSendFile, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
					.addComponent(jLabelChatHistory))
		);
		jPanelToolModeLayout.setVerticalGroup(
				jPanelToolModeLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanelToolModeLayout.createSequentialGroup()
					.addGroup(jPanelToolModeLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, jPanelToolModeLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(jLabelSendFile, 0, 0, Short.MAX_VALUE))
						.addComponent(jLabelChatHistory, Alignment.LEADING)
						.addComponent(jLabelSmileImg, Alignment.LEADING)
						.addComponent(jLabelSendImg, Alignment.LEADING))
					.addContainerGap())
		);
		jPanelToolMode.setLayout(jPanelToolModeLayout);

		jScrollPane3.setViewportView(jTextPaneSendMessage);

		GroupLayout jPanel发送面板Layout = new GroupLayout(
				jPanelSendMode);
		jPanelSendMode.setLayout(jPanel发送面板Layout);
		jPanel发送面板Layout
				.setHorizontalGroup(jPanel发送面板Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addComponent(jPanelToolMode,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								jPanel发送面板Layout
										.createSequentialGroup()
										.addContainerGap(321, Short.MAX_VALUE)
										.addComponent(
												jButtonClose,
												GroupLayout.PREFERRED_SIZE,
												76,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButtonSend,
												GroupLayout.PREFERRED_SIZE,
												77,
												GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5))
						.addComponent(jScrollPane3,
								GroupLayout.DEFAULT_SIZE, 486,
								Short.MAX_VALUE));
		jPanel发送面板Layout
				.setVerticalGroup(jPanel发送面板Layout
						.createParallelGroup(
								GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								jPanel发送面板Layout
										.createSequentialGroup()
										.addComponent(
												jPanelToolMode,
												GroupLayout.PREFERRED_SIZE,
												36,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												GroupLayout.PREFERRED_SIZE,
												96,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10)
										.addGroup(
												jPanel发送面板Layout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																jButtonSend,
																GroupLayout.DEFAULT_SIZE,
																35,
																Short.MAX_VALUE)
														.addComponent(
																jButtonClose,
																GroupLayout.DEFAULT_SIZE,
																35,
																Short.MAX_VALUE))
										.addContainerGap()));

		GroupLayout jPanel左侧面板Layout = new GroupLayout(
				jPanelLeftMode);
		jPanel左侧面板Layout.setHorizontalGroup(
			jPanel左侧面板Layout.createParallelGroup(Alignment.LEADING)
				.addComponent(jPanelSendMode, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jPanelChatMode, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
				.addComponent(jPanelTopMode, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		jPanel左侧面板Layout.setVerticalGroup(
			jPanel左侧面板Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel左侧面板Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jPanelTopMode, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(jPanelChatMode, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jPanelSendMode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		jPanelLeftMode.setLayout(jPanel左侧面板Layout);

		GroupLayout layout = new GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(jPanelLeftMode,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanelLeftMode,
						GroupLayout.Alignment.TRAILING,
						GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt)
	{
		// TODO add your handling code here:
		beforeClose();
	}

	private void jLabelSendFileMouseClicked(MouseEvent evt)
	{
		// TODO add your handling code here:
		sendFile();
	}

	private void jButtonSendActionPerformed(ActionEvent evt)
	{
		// TODO add your handling code here:
		sendMessage();
	}
	private void jButtonCloseActionPerformed(ActionEvent evt)
	{
		// TODO add your handling code here:
		sendMessage();
	}

	public javax.swing.JTextPane getjTextPane聊天记录()
	{
		return jTextPaneGetMessage;
	}

	public void setjTextPane聊天记录(javax.swing.JTextPane ChatHistory)
	{
		this.jTextPaneGetMessage = ChatHistory;
	}

	/**
	 * 发送消息，空的方法，由子类实现
	 */
	public void sendMessage()
	{
		
	}
	
	public void beforeClose(){}
	/**
	 * 选择表情
	 */
	public void selectFace(){}
	/**
	 * 处理消息中的表情
	 */
	public void dealIcon(String str)
	{
		
	}
	/**
	 * 发送文件
	 */
	public void sendFile()
	{
		
	}

	/**
	 * 发送图片
	 */
	public void sendImg()
	{
		
	}

}