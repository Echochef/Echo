package com.zhangchu.fuckland.frame.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.zhangchu.fuckland.domain.Msg;
import com.zhangchu.fuckland.domain.MyFrame;
import com.zhangchu.fuckland.domain.Player;
import com.zhangchu.fuckland.domain.Poker;
import com.zhangchu.fuckland.servers.CanOutPoker;
import com.zhangchu.fuckland.servers.IsTruePoker;
import com.zhangchu.fuckland.servers.ListeningPaiShu;

public class MainFrame extends MyFrame {

	public static int myId;
	public static Msg leftmsg;
	public static Msg rightmsg;
	static JLabel touXiangLeft;
	static JLabel playerNameLeft;
	static JLabel shengPaiShuLeft;
	public static int shengLeft=0;
	
	static JLabel touXiangRight;
	static JLabel playerNameRight;
	static JLabel shengPaiShuRight;
	public static int shengRight=0;
	
	static JLabel touXaingMine;
	static JLabel playerNameMine;
	public static JLabel shengPaiShuMine;
	public static int shengCnt=0;
	
	public static JLabel naoZhong;
	
	
	public static JLabel out=null;
	public static JLabel pass=null;
	
	static JTextField textChat;
	static JButton buttonChatSend;
	
	public  static MainFrame mainFrame;
	private static JPanel pokerPanel;
	public static JPanel showOutPokerPanel;
	public static JPanel showOutPokerPanelLeft;
	public static JPanel showOutPokerPanelRight;
	private static List<JLabel> pokerLabels=new ArrayList<JLabel>();
	private static final long serialVersionUID = 1769789785860587426L;

