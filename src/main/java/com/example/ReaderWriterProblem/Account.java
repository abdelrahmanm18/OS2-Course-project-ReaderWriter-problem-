package com.example.ReaderWriterProblem;

import com.example.raceconditionex2.MyRunnable;

public class Account {
    static float balance=100;

    public Account(int number)
    {
        balance = number;
    }


    public static float despoit(int amount){
        balance = balance + (float)amount;
        return balance;
    }

    public static float withDrawl(int amount){
            balance = balance - (float)amount;
            return balance;
    }


    public static float getBalance(){
        return balance;
    }

    public static void main(String[] args) {
        MyRunnable myRunnable =new MyRunnable();
        Account account = new Account(200);
        account.getBalance();
        account.despoit(50);
        System.out.println("Balance is " + getBalance());
        account.withDrawl(100);
        System.out.println("Balance is " + getBalance());
    }
}
