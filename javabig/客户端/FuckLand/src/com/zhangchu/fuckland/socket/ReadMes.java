package com.zhangchu.fuckland.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhangchu.fuckland.domain.Player;
import com.zhangchu.fuckland.domain.Poker;
import com.zhangchu.fuckland.frame.main.Fuckland;
import com.zhangchu.fuckland.frame.main.MainFrame;
import com.zhangchu.fuckland.servers.CanOutPoker;

public class ReadMes extends Thread {
    private Socket socket;
 
    public ReadMes(Socket socket) {
        this.socket = socket;
    }
 
	public void run() {
		DataInputStream objInput = null;
		try {
			objInput = new DataInputStream(socket.getInputStream());
			while (true) {
				String s = (String) objInput.readUTF();
				if(s!=null&&s.length()>0){
					List<Player> players=new ArrayList<Player>();//游戏开始获取的玩家
					List<Poker> pokers=new ArrayList<Poker>();//出的牌
					int who=-1;//谁出的牌
					try{
						JSONArray jsonArray=JSONArray.parseArray(s);
						for(int i=0;i<jsonArray.size();i++){
							JSONObject jsonObject= (JSONObject) jsonArray.get(i);
							JSONArray array=jsonObject.getJSONArray("pokers");
							List<Poker> list=new ArrayList<Poker>();
							int pid=jsonObject.getInteger("id");
							boolean island=jsonObject.getBoolean("island");
							String pname=jsonObject.getString("name");
							Player player=new Player(pid, pname, null, island);
							for(int j=0;j<array.size();j++){
								JSONObject jsonObject1= (JSONObject) array.get(j);
								int id=jsonObject1.getInteger("id");
								String name=jsonObject1.getString("name");
								int color=jsonObject1.getInteger("color");
								boolean out=jsonObject1.getBoolean("out");
								Poker p=new Poker(color, id, name, out);
								list.add(p);
							}
							player.setPokers(list);
							players.add(player);
							
						}
					}catch(Exception e){
						//不是玩家列表
						try{
							JSONObject j=JSONObject.parseObject(s);
							who=j.getInteger("id");
							JSONArray ja=j.getJSONArray("list");
							
							System.out.println(who);
							
							if(who==888&&ja==null){
								if(MainFrame.myId==0){
									JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>失     败</font></h1></html>"), "游戏结束", JOptionPane.INFORMATION_MESSAGE); 									
								}else{
									JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>胜     利</font></h1></html>"), "游戏结束", JOptionPane.INFORMATION_MESSAGE); 									
								}
							}else if(who==999&&ja==null){
								if(MainFrame.myId==0){
									JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>胜     利</font></h1></html>"), "游戏结束", JOptionPane.INFORMATION_MESSAGE); 									
								}else{
									JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>失     败</font></h1></html>"), "游戏结束", JOptionPane.INFORMATION_MESSAGE); 									
								}
							}
							if(who>=0&&ja==null){
								// 上家不要
								if(who==MainFrame.myId){
									//自己不要的
									CanOutPoker.setCannotOut(MainFrame.out,MainFrame.pass);
									CanOutPoker.setCountShow(1, MainFrame.naoZhong);
									MainFrame.showOutPokerPanelRight.removeAll();
									
									JLabel jLabel=new JLabel();
									jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/passshow.png")));
									jLabel.setBounds(0,60, 100, 50);
									MainFrame.showOutPokerPanel.add(jLabel);
									MainFrame.mainFrame.repaint();
								}else{
									MainFrame.saveMsg(who, pokers);
								}
								continue;
							}
							for(int i=0;i<ja.size();i++){
								JSONObject jj=ja.getJSONObject(i);
								int id=jj.getInteger("id");
								int color=jj.getInteger("color");
								String name=jj.getString("name");
								boolean out=jj.getBooleanValue("out");
								Poker p=new Poker(color, id, name, out);
								pokers.add(p);
							}
							if(who>=0&&pokers!=null&&pokers.size()>0){
								//开始处理 接收到的牌
								if(who==MainFrame.myId){
									//自己打出去的牌
									MainFrame.showOutPoker();//显示打出去的牌  从牌堆清除
									//设置不允许出牌
									CanOutPoker.setCannotOut(MainFrame.out,MainFrame.pass);
									CanOutPoker.setCountShow(1, MainFrame.naoZhong);
									MainFrame.showOutPokerPanelRight.removeAll();
									
									int newSheng=MainFrame.shengCnt-pokers.size();
									MainFrame.shengPaiShuMine.setText(newSheng+"");
									MainFrame.shengCnt=newSheng;
									
								}else{
									MainFrame.saveMsg(who, pokers);
								}
								//妈蛋 5毛钱的都不让加
//								//如果是炸弹 加5毛钱的特效
//								if(pokers.size()==2&&IsTruePoker.isWangZha(pokers)){
//									new BoomTheard().start();
//								}else if(pokers.size()==4){
//									if(IsTruePoker.isSame(pokers, 4)){
//										new BoomTheard().start();
//									}
//								}
							}
						}catch(Exception ee){
							if(Integer.parseInt(s)<5){
								Fuckland.peoples.setText(s);
							}
							continue;
						}
					}
					//是玩家列表
					if(players.size()==3){
						//开局
						for(int i=0;i<players.size();i++){
							if(players.get(i).getName().equals(Fuckland.jTextField.getText().trim())){
								MainFrame.startMain(players,i);
								Fuckland.fuckland.dispose();
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage()!=null)
				JOptionPane.showMessageDialog(null, new JLabel("<html><h1><font color='red'>"+e.getMessage()+"</font></h1></html>"), "错误", JOptionPane.ERROR_MESSAGE); 				
			
		} finally {
			try {
				if(objInput!=null)
					objInput.close();
			} catch (IOException e) {
			}
		}
    }
 
}