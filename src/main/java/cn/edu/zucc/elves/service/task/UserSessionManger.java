package cn.edu.zucc.elves.service.task;

import cn.edu.zucc.elves.model.user.UserView;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class UserSessionManger {
	private Logger logger = LoggerFactory.getLogger(UserSessionManger.class);
	private static Map<String, UserView> userInfoMap = new HashMap<>();
	private int USER_SESSION_TIME_OUT = 30;//默认30分钟
	@Autowired
	private Environment environment;

	@Scheduled(fixedRate = 60 * 1000)
	public void checkTimeOut() {
		try {
			String time = environment.getProperty("session.timeout");
			if(StringUtils.isNotEmpty(time)) {
				USER_SESSION_TIME_OUT = Integer.parseInt(time);
			}
			Iterator<Map.Entry<String, UserView>> entries = userInfoMap.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<String, UserView> entry = entries.next();
				String key = entry.getKey();
				UserView userView = entry.getValue();
				long lastUpdateTime = userView.getLastUpdateTime();
				if ((System.currentTimeMillis() - lastUpdateTime) >= USER_SESSION_TIME_OUT * 60 * 1000) {
					entries.remove();
					logger.info("清除超时登录用户，sessionId：" + key + ", username: " + userView.getRealName());
				}
			}
//			Set<String> timeOutSet = new HashSet<>();
//			for (String key : userInfoMap.keySet()) {
//				UserView userView = userInfoMap.get(key);
//				long lastUpdateTime = userView.getLastUpdateTime();
//				if ((System.currentTimeMillis() - lastUpdateTime) >= USER_SESSION_TIME_OUT*60*1000) {
//					timeOutSet.add(key);
//				}
//			}
//
//			for (String key : timeOutSet) {
//				UserView user = userInfoMap.get(key);
//				userInfoMap.remove(key);
//				// 清楚该登录用户的查询数据
//				logger.Information("清除超时登录用户，sessionId：" + key + ", username: "
//						+ user.getRealName());
//			}
		} catch (Exception e) {
			logger.error("检查会话超时出错", e);
		}
	}

	/**
	 * 根据userId查询sessionId
	 * 
	 * @param userId
	 * @return 用户id对应的有session，则返回sessionId,否则返回null
	 */
	public static String getSessionIdByUserId(long userId) {
		if (!userInfoMap.isEmpty()) {
			for (String key : userInfoMap.keySet()) {
				UserView userView = userInfoMap.get(key);
				if (userView != null && userView.getId() == userId) {
					return key;
				}
			}
		}
		return null;
	}

	public static void add(String sessionId, UserView user) {
		user.setLastUpdateTime(System.currentTimeMillis());
		userInfoMap.put(sessionId, user);
	}

	public static void remove(String sessionId){
		userInfoMap.remove(sessionId);
	}

	public static UserView get(String sessionId){
		UserView userView = userInfoMap.get(sessionId);
		if (userView != null) {
			userView.setLastUpdateTime(System.currentTimeMillis());
		}
		return userView;
	}
}