	public static void startMain(List<Player> players,int my) {
		myId=players.get(my).getId();
		System.out.println("myid="+myId);
		leftmsg=new Msg();
		rightmsg=new Msg();
		
		mainFrame = new MainFrame();
		mainFrame.setLayout(null);
		mainFrame.setSize(1000, 600);
		mainFrame.setLocationRelativeTo(null);// ���þ��У�Ҫ�������ô��ڴ�С֮��
		
		
		setChatView(mainFrame);//���촰��
		setPlayerView(mainFrame);//��������Ϣ
		setPlayerRightView(mainFrame);//�Ҳ������Ϣ
		setOutPokerView(mainFrame);//���� pass ��ť
		setShowOutPokerView(mainFrame);//��ʾ���ȥ����
		setShowOutPokerLeftView(mainFrame);
		setShowOutPokerRightView(mainFrame);
		setIdentityView(mainFrame);
		setNaoZhong(mainFrame);
		
		
		setMyPokerView(mainFrame,players.get(my).getPokers());//�˿��б�
		
		setAllInfo(players,my);//��������Ϣ
		
		new ListeningPaiShu().start();
		JLabel bg=new JLabel();
		bg.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/mainBg.png")));
		bg.setBounds(0, 0, 800, 600);
		mainFrame.add(bg);
		mainFrame.repaint();
	}
	public static void setNaoZhong(MainFrame mainFrame){
		naoZhong=new JLabel();
		naoZhong.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/naozhong.png")));
		naoZhong.setForeground(Color.WHITE);
		naoZhong.setFont(new Font("΢���ź�", Font.BOLD, 22));
		naoZhong.setHorizontalTextPosition(JLabel.CENTER);
		
		//naoZhong.setVisible(false);
		mainFrame.add(naoZhong);
	}
	/**
	 * �ӷ�������ȡ�����Ϣ ��������Ϣ
	 * @param players
	 * @param my
	 */
	public static void setAllInfo(List<Player> players,int my){
		if(players.size()!=3){
			return ;
		}
		CanOutPoker.setCannotOut(out,pass);//���ò��ɳ���
		
		Player mine=players.get(my);
		playerNameMine.setText(mine.getName());
		shengCnt=mine.getPokers().size();
		shengPaiShuMine.setText(shengCnt+"");
		
		ImageIcon nongmin=new ImageIcon(MainFrame.class.getClassLoader().getResource("image/nongmin.png"));
		ImageIcon dizhu=new ImageIcon(MainFrame.class.getClassLoader().getResource("image/dizhu.png"));
		if(mine.getId()==0){
			//�ǵ���  1���Ҳ� 2�����
			touXaingMine.setIcon(dizhu);
			
			
			Player right = null;
			Player left = null;
			for(int i=0;i<players.size();i++){
				if(players.get(i).getId()==1){
					right=players.get(i);
				}else if(players.get(i).getId()==2){
					left=players.get(i);
				}
			}
			if(left==null||right==null){
				return;
			}
			touXiangLeft.setIcon(nongmin);
			playerNameLeft.setText(left.getName());
			shengLeft=left.getPokers().size();
			shengPaiShuLeft.setText(shengLeft+"");
			
			touXiangRight.setIcon(nongmin);
			playerNameRight.setText(right.getName());
			shengRight=right.getPokers().size();
			shengPaiShuRight.setText(shengRight+"");
			
			//�ǵ��� ��һ������  
			CanOutPoker.setCanOut(out, choose,pass);//���ÿ��Գ���
			pass.addMouseListener(null);//������һ�Ѳ����Բ�����
			pass.setEnabled(false);
			
			CanOutPoker.setCountShow(0,naoZhong);
		}else if (mine.getId()==1) {
			//��ũ��  0����� 2���Ҳ�
			CanOutPoker.setCountShow(2,naoZhong);
			touXaingMine.setIcon(nongmin);
			Player right = null;
			Player left = null;
			for(int i=0;i<players.size();i++){
				if(players.get(i).getId()==2){
					right=players.get(i);
				}else if(players.get(i).getId()==0){
					left=players.get(i);
				}
			}
			if(left==null||right==null){
				return;
			}
			
			touXiangLeft.setIcon(dizhu);
			playerNameLeft.setText(left.getName());
			shengLeft=left.getPokers().size();
			shengPaiShuLeft.setText(shengLeft+"");
			
			touXiangRight.setIcon(nongmin);
			playerNameRight.setText(right.getName());
			shengRight=right.getPokers().size();
			shengPaiShuRight.setText(shengRight+"");
		}else if (mine.getId()==2) {
			//��ũ�� 0���Ҳ� 1�����
			CanOutPoker.setCountShow(1,naoZhong);
			touXaingMine.setIcon(nongmin);
			
			Player right = null;
			Player left = null;
			for(int i=0;i<players.size();i++){
				if(players.get(i).getId()==0){
					right=players.get(i);
				}else if(players.get(i).getId()==1){
					left=players.get(i);
				}
			}
			if(left==null||right==null){
				return;
			}
			
			touXiangLeft.setIcon(nongmin);
			playerNameLeft.setText(left.getName());
			shengLeft=left.getPokers().size();
			shengPaiShuLeft.setText(shengLeft+"");
			
			touXiangRight.setIcon(dizhu);
			playerNameRight.setText(right.getName());
			shengRight=right.getPokers().size();
			shengPaiShuRight.setText(shengRight+"");
		}
	}
	/**
	 * ����Լ�����Ϣ
	 * @param mainFrame
	 */
	public static void setIdentityView(MainFrame mainFrame){
		JPanel jPanel=new JPanel();
		jPanel.setBounds(5,470,130,85);
		jPanel.setOpaque(false);
		//jPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		jPanel.setLayout(null);
		
		touXaingMine=new JLabel();
		touXaingMine.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/user.png")));
		touXaingMine.setBounds(2, 2, 60, 80);
		
		playerNameMine=new JLabel("��xx");
		playerNameMine.setForeground(Color.WHITE);
		playerNameMine.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		playerNameMine.setBounds(50, 2, 100, 30);
		
		JLabel shengYu=new JLabel("ʣ��:");
		shengYu.setForeground(Color.WHITE);
		shengYu.setFont(new Font("΢���ź�", Font.BOLD, 10));
		shengYu.setBounds(60, 30, 50, 20);
		
		shengPaiShuMine=new JLabel("00");
		shengPaiShuMine.setForeground(Color.WHITE);
		shengPaiShuMine.setFont(new Font("΢���ź�", Font.BOLD, 22));
		shengPaiShuMine.setHorizontalTextPosition(JLabel.CENTER);
		shengPaiShuMine.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/bg_shengPai.png")));
		shengPaiShuMine.setBounds(75, 50, 30, 30);
		
		
		jPanel.add(shengPaiShuMine);
		jPanel.add(shengYu);
		jPanel.add(playerNameMine);
		jPanel.add(touXaingMine);
		
		mainFrame.add(jPanel);
	}
	/**
	 * �������촰�����
	 * @param mainFrame
	 */
	public static void setChatView(MainFrame mainFrame){
		JPanel jPanel=new JPanel();
		jPanel.setBounds(800,0,200, 600);
		jPanel.setBackground(Color.darkGray);
		jPanel.setLayout(null);
		
		textChat=new JTextField();
		textChat.setBounds(0, 550, 140, 20);
		
		buttonChatSend=new JButton("����");
		buttonChatSend.setBounds(140,550,60, 20);
		jPanel.add(buttonChatSend);
		jPanel.add(textChat);
		
		mainFrame.add(jPanel);
		mainFrame.repaint();
	}
	/**
	 * ����������������
	 * @param mainFrame
	 */
	public static void setPlayerView(MainFrame mainFrame){
		JPanel jPanel=new JPanel();
		jPanel.setBounds(10,150,60,160);
		jPanel.setOpaque(false);
		//jPanel.setBorder(BorderFactory.createLineBorder(Color.white));
		jPanel.setLayout(null);
		
		touXiangLeft=new JLabel();
		touXiangLeft.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/user.png")));
		touXiangLeft.setBounds(5, 0, 60, 80);
		jPanel.add(touXiangLeft);
		
		playerNameLeft=new JLabel("��xx");
		playerNameLeft.setForeground(Color.WHITE);
		playerNameLeft.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		playerNameLeft.setBounds(5, 80, 100, 30);
		
		
		JLabel shengYu=new JLabel("ʣ��:");
		shengYu.setForeground(Color.WHITE);
		shengYu.setFont(new Font("΢���ź�", Font.BOLD, 10));
		shengYu.setBounds(5, 100, 50, 20);
		
		shengPaiShuLeft=new JLabel("00");
		shengPaiShuLeft.setForeground(Color.WHITE);
		shengPaiShuLeft.setFont(new Font("΢���ź�", Font.BOLD, 22));
		shengPaiShuLeft.setHorizontalTextPosition(JLabel.CENTER);
		shengPaiShuLeft.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/bg_shengPai.png")));
		shengPaiShuLeft.setBounds(13, 120, 30, 30);
		
		jPanel.add(shengYu);
		jPanel.add(playerNameLeft);
		jPanel.add(shengPaiShuLeft);
		
		mainFrame.add(jPanel);
		mainFrame.repaint();
	}
	
