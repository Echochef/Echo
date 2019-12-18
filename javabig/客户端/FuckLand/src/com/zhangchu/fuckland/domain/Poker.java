package com.zhangchu.fuckland.domain;

import java.io.Serializable;

public class Poker implements Serializable{

	private static final long serialVersionUID = 489483938802054094L;
	
	private int id;
	private String name;
	private int color;
	private boolean isOut;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public boolean isOut() {
		return isOut;
	}
	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Poker( int color,int id, String name, boolean isOut) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.isOut = isOut;
	}
	public Poker() {
		super();
	}
	public String toString() {
		return "Poker [id=" + id + ", name=" + name + ", color=" + color
				+ ", isOut=" + isOut + "]"+"\n";
	}
	
	
}
