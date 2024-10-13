package com.user_registration_system.user_registration_system.Service;

import com.user_registration_system.user_registration_system.Entity.User;
import com.user_registration_system.user_registration_system.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//1.註解為Service層
//2.調用UserRepository

//驗證用戶的email和密碼

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //根據email取得用戶的資訊，並驗證email和密碼是否正確
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        //如果找不到用戶，就回傳找不到使用這個email的用戶
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email :" + email);
        }

        //給用戶ROLE_USER權限
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        //驗證用戶的email和密碼是否正確，正確就可以登入，如果email或密碼有誤拒絕登入。
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
