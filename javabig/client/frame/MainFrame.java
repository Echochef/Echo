package client.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MainFrame extends JFrame
{
	//头像
	public JLabel headImg;
	//在线状态
	public JComboBox comboBoxState;
	//用户名、个性签名
	public JLabel userName;
	public JLabel signate;
	//分组
	public JTree tree;
	private JTabbedPane tabbedPane;
	//好友列表、群组、最近模块
	private JPanel friendList;
	private JPanel groupChat;
	private JPanel recentlyChat;
	
	//用户信息面板、好友面板、底部面板
	private JPanel userinfomation;
	private JPanel friendsmode;
	private JPanel buttomMode;
	
	//个人管理
	private JButton btnNewButton;
	
	//好友面板相关按钮、右击菜单按钮
	private JScrollPane scrollPaneFridends;
	private JPopupMenu popupMenuFriendList;
	private JMenuItem menuItemSendInstantMessage;
	private JMenuItem menuItemCheckFriendInfo;
	private JMenuItem menuItemSendFile;
	private JMenuItem menuItemDeletUser;


	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		getContentPane().setBackground(SystemColor.controlHighlight);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/client/img/QQ_64.png")));
		setTitle("chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 600);
		//用户信息面板
		userinfomation = new JPanel();
		userinfomation.setBackground(SystemColor.controlHighlight);
		//好友列表面板
		friendsmode = new JPanel();
		friendsmode.setBackground(SystemColor.controlHighlight);
		//底部面板
		buttomMode = new JPanel();
		buttomMode.setBackground(SystemColor.controlHighlight);
		
		JLabel label = new JLabel();
		label.setBorder(null);
		label.setIcon(new ImageIcon(MainFrame.class.getResource("/client/img/button/QQ_settings_1.png")));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(userinfomation, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
				.addComponent(friendsmode, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(buttomMode, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(userinfomation, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(friendsmode, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addComponent(buttomMode, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
		);
		friendsmode.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		friendsmode.add(tabbedPane, BorderLayout.CENTER);
		
		friendList = new JPanel();
		tabbedPane.addTab("\u597D\u53CB", new ImageIcon(MainFrame.class.getResource("/client/img/friend_list.png")), friendList, null);
		friendList.setLayout(new BorderLayout(0, 0));
		
		scrollPaneFridends = new JScrollPane();
		friendList.add(scrollPaneFridends);
		
		tree = new JTree();
		tree.setBackground(SystemColor.controlHighlight);
		scrollPaneFridends.setViewportView(tree);
		
		popupMenuFriendList = new JPopupMenu();
		popupMenuFriendList.setBackground(SystemColor.controlHighlight);
		addPopup(tree, popupMenuFriendList);
		
		menuItemSendInstantMessage = new JMenuItem("发送消息");
		menuItemSendInstantMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				startChat(e);
			}
		});
		menuItemSendInstantMessage.setIcon(new ImageIcon(MainFrame.class.getResource("/client/img/QQ_16.png")));
		popupMenuFriendList.add(menuItemSendInstantMessage);
		
		menuItemSendFile = new JMenuItem("文件发送");
		menuItemSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				sendFile(e);
			}
		});
		popupMenuFriendList.add(menuItemSendFile);
		
		menuItemCheckFriendInfo = new JMenuItem("查看资料");
		menuItemCheckFriendInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				getFriendInfo(e);
			}
		});
		popupMenuFriendList.add(menuItemCheckFriendInfo);
		
		menuItemDeletUser = new JMenuItem("删除用户");
		menuItemDeletUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				deleteFriend(e);
			}
		});
		popupMenuFriendList.add(menuItemDeletUser);

		groupChat = new JPanel();
		tabbedPane.addTab("\u7FA4\u7EC4", new ImageIcon(MainFrame.class.getResource("/client/img/friend_qun.png")),  groupChat, null);
		
		JButton grouChatRoom = new JButton("进入聊天室");
		grouChatRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				gotoChatRoom();
			}
		});
		grouChatRoom.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		
		JButton buildGroupChatRoom = new JButton("创建聊天室");
		buildGroupChatRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				buildNewChatRoom();
			}
		});
		buildGroupChatRoom.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		GroupLayout groupChat1 = new GroupLayout(groupChat);
		 groupChat1.setHorizontalGroup(
				 groupChat1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,  groupChat1.createSequentialGroup()
					.addContainerGap()
					.addGroup( groupChat1.createParallelGroup(Alignment.TRAILING)
						.addComponent(buildGroupChatRoom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
						.addComponent(grouChatRoom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
					.addContainerGap())
		);
		 groupChat1.setVerticalGroup(
				 groupChat1.createParallelGroup(Alignment.LEADING)
				.addGroup( groupChat1.createSequentialGroup()
					.addContainerGap()
					.addComponent(grouChatRoom, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(buildGroupChatRoom, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		groupChat.setLayout(groupChat1);
		
		recentlyChat = new JPanel();
		tabbedPane.addTab("\u6700\u8FD1", new ImageIcon(MainFrame.class.getResource("/client/img/friend_history.png")), recentlyChat, null);
		buttomMode.setLayout(null);
		
		userinfomation.setLayout(null);
		headImg = new JLabel("");
		headImg.setIcon(new ImageIcon(MainFrame.class.getResource("/client/img/headImage/head_boy_01_64.jpg")));
		headImg.setBounds(15, 15, 64, 64);
		userinfomation.add(headImg);
		
		comboBoxState = new JComboBox();
		comboBoxState.setBounds(91, 15, 58, 28);
		userinfomation.add(comboBoxState);
		
		userName = new JLabel("浅雨");
		userName.setFont(new Font("黑体", Font.BOLD, 18));
		userName.setBounds(159, 18, 90, 25);
		userinfomation.add(userName);
		
		signate = new JLabel("你好！");
		signate.setBounds(91, 55, 210, 18);
		userinfomation.add(signate);
		
		btnNewButton = new JButton("个人管理");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setBounds(224, 73, 97, 23);
		userinfomation.add(btnNewButton);
		getContentPane().setLayout(groupLayout);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				changeInfomation(e);
			}
		});
	}
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			//鼠标按下事件
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			//鼠标松开事件
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			//显示菜单
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	/**
	 * 开始聊天
	 */
	public void startChat(ActionEvent e)
	{
	}
	/**
	 * 获取好友资料
	 * @param e
	 */
	public void getFriendInfo(ActionEvent e)
	{
		
	}
	/**
	 * 个人信息管理
	 */
	public void changeInfomation(ActionEvent e)
	{
		
	}
	/**
	 * 发送文件
	 * @param e
	 */
	public void sendFile(ActionEvent e)
	{
		
	}
	/**
	 * 删除好友
	 * @param e
	 */
	public void deleteFriend(ActionEvent e)
	{
		
	}
	/**
	 * 进入聊天室
	 */
	public void gotoChatRoom()
	{
		
	}
	
	/**
	 * 新建聊天室
	 */
	public void buildNewChatRoom()
	{
		
	}
	
}