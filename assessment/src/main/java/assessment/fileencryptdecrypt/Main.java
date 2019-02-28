package assessment.fileencryptdecrypt;

import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		EncryptedPasswordLogin enc = new EncryptedPasswordLogin();
		Scanner scan = new Scanner(System.in);


		boolean flag = true;
		while(flag){
			System.out.println("ENTER 1 to register, ENTER 2 to Login, ENTER 3 to exit");
			int choose = scan.nextInt();

			switch(choose){
			case 1:
				enc.register();
				break;
			case 2:
				enc.login();
				flag = false;
				break;

			case 3:
				flag = false;
				break;
			}
			}
		}


}