	/**
	 * �����Ҳ����������
	 * @param mainFrame
	 */
	public static void setPlayerRightView(MainFrame mainFrame){
		JPanel jPanel=new JPanel();
		jPanel.setBounds(740,150,60,160);
		jPanel.setOpaque(false);
		//jPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		jPanel.setLayout(null);
		
		
		touXiangRight=new JLabel();
		touXiangRight.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/user.png")));
		touXiangRight.setBounds(5, 0, 60, 80);
		jPanel.add(touXiangRight);
		
		playerNameRight=new JLabel("��xx");
		playerNameRight.setForeground(Color.WHITE);
		playerNameRight.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		playerNameRight.setBounds(5, 80, 100, 30);
		
		
		JLabel shengYu=new JLabel("ʣ��:");
		shengYu.setForeground(Color.WHITE);
		shengYu.setFont(new Font("΢���ź�", Font.BOLD, 10));
		shengYu.setBounds(5, 100, 50, 20);
		
		shengPaiShuRight=new JLabel("00");
		shengPaiShuRight.setForeground(Color.WHITE);
		shengPaiShuRight.setFont(new Font("΢���ź�", Font.BOLD, 22));
		shengPaiShuRight.setHorizontalTextPosition(JLabel.CENTER);
		shengPaiShuRight.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/bg_shengPai.png")));
		shengPaiShuRight.setBounds(13, 120, 30, 30);
		
		jPanel.add(shengYu);
		jPanel.add(playerNameRight);
		jPanel.add(shengPaiShuRight);
		
		mainFrame.add(jPanel);
		mainFrame.repaint();
	}
	
	/**
	 * �����˿���ʾ������
	 * @param mainFrame
	 */
	public static void setMyPokerView(MainFrame mainFrame,List<Poker> list){
		list=IsTruePoker.sortPokerByColor(list);
		pokerPanel=new JPanel();
		pokerPanel.setOpaque(false);
		//pokerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		pokerPanel.setBounds(130, 450, 680, 110);
		pokerPanel.setLayout(null);
		
		int x=list.size()*30-25;
		for(int i=0;i<list.size();i++){
			Poker poker=list.get(i);
			JLabel jLabel=new JLabel();
			jLabel.setText(poker.getColor()+"");
			jLabel.setName(poker.getId()+"");
			jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/poker/"+poker.getId()+".png")));
			jLabel.setBounds(x, 20, 62, 85);
			
			pokerLabels.add(jLabel);
			pokerPanel.add(jLabel,i);
			x=x-30;
		}
		mainFrame.add(pokerPanel);
		mainFrame.repaint();
		setPokerOnClick();
	}
	
