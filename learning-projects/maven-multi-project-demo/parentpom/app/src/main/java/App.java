package com.bm.learning.multimodule.app;

import com.bm.learning.multimodule.dto.User;
import com.bm.learning.multimodule.helpers.StringUtils;

public class App{
	public static void main(String[] args){
		User user = new User("karim");
		String userUpperCase = StringUtils.toUpper(user.getFirstName());
		System.out.println(userUpperCase);

	}
}
