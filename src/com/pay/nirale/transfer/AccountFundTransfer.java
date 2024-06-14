package com.pay.nirale.transfer;

import java.util.Scanner;
import com.pay.nirale.bean.User;
import com.pay.nirale.bean.utils.Utils;
import com.pay.nirale.service.UserService;
import com.pay.nirale.validate.ValidateUser;

public abstract class AccountFundTransfer implements UserService {

    Scanner scanner = new Scanner(System.in);
    User user1, user2;
    int user, activeAccount;
    private String accType;

    {
        user1 = new User();
        user2 = new User();
    }

    public static void main(String[] args) {
        AccountFundTransfer fundTransfer;
        fundTransfer = new AccountFundTransfer() {};
        fundTransfer.mainMenu();
    }
    
    

    private void mainMenu() {
        System.out.println("");

        if (activeAccount != 0) {
            System.out.println("1. Logout");
            System.out.println("2. My Account Detail");
            System.out.println("3. Account Activity");
            System.out.println("4. Fund Transfer");
            System.out.println("5. Withdraw");
            System.out.println("6. Change Pin");
        } else {
            System.out.println("1. Login");
            System.out.println("2. Create Account");
        }
        System.out.println("");

        int menuChoice = scanner.nextInt();
        if (menuChoice == 1) {
            if (activeAccount == 1) {
                Logout(user1);
            } else if (activeAccount == 2) {
                Logout(user2);
            } else {
                this.Login();
            }
        } else if (menuChoice == 2) {
            if (activeAccount != 0) {
                if (activeAccount == 1) {
                    accountInfo(user1);
                } else if (activeAccount == 2) {
                    accountInfo(user2);
                }
            } else {
                this.CreateAccount();
            }
        } else if (menuChoice == 3) {
            if (activeAccount == 1) {
                print(user1.getHistory());
            } else {
                print(user2.getHistory());
            }
            mainMenu();
        } else if (menuChoice == 4) {
            if (activeAccount == 1) {
                this.checkAccount(user1, user2);
            } else {
                this.checkAccount(user2, user1);
            }

        } else if (menuChoice == 5) {
            if (activeAccount == 1) {
                this.Withdraw(user1);
            } else {
                this.Withdraw(user2);
            }
        } else if (menuChoice == 6) {
            if (activeAccount == 1) {
                this.ChangePin(user1);
            } else {
                this.ChangePin(user2);
            }
        }
 }

    @Override
    public void Login() {
        System.out.println(" -----*****Welcome to Bank*****----- ");
        System.out.println("Enter your bank Account Number");
        String accNumber = scanner.next();

        if (accNumber.equalsIgnoreCase(user1.getAccountNo())) {
            System.out.println("!! Enter 6 Digit Pin !!");
            int pin = scanner.nextInt();
            if (ValidateUser.verifyPin(pin, user1)) {
                activeAccount = 1;
                System.out.println("!! Login Successfull !!");
                createLog(user1, "Account Login");
                mainMenu();
            } else {
                System.out.println("!! Wrong Pin Try Again !!");
                Login();
            }
        } else if (accNumber.equalsIgnoreCase(user2.getAccountNo())) {
            System.out.println("!! Enter 6 Digit Pin !!");
            int pin = scanner.nextInt();
            if (ValidateUser.verifyPin(pin, user2)) {
                activeAccount = 2;
                System.out.println("!! Login Successfull !!");
                createLog(user2, "Account Login");
                mainMenu();
            } else {
                System.out.println("!! Wrong Pin Try Again !!");
                Login();
            }
        } else {
            System.out.println("!! Your Account Does not Exist !!");
            mainMenu();
        }
    }

    @Override
    public void CreateAccount() {

        if (user1.getUserName() == null) {
            user = 1;
        } else if (user2.getUserName() == null) {
            user = 2;
        } else {
            System.out.println("!! Oops Only 2 User Can be Created !!");
        }

        System.out.println("-----------Fill Detail to Continue------------");

        System.out.println("====| Enter Bank Name |====");
        String bankName = scanner.next();

        if (!ValidateUser.checkLength(3, bankName, false)) {
            print("[!! Bank Name is Not Valid or Empty !!]");
            CreateAccount();
        }

        System.out.println("====| Full Name |====");
        String name = scanner.next();

        if (!ValidateUser.checkLength(2, name, false)) {
            print("[!! Name is Not Valid or Empty !!]");
            CreateAccount();
        }

        System.out.println("====| Email |====");
        String email = scanner.next();

        if (!ValidateUser.checkLength(10, email, false) && !ValidateUser.validateEmail(email)) {
            print("[!! Email is Not Valid or Empty !!]");
            CreateAccount();
        }

        System.out.println("====| Mobile Number |====");
        String mobile = scanner.next();

        if (ValidateUser.validateMaxMobile(mobile) || ValidateUser.validateMinMobile(mobile)) {
            print("[!! Mobile Number must be 10 Digit !!]");
            CreateAccount();
        }

        System.out.println("====| Create IFSC Code |====");
        String ifsc = scanner.next();

        if (!ValidateUser.checkLength(11, ifsc, true)) {
            print("[!! Ifsc is Not Valid or Empty !!]");
            CreateAccount();
        }

        System.out.println("====| Select Account Type |====");
        System.out.println("1. Saving ");
        System.out.println("2. Current ");
        int accountType = scanner.nextInt();

        if (accountType != 0 && accountType <= 2) {
            if (accountType == 1) {
                accType = "Saving";
            } else {
                accType = "Current";
            }
        } else {
            print("[!! Account Type is Not Valid !!]");
        }

        System.out.println("====| Enter Amount You want to Save |====");
        int amount = scanner.nextInt();

        if (amount < 0) {
            print("[!! Sorry You Can Not Open an Account With 0(Zero) !!]");
        }

        System.out.println("====| Create 6 Digit Pin |====");
        int pin = scanner.nextInt();

        if (!ValidateUser.checkLength(6, String.valueOf(pin), true)) {
            print("[!! Must be 6 Digit !!]");
            CreateAccount();
        }

        System.out.println("====| Generating 11 Digit Account Number |====");
        String acNum = Utils.generateAcNum();

        if (user == 1) {
            user1.setBankName(bankName);
            user1.setUserName(name);
            user1.setEmail(email);
            user1.setType(accType);
            user1.setMobileNo(mobile);
            user1.setIfsCode(ifsc);
            user1.setAccountBalance(amount);
            user1.setAccountNo(acNum);
            user1.setAccountPin(pin);
            user1.setHistory(Utils.getTimestamp());
            this.createLog(user1, " Account Created ");
            this.accountInfo(user1);
        } else {
            user2.setBankName(bankName);
            user2.setUserName(name);
            user2.setEmail(email);
            user2.setType(accType);
            user2.setMobileNo(mobile);
            user2.setIfsCode(ifsc);
            user2.setAccountBalance(amount);
            user2.setAccountNo(acNum);
            user2.setAccountPin(pin);
            user2.setHistory(Utils.getTimestamp());
            this.createLog(user2, " Account Created ");
            this.accountInfo(user2);
        }
    }

    private void accountInfo(User user) {
        System.out.println("---------------****************----------------");
        System.out.println("-----------****[ Account Created Successfully ]****-------------");
        System.out.println("!! Account Detail !!");
        System.out.println("!! Bank Name => " + user.getBankName());
        System.out.println("!! Account Name => " + user.getUserName());
        System.out.println("!! Account Number => " + user.getAccountNo());
        System.out.println("!! Account Email => " + user.getEmail());
        System.out.println("!! Mobile Number => " + user.getMobileNo());
        System.out.println("!! Account Balance => " + user.getAccountBalance());
        System.out.println("!! Account Type => " + user.getType());
        System.out.println("!! IFSC Code => " + user.getIfsCode());
        System.out.println("!! Account Pin => " + user.getAccountPin());
        System.out.println("---------------****************----------------");
        this.mainMenu();
    }