	/**
	 * ���ó��� pass ������
	 * @param mainFrame
	 */
	public static void setOutPokerView(MainFrame mainFrame){
		JPanel outPokerPanel=new JPanel();
		outPokerPanel.setOpaque(false);
		outPokerPanel.setBounds(260, 410, 305, 35);
		outPokerPanel.setLayout(null);
		
		out=new JLabel();
		out.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/out1.png")));
		out.setBounds(0, 0, 135, 30);
		
		pass=new JLabel();
		pass.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/pass.png")));
		pass.setBounds(155, 0, 135, 30);
		
		outPokerPanel.add(out);
		outPokerPanel.add(pass);
		
		mainFrame.add(outPokerPanel);
		mainFrame.repaint();
	}
	
	/**
	 * ���ô��ȥ���� ��ʾ
	 * @param mainFrame
	 */
	public static void setShowOutPokerView(MainFrame mainFrame){
		showOutPokerPanel=new JPanel();
		showOutPokerPanel.setOpaque(false);
		showOutPokerPanel.setBounds(245, 300, 305, 110);
		showOutPokerPanel.setLayout(null);
		
		mainFrame.add(showOutPokerPanel);
		mainFrame.repaint();
	}
	/**
	 * ���������ȥ���� ��ʾ
	 * @param mainFrame
	 */
	public static void setShowOutPokerLeftView(MainFrame mainFrame){
		showOutPokerPanelLeft=new JPanel();
		showOutPokerPanelLeft.setOpaque(false);
		showOutPokerPanelLeft.setBounds(70, 60, 350, 110);
		showOutPokerPanelLeft.setLayout(null);
		
		mainFrame.add(showOutPokerPanelLeft);
		mainFrame.repaint();
	}
	/**
	 * �����Ҳ���ȥ���� ��ʾ
	 * @param mainFrame
	 */
	public static void setShowOutPokerRightView(MainFrame mainFrame){
		showOutPokerPanelRight=new JPanel();
		showOutPokerPanelRight.setOpaque(false);
		showOutPokerPanelRight.setBounds(400, 200, 330, 110);
		showOutPokerPanelRight.setLayout(null);
		
		mainFrame.add(showOutPokerPanelRight);
		mainFrame.repaint();
	}
	
	/**
	 * ��ʾ���ȥ����
	 * @param list
	 */
	public static void showOutPoker(){
		showOutPokerPanel.removeAll();
		int x=choose.size()*30-25;
		for(int i=0;i<choose.size();i++){
			JLabel jLabel=new JLabel();
			jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/poker/"+choose.get(i).getName()+".png")));
			jLabel.setBounds(x, 0, 62, 85);
			showOutPokerPanel.add(jLabel,i);
			x=x-15;
			pokerPanel.remove(choose.get(i));
		}
		choose.removeAll(choose);
		mainFrame.repaint();
	}
	/**
	 * ȡ������  ��ѡ����� ȫ������
	 * @param list
	 */
	public static void allPokerDown(List<JLabel> list){
		int size=list.size();
		if(list!=null&&size>0){
			for(int i=0;i<size;i++){
				JLabel onJlabel=list.get(i);
				if(onJlabel.getY()==0){
					onJlabel.setBounds(onJlabel.getX(),20, onJlabel.getWidth(), onJlabel.getHeight());
				}
			}
		}
	}
	/**
	 * ѡ��andȡ��ѡ���˿�
	 * @param onJlabel
	 */
	public static void setPokerUpOrDown(JLabel onJlabel){
		if(onJlabel.getY()==20){
			onJlabel.setBounds(onJlabel.getX(), 0, onJlabel.getWidth(), onJlabel.getHeight());
			choose.add(onJlabel);
		}else if(onJlabel.getY()==0){
			onJlabel.setBounds(onJlabel.getX(),20, onJlabel.getWidth(), onJlabel.getHeight());
			choose.remove(onJlabel);
		}
	}
	
