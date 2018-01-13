package cn.edu.zucc.elves.model.info;

/**
 * @Author:eternallove
 * @Description:
 * @Date: Created in 2018/1/13
 **/
public class ErrorInfo implements Information {
    private String errMsg;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
