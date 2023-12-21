package com.CodeWithBhargav.Controller;

import com.CodeWithBhargav.Model.Account;
import com.CodeWithBhargav.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/{id}")
    private ResponseEntity<Account> getAccountDetailByUserId(@PathVariable int id){
        Account account=accountService.findById(id);
        if(account!=null)
            return  new ResponseEntity<>(account, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }




}
