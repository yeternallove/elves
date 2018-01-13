package cn.edu.zucc.elves.controller;

import cn.edu.zucc.elves.exception.ParamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
        String errorMessage ;
    	if(e instanceof ParamException) {
    		ParamException paramException = (ParamException) e;
			response.setStatus(paramException.getHttpCode());
    		errorMessage = paramException.getErrorMessage();
    	} else if (e instanceof NoHandlerFoundException) {
    		response.setStatus(HttpStatus.NOT_FOUND.value());
    		errorMessage = e.getMessage();
		} else {
			logger.error("服务器内部错误", e);
    		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    		errorMessage = "服务器内部错误";
//			errorMessage=e.getMessage();
		}
    	Map<String, String> error = new HashMap<String, String>();
    	error.put("errMsg", errorMessage);
        return error;
    }
}