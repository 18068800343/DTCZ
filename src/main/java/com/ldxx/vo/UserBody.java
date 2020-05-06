package com.ldxx.vo;

public class UserBody {
    private String USER_NAME;//姓名
    private String USER_ACCOUNT;//用户名
    private String PASSWORD;//密码 (base64加密)
    private String COMPANY_ID;//公司id
    private String COMPANY_NAME;//公司名称
    private String COMPANY_TYPE;//公司类型 0:控股公司 1路公司 2养护单位 3第三方单位
    private String TYPE; //0新增 1 删除 2 修改

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_ACCOUNT() {
        return USER_ACCOUNT;
    }

    public void setUSER_ACCOUNT(String USER_ACCOUNT) {
        this.USER_ACCOUNT = USER_ACCOUNT;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getCOMPANY_ID() {
        return COMPANY_ID;
    }

    public void setCOMPANY_ID(String COMPANY_ID) {
        this.COMPANY_ID = COMPANY_ID;
    }

    public String getCOMPANY_NAME() {
        return COMPANY_NAME;
    }

    public void setCOMPANY_NAME(String COMPANY_NAME) {
        this.COMPANY_NAME = COMPANY_NAME;
    }

    public String getCOMPANY_TYPE() {
        return COMPANY_TYPE;
    }

    public void setCOMPANY_TYPE(String COMPANY_TYPE) {
        this.COMPANY_TYPE = COMPANY_TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
