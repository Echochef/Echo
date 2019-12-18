package client.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QunChatFrame extends JFrame
{
	private JPanel contentPane;
	//发送、关闭按钮
	public JButton sendButton;
	public JButton closeButton;
	//发送、接收消息框
	public JTextPane textPaneSend;
	public JTextPane textPaneGet;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					QunChatFrame frame = new QunChatFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QunChatFrame()
	{
		setBackground(SystemColor.controlHighlight);
		setTitle("聊天室");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 576);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelLeft, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
					.addGap(209))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelLeft, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
		);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(SystemColor.controlHighlight);
		
		JPanel panelMiddle = new JPanel();
		
		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_panelLeft = new GroupLayout(panelLeft);
		gl_panelLeft.setHorizontalGroup(
				gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 476, Short.MAX_VALUE)
				.addComponent(panelTop, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
				.addComponent(panelMiddle, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
		);
		gl_panelLeft.setVerticalGroup(
				gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLeft.createSequentialGroup()
					.addComponent(panelTop, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelMiddle, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
		);
		panelTop.setLayout(null);
		
		//头像
		JLabel headImg = new JLabel("");
		headImg.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/QQ_64.png")));
		headImg.setBounds(6, 6, 64, 64);
		panelTop.add(headImg);
		
		JLabel video = new JLabel("");
		video.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_video_54.png")));
		video.setBounds(82, 16, 54, 54);
		panelTop.add(video);
		
		JLabel voice = new JLabel("");
		voice.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_voice_54.png")));
		voice.setBounds(138, 16, 54, 54);
		panelTop.add(voice);
		
		JLabel sendFile = new JLabel("");
		sendFile.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_sendfile_54.png")));
		sendFile.setBounds(193, 16, 54, 54);
		panelTop.add(sendFile);
		panelMiddle.setLayout(new BorderLayout(0, 0));
		
		textPaneGet = new JTextPane();
		textPaneGet.setEditable(false);
		textPaneGet.setBackground(SystemColor.controlHighlight);
		textPaneGet.setOpaque(false);
		panelMiddle.add(textPaneGet);
		
		textPaneSend = new JTextPane();
		textPaneSend.setBackground(SystemColor.controlHighlight);
		
		sendButton = new JButton("\u53D1\u9001");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		sendButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		
		closeButton = new JButton("\u5173\u95ED");
		closeButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		
		//发送表情
		JLabel faceImg = new JLabel("");
		faceImg.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_face_32.png")));
		//发送图片
		JLabel picture = new JLabel("");
		picture.setIcon(new ImageIcon(QunChatFrame.class.getResource("/client/img/chat/fun_picture_32.png")));
		
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
				gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addContainerGap(286, Short.MAX_VALUE)
					.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addContainerGap()
					.addComponent(faceImg)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(picture)
					.addContainerGap(396, Short.MAX_VALUE))
				.addComponent(textPaneSend, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
		);
		gl_panelBottom.setVerticalGroup(
				gl_panelBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(faceImg)
						.addComponent(picture))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPaneSend, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.LEADING, false)
						.addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panelBottom.setLayout(gl_panelBottom);
		panelLeft.setLayout(gl_panelLeft);
		contentPane.setLayout(gl_contentPane);
	}
}
