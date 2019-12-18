package com.zhangchu.fuckland.servers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.alibaba.fastjson.JSON;
import com.zhangchu.fuckland.domain.Msg;
import com.zhangchu.fuckland.domain.Poker;
import com.zhangchu.fuckland.frame.main.MainFrame;
import com.zhangchu.fuckland.socket.Connect;

public class OutMouseEvent implements MouseListener{

	private JLabel out;
	private List<JLabel> choose;
	public OutMouseEvent(JLabel out,List<JLabel> choose){
		this.choose=choose;
		this.out=out;
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
		out.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/out1.png")));
	}
	public void mouseEntered(MouseEvent e) {
		out.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/out3.png")));
	}
	public void mouseClicked(MouseEvent e) {
		int size=choose.size();
		System.out.println("点击了出牌按钮        牌数："+size);
		//玩家点击了出牌按钮  判断choose是否有值；判断是否符合出牌标准；判断是否符合上一家出牌标准；判断是否大过上家；根据choose清除JLabel;清空choose；发送信息至服务器
		if(choose!=null&&choose.size()>0){
			String s=IsTruePoker.isTruePoker(JLabelToPoker.toPokerList(choose));
			System.out.println(s);
			//当s！=ERROR 说明满足游戏规则
			//需要再判断是否比上家出牌大 或者 是否自己先出
			if(!s.equals(IsTruePoker.ERROR)){
				List<Poker> list=JLabelToPoker.toPokerList(choose);
				System.out.println("上家的牌: "+MainFrame.leftmsg.getList());
				if(IsBigger.isBigger(MainFrame.leftmsg.getList(),MainFrame.rightmsg.getList(), list)){
					System.out.println("允许出牌");
					System.out.println(JSON.toJSONString(new Msg(MainFrame.myId,list)));
					Connect.sendMes.setMsg(JSON.toJSONString(new Msg(MainFrame.myId,list)));
					
				}
			}
		}
	}
	

}
