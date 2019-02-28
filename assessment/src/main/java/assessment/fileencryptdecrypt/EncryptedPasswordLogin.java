package assessment.fileencryptdecrypt;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class EncryptedPasswordLogin {
	List<Account> newAccount = new ArrayList<Account>();
	List<Account> existingAccounts = new ArrayList<Account>();

	public List<Account> register(){

		Scanner scan = new Scanner(System.in);

		System.out.println("REGISTER FORM" + "\n\n");
		System.out.println("Enter Username");
		String username = scan.nextLine();

		for(Account checkUsername : readFile("listOfAccounts.csv")){
			if(username.equals(checkUsername.getUsername())){
				System.out.println("Sorry Username Already exist");
				return null;
			}
		}
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
						newAccount.clear();
						existingAccounts.clear();
						System.out.println("Added new Account");

						return newAccount;

					}
				}




		return newAccount;

	}

	public Account login(){

		Scanner scan = new Scanner(System.in);
		boolean log = false;
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
				log = true;
			}	
		}
		
		if(!log==true){
			System.out.println("No Accounts Found");
		}


		return null;
	}
	public List<Account> readFile(String listOfAccounts){
		List<Account> listFromFile = new ArrayList<Account>();
		FileReader file;
        try {
            file = new FileReader(listOfAccounts);
            Scanner scanNew = new Scanner(file);

            while (scanNew.hasNext()){
                String dataOfAllAccounts = scanNew.next();

                String[] values = dataOfAllAccounts.split(",");

                listFromFile.add(new Account(values[0], values[1]));

            }

        }catch (IOException e){
            e.printStackTrace();
        }
		return listFromFile;

	}
	public void writeFile(List<Account> registeredAccount){
		File file = null;

		for(Account existing : readFile("listOfAccounts.csv")){
			existingAccounts.add(new Account(existing.getUsername(), existing.getPassword()));
		}

        try{
            String filename = "listOfAccounts.csv";

            file = new File (filename);

            if(!file.exists()) {
                file.createNewFile();

                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                for(Account str: newAccount) {
                	bw.write(str.getUsername()+","+str.getPassword()+"\n");
                	}
                for(Account str: existingAccounts){
                	bw.write(str.getUsername()+","+str.getPassword()+"\n");
                }
                bw.close();
                System.out.println("File Created");
            }else{


                file.delete();
                file.createNewFile();
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                for(Account str: newAccount) {
                	bw.write(str.getUsername()+","+str.getPassword()+"\n");
                	}
                for(Account str: existingAccounts){
                	bw.write(str.getUsername()+","+str.getPassword()+"\n");
                }
                bw.close();
                System.out.println("File replaced");

            }
        }catch (IOException e){
            e.printStackTrace();
        }
	}
}