	static List<JLabel> choose=new ArrayList<JLabel>();//�����ѡ����˿�
	static List<JLabel> duoXuan=new ArrayList<JLabel>();//Ҳ�Ǳ�ѡ��� ���ǿ�����ѡ�� ����ȡ��ѡ��
	static int duoXuanFalg=0;//�Ƿ��Ƕ�ѡ���
	/**
	 * �����˿˵���¼�
	 */
	public static void setPokerOnClick(){
		for(int i=0;i<pokerLabels.size();i++){
			pokerLabels.get(i).addMouseListener(new MouseListener() {
				public void setPokersUpOrDown(){
					if(duoXuan!=null&&duoXuan.size()>0){
						for(int i=0;i<duoXuan.size();i++){
							setPokerUpOrDown(duoXuan.get(i));
						}
					}
				}
				public void mouseReleased(MouseEvent e) {
					duoXuanFalg=0;
					setPokersUpOrDown();
					duoXuan=new ArrayList<JLabel>();
				}
				public void mousePressed(MouseEvent e) {
					//��갴��
					duoXuanFalg=1;
					duoXuan.add((JLabel)e.getSource());
				}
				public void mouseExited(MouseEvent e) {
					//����뿪
				}
				public void mouseEntered(MouseEvent e) {
					//�����ͣ
					if(duoXuanFalg==1){
						duoXuan.add((JLabel)e.getSource());
					}
				}
				public void mouseClicked(MouseEvent e) {
					// �����
				}
			});
		}
	}
	
	/**
	 * ��ʾ��������
	 */
	public static void showOtherOutPokerLeft(){
		showOutPokerPanelLeft.removeAll();
		List<Poker> list =leftmsg.getList();
		CanOutPoker.setCanOut(out, choose, pass);
		if(list==null||list.size()==0){
			//�����˲���
			JLabel jLabel=new JLabel();
			jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/passshow.png")));
			jLabel.setBounds(0, 60, 100, 50);
			showOutPokerPanelLeft.add(jLabel);
			
			//�ж�����Ҳ����Ҳ�ǲ��� �Լ��س�
			if(rightmsg.getList()==null||rightmsg.getList().size()==0){
				pass.addMouseListener(null);
				pass.setEnabled(false);
			}
		}else{
			int newSheng=shengLeft-list.size();
			shengPaiShuLeft.setText(newSheng+"");
			shengLeft=newSheng;
		}
		int x=list.size()*15+5;//350
		for(int i=0;i<list.size();i++){
			JLabel jLabel=new JLabel();
			jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/poker/"+list.get(i).getId()+".png")));
			jLabel.setBounds(x, 0, 62, 85);
			showOutPokerPanelLeft.add(jLabel,i);
			x=x-15;
		}
		showOutPokerPanel.removeAll();
		
		CanOutPoker.setCountShow(0, naoZhong);
		mainFrame.repaint();
	}
	
	/**
	 * ��ʾ�Ҳ������
	 */
	public static void showOtherOutPokerRight(){
		showOutPokerPanelRight.removeAll();
		List<Poker> list=rightmsg.getList();
		if(list==null||list.size()==0){
			//�Ҳ���˲���
			JLabel jLabel=new JLabel();
			jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/passshow.png")));
			jLabel.setBounds(230, 60, 100, 50);
			showOutPokerPanelRight.add(jLabel);
		}else{
			int newSheng=shengRight-list.size();
			shengPaiShuRight.setText(newSheng+"");
			shengRight=newSheng;
		}
		int x=270;//330
		for(int i=0;i<list.size();i++){
			JLabel jLabel=new JLabel();
			jLabel.setIcon(new ImageIcon(MainFrame.class.getClassLoader().getResource("image/poker/"+list.get(i).getId()+".png")));
			jLabel.setBounds(x, 0, 62, 85);
			showOutPokerPanelRight.add(jLabel,i);
			x=x-15;
		}
		showOutPokerPanelLeft.removeAll();
		CanOutPoker.setCountShow(2, naoZhong);
		mainFrame.repaint();
	}
	
	/**
	 * ������� ���� �Ҳ� poker
	 * @param who
	 * @param list
	 */
	public static void saveMsg(int who,List<Poker> list){
		// �����ж��Լ�ʲô���
		if (myId == 0) {
			// �ж��������
			if (who == 1) {
				// �Ҳ�
				rightmsg.setId(who);
				rightmsg.setList(list);
				showOtherOutPokerRight();
			} else if (who == 2) {
				// ���
				leftmsg.setId(who);
				leftmsg.setList(list);
				showOtherOutPokerLeft();
			}
		} else if (myId == 1) {
			if (who == 0) {
				// ���
				leftmsg.setId(who);
				leftmsg.setList(list);
				showOtherOutPokerLeft();
			} else if (who == 2) {
				// �Ҳ�
				rightmsg.setId(who);
				rightmsg.setList(list);
				showOtherOutPokerRight();
			}
		} else if (myId == 2) {
			if (who == 1) {
				// ���
				leftmsg.setId(who);
				leftmsg.setList(list);
				showOtherOutPokerLeft();
			} else if (who == 0) {
				// �Ҳ�
				rightmsg.setId(who);
				rightmsg.setList(list);
				showOtherOutPokerRight();
			}
		}
	}
	
}

