//package com.product.serviceimpl;
//
//import com.product.entity.CustomUsers;
//import com.product.repository.CustomUsersRepository;
//import com.product.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CustomUserServiceImpl implements UserDetailsService {
//    @Autowired
//    private CustomUsersRepository customUsersRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return this.customUsersRepository.findbyUsername(username);
//    }
//    public String create(String username,String password){
//        CustomUsers cs=new CustomUsers();
//                 cs.setUsername(username);
//                 cs.setPassword(new BCryptPasswordEncoder().encode(password));
//                 cs.setAuthorities("CustomUsers");
//                cs.setAuthorities("CustomUsers");
//        customUsersRepository.save(cs);
//        return "Create Successfully";
//    }
//}
