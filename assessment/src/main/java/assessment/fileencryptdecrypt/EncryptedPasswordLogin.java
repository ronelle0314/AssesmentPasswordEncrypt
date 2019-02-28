package assessment.fileencryptdecrypt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class EncryptedPasswordLogin {

	public List<Account> register(){
		List<Account> newAccount = new ArrayList<Account>();
		Scanner scan = new Scanner(System.in);

		System.out.println("REGISTER FORM" + "\n\n");
		System.out.println("Enter Username");
		String username = scan.nextLine();

		for(Account checkUsername : readFile("listOfAccounts.csv")){
			if(username.equals(checkUsername.getUsername())){
				System.out.println("Sorry Username Already exist");
				return null;
			}else{
				System.out.println("Enter Password");

				boolean flag = true;

				while(flag){
					String password = scan.nextLine();

					if(password.equals(null)){
						System.out.println("Kindly Enter a password");
					}else{
						flag = false;

						String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());

						newAccount.add(new Account(username, encodedPassword));

						writeFile(newAccount);

						System.out.println("Added new Account");

						return newAccount;

					}
				}

			}
		}
		return newAccount;

	}

	public Account login(){

		Scanner scan = new Scanner(System.in);

		System.out.println("LOGIN FORM"+ "\n\n");

		System.out.println("Enter Username");
		String username = scan.nextLine();

		System.out.println("Enter Password");
		String password = scan.nextLine();



		for(Account checkAccount : readFile("listOfAccounts.csv")){
			byte[] decodedBytes = Base64.getDecoder().decode(checkAccount.getPassword());
			String decodedPassword = new String(decodedBytes);

			if(username.equals(checkAccount.getUsername()) && password.equals(decodedPassword)){
				System.out.println("SUCCESSFULLY LOGIN");
				System.out.println("username: " + checkAccount.getUsername());
				System.out.println("Encrypted Password: " + checkAccount.getPassword());
			}else{
				System.out.println("No Accounts Found");
			}
		}


		return null;
	}
	public List<Account> readFile(String listOfAccounts){

		return null;

	}
	public void writeFile(List<Account> registeredAccount){

	}
}
