package com.revature.project0;

import com.revature.project0.screens.LoginScreen;
import com.revature.project0.screens.RegisterScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Driver {

    public static void main(String[] args) {
//        AppUser newUser = new AppUser("wsingleton", "p4ssw0rd",
//                                      "wezley.singleton@revature.com",
//                                      "Wezley", "Singleton", 30);

//        newUser.toString()
//        System.out.printf("Hello and welcome, %s! I see that you are %d years old, nice!", newUser.getUsername(), newUser.getAge());

        // doesn't work because %d only works with digits
//        System.out.printf("Test char with digit specifier: %d", 'a');

        // try () {} == try-with-resources
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            String logOrReg = " ";
            while (!(logOrReg.equals("l")) || !(logOrReg.equals("r"))) {
                System.out.print("Would you like to log in or register?(l or r) ");
                logOrReg = consoleReader.readLine();
                if (logOrReg.equals("l")) {
                    LoginScreen loginScreen = new LoginScreen(consoleReader);
                    LoginScreen.render();
                    break;
                } else if (logOrReg.equals("r")) {
                    RegisterScreen registerScreen = new RegisterScreen(consoleReader);
                    RegisterScreen.render();
                    break;
                } else {
                    System.out.println("l or r only");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // what we used to have to do prior to Java 7's try-with-resources
//        finally {
//            // this block will execute regardless of whether or not the try block code throws an
//            // exception or executes successfully
//            try {
//                consoleReader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


    }

}
