package com.onlinebanking.dbmsonlinebanking.api;

import com.onlinebanking.dbmsonlinebanking.domain.Transaction;
import com.onlinebanking.dbmsonlinebanking.domain.User;
import com.onlinebanking.dbmsonlinebanking.domain.loanAccount;
import com.onlinebanking.dbmsonlinebanking.service.loanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/loan")
@RestController
@CrossOrigin
public class loanController {

    private final loanService loanService;

    @Autowired
    public loanController(loanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping(path = "create/{id}")
    public loanAccount getPAccountDetails(@PathVariable("id") Long userId) {
        return loanService.createLoanAcc(userId);
    }

    @PostMapping(path = "get")
    public loanAccount getLoan(@RequestBody loanAccount loan) {
        return loanService.getLoan(loan);
    }

    @PostMapping(path = "emi")
    public Transaction payEMI(@RequestBody User user) {
        return loanService.payEMI(user);
    }

}
