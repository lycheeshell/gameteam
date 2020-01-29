package edu.nju.model;

/**
 * 不继承Entity类，直接由DBA在数据库管理Manager的数据
 * @Author ：lycheeshell
 * @Date ：Created in 22:07 2020/1/8
 */
public class Manager {

    private String managerId;
    private String account;
    private String password;
    private String phone;
    private String email;

    public Manager() {
        super();
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
