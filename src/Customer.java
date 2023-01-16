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
import java.util.*;

public class Customer extends registeredUser{

    private String name;
    private String email;
    private String address;
    private String phone;
    private String membership;
    private String customerID;

    private double totalSpending;

    public Customer(String username, String password) throws IOException{
        super(username, password);

        File file = new File("src/database/user.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (data.get(6).equals(username)) {
                this.customerID = data.get(0);
                this.name = data.get(1);
                this.email = data.get(2);
                this.address = data.get(3);
                this.phone = data.get(4);
                this.membership = data.get(5);
                this.totalSpending = Double.parseDouble(data.get(8));
                break;
            }
        }

    }

    public String getCustomerID() {
        return customerID;
    }

    public void viewInformation() {
        System.out.println(
            "Name: " + this.name + "\n" +
            "Email: " + this.email + "\n" +
            "Phone: " + this.phone + "\n" +
            "Address: " + this.address);
    }

    public void updateInformation(String name, String phone, String email, String address) throws IOException {

        if(name.equals("")) {
            name = this.name;
        }

        if(phone.equals("")) {
            phone = this.phone;
        }

        if(email.equals("")) {
            email = this.email;
        }

        if(address.equals("")) {
            address = this.address;
        }

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

        //DECLARATION

        File file = new File("src/database/user.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);

        String total = "";

        //DO THE THING

        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);

            if (!(data.get(6).equals(this.getUsername()))) {
                if(total == "") {
                    total = lineData;
                } else {
                    total += "\n" + lineData;
                }
            } else {
                if(total == "") {
                    total = data.get(0) + "," + name + "," + email + "," + address+ "," + phone + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);
                } else {
                    total += "\n" + data.get(0) + "," + name + "," + email + "," + address+ "," + phone + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);
                }
            }

        }

        //WRITE TO FILE

        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println(this.getUsername() +" Updated their profile Successfully!");
        fw.close();
        fileScanner.close();

    }

    public void checkMembership() {
        System.out.println("Membership: " + this.membership);
    }

    public void viewAllProductByCategoryAndPrice(String category, String way) throws IOException {
        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        ArrayList<ArrayList> total = new ArrayList<>();
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));
            if(category.equals("")) {
                total.add(data);
            } else if (category.equals(data.get(3))) {
                total.add(data);
            }
        }
        if (way.equals("ASC")) {
            Collections.sort(total, SORT_TEAM_BY_GOALS_ASCENDING);
        } else if (way.equals("DES")) {
            Collections.sort(total, SORT_TEAM_BY_GOALS_DESCENDING);
        }

        for(int i = 0; i < total.size(); i++){
            System.out.println("Product ID: " + total.get(i).get(0) + " " +
                    "Product Name: " + total.get(i).get(1) + " " +
                    "Product Price: " + total.get(i).get(2) + " " +
                    "Category: " + total.get(i).get(3)
            );
        }

    }

    static final Comparator<ArrayList> SORT_TEAM_BY_GOALS_DESCENDING = new Comparator<ArrayList>(){
        public int compare(ArrayList product1, ArrayList product2){
            return Double.compare(Double.parseDouble(product2.get(2).toString()), Double.parseDouble(product1.get(2).toString()));
        }
    };

    static final Comparator<ArrayList> SORT_TEAM_BY_GOALS_ASCENDING = new Comparator<ArrayList>(){
        public int compare(ArrayList product1, ArrayList product2){
            return Double.compare(Double.parseDouble(product1.get(2).toString()), Double.parseDouble(product2.get(2).toString()));
        }
    };

    public void viewAllProductByCategoryAndPriceRange(String category, double lowPrice, double highPrice) throws IOException {
        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        ArrayList<ArrayList> total = new ArrayList<>();
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));
            if(category.equals("")) {
                if(lowPrice <= Double.parseDouble(data.get(2)) && Double.parseDouble(data.get(2)) <= highPrice){
                    total.add(data);
                }
            } else if (category.equals(data.get(3))) {
                if(lowPrice <= Double.parseDouble(data.get(2)) && Double.parseDouble(data.get(2)) <= highPrice){
                    total.add(data);
                }
            }
        }


        for(int i = 0; i < total.size(); i++){
            System.out.println("Product ID: " + total.get(i).get(0) + " " +
                    "Product Name: " + total.get(i).get(1) + " " +
                    "Product Price: " + total.get(i).get(2) + " " +
                    "Category: " + total.get(i).get(3)
            );
        }

    }

    public void placeOrder(String productID) throws IOException{
        //PRODUCT
        Product product = new Product(productID);


        //DECLARE
        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);

        //GET LAST LINE

        ArrayList<String> data = new ArrayList<>();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            data = new ArrayList<>(Arrays.asList(line.split(",")));
        }

        //WRITE TO FILE
        int newNumber = 0;
        String finalID = "";
        double total = product.getProductPrice();

        if(!data.isEmpty()) {
            String lastID = data.get(0);
            String numberOnly = lastID.replaceAll("[^0-9]", "");
            newNumber = Integer.parseInt(numberOnly);
        }

        FileWriter fw = new FileWriter(file, true);

        double discount = 0;
        if(!(this.membership.equals("no_membership"))) {
            if(this.membership.equals("SILVER")) {
                discount = 0.05;
                total -= discount * total;
            } else if(this.membership.equals("GOLD")) {
                discount = 0.1;
                total -= discount * total;
            } else if(this.membership.equals("PLATINUM")){
                discount = 0.15;
                total -= discount * total;
            }
        }



        if(!data.isEmpty()) {
            fw.write("\n" + "O" + (newNumber+1) + "," + java.time.LocalDate.now() + "," + this.customerID + "," + productID + "," + product.getProductPrice() + "," + discount + "," + total + "," + "delivering");
            finalID = "O" + (newNumber+1);
        } else {
            fw.write("O1" + "," + java.time.LocalDate.now() + "," + this.customerID + "," + productID + "," + product.getProductPrice() + "," + discount + "," + total + "," + "delivering");
            finalID = "O1";
        }

        fw.close();
        fileScanner.close();

        Order order = new Order(finalID);
        order.showOrder();


        this.updateTotalSpending(total);
        this.updateMembership(this.totalSpending);
    }



    public void updateMembership(Double totalSpending) throws IOException {
        String before = this.membership;



        if(this.totalSpending > 5000000) {
            this.membership = "SILVER";
            if(this.totalSpending > 10000000){
                this.membership = "GOLD";
                if(this.totalSpending > 25000000) {
                    this.membership = "PLATINUM";
                }
            }
        }
        if(!(before.equals(this.membership))) {

            File file = new File("src/database/user.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner fileScanner = new Scanner(file);

            String total = "";

            //DO THE THING

            while (fileScanner.hasNext()) {

                String line = fileScanner.nextLine();
                ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

                String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);

                if (!(data.get(6).equals(this.getUsername()))) {
                    if(total == "") {
                        total = lineData;
                    } else {
                        total += "\n" + lineData;
                    }
                } else {
                    if(total == "") {
                        total = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + this.membership + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);
                    } else {
                        total += "\n" + data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + this.membership+ "," + data.get(6) + "," + data.get(7) + "," + data.get(8);
                    }
                }
            }

            //WRITE TO FILE

            FileWriter fw = new FileWriter(file, false);
            fw.write(total);
            System.out.println(this.getUsername() +" Updated Their Membership Successfully!");
            fw.close();
            fileScanner.close();
        }

    }

    public void updateTotalSpending(Double newAddingBalance) throws IOException {

        this.totalSpending += newAddingBalance;

        File file = new File("src/database/user.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);
        String total = "";
        //DO THE THING
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);

            if (!(data.get(6).equals(this.getUsername()))) {
                if(total == "") {
                    total = lineData;
                } else {
                    total += "\n" + lineData;
                }
            } else {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + this.totalSpending;
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + this.totalSpending;
                }
            }
        }
        //WRITE TO FILE

        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println(this.getUsername() +" Updated Their Total Spending Successfully!");
        fw.close();
        fileScanner.close();

    }

    public void showOrderByID(String orderID) throws IOException {
        Order order = new Order(orderID);
        order.showOrder();
    }

    public void showAllOrder() throws IOException {
        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if(data.get(2).equals(this.customerID)) {
                showOrderByID(data.get(0));
            }
        }
    }
}

