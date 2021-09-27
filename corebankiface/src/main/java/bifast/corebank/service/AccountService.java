package bifast.corebank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bifast.corebank.model.Account;
import bifast.corebank.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountEnquiryRepository;

    
    public Account getAccountInquiry(String transactionId,String accountNumber,BigDecimal amount) {

        return accountEnquiryRepository.getAccountInquiry(transactionId,accountNumber,amount);
    }
    
    public Account getAccountByAccountNumber(String accountNumber) {

        return accountEnquiryRepository.getAccountByAccountNumber(accountNumber);
    }
    
    public Account save(Account account) {

        return accountEnquiryRepository.save(account);
    }
    
}
