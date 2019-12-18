package com.zhangchu.fuckland.frame.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.zhangchu.fuckland.domain.MyFrame;
import com.zhangchu.fuckland.socket.Connect;

public class Fuckland extends MyFrame {

	private static final long serialVersionUID = 881792201169758236L;
	
	private static JButton jButton;
	public static JTextField jTextField;
	public static JLabel peoples;
	public static Fuckland fuckland;
	public static void main(String[] args) {
		fuckland = new Fuckland();
		fuckland.setLayout(null);
		fuckland.setSize(250, 200);
		fuckland.setLocationRelativeTo(null);// 设置居中，要放在设置窗口大小之后
		
		Font font=new Font("微软雅黑", 1, 13) ;
		
		JLabel jLabel=new JLabel("请输入姓名:");
		jLabel.setFont(font);
		jLabel.setBounds(10, 20, 70, 20);
		
		jTextField=new JTextField();
		jTextField.setFont(font);
		jTextField.setBounds(82, 20, 150, 20);
		
		JLabel jLabel2=new JLabel("当前人数:");
		jLabel2.setFont(font);
		jLabel2.setBounds(10, 60, 60, 20);
		
		peoples=new JLabel("0");
		peoples.setFont(font);
		peoples.setBounds(72, 60, 10, 20);
		
		JLabel jLabel3=new JLabel("(当人数到达3时,游戏开始)");
		jLabel3.setFont(font);
		jLabel3.setBounds(10, 80, 180, 20);
		
		jButton=new JButton("准     备");
		jButton.setBounds(10, 130, 225, 30);
		jButton.setFont(font);
		
		jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=jTextField.getText();
				if(name!=null&&name.length()>0){
					jButton.setEnabled(false);
					boolean b=Connect.connect();
					if(b){
						
					}
				}else{
					JOptionPane.showMessageDialog(null, new JLabel("<html><h2><font color='red'>请输入姓名</font></h2></html>"), "提示", JOptionPane.INFORMATION_MESSAGE); 
				}
			}
		});
		
		fuckland.add(jButton);
		fuckland.add(jLabel3);
		fuckland.add(peoples);
		fuckland.add(jLabel2);
		fuckland.add(jTextField);
		fuckland.add(jLabel);
		fuckland.repaint();
	}
	

}
