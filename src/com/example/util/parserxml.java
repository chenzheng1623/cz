package com.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.R.string;
import android.util.Xml;

public class parserxml {


	InputStream inputStream=null;

	public parserxml(InputStream inputStream) {
		this.inputStream=inputStream;
	}

	public List<Map<String, String>> parserlogin(){
		List<Map<String, String>> listinfo =new ArrayList<Map<String,String>>();
		Map<String , String> content = null;
		XmlPullParser parser=Xml.newPullParser();
		int ii=1;
		try {
			parser.setInput(inputStream, "utf-8");
			int  i=parser.getEventType();
			while(i!=XmlPullParser.END_DOCUMENT){
				switch (i) {
				case XmlPullParser.START_DOCUMENT:
					content=new HashMap<String, String>();
					break;
				case XmlPullParser.START_TAG:
					String name=parser.getName();
					if("INF".equals(name)){
						String aa=	parser.nextText();
						content.put("cz"+ii, aa);
						ii++;
					}
					break;
				case XmlPullParser.END_TAG:
					listinfo.add(content);
					break;
				}
			i=parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listinfo;


	}

}
