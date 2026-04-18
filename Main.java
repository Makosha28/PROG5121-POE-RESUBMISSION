/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author mamphekgonthabiseng
 */
import java.util.Scanner;

class Registration {
    private String regiUsername;
    private String regiPassword;
    private String regiCellPhoneNumber;

    // Boolean checkUserName
    public boolean checkUserName(String username) {
      return username.contains("_") && username.length() <= 5;
    }

    // Boolean checkPassword
    public boolean checkPassword(String password) {
        if  (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char chari : password.toCharArray()) {
            if (Character.isUpperCase(chari)) hasUpper = true;
            else if (Character.isDigit(chari)) hasNumber = true;
            else if (!Character.isLetterOrDigit(chari)) hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Boolean checkCellPhoneNumber
    public boolean checkCellPhoneNumber(String mobileNumber) {
        return mobileNumber.matches("^\\+27[6-8]\\d{8}$");
    }

    // String registerUser(Scanner keyboard) - Main registration method
    public String registerUser(Scanner keyboard) {
        System.out.println("REGISTRATION ");

        // Username
        System.out.print("Enter username: ");
        String username = keyboard.nextLine().trim();

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        regiUsername = username;
        System.out.println("Username successfully captured.");

        // Password
        System.out.print("Enter password: ");
        String password = keyboard.nextLine().trim();

        if (!checkPassword(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        regiPassword = password;
        System.out.println("Password successfully captured.");

        // Cell phone number
        System.out.print("Enter cell phone number: ");
        String phone = keyboard.nextLine().trim();

        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        regiCellPhoneNumber = phone;
        System.out.println("Cell phone number successfully added.");

        return "Registration successful!";
    }

    // Login class method calls
    public String getStoredUsername() {
        return regiUsername;
    }

    public String getStoredPassword() {
        return regiPassword;
    }
}

//  LOGIN CLASS 
class Login {

    private final Registration registration;

    public Login(Registration registration) {
        this.registration = registration;
    }

    // Boolean loginUser(Scanner keyboard)
    public boolean loginUser(Scanner keyboard) {
        System.out.println("LOGIN");

        System.out.print("Enter username: ");
        String username = keyboard.nextLine();

        System.out.print("Enter password: ");
        String password = keyboard.nextLine();

        String regUser = registration.getStoredUsername();
        String regPass = registration.getStoredPassword();

        if (regUser == null || regPass == null) {
            System.out.println("No registered user found. Please register first.");
            return false;
        }

        if (username.equals(regUser) && password.equals(regPass)) {
            System.out.println("Welcome Makosha Mamphekgo, it is great to see you again.");
            return true;
        } else {
            System.out.println("Username or password incorrect, please try again.");
            return false;
        }
    }
}

// MAIN CLASS
public class Main {
    public static void main(String[] args) {
            Scanner keyboard = new Scanner(System.in);
            Registration regi= new Registration();
            Login loginSystem = new Login(regi);
            
            int choice;
            int attempts =0;
            
            do {
                System.out.println("MAIN MENU ");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Select your option: ");
                
                while (!keyboard.hasNextInt()) {
                    System.out.println("Invalid input, Please choose a number.");
                    keyboard.next();
                }
                choice = keyboard.nextInt();
                keyboard.nextLine(); 
                
                switch (choice) {
                    case 1:
                        String result = regi.registerUser(keyboard);
                        System.out.println(result);
                        break;
                        
                    case 2:
                        loginSystem.loginUser(keyboard);
                        break;
                        
                    case 3:
                        System.out.println("Goodbye!");
                        break;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 3 &&attempts<3);
            if (attempts>=3 && choice !=3){
               System.out.print("Reached maximum attempts,Goodbye!");
            }
        }
    }

