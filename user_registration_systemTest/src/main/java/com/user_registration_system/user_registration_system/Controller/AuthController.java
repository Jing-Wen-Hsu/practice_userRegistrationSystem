package com.user_registration_system.user_registration_system.Controller;

import com.user_registration_system.user_registration_system.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


//      Coding步驟
//0.建立AuthController.java
//  "Auth" 是 "Authentication"（身份驗證）和 "Authorization"（授權）的縮寫，通常用來指代與用戶身份相關的功能和過程。
//1.宣告為@Controller層


@Controller
public class AuthController {

    //顯示首頁
    @GetMapping("/")
    public String index() {
        return "index";
        //應有一個對應首頁的html名稱為index.html;
        //啟動專案後可進入首頁http://localhost:8080/ ，網頁上只有Home和Register。   //到這邊卡住了 訪問這個網址一直跳轉到內鍵的login//
    }

    //顯示註冊頁面
    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user",user);
        return "register"; //對應register.html
    }


}