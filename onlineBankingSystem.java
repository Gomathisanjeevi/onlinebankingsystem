package com.onlineBanksystem;
import java.util.*;
public class OnlineBankSystem {

		private static Map<String, User> users = new HashMap<>();
	    private static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("1. Create Account");
	            System.out.println("2. Login");
	            System.out.println("3. Exit");
	            System.out.print("Select an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    createAccount();
	                    break;
	                case 2:
	                    login();
	                    break;
	                case 3:
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Invalid option. Try again.");
	            }
	        }
	    }

	    private static void createAccount() {
	        System.out.print("Enter name: ");
	        String name = scanner.nextLine();
	        System.out.print("Enter email: ");
	        String email = scanner.nextLine();
	        System.out.print("Enter phone: ");
	        String phone = scanner.nextLine();
	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        User user = new User(name, email, phone, password);
	        users.put(email, user);
	        System.out.println("Account created successfully.");
	    }

	    private static void login() {
	        System.out.print("Enter email: ");
	        String email = scanner.nextLine();
	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        User user = users.get(email);
	        if (user != null && user.validatePassword(password)) {
	            userMenu(user);
	        } else {
	            System.out.println("Invalid email or password.");
	        }
	    }

	    private static void userMenu(User user) {
	        while (true) {
	            System.out.println("1. View Balance");
	            System.out.println("2. Deposit Funds");
	            System.out.println("3. Withdraw Funds");
	            System.out.println("4. Transfer Funds");
	            System.out.println("5. View Transaction History");
	            System.out.println("6. Update Personal Information");
	            System.out.println("7. Logout");
	            System.out.print("Select an option: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline

	            switch (choice) {
	                case 1:
	                    System.out.println("Current Balance: $" + user.getBalance());
	                    break;
	                case 2:
	                    depositFunds(user);
	                    break;
	                case 3:
	                    withdrawFunds(user);
	                    break;
	                case 4:
	                    transferFunds(user);
	                    break;
	                case 5:
	                    viewTransactionHistory(user);
	                    break;
	                case 6:
	                    updatePersonalInfo(user);
	                    break;
	                case 7:
	                    return;
	                default:
	                    System.out.println("Invalid option. Try again.");
	            }
	        }
	    }

	    private static void depositFunds(User user) {
	        System.out.print("Enter amount to deposit: ");
	        double amount = scanner.nextDouble();
	        scanner.nextLine(); // Consume newline
	        user.deposit(amount);
	        System.out.println("Deposited: " + amount);
	    }

	    private static void withdrawFunds(User user) {
	        System.out.print("Enter amount to withdraw: ");
	        double amount = scanner.nextDouble();
	        scanner.nextLine(); // Consume newline
	        if (user.withdraw(amount)) {
	            System.out.println("Withdrew: " + amount);
	        } else {
	            System.out.println("Insufficient balance.");
	        }
	    }

	    private static void transferFunds(User user) {
	        System.out.print("Enter recipient email: ");
	        String recipientEmail = scanner.nextLine();
	        System.out.print("Enter amount to transfer: ");
	        double amount = scanner.nextDouble();
	        scanner.nextLine(); // Consume newline

	        User recipient = users.get(recipientEmail);
	        if (recipient != null) {
	            if (user.transfer(recipient, amount)) {
	                System.out.println("Transferred: " + amount + " to " + recipient.getName());
	            } else {
	                System.out.println("Insufficient balance.");
	            }
	        } else {
	            System.out.println("Recipient not found.");
	        }
	    }

	    private static void viewTransactionHistory(User user) {
	        List<String> history = user.getTransactionHistory();
	        if (history.isEmpty()) {
	            System.out.println("No transactions found.");
	        } else {
	            for (String transaction : history) {
	                System.out.println(transaction);
	            }
	        }
	    }

	    private static void updatePersonalInfo(User user) {
	        System.out.print("Enter new name: ");
	        String newName = scanner.nextLine();
	        System.out.print("Enter new email: ");
	        String newEmail = scanner.nextLine();
	        System.out.print("Enter new phone: ");
	        String newPhone = scanner.nextLine();

	        user.updatePersonalInfo(newName, newEmail, newPhone);
	        users.remove(user.getName());
	        users.put(newEmail, user);
	        System.out.println("Personal information updated.");
	   


	}

}