    private void print(String string) {
        System.out.println(string);
    }

    @Override
    public void Logout(User user) {
        activeAccount = 0;
        System.out.println("!! Logout Successfully !!");
        createLog(user, "Account Logout");
        mainMenu();
    }

    @Override
    public void MyAccountDetail() {

    }

    @Override
    public void AccountActivity() {

    }

    @Override
    public void FundTransfer(int amount, int pin, User fromUser, User toUser) {
        if (ValidateUser.verifyPin(pin, fromUser)) {
            if (amount <= fromUser.getAccountBalance()) {
                toUser.setAccountBalance(toUser.getAccountBalance() + amount);
                fromUser.setAccountBalance(fromUser.getAccountBalance() - amount);
                print("-----*****[ Fund Transfer Successfully ]*****-----");
                System.out.println("!! ~ Available Balance =: " + fromUser.getAccountBalance());
                createLog(fromUser, amount + " Transfer to " + toUser.getUserName());
                createLog(toUser, amount + " Received from " + fromUser.getUserName());
                mainMenu();
            } else {
                System.out.println("!! Not Enough Balance in Account !!");
                System.out.println("-----------------------------------");
                mainMenu();
            }
        } else {
            System.out.println("!! Your Pin is Incorrect ....!!");
        }
    }

    @Override
    public void Withdraw(User user) {
        System.out.println("!! Enter Amount : ");
        int amount = scanner.nextInt();

        System.out.println("!! Enter 6 Digit Pin : ");
        int pin = scanner.nextInt();
        if (ValidateUser.verifyPin(pin, user)) {
            if (amount <= user.getAccountBalance()) {
                user.setAccountBalance(user.getAccountBalance() - amount);
                print("------*****[ Withdraw Successfully ]*****-----");
                System.out.println("!! ~ Available Balance =: " + user.getAccountBalance());
                createLog(user, amount + "Withdraw");
                mainMenu();
            } else {
                System.out.println("!! Not Enough Balance in Account !!");
                System.out.println("-----------------------------------");
                mainMenu();
            }
        } else {
            System.out.println("!! Pin is Not Valid !!");
            mainMenu();
        }
    }

    @Override
    public void ChangePin(User user) {
        print("!!~ Enter Your Old Pin: ");
        int oldPin = scanner.nextInt();
        if (oldPin == user.getAccountPin()) {
            print("!!~ Enter Your New Pin: ");
            int newPin = scanner.nextInt();
            user.setAccountPin(newPin);
            print("**************[ Your Pin has Updated Successfully ]*************");
            mainMenu();
        } else {
            print("!!~ you have Entered Wrong Pin !!");
            mainMenu();
        }
    }

    public void createLog(User user, String msg) {

        String history = null;
        if (user.getHistory() == null) {
            history = "";
        } else {
            history = user.getHistory();
        }
        user.setHistory(msg + " on " + Utils.getTimestamp() + "\n" + history);
    }

    public void checkAccount(User fromUser, User toUser) {
        System.out.println("!! ~ Enter Receiver Account no. you want to send money ");
        String accountNo = scanner.next();
        if (accountNo.equalsIgnoreCase(fromUser.getAccountNo())) {
            System.out.println("!! ~ You can not send money own / same Account !!");
            mainMenu();
        } else if (accountNo.equalsIgnoreCase(toUser.getAccountNo())) {
            print("!! ~ You can not sending money to " + toUser.getUserName());
            print("!! ~ Enter Amount: ");
            int amount = scanner.nextInt();
            print("!! ~ Enter 6 Digit Pin: ");
            int pin = scanner.nextInt();
            if (!ValidateUser.verifyPin(pin, fromUser)) {
                print("!! InCorrect Pin !!");
                mainMenu();
            }
            if (activeAccount == 1) {
                FundTransfer(amount, pin, fromUser, toUser);
            } else {
                FundTransfer(amount, pin, toUser, fromUser);
            }
        } else {
            System.out.println("!! This Account Number does not Exist.... ");
            mainMenu();
        }
    }

}
