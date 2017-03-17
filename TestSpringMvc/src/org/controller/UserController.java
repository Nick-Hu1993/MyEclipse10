package org.controller;

import java.util.ArrayList;

import org.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	@RequestMapping("/getJson")
	@ResponseBody
	public Object getJson() throws Exception {
		ArrayList<Object> li = new ArrayList();
		li.add(new Product("nick", "admin"));
		return li;
	}
}
