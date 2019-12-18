package com.zhangchu.fuckland.servers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.alibaba.fastjson.JSON;
import com.zhangchu.fuckland.domain.Msg;
import com.zhangchu.fuckland.frame.main.MainFrame;
import com.zhangchu.fuckland.socket.Connect;

public class PassMouseEvent implements MouseListener{
	
	private JLabel pass;
	private List<JLabel> choose;
	public PassMouseEvent(JLabel pass,List<JLabel> choose){
		this.choose=choose;
		this.pass=pass;
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		pass.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/pass.png")));
	}
	public void mouseEntered(MouseEvent e) {
		pass.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/pass1.png")));
	}
	public void mouseClicked(MouseEvent e) {
		//玩家选择不出  将choose中选择的扑克取消选择 清空choose 发送不出信息至服务器
		MainFrame.allPokerDown(choose);
		choose.removeAll(choose);
		Connect.sendMes.setMsg(JSON.toJSONString(new Msg(MainFrame.myId,null)));
	}
}
