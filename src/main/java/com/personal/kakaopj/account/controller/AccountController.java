package com.personal.kakaopj.account.controller;

import com.personal.kakaopj.account.dto.UserSignupDto;
import com.personal.kakaopj.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("account/")
public class AccountController {

    @Autowired
    private AccountService accountService;
    public void setUserService(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("signup")
    //Request로 묶어서 받기, 변수 2개 초과하면
    public boolean signup(@RequestBody UserSignupDto userSignupDto){
        if (accountService.signupUser(userSignupDto) != null)
            return true;
        return false;
    }

    //login

    //logout

    //withdrawal
}
