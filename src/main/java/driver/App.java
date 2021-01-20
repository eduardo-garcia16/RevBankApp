package driver;

import java.util.List;
import java.util.Scanner;

import model.Account;
import model.User;
import services.AccountServices;
import services.AccountServicesImpl;
import services.UserServices;
import services.UserServicesImpl;

public class App {

	public static void main(String[] args) {
		boolean running = true;
		boolean loggedIn = true;
		String choice;
		String usernameTemp;
		String passwordTemp;
		String temp1;
		String temp2;
		String temp3;
		int choiceInt;
		double choiceDouble;
		Scanner in = new Scanner(System.in);
		User u;
		Account a;
		List<User> users;
		List<Account> accounts;
		UserServices user = new UserServicesImpl();
		AccountServices acc = new AccountServicesImpl();

		System.out.println("Welcome to the RevBanking App!\n");
		do {
			System.out.println("What would you like to do?\n(1) Register (2) Login (3) Exit");
			choice = in.nextLine();
			choiceInt = Integer.parseInt(choice);
			switch (choiceInt) {
			case 1:
				System.out.println("Enter a UNIQUE username!");
				usernameTemp = in.nextLine();
				System.out.println("Enter a pssword!");
				passwordTemp = in.nextLine();
				if (user.addUser(new User(usernameTemp, passwordTemp)))
					System.out.println("User registered! Restarting app. . .");
				else
					System.out.println("Error registering new user");
				break;
			case 2:
				System.out.println("Enter your username:");
				usernameTemp = in.nextLine();
				System.out.println("Enter your password:");
				passwordTemp = in.nextLine();
				if (user.getUser(usernameTemp).getPassword().equals(passwordTemp)) {
					System.out.println("Welcome, " + user.getUser(usernameTemp).getUsername() + "!\n");
					do {
						if(user.getUser(usernameTemp).isAdmin() == 1)
							System.out.println("ADMIN: Use (4) to access admin controls");
						
						System.out.println("What do you wish to do?\n(1) Review Accounts (2) Add Account (3) Logout");
						choice = in.nextLine();
						choiceInt = Integer.parseInt(choice);
						switch (choiceInt) {
						case 1:
							System.out.println("Present Accounts: ");
							int accountsNum = acc.getAllUserAccounts(user.getUser(usernameTemp).getId()).size();
							if(accountsNum == 0)
								System.out.println("No present accounts.");
							else {
								accounts = acc.getAllUserAccounts(user.getUser(usernameTemp).getId());
								for(Account a1 : accounts) {
									System.out.println(a1);
								}
								System.out.println("Enter 'ID' of account you wish to change:");
								choice = in.nextLine();
								choiceInt = Integer.parseInt(choice);
								a = acc.getAccount(choiceInt);
								System.out.println(acc.getAccount(choiceInt));
								System.out.println("Would you like to: (1) Withdraw (2) Deposit (3) Delete?");
								choice = in.nextLine();
								choiceInt = Integer.parseInt(choice);
								switch(choiceInt) {
								case 1:
									System.out.println("Enter how much to withdraw from account:");
									choice = in.nextLine();
									choiceDouble = Double.parseDouble(choice);
									if(acc.deposit(a.getId(), choiceDouble))
										System.out.println("Withdrew "+choiceDouble+" from account");
									else
										System.out.println("Error withdrawing - Improper Balance");
									break;
								case 2:
									System.out.println("Enter how much to deposit into account:");
									choice = in.nextLine();
									choiceDouble = Double.parseDouble(choice);
									if(acc.deposit(a.getId(), choiceDouble))
										System.out.println("Deposited "+choiceDouble+" into account");
									break;
								case 3:
									System.out.println("Account must be empty to delete. Are you sure you wish to delete? (1) YES (2) NO");
									choice = in.nextLine();
									choiceInt = Integer.parseInt(choice);
									if(choiceInt == 1)
										acc.deleteAccount(a.getId());
									break;
								}
							}
							break;
						case 2:
							System.out.println("Enter custom account type/name:");
							choice = in.nextLine();
							if(acc.addAccount(new Account(user.getUser(usernameTemp).getId(), choice, 0.0)))
								System.out.println("Account added succesfully.");
							else
								System.out.println("Error adding account");
							break;
						case 3:
							System.out.println("Logging out. . .");
							break;
						case 4:
							if(user.getUser(usernameTemp).isAdmin() == 0)
								System.out.println("Forbidden");
							else {
								System.out.println("ADMIN commands: (1) CREATE (2) READ ALL (3) UPDATE (4) DELETE");
								choice = in.nextLine();
								choiceInt = Integer.parseInt(choice);
								switch(choiceInt) {
								case 1:
									System.out.println("Enter username:");
									temp1 = in.nextLine();
									System.out.println("Enter password:");
									temp2 = in.nextLine();
									System.out.println("Is admin? (1) YES (0) NO");
									choice = in.nextLine();
									choiceInt = Integer.parseInt(choice);
									user.addUser(new User(temp1, temp2, choiceInt));
									break;
								case 2:
									System.out.println("All records:");
									System.out.println(user.getAllUsers());
									break;
								case 3:
									System.out.println("Enter user id to update:");
									choice = in.nextLine();
									choiceInt = Integer.parseInt(choice);
									System.out.println("Enter new username:");
									temp1 = in.nextLine();
									System.out.println("Enter new password:");
									temp2 = in.nextLine();
									System.out.println("Is admin? (1) YES (0) NO");
									temp3 = in.nextLine();
									u = new User(temp1, temp2, Integer.parseInt(temp3));
									user.updateUser(u);
									break;
								case 4:
									System.out.println("enter user id to delete");
									choice = in.nextLine();
									choiceInt = Integer.parseInt(choice);
									user.deleteUser(choiceInt);
									System.out.println("User Deleted");
									break;
								}
							}
							break;
						}
						
						if(loggedIn == true) {
							System.out.println("\nMore Work?\n(1) YES (2) NO");
							choice = in.nextLine();
							choiceInt = Integer.parseInt(choice);
							if(choiceInt == 2)
								loggedIn = false;
						}
					} while(loggedIn);
					
				} else
					System.out.println("Invalid username or password. Please try again. Restarting app. . .");
				break;
			case 3:
				System.out.println("Exiting App. . .");
				running = false;
				break;
			}
			
			if(running == true) {
				System.out.println("\nExit Bank App?\n(1) YES (2) NO");
				choice = in.nextLine();
				choiceInt = Integer.parseInt(choice);
				if (choiceInt == 1)
					running = false;
			}
		} while (running);
	}

}
