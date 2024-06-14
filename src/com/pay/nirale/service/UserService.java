package com.pay.nirale.service;

import com.pay.nirale.bean.User;

public interface UserService {
    
    public void Login();
    
    public void checkAccount(User fromUser, User toUser);

    public void CreateAccount();

    public void Logout(User user);

    public void MyAccountDetail();

    public void AccountActivity();

    public void FundTransfer(int amount, int pin, User fromUser, User toUser);

    public void Withdraw(User user);

    public void ChangePin(User user);
    
    public void createLog(User user, String msg);

}
