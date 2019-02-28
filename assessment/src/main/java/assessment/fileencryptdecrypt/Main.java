package assessment.fileencryptdecrypt;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		EncryptedPasswordLogin enc = new EncryptedPasswordLogin();
		Scanner scan = new Scanner(System.in);

		System.out.println("ENTER 1 to register, ENTER 2 to Login");
		int choose = scan.nextInt();


		switch(choose){
		case 1:
			enc.register();
		case 2:
			enc.login();
		}
	}

}
