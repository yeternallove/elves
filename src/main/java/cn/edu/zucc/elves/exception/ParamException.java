package cn.edu.zucc.elves.exception;

public class ParamException extends Exception {

	// 异常code
	private int httpCode;
	// 异常信息
	private String errorMessage;
	
	public ParamException(){}
	
	public ParamException(int httpCode) {
		this.httpCode = httpCode;
	}
	
	public ParamException(int httpCode, String errorMessage) {
		this.httpCode = httpCode;
		this.errorMessage = errorMessage;
	}
	
	public int getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
