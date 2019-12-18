package com.zhangchu.fuckland.domain;

import java.io.Serializable;
import java.util.List;

public class Msg implements Serializable{

	private static final long serialVersionUID = -3911518404188182583L;
	
	private int id;
	private List<Poker> list;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Poker> getList() {
		return list;
	}
	public void setList(List<Poker> list) {
		this.list = list;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Msg [id=" + id + ", list=" + list + "]";
	}
	public Msg(int id, List<Poker> list) {
		super();
		this.id = id;
		this.list = list;
	}
	public Msg() {
		super();
	}
	

}
