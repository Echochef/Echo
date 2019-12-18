package client.frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import client.frame.MyPanel;
import javax.swing.JLabel;
//import com.sun.awt.AWTUtilities;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	//用户名、密码、头像
	public JTextField textFieldUserName;
	public JPasswordField UserPwd;
	public JLabel headImg;
	//记住密码、自动登录按钮
	public JCheckBox checkBoxMemoryPwd;
	public JCheckBox checkBoxSelfLogin;
	//登录、注册按钮
	public JLabel loginButton;
	public JLabel registerButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame()
	{
		setTitle("chat");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/client/img/QQ_64.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 308);
		
		contentPane = new MyPanel("../img/registerBGimg2.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserPwd = new JPasswordField();
		UserPwd.setEchoChar('●');
		UserPwd.setBounds(104, 163, 201, 26);
		contentPane.add(UserPwd);
		
		headImg = new JLabel("");
		headImg.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/headImage/head_boy_01_64.jpg")));
		headImg.setBounds(18, 127, 64, 64);
		contentPane.add(headImg);
		
		checkBoxMemoryPwd = new JCheckBox("记住密码");
		checkBoxMemoryPwd.setBounds(104, 198, 76, 18);
		contentPane.add(checkBoxMemoryPwd);
		
		checkBoxSelfLogin = new JCheckBox("自动登录");
		checkBoxSelfLogin.setBounds(229, 198, 76, 18);
		contentPane.add(checkBoxSelfLogin);
		
		loginButton = new JLabel("");
		loginButton.setIcon(new ImageIcon(LoginFrame.class.getResource("/client/img/button/button_login_1.png")));
		loginButton.setBounds(161, 240, 69, 22);
		contentPane.add(loginButton);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setBounds(104, 128, 201, 26);
		contentPane.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		registerButton = new JLabel("注册账号");
		registerButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
		registerButton.setForeground(new Color(0, 51, 255));
		registerButton.setBounds(0, 244, 55, 18);
		contentPane.add(registerButton);
		
		JLabel lblNewLabel = new JLabel("Welcom TO Chat…");
		lblNewLabel.setBackground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Franklin Gothic Heavy", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel.setBounds(48, 27, 257, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账号");
		lblNewLabel_1.setBounds(306, 133, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel label = new JLabel("密码");
		label.setBounds(306, 168, 58, 15);
		contentPane.add(label);

	}
}
