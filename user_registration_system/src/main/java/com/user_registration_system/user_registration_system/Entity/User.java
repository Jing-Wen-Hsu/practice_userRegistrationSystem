package com.user_registration_system.user_registration_system.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

//keyWord說明
//Entity 類似於資料庫中的表
//結構:id、email、password
//註解 @NotEmpty(message = "")  欄位不可為空(必填)，為空時 前端會顯示message的訊息。
//註解 @Email 須符合email格式(XXX@XXX)，且不可為空，
//註解 @GeneratedValue 通常與@Id一起用，用於定義主鍵生成策略->自增長；(strategy = GenerationType.IDENTITY)數據庫自增字段
//註解@Column(unique = true, nullable = false) 一個email只能申請一個會員帳號;
            // unique = true 該欄位值不能重複、nullable = false該欄位不可為null，即在插入或更新數據時，必須提供該欄位的值，不能留空。


//Coding步驟
//1.宣告層
//2.定義變數(id、email、passwd...等)
//2-1.為變數加上需有的註解
//3-1.Generate出建構式: (1)無參數、 (2)有參數
//3-2.Generate出Getter、Setter
//4.執行專案→確認資料庫中的表結構建立完成。


@Entity //宣告為Entity層
@Table(name = "users") //指定數據庫中的表名
public class User {
    @Id //註解Id為主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主鍵(ID)自增長，(strategy = GenerationType.IDENTITY)數據庫自增字段
    private long id;

    @Column(unique = true, nullable = false) //一個email只能申請一個會員帳號
    @NotEmpty(message = "email為必填項目")
    @Email
    private String email;

    @NotEmpty(message = "密碼為必填項目")
    private String password;

    public User() {
    }

    public User(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotEmpty(message = "email為必填項目") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "email為必填項目") @Email String email) {
        this.email = email;
    }

    public @NotEmpty(message = "密碼為必填項目") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "密碼為必填項目") String password) {
        this.password = password;
    }
}
