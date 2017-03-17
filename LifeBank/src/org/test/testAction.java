package org.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class testAction extends ActionSupport{
	private int id;
	
	private Object result;
	
	@Override
	public String execute() throws Exception {
		List li  = new ArrayList<>();
		
		if(id==0){		//chengong
			Map<String, String> map = new HashMap<String, String>();
			Map<String, String> map2 = new HashMap<String, String>();
			
			map.put("a","1");
			map.put("b","2");
			map.put("c","3");
			
			map2.put("result", "1");
			
			li.add(map);
			li.add(map2);
			result = li;
		}else{		//shibai 
			Map<String, String> map = new HashMap<String, String>();
			Map<String, String> map2 = new HashMap<String, String>();
			
			map.put("a","1");
			map.put("b","2");
			map.put("c","3");
			
			map2.put("result", "0");
			
			li.add(map);
			li.add(map2);
			result = li;
		}
		
		return SUCCESS;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}
