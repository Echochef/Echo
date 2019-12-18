package com.zhangchu.fucklandserver.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.zhangchu.fuckland.domain.Player;
import com.zhangchu.fuckland.domain.Poker;

public class Main {
	public static int flag=0;
	public static final int PORT = 8089;//监听的端口号     
	
	private static List<Player> players;
	private static List<Poker> allPoker;
	public static void main(String[] args) {
		allPoker=new ArrayList<>();
		players=new ArrayList<>();
		setPoker();
		
	    Main server = new Main();    
	    server.init();    
	}
	
	public void init() {    
        try {    
            @SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(10000,3,InetAddress.getByName("127.0.0.1"));    
            while (true) {    
                // 一旦有堵塞, 则表示服务器与客户端获得了连接    
                Socket client = serverSocket.accept();    
                // 处理这次连接    
                new HandlerThread(client);    
            }    
        } catch (Exception e) {    
            System.out.println("服务器异常: " + e.getMessage());    
        }    
    }    
    
	   private class HandlerThread implements Runnable {    
	        private Socket socket;    
	        public HandlerThread(Socket client) {    
	            socket = client;
	            Player player=new Player();
	            player.setSocket(socket);
	            players.add(player);
	            new Thread(this).start();    
	        }    
	    
	        public void run() {    
	            try {    
	            	DataInputStream input = new DataInputStream(socket.getInputStream());   
	                while(true){
	                	String clientInputStr = (String) input.readUTF();
		                // 处理客户端数据    
		                System.out.println("客户端发过来的内容:" + clientInputStr);
		                if(flag==-1){
		                	sendMsg(clientInputStr);//群发
		                }
						if(flag==0){
							for(int i=0;i<players.size();i++){
			                	Player player=players.get(i);
			                	if(player.getName()!=null&&player.getName().length()>0){
			                		continue;
			                	}
			                	if(player.getSocket()==socket){
			                		player.setName(clientInputStr);
			                	}
			                }
							sendMsg(players.size()+"");//群发
							
							if(players.size()==3){
								randomPoker();
								flag=-1;
							}
						}
	                }
	            } catch (Exception e) {
	            	try{
	            		if(e.getMessage().equals("Connection reset")){
		            		for(int i=0;i<players.size();i++){
		            			if(players.get(i).getSocket()==socket){
		            				players.remove(i);
		            			}
		            		}
		            	}
	            	}catch(Exception ee){
	            		System.out.println("不做处理");
	            	}
	            	
	                System.out.println("服务器 run 异常: " + e.getMessage());    
	            } finally {    
	                if (socket != null) {    
	                    try {    
	                        socket.close();    
	                    } catch (Exception e) {    
	                        socket = null;    
	                        System.out.println("服务端 finally 异常:" + e.getMessage());    
	                    }    
	                }    
	            }   
	        }    
	    }    
	
    public static void sendMsg(String msg) throws IOException{
    	for(int i=0;i<players.size();i++){
    		DataOutputStream out = new DataOutputStream(players.get(i).getSocket().getOutputStream());
    		out.writeUTF(msg);
    	}
    }
	public static void setPoker(){
		Poker p1=new Poker(0, "dahou", 999, false);
		Poker p2=new Poker(1, "xiaohou", 888, false);
		
		Poker p3=new Poker(2, "2", 777, false);
		Poker p4=new Poker(3, "2", 777, false);
		Poker p5=new Poker(4, "2", 777, false);
		Poker p6=new Poker(5, "2", 777, false);
		allPoker.add(p1);
		allPoker.add(p2); 
		allPoker.add(p3);
		allPoker.add(p4);
		allPoker.add(p5);
		allPoker.add(p6);
		int j=3;
		int cnt=0;
		for(int i=53;i>=6;i--){
			if(cnt==4){
				j++;
				cnt=0;
			}
			Poker p=new Poker(i, j+"", j, false);	
			allPoker.add(p);
			cnt++;
		}
	}
	
	public static void randomPoker(){
		Random random=new Random();
		List<Poker> all=new ArrayList<>();
		all=allPoker;//allPoker留着备用
		List<Poker> list1=new ArrayList<>();//第一个人的牌
		List<Poker> list2=new ArrayList<>();//第二个人的牌
		List<Poker> list3=new ArrayList<>();//第三个人的牌
		int pokers=54;
		for(int i=0;i<17;i++){
			int r=random.nextInt(pokers);
			list1.add(all.get(r));
			all.remove(r);
			pokers--;
		}
		for(int i=0;i<17;i++){
			int r=random.nextInt(pokers);
			list2.add(all.get(r));
			all.remove(r);
			pokers--;
		}
		for(int i=0;i<all.size();i++){
			list3.add(all.get(i));
		}
		
		//开始随机发给三个人
		int r = random.nextInt(9);
		Player p1=players.get(0);
		Player p2=players.get(1);
		Player p3=players.get(2);
		if (r >= 0 && r <= 2) {
			p1.setPokers(list1);
			p1.setId(1);//0-地主 1 2-农民
			r = random.nextInt(9)+1;
			if(r%2==0){
				p2.setPokers(list2);
				p3.setPokers(list3);
				p3.setIsland(true);
				p2.setId(2);
				p3.setId(0);
			}else{
				p3.setPokers(list2);
				p2.setPokers(list3);
				p2.setIsland(true);
				p2.setId(0);
				p3.setId(2);
			}
		} else if (r >= 3 && r <= 5) {
			p1.setPokers(list2);
			p1.setId(2);
			r = random.nextInt(9)+1;
			if(r%2==0){
				p2.setPokers(list1);
				p3.setPokers(list3);
				p3.setIsland(true);
				p2.setId(1);
				p3.setId(0);
			}else{
				p3.setPokers(list1);
				p2.setPokers(list3);
				p2.setIsland(true);
				p2.setId(0);
				p3.setId(1);
			}
		} else {
			p1.setPokers(list3);
			p1.setId(0);
			p1.setIsland(true);
			r = random.nextInt(9)+1;
			if(r%2==0){
				p2.setPokers(list2);
				p3.setPokers(list1);
				p2.setId(2);
				p3.setId(1);
			}else{
				p3.setPokers(list2);
				p2.setPokers(list1);
				p2.setId(1);
				p3.setId(2);
			}
		}
		for(int i=0;i<players.size();i++){
    		try {
    			DataOutputStream out = new DataOutputStream(players.get(i).getSocket().getOutputStream());
				out.writeUTF(JSON.toJSONString(players));
			} catch (Exception e) {
			}
    	}
	}
}
