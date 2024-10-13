package com.user_registration_system.user_registration_system.Repository;

import com.user_registration_system.user_registration_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Coding 步驟
//0.建立UserRepository.java 檔案
//1.宣告@Repository //可以省略。Spring Data JPA 會自動識別並處理接口，並將其作為 Spring Bean 注入，因此不加這個註解也能正常工作。
//2.設定為interface (java檔會變為interface檔)，並繼承JpaRepository
//  JpaRepository<User,Long> User是自己定義的實體類，Long是主鍵類型(也就是id的類型)。



//public class UserRepository {
//}

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
