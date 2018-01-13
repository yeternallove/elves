package cn.edu.zucc.elves.controller;

import cn.edu.zucc.elves.exception.ParamException;
import cn.edu.zucc.elves.model.info.Information;
import cn.edu.zucc.elves.model.user.User;
import cn.edu.zucc.elves.service.task.UserSessionManger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	/**
	 * 登录
	 * @return
	 * @throws ParamException 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Information login(HttpServletRequest request,
							 HttpServletResponse response,
							 @RequestBody(required = false) User user) throws ParamException {
		Information information= null;

		return information;
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Object logout(HttpServletRequest request,
			HttpServletResponse response) {
		String sessionId = request.getSession().getId();
		UserSessionManger.remove(sessionId);
		return null;
	}


}
