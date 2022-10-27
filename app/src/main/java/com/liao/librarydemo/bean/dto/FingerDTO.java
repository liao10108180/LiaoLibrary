package com.liao.librarydemo.bean.dto;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-16
 * @Description: description class
 */

@Entity
public class FingerDTO {
    @Id(autoincrement = true)
    private Long id;

    private String fingerSign;

    @Generated(hash = 467343103)
    public FingerDTO(Long id, String fingerSign) {
        this.id = id;
        this.fingerSign = fingerSign;
    }

    @Generated(hash = 479865768)
    public FingerDTO() {
    }

    public String getFingerSign() {
        return this.fingerSign;
    }

    public void setFingerSign(String fingerSign) {
        this.fingerSign = fingerSign;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FingerDTO{" +
                "id=" + id +
                ", fingerSign='" + fingerSign + '\'' +
                '}';
    }
}
