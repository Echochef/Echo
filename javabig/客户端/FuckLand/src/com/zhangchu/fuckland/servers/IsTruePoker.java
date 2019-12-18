package com.zhangchu.fuckland.servers;

import java.util.ArrayList;
import java.util.List;


import com.zhangchu.fuckland.domain.Poker;

public class IsTruePoker {

	public final static String DANZHANG = "DANZHANG";
	public final static String WANGZHA = "WANGZHA";
	public final static String DUIZI = "DUIZI";
	public final static String SANGETOU = "SANGETOU";
	public final static String SANDAIYI = "SANDAIYI";
	public final static String SANDAIYIDUI = "SANDAIYIDUI";
	public final static String ZHADAN = "ZHADAN";
	public final static String SHUNZI = "SHUNZI";
	public final static String LIANDUI = "LIANDUI";
	public final static String SHUANGFEI = "SHUANGFEI";
	public final static String ERROR = "ERROR";
	
	public static String isTruePoker(List<Poker> list){
		int listCnt=list.size();
		list=sortByColor(list);
		if(listCnt==1){
			//单张
			return DANZHANG;
		}else if(listCnt==2){
			//对子
			if(isSame(list, listCnt)){
				return DUIZI;
			}
			//王炸
			if(isWangZha(list)){
				return WANGZHA;
			}
			return ERROR;
		}else if(listCnt==3){
			//三个头
			if(isSame(list, listCnt)){
				return SANGETOU;
			}
			return ERROR;
		}else if(listCnt==4){
			//炸弹
			if(isSame(list, listCnt)){
				return ZHADAN;
			}
			//三带一
			if(isSanDaiYi(list)){
				return SANDAIYI;
			}
			return ERROR;
		}else if(listCnt>=5){
			//顺子
			if(isShunZi(list)){
				return SHUNZI;
			}else if(isSanDaiYiDui(list)){
				//三代一对
				return SANDAIYIDUI;
			}else if(isLianDui(list)){
				//连对
				return LIANDUI;
			}else if(isShuangFei(list)){
				//双飞or双飞双带
				return SHUANGFEI;
			}
		}
		return ERROR;
	}
	
	public static boolean isWangZha(List<Poker> list){
		if((list.get(0).getColor()==999&&list.get(1).getColor()==888)||list.get(0).getColor()==888&&list.get(1).getColor()==999){
			return true;
		}
		return false;
	}
	/**
	 * 判断list内扑克是否相同
	 * @param list
	 * @param i
	 * @return
	 */
	public static boolean isSame(List<Poker> list,int i){
		for(int j=0;j<i-1;j++){
			int a=list.get(j).getColor();
			int b=list.get(j+1).getColor();
			if(a!=b){
				return false;
			}
		}
		return true;
	}
	
	/**
	 *  判断是否是三带一
	 * @param list
	 * @return
	 */
	public static boolean isSanDaiYi(List<Poker> list){
		List<Poker> temp=new ArrayList<Poker>();
		temp.addAll(list);
		if(isSame(temp, 3)){
			return true;
		}
		temp.remove(0);
		if(isSame(temp, 3)){
			return true;
		}
		return false;
	}
	
	/**
	 *  判断是否是三带一对
	 * @param list
	 * @return
	 */
	public static boolean isSanDaiYiDui(List<Poker> list){
		List<Poker> temp=new ArrayList<Poker>();
		temp.addAll(list);
		if(temp.size()==5){
			if(isSame(temp, 3)){
				temp.remove(0);
				temp.remove(0);
				temp.remove(0);
				if(isSame(temp, 2)){
					return true;
				}
				return false;
			}
			if(isSame(temp, 2)){
				temp.remove(0);
				temp.remove(0);
				if(isSame(temp, 3)){
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 根据Color排序 
	 * @param list
	 * @return
	 */
	public static List<Poker> sortByColor(List<Poker> list){
		for(int i=0;i<list.size();i++){
			for(int j=i;j<list.size();j++){
				int a=list.get(i).getColor();
				int b=list.get(j).getColor();
				if(a>b){
					Poker temp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据Color排序  List<Poker>
	 * @param list
	 * @return
	 */
	public static List<Poker> sortPokerByColor(List<Poker> list){
		for(int i=0;i<list.size();i++){
			for(int j=i;j<list.size();j++){
				int a=list.get(i).getColor();
				int b=list.get(j).getColor();
				if(a>b){
					Poker temp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		return list;
	}
	/**
	 * 判断是否是顺子
	 * @param list
	 * @return
	 */
	public static boolean isShunZi(List<Poker> list){
		for(int i=0;i<list.size()-1;i++){
			int a=list.get(i).getColor();
			int b=list.get(i+1).getColor();
			if(b-a!=1){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断是否是连对
	 * @param list
	 * @return
	 */
	public static boolean isLianDui(List<Poker> list){
		int size=list.size();
		if(size<6&&size%2!=0){
			return false;
		}
		for(int i=0;i<size;i++){
			int a=list.get(i).getColor();
			int b=list.get(i+1).getColor();
			if(a!=b){
				return false;
			}
			i++;
		}
		for(int i=0;i<size;i++){
			int a=list.get(i).getColor();
			int b=list.get(i+2).getColor();
			if(b-a!=1){
				return false;
			}
			i++;
			i++;
		}
		return true;
	}
	
	/**
	 * 判断是不是双飞
	 * @param list
	 * @return
	 */
	public static boolean isShuangFei(List<Poker> list){
		List<Integer> cnt=feiCnt(list);
		if(cnt!=null&&cnt.size()>=2){
			int len=cnt.size();
			for(int i=0;i<len-1;i++){
				if(cnt.get(i+1)-cnt.get(i)!=1){
					return false;
				}
			}
			int dui=feiDuiCnt(list,cnt);
			if(dui==len||dui==0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断三个头有几个
	 * @param list
	 * @return
	 */
	public static List<Integer> feiCnt(List<Poker> list){
		List<Integer> cnt = new ArrayList<Integer>();
		for(int i=0;i<list.size()-2;i++){
			int a=list.get(i).getColor();
			int b=list.get(i+1).getColor();
			int c=list.get(i+2).getColor();
			if(a==b&&a==c){
				cnt.add(a);
			}
		}
		return cnt;
	}
	/**
	 * 判断双飞中对子有几个
	 * @param list
	 * @return
	 */
	public static int feiDuiCnt(List<Poker> list,List<Integer> fei){
		List<Poker> temp=new ArrayList<Poker>();
		temp.addAll(list);
		int cnt = 0;
		for(int i=0;i<temp.size();i++){
			for(int j=0;j<fei.size();j++){
				int a=list.get(i).getColor();
				if(a==fei.get(j)){
					temp.remove(i);
				}
			}
		}
		int size=temp.size();
		if(size>0&&size%2==0){
			for(int i=0;i<temp.size()-1;i++){
				int a=list.get(i).getColor();
				int b=list.get(i+1).getColor();
				if(a==b){
					cnt++;
				}
			}
		}
		return cnt;
	}
}
