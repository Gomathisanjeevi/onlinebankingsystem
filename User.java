package com.onlineBanksystem;
import java.util.*;
public class User {
	 private String name;
	    private String email;
	    private String phone;
	    private String password;
	    private double balance;
	    private List<String> transactionHistory;

	    public User(String name, String email, String phone, String password) {
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	        this.password = password;
	        this.balance = 0.0;
	        this.transactionHistory = new ArrayList<>();
	    }

	    public String getName() {
	        return name;
	    }

	    public void updatePersonalInfo(String name, String email, String phone) {
	        this.name = name;
	        this.email = email;
	        this.phone = phone;
	    }

	    public boolean validatePassword(String password) {
	        return this.password.equals(password);
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void deposit(double amount) {
	        balance += amount;
	        transactionHistory.add("Deposited: " + amount);
	    }

	    public boolean withdraw(double amount) {
	        if (amount <= balance) {
	            balance -= amount;
	            transactionHistory.add("Withdrew: " + amount);
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public boolean transfer(User toUser, double amount) {
	        if (amount <= balance) {
	            balance -= amount;
	            toUser.deposit(amount);
	            transactionHistory.add("Transferred: " + amount + " to " + toUser.getName());
	            toUser.transactionHistory.add("Received: " + amount + " from " + this.name);
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public List<String> getTransactionHistory() {
	        return transactionHistory;
	    }
	}

