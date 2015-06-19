package com.example.bean;

import android.widget.TextView;

public class curse {
	public int id;
	public  String name;
	public  String xuliehao;
	public  String bianma;
	public  String teacher;
	public  String time_adress;
	public  String times;
	public  String xuefen;
	public  String status;
	public  String action;
	public String total;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getXuliehao() {
		return xuliehao;
	}
	public void setXuliehao(String xuliehao) {
		this.xuliehao = xuliehao;
	}
	public String getBianma() {
		return bianma;
	}
	public void setBianma(String bianma) {
		this.bianma = bianma;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getTime_adress() {
		return time_adress;
	}
	public void setTime_adress(String time_adress) {
		this.time_adress = time_adress;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getXuefen() {
		return xuefen;
	}
	public void setXuefen(String xuefen) {
		this.xuefen = xuefen;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "curse [name=" + name + ", xuliehao=" + xuliehao + ", bianma="
				+ bianma + ", teacher=" + teacher + ", time_adress="
				+ time_adress + ", times=" + times + ", xuefen=" + xuefen
				+ ", status=" + status + ", action=" + action + ", total="
				+ total + "]";
	}

}
