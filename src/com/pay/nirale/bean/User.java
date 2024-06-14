package com.pay.nirale.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String userName, email, ifsCode, bankName, mobileNo, accountNo, history, Type;
    private int accountPin;
    private double accountBalance;
    
    public User(){
        
    }

    public User(String userName, String email, String ifsCode, String bankName, String mobileNo, String accountNo, String history, String Type, int accountPin, double accountBalance) {
        this.userName = userName;
        this.email = email;
        this.ifsCode = ifsCode;
        this.bankName = bankName;
        this.mobileNo = mobileNo;
        this.accountNo = accountNo;
        this.history = history;
        this.Type = Type;
        this.accountPin = accountPin;
        this.accountBalance = accountBalance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIfsCode() {
        return ifsCode;
    }

    public void setIfsCode(String ifsCode) {
        this.ifsCode = ifsCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(int accountPin) {
        this.accountPin = accountPin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", email=" + email + ", ifsCode=" + ifsCode + ", bankName=" + bankName + ", mobileNo=" + mobileNo + ", accountNo=" + accountNo + ", history=" + history + ", Type=" + Type + ", accountPin=" + accountPin + ", accountBalance=" + accountBalance + '}';
    }

     
}
