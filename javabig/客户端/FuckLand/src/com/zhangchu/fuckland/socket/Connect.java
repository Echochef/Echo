package com.zhangchu.fuckland.socket;

import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.zhangchu.fuckland.frame.main.Fuckland;


public class Connect {
	public static final String IP_ADDR = "127.0.0.1";// ��������ַ
	public static final int PORT = 8089;// �������˿ں�

	private static Socket socket;

	public static SendMes sendMes;
	public static boolean connect(){
		try {
			socket = new Socket(IP_ADDR, PORT);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>"+e.getMessage()+"</font></h1></html>"), "����", JOptionPane.ERROR_MESSAGE); 
		}
		new ReadMes(socket).start();
		
		sendMes=new SendMes(socket);
		sendMes.setMsg(Fuckland.jTextField.getText().trim());
		sendMes.start();
		
		
        System.out.println("���ӳɹ�");
		return true;
	}
}
