package client.frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.MyTools;
import com.MyTools.Flag;

import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;

import client.control.Login;
import client.control.Main;
import client.control.Register;
import client.socket.CS_TCP;

import server.common.JDBC;
import server.dao.IUser;
import server.dao.UserDao;
import server.entity.Users;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class personalMana extends JFrame{
	JDBC con = new JDBC();
	public JPanel contentPane;
	//用户名、密码、重复密码
	public JTextField txtName;
	public JPasswordField pwd;
	public JPasswordField pwdRe;
	//Email、生日、性别单选
	public JTextField txtEmail;
	public JTextField txtbirthday;
	public JComboBox comGender;
	//个性签名、头像单选
	public JTextArea txtSignat;
	public JComboBox comboBoxHeadImage;
	//注册按钮
	public JButton btnRegister;
	
	public CS_TCP cs_TCP;//客户端与服务器的TCP连接
	Main main=null;
	Users users = null;
	
	String gender1 = "男";
	String gender2 = "女";
	
//	public static void main(String[] args) {
//		new personalMana(null).setVisible(true);
//	}
	public personalMana(Main main)
	{
		this.main = main;
		users = queryByName(main.userName.getText());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				beforeClose();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/client/img/QQ_64.png")));
		setTitle(users.getName()+"个人管理窗口");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(592, 553);
		
		contentPane = new MyPanel("../img/registerBGimg2.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUserName = new JLabel("用户名:");
		lbUserName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbUserName.setBounds(22, 45, 70, 24);
		contentPane.add(lbUserName);
		
		JLabel lbPwd = new JLabel("密码:");
		lbPwd.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbPwd.setBounds(38, 96, 54, 30);
		contentPane.add(lbPwd);
		
		JLabel lbPwdre = new JLabel("重复密码:");
		lbPwdre.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbPwdre.setBounds(22, 138, 79, 35);
		contentPane.add(lbPwdre);
		
		JLabel lbEmail = new JLabel("E-mail:");
		lbEmail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbEmail.setBounds(22, 234, 70, 28);
		contentPane.add(lbEmail);
		
		JLabel lbBirthday = new JLabel("生日:");
		lbBirthday.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbBirthday.setBounds(38, 285, 46, 24);
		contentPane.add(lbBirthday);
		
		JLabel lbSignature = new JLabel("个性签名:");
		lbSignature.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbSignature.setBounds(22, 332, 79, 28);
		contentPane.add(lbSignature);
		
		btnRegister = new JButton("立即修改");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(0, 100, 0));
		btnRegister.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnRegister.setBounds(57, 431, 161, 60);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("返回");
		btnCancel.setForeground(new Color(255, 250, 250));
		btnCancel.setBackground(new Color(106, 90, 205));
		btnCancel.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cancle();
			}
		});
		btnCancel.setBounds(248, 431, 100, 60);
		contentPane.add(btnCancel);
		
		txtName = new JTextField(users.getName());
		txtName.setBounds(111, 40, 217, 35);
		txtName.setEditable(false); 
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		pwd = new JPasswordField(users.getPwd());
		pwd.setBounds(111, 92, 217, 35);
		contentPane.add(pwd);
		
		pwdRe = new JPasswordField(users.getPwd());
		pwdRe.setBounds(113, 138, 215, 35);
		contentPane.add(pwdRe);
		
		txtEmail = new JTextField(users.getEmail());
		txtEmail.setColumns(10);
		txtEmail.setBounds(111, 231, 217, 35);
		contentPane.add(txtEmail);
		
		if(users.getGender().equals("女")) {
			gender1 = "女";
			gender2 = "男";
		}
			
		comGender = new JComboBox();
		comGender.setModel(new DefaultComboBoxModel(new String[] {gender1, gender2}));
		comGender.setBounds(111, 185, 59, 35);
		contentPane.add(comGender);
		
		JLabel lbGender = new JLabel("性别:");
		lbGender.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbGender.setBounds(38, 190, 46, 24);
		contentPane.add(lbGender);
		
		txtSignat = new JTextArea(users.getSignature());
		txtSignat.setBounds(111, 328, 217, 75);
		contentPane.add(txtSignat);
		
		txtbirthday = new JTextField(users.getBirthday().toString());
		txtbirthday.setBounds(111, 281, 217, 35);
		contentPane.add(txtbirthday);
		txtbirthday.setColumns(10);
		
		comboBoxHeadImage = new JComboBox();
		comboBoxHeadImage.setBounds(375, 193, 138, 117);
		contentPane.add(comboBoxHeadImage);
		
		JLabel lblHead = new JLabel("头像选择:");
		lblHead.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblHead.setBounds(375, 139, 100, 32);
		contentPane.add(lblHead);
		
		JLabel lblNewLabel = new JLabel("个人管理");
		lblNewLabel.setBounds(253, 0, 249, 30);
		contentPane.add(lblNewLabel);
		
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
    					
    					String sex = gender1;
    					if(comGender.getSelectedIndex()==1)
    						sex = gender2;
    					cs_TCP.sendMessage(Flag.CHANGE+MyTools.FLAGEND//注册标志
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
	public void beforeClose()
	{
		if(cs_TCP!=null)
			cs_TCP.closeSocket();
	}
	
	/**
	 * 取消按钮事件
	 */
	public void cancle()
	{
		if(cs_TCP!=null)
			cs_TCP.closeSocket();
		this.dispose();
	}
	public void actionPerformed(ActionEvent e) 
	{
		
	}

	public Users queryByName(String userName)
	{
		Users user = null;
		ResultSet rs = con.query("select * from users where u_name=?",
				new String[]{userName});
		try 
		{
			// 开始遍历获取数据
			while (rs.next()) {
				user = new Users();// 如果找到数据，创建用户对象
				user.setId(rs.getInt("u_id"));
				user.setName(rs.getString("u_name"));
				user.setPwd(rs.getString("u_pwd"));
				user.setGender(rs.getString("u_gender"));
				user.setEmail(rs.getString("u_email"));
				user.setLastLogin(rs.getString("u_last_login"));
				user.setLastExit(rs.getString("u_last_exit"));
				user.setRemarke(rs.getString("u_remarke"));
				user.setSignature(rs.getString("u_signature"));
				user.setHeadImg(rs.getString("u_head_Img"));
				user.setBirthday(rs.getDate("u_birthday"));
				user.setType(rs.getString("u_type"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//con.closeConnection();// 关闭所有数据库对象
		}
		return user;
	}
}
