/*
  RMIT University Vietnam
  Course: COSC2081 Programming 1
  Semester: 2022C
  Assessment: Assignment 3
  Author: Your names (e.g. Nguyen Van Minh)
  ID: Your student ids (e.g. 1234567)
  Acknowledgement:
  - https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
  - https://stackoverflow.com/questions/17732417/delete-last-line-in-text-file
  - https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Guest {


    public void register(String username, String password, String name, String phone, String email, String address) {
        try {
            File file = new File("src/database/user.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            //CHECK IF USERNAME IS UNIQUE

            Scanner fileScanner = new Scanner(file);


            ArrayList<String> data = null;
            while (fileScanner.hasNext()) {

                String line = fileScanner.nextLine();
                data = new ArrayList<>(Arrays.asList(line.split(",")));

                if (data.get(6).equals(username)) {
                    System.out.println("Username is not unique");
                    return;
                }
            }

            //WRITE TO FILE

            String lastID = data.get(0);
            String numberOnly = lastID.replaceAll("[^0-9]", "");
            int newNumber = Integer.parseInt(numberOnly);

            FileWriter fw = new FileWriter(file, true);
            if(!data.isEmpty()) {
                fw.write("\n" + "C" + (newNumber+1) + "," + name + "," + email + "," + address + "," + phone + "," + "no_membership" + "," + username + "," + password + "," + 0);
            } else {
                fw.write("C1" + "," + name + "," + email + "," + address + "," + phone + "," + "no_membership" + "," + username + "," + password + "," + 0);
            }

            System.out.println("Register successfully.");
            fw.close();
            fileScanner.close();

        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public Customer customerLogin(String username, String password) throws IOException {

        File file = new File("src/database/user.txt");

        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);

        // CHECK CORRECT USERNAME AND PASSWORD

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if(data.get(6).equals(username) && data.get(7).equals(password)) {
                System.out.println(username + " successfully logged in.");
                return new Customer(username, password);
            }
        }

        System.out.println("Wrong username/password or account was not created.");
        return null;
    }

    public Admin adminLogin(String username, String password) throws IOException {

        File file = new File("src/database/admin.txt");

        if(!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);

        // CHECK CORRECT USERNAME AND PASSWORD

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if(data.get(0).equals(username) && data.get(1).equals(password)) {
                System.out.println(username + " successfully logged in.");
                return new Admin(username, password);
            }
        }
        System.out.println("Wrong username/password or account was not created.");
        return null;
    }
}
