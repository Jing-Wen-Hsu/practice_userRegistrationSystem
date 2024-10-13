package com.user_registration_system.user_registration_system.Service;


import com.user_registration_system.user_registration_system.Entity.User;
import com.user_registration_system.user_registration_system.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


//  續AuthController
//1.@Service 註解為Service層
//2.注入UserRepository實例
//3.Generate出UserService 有參數建構式
//4.定義創建用戶的方法
//5.註冊幾筆資料，並使用相同的email註冊，這時網頁會出現500錯誤。
    //--接下來要來解決顯示500錯誤的問題，當email已經註冊了，會在前端顯示錯誤訊息，而不是500錯誤。--
    //--回到UserRepository添加程式碼--
//6.添加透過email查詢是否已建立過用戶的方法
    //--回到AuthController.java添加程式碼--
//7.定義顯示註冊用戶的方法
    //--前往AuthController編寫顯示全體用戶的網頁

@Service
//宣告為UserService類，並且可被其他類訪問
public class UserService {
    private final UserRepository userRepository;
    //將一個 UserRepository 的實例注入到 UserService 中，使其可以使用 userRepository 進行數據庫操作。
        //是一個私有且不可變的成員變數，類型是 UserRepository。這個變數用來與數據庫進行交互，負責用戶數據的操作。

        //有參數建構式；當 UserService 的實例被創建時，Spring 會自動將 UserRepository 的實例注入進來。
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //創建用戶的方法
    public void createUser(User user) {
        userRepository.save(user); //調用 userRepository 的 save 方法，將 user 物件儲存到數據庫中，實現用戶的創建功能。
    }

    //6.透過email查詢是否已建立過用戶
    public  User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
     //可在AuthController.java中用這個搜尋有沒有人用過這個email


    //7.顯示註冊用戶
    public List<User> getAllUsers(){
        return userRepository.findAll();
    } //前往AuthController編寫顯示全體用戶的網頁

}
