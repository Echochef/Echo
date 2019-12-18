package server.frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import client.control.Main;

import com.MyTools;
import javax.swing.ImageIcon;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	ServerMana serverMana;
	
    //主方法
	public static void main(String[] args) 
	{
		new MainWindow().setVisible(true);
	}

	//服务器界面
	public MainWindow() 
	{
		setTitle("server");
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		
		setBounds((size.width-753)/2, (size.height-500)/2, 500, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		serverMana=new ServerMana();
		tabbedPane.addTab("服务管理", new ImageIcon(MainWindow.class.getResource("/client/img/manager_server.png")), serverMana, null);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
              int n=JOptionPane.showConfirmDialog(null,"确认退出吗?","确认对话框",
                                       JOptionPane.YES_NO_OPTION );
              if(n==JOptionPane.YES_OPTION)  
                 System.exit(0);
            }});
	    validate();  
	}
}
