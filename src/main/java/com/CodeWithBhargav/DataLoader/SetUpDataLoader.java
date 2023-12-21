package com.CodeWithBhargav.DataLoader;

import com.CodeWithBhargav.Model.Account;
import com.CodeWithBhargav.Model.TransactionalType;
import com.CodeWithBhargav.Repository.AccountRepository;
import com.CodeWithBhargav.Repository.TransactionalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    @Autowired
    private TransactionalTypeRepository transactionalTypeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        TransactionalType credit = createTypeIfNotFound(TransactionalType.CREDIT);
        TransactionalType debit = createTypeIfNotFound(TransactionalType.DEBIT);

        createUserIfNotFound(50000, "Bhargava", 1234);
        createUserIfNotFound(10000, "Siva", 4321);


    }


    @Transactional
    private TransactionalType createTypeIfNotFound(String type) {
        TransactionalType transactionalType = transactionalTypeRepository.findByType(type);
        if (transactionalType == null) {
            transactionalType = new TransactionalType();
            transactionalType.setType(type);
            transactionalType = transactionalTypeRepository.save(transactionalType);
        }
        return transactionalType;
    }

    @Transactional
    private Account createUserIfNotFound(final int balance, final String name, final int pinnumber) {
        Optional<Account> account = accountRepository.findByname(name);
        Account user = null;
        if (account.isEmpty()) {

            user = new Account();
            user.setName(name);
            user.setBalance(balance);
            user.setPinNumber(pinnumber);
            user = accountRepository.save(user);
        }
        return user;
    }
}
