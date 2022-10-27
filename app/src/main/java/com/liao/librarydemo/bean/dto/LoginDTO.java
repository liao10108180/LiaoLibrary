package com.liao.librarydemo.bean.dto;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-16
 * @Description: description class
 */

@Entity
public class LoginDTO {
    @Id(autoincrement = true)
    private Long id;

    private String account;

    private String password;

    private String department;

    private String departmentCode;

    @Generated(hash = 1040855987)
    public LoginDTO(Long id, String account, String password, String department,
            String departmentCode) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.department = department;
        this.departmentCode = departmentCode;
    }

    @Generated(hash = 1173114744)
    public LoginDTO() {
    }


    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentCode() {
        return this.departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", departmentCode='" + departmentCode + '\'' +
                '}';
    }
}
