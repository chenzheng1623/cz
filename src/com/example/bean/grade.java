package com.example.bean;

import android.widget.TextView;

public class grade {


	public  String name;
	public  String type;
	public  String normal;
	public  String end;
	public  String total;
	public  String xuefen;
	public  String all;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNormal() {
		return normal;
	}
	public void setNormal(String normal) {
		this.normal = normal;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getXuefen() {
		return xuefen;
	}
	public void setXuefen(String xuefen) {
		this.xuefen = xuefen;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	
	@Override
	public String toString() {
		return "grade [name=" + name + ", type=" + type + ", normal=" + normal
				+ ", end=" + end + ", total=" + total + ", xuefen=" + xuefen
				+ ", all=" + all + "]";
	}
	
}
