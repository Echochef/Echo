package com.zhangchu.fuckland.servers;

import java.util.List;

import com.zhangchu.fuckland.domain.Poker;

public class IsBigger {
	public static boolean isBigger(List<Poker> leftList,List<Poker> rightList,List<Poker> choose){
		//如果上家不要 或者第一把出  list=null
		if(leftList==null||leftList.size()==0){
			if(rightList==null||rightList.size()==0){
				//两家都不要
				return true;
			}else{
				if(isRealBigger(rightList, choose)){
					return true;
				}
				return false;
			}
		}else{
			if(isRealBigger(leftList, choose)){
				return true;
			}
			return false;
		}
		
	}
	public static boolean isRealBigger(List<Poker> leftList,List<Poker> choose){
		// 首先判断牌型是不是一样
		String paiXing = IsTruePoker.isTruePoker(leftList);
		if (paiXing.equals(IsTruePoker.isTruePoker(choose))) {
			// 根据牌型来判断大小
			if (IsTruePoker.DANZHANG.equals(paiXing)) {
				// 单张
				if (isBiggerLast(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.WANGZHA.equals(paiXing)) {
				// 王炸
				// 开挂了
			} else if (IsTruePoker.DUIZI.equals(paiXing)) {
				// 对子
				if (isBiggerLast(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.SANGETOU.equals(paiXing)) {
				// 三张
				if (isBiggerLast(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.SANDAIYI.equals(paiXing)) {
				// 三带一
				if (isBiggerSan(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.SANDAIYIDUI.equals(paiXing)) {
				// 三带一对
				if (isBiggerSan(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.ZHADAN.equals(paiXing)) {
				// 炸弹
				if (isBiggerLast(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.SHUNZI.equals(paiXing)) {
				// 顺子
				if (isBiggerLast(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.LIANDUI.equals(paiXing)) {
				// 连对
				if (isBiggerLast(leftList, choose)) {
					return true;
				}
				return false;
			} else if (IsTruePoker.SHUANGFEI.equals(paiXing)) {
				// 双飞
				if (isBiggerSan(leftList, choose)) {
					return true;
				}
				return false;
			} 
		}else if(choose.size()==2){
			//判断是不是王炸
			if(IsTruePoker.isWangZha(choose)){
				return true;
			}
			return false;
		} else if(choose.size()==4){
			//判断是不是炸弹
			if(IsTruePoker.isSame(choose, 4)){
				return true;
			}
			return false;
		}
		return false;
	}
	
	public static boolean isBiggerLast(List<Poker> list,List<Poker> choose){
		if(list.get(list.size()-1).getColor()<choose.get(choose.size()-1).getColor()){
			return true;
		}
		return false;
	}
	
	public static boolean isBiggerSan(List<Poker> list,List<Poker> choose){
		int a=san(list);
		int b=san(choose);
		if(a==-1||b==-1){
			return false;
		}
		if(b>a){
			return true;
		}
		return false;
	}
	
	public static int san(List<Poker> list){
		for(int i=0;i<list.size()-2;i++){
			int a=list.get(i).getColor();
			int b=list.get(i+1).getColor();
			int c=list.get(i+2).getColor();
			if(a==b&&a==c){
				return a;
			}
		}
		return -1;
	}
}
