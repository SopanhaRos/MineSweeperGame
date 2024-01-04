/*
 * 1. Register (validate the username before register)
 * 2. login 
 * 
 * save data in text file
 * login, can display information more than username and pasword
 * can change username
 * can change password
 * overriding toString method
 * overriding equal method
 */

package MineSweeperGame;

import java.util.Scanner;

public class Authenticate {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tmpName;
        String tmpPassword;
        int choice;

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("other. Quit");
        choice = sc.nextInt();
        String tmp = sc.nextLine();
        

        switch (choice) {
            case 1:
                do
                {
                    System.out.println("Input name: ");
                    tmpName = sc.nextLine();
                }while(LogIn.VerifyUserName(tmpName));
                System.out.println("Input password: ");
                tmpPassword = sc.nextLine();
                Register.registerUser(tmpName, tmpPassword);
                break;
            case 2:

                System.out.println("Input name: ");
                tmpName = sc.nextLine();
                System.out.println("Input password: ");
                tmpPassword = sc.nextLine();
                User tmpuser = new User(tmpName,tmpPassword);
                LogIn.loginUser(tmpuser);
            default:
                break;
        }

        sc.close();
    }
}
