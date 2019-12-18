package com.zhangchu.fuckland.servers;

import com.alibaba.fastjson.JSON;
import com.zhangchu.fuckland.domain.Msg;
import com.zhangchu.fuckland.frame.main.MainFrame;
import com.zhangchu.fuckland.socket.Connect;

public class ListeningPaiShu extends Thread{
	
	public void run() {
		while(true){
			if(MainFrame.shengCnt==0){
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(MainFrame.myId==0){
			//地主胜利
			Connect.sendMes.setMsg(JSON.toJSONString(new Msg(999,null)));
		}else{
			//农民胜利
			Connect.sendMes.setMsg(JSON.toJSONString(new Msg(888,null)));
		}
		
	
	}

}
