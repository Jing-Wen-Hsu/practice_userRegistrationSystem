package com.user_registration_system.user_registration_system.Controller;

import com.user_registration_system.user_registration_system.Entity.User;
import com.user_registration_system.user_registration_system.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


//      Coding步驟
//0.建立AuthController.java
//  "Auth" 是 "Authentication"（身份驗證）和 "Authorization"（授權）的縮寫，通常用來指代與用戶身份相關的功能和過程。
//1.註解為@Controller層
//2.首頁->做出首頁(顯示功能+畫面)  @GetMapping("/")訪問首頁(index) ，同時建立index.htm1
//3.註冊畫面->註冊頁面(顯示功能+畫面) @GetMapping("/register")訪問註冊頁(register) ，同時建立register.htm1(該有的資料輸入欄位要建好)
//4.註冊處理及驗證 私有類UserService
//  4-1.Generate出AuthController的有參數建構式
//  4-2.@PostMapping完成註冊並儲存(註冊資料驗證合格會新增一筆新的使用者資料 因此用post)
//      判別註冊資料是否符合條件，符合者將註冊資訊添加製模型中，不符合者導回註冊頁面(並保留原填寫的資料)。
    // 接下來要定義UserService.java將註冊的用戶資料儲存到資料庫中
//5.添加saveUser程式碼-透過email查詢是否已有用戶使用
    //--接下來要到UserService.java編寫顯示全體用戶的email的頁面
//6.顯示全體用戶email的網頁
//7.登入頁面

@Controller
public class AuthController {

    private final UserService userService;
    //AuthController的有參數建構式
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //顯示首頁
    @GetMapping("/")
    public String index() {
        return "index"; //一個對應首頁的html名稱為index.html;
        //啟動專案後可進入首頁http://localhost:8080/ ，網頁上只有Home和Register。
            //到這邊卡住了 訪問這個網址一直跳轉到內建的login，檢查了三個小時之後 什麼都沒做就自己好了= =//
    }

    //顯示註冊頁面 -> 先把顯示的方法寫出來 接著要設計註冊頁面(register.html)
    @GetMapping("/register")
    public String register(Model model) {
        User user = new User(); //創建一個User物件用於接收註冊表單中的數據(email、password等，看自己如何設計註冊所需資訊)
        model.addAttribute("user",user);//將剛剛創建的user物件添加至模型中。
                                                    //考慮到使用者體驗，註冊資料不符合要求也要先添加製模型中，驗證錯誤時這些資料需呈現於頁面上讓使用者知道。
        return "register"; //對應register.html，顯示註冊表單 ，註冊表單中有 email、password要填寫 最後按register按鈕提交。
    }
    //↑這段程式碼的功能是當用戶訪問 /register 時，顯示一個註冊表單，並將一個新的 User 物件傳遞到視圖中，以便用戶可以填寫相關信息。

    //處理註冊
    @PostMapping("/register")
    public String saveUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        //5.
        //添加程式碼-透過email查詢是否已有用戶使用
        //檢查用戶(email)是否存在
        User userExisting = userService.getUserByEmail(user.getEmail());

            //如果該email已存在，顯示錯誤訊息:被註冊
        if(userExisting != null) {
            bindingResult.rejectValue("email",null,"email已註冊");
        }

        //判別使用者輸入的資訊是否符合，符合者將資料庫添加製模型中，不符合者導回註冊頁面
        if(bindingResult.hasErrors()){ //有錯誤
            model.addAttribute("user",user); //儲存驗證結果對象，將 user 物件添加到模型中，導回註冊頁時畫面並保留原輸入的資訊
            return "register";
        }
        //註冊資料符合規定 直接新增
        userService.createUser(user);
        return "redirect:/"; //導回首頁
             //接下來要定義UserService.java將註冊的用戶資料儲存到資料庫中
    }


    //6.
    //顯示全體用戶的網頁
    @GetMapping("/users")
    public String users(Model model){
        List<User> users =userService.getAllUsers(); //準備一個List儲存調用userService的getAllusers方法取得的所有用戶
        model.addAttribute("users",users);//將 users 列表添加到 model 中，並指定其名稱為 "users"
        return "users"; //user.html網頁
    } //建立users.html
 //又卡住了 明明沒有問題 一直自己導去login頁面 = = //

    //7.
    //顯示登入頁面
//    @GetMapping("/login")
//    public String login(Model model){
//        return "login";
//    }


}