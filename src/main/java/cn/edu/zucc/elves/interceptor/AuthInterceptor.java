package cn.edu.zucc.elves.interceptor;

import cn.edu.zucc.elves.service.task.UserSessionManger;
import cn.edu.zucc.elves.model.user.UserView;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class AuthInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
    public static final String error = "/ErrorInfo/";
    public static final String loginUrl = "/login";

    // public static final String indexUrl="/index";

    private void addHeadAndPrintWriteMsg(HttpServletResponse response,
                                         String errorMsg) throws IOException {
        if (response != null && StringUtils.isNotEmpty(errorMsg)) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader(
                    "Access-Control-Allow-Headers",
                    "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
            response.addHeader("Access-Control-Allow-Methods",
                    "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(errorMsg);
            out.flush();
            out.close();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        boolean authFlag = true;
//        String contextPath = request.getContextPath();
//        String res = request.getRequestURI();
//        String requestUrl = "";
//        if (res.startsWith(contextPath)) {
//            requestUrl = res.substring(contextPath.length(), res.length());
//        }
//        if (!requestUrl.endsWith("/")) {
//            requestUrl = requestUrl + "/";
//        }
//        // 因此对login情况做排除
//        if (requestUrl.contains(loginUrl) || error.equals(requestUrl)) {
//            return true;
//        }
//        if (handler instanceof HandlerMethod) {
//            String sessionId = request.getSession().getId();
//            // 登录验证
//            if (sessionId == null) {
//                return true;
//            }
//            JSONObject json;
//            UserView user = UserSessionManger.get(sessionId);
//            if (user == null) {
//                authFlag = false;
//                logger.info("用户没有登录");
//                json = new JSONObject();
//                json.put("ErrorInfo", "用户没有登录");
//                json.put("errorCode", "401");
//                addHeadAndPrintWriteMsg(response, json.toString());
//            }
//            //有@auth注解的接口需要做权限验证
////            Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
////            if (auth != null) {
////                authFlag = false;
////                // 页面权限认证
////                if (!authFlag) {
////                    authFlag = false;
////                    logger.Information("权限认证失败");
////                    json.put("ErrorInfo", "权限认证失败");
////                    json.put("errorCode", "402");
////                    addHeadAndPrintWriteMsg(response, json.toString());
////                }
//        }
////    }
        return authFlag;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
