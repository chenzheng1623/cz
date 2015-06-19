package com.example.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.example.bean.coursetableday;
import com.example.bean.curse;
import com.example.bean.grade;

import android.app.AlertDialog;
import android.util.Log;

public class praserhtml {

	private Document doc = null;
	//要访问的网址的成绩url
	private String gradeURL=null;
	//要访问的网址的课程url
	private String courseURL=null;
	//要访问网址的string
	private String html;
	public praserhtml(String gradeURL,int timeout){
		this.gradeURL=gradeURL;
		try {
			URL url = new URL(gradeURL);
			doc = Jsoup.parse(url, timeout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public praserhtml( String html) {
		this.html=html;
		doc=Jsoup.parse(html);
	}



	/**
	 * 解析成绩
	 * @return
	 */
	public List<grade> praserchengji(){
		Elements trs=doc.select("tr");
		List<grade> listchengji=new ArrayList<grade>();
		Log.i("trsize", "---"+trs.size());
		//遍历每一行
		for (int row = 1; row < trs.size()-3; row++) {
			//获取这一行的，所有td
			Elements tds=trs.get(row).select("td");
			//每一行都封装成一个成绩	
			grade grade=new grade();
			//遍历每一行的，每一个td
			Log.i("tag", "------------tds"+tds.size());
			for (int td = 0; td < tds.size(); td++) {
				Element elename =tds.get(td);//拿到td
				String value=elename.text();//拿到td的值
				switch (td) {
				case 0:
					grade.setAll(value);
					break;
				case 2:
					grade.setName(value);
					break;
				case 3:
					grade.setType(value);
					break;
				case 6:
					grade.setNormal(value);
					break;
				case 8:
					grade.setEnd(value);
					break;
				case 9:
					grade.setTotal(value);
					break;
				case 11:
					grade.setXuefen(value);
					break;
				}
			}
			listchengji.add(grade);
		}
		for (grade grade : listchengji) {
		}

		return listchengji;
	}

	public List<grade> pchengji(){

		return null;
	}
	/**
	 * 解析出所有的课程
	 * @param cz
	 * @return
	 */
	public List<curse> prasercourse(){
		//		URL url;
		//		Document doc;
		Elements trs = null;
		//		try {
		//			url = new URL(cz);
		//			doc=Jsoup.parse(url, 50000);
		trs=doc.select("tr");
		//		} catch (MalformedURLException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
		List<curse> listchengji=new ArrayList<curse>();

		for (int i = 1; i < trs.size(); i++) {
			Elements tds=trs.get(i).select("td");
			curse course=new curse();
			for (int j = 0; j < tds.size(); j++) {
				Element elename=tds.get(j);
				String name=elename.text();
				switch (j) {
				case 0:
					if (i==trs.size()-1) {
						course.setTotal(name);
					}else {
						course.setXuliehao(name);
					}
					break;
				case 1:
					course.setBianma(name);
					break;
				case 2:
					course.setName(name);
					break;
				case 3:
					course.setTeacher(name);
					break;
				case 4:
					course.setTime_adress(name);
					break;
				case 5:
					course.setTimes(name);
					break;
				case 6:
					course.setXuefen(name);
					break;
				case 7:
					course.setStatus(name);
					break;
				case 8:
					course.setAction(name);
					break;
				}
			}

			listchengji.add(course);
		}
		return listchengji;
	}

	public List<coursetableday> getCoursetables(){
		//		URL url;
		//		Document doc;
		Elements trs = null;
		//		try {
		//			url = new URL(cz);
		//			doc=Jsoup.parse(url, 50000);
		trs=doc.select("tr");
		//		} catch (MalformedURLException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//			Log.i("jsoup", "网络超时异常");
		//		}
		List<coursetableday> listtable=new ArrayList<coursetableday>();
		for (int i = 1; i < trs.size(); i++) {
			Elements tds=trs.get(i).select("td");
			coursetableday table=new coursetableday();
			for (int j = 1; j < tds.size(); j++) {
				String aa=tds.get(j).text();
				String[] valString1=aa.split("班级");
				String valString=valString1[0];
				if(valString==" "|| valString.equals(null)){
					valString="亲！！\n这个时间没有克课哦\n尽情的玩耍吧";
				}
				switch (j) {
				case 1:
					table.setOne(valString);
					break;
				case 2:
					table.setTwo(valString);
					break;
				case 3:
					table.setThree(valString);
					break;
				case 4:
					table.setFour(valString);
					break;
				case 5:
					table.setFive(valString);
					break;

				}
			}
			listtable.add(table);

		}


		return listtable;

	}

}
