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

public class Admin extends registeredUser{
    private String username;
    private String password;

    public Admin(String username, String password) {
        super(username, password);
        //ADD THE OTHER ATTRIBUTES BY ACCESSING DATABASE
    }

    public void viewAllUser() {
        try {

            File file = new File("src/database/user.txt");
            if (!file.exists()) {
                file.createNewFile();
            }


            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

                System.out.println(
                    "Customer ID: " + data.get(0) + " " +
                    "Name: " + data.get(1) + " " +
                    "Email: " + data.get(2) + " " +
                    "Address: " + data.get(3) + " " +
                    "Phone: " + data.get(4) + " " +
                    "Membership: " + data.get(5) + " " +
                    "Total Spending: " + data.get(8)
                );
            }

        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public void viewAllProduct() throws IOException{

        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            System.out.println(
                    "Product ID: " + data.get(0) + " " +
                    "Product Name: " + data.get(1) + " " +
                    "Product Price: " + data.get(2) + " " +
                    "Category: " + data.get(3)
            );
        }
    }

    public void viewAllOrder() throws IOException{

        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            System.out.println(
                    "Order ID: " + data.get(0) + " " +
                    "Order Date: " + data.get(1) + " " +
                    "Customer ID: " + data.get(2) + " " +
                    "Product ID: " + data.get(3) + " " +
                    "Product Price:" + data.get(4) + " " +
                    "Discount: " + data.get(5) + " " +
                    "Total: " + data.get(6) + " " +
                    "Status: " + data.get(7)
            );
        }
    }

    public void addProduct(String productName, double productPrice, String category) throws IOException {
        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //CHECK IF PRODUCT NAME IS UNIQUE

        Scanner fileScanner = new Scanner(file);


        ArrayList<String> data = null;
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (data.get(1).equals(productName)) {
                System.out.println("Product name is not unique");
                return;
            }
        }

        //WRITE TO FILE

        String lastID = data.get(0);
        String numberOnly = lastID.replaceAll("[^0-9]", "");
        int newNumber = Integer.parseInt(numberOnly);

        FileWriter fw = new FileWriter(file, true);
        fw.write("\n" + "P" + (newNumber+1) + "," + productName + "," + productPrice + "," + category);
        System.out.println(productName + " was added to the database.");
        fw.close();
        fileScanner.close();
    }

    public void removeProductByID(String productID) throws IOException {

        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //CHECK IF USERNAME IS UNIQUE

        Scanner fileScanner = new Scanner(file);

        boolean found = false;
        String total = "";


        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (!(data.get(0).equals(productID))) {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," +  data.get(2) + "," + data.get(3);
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," +  data.get(2) + "," + data.get(3);
                }
            } else {
                found = true;
            }

        }

        if(found == false) {
            FileWriter fw = new FileWriter(file, false);
            fw.write(total);
            System.out.println("Cant' find Product by ID " + productID);
            fw.close();
            fileScanner.close();
            return;
        }

        //WRITE TO FILE
        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println("Removed Successfully Product " + productID);
        fw.close();
        fileScanner.close();
    }

    public void updatePriceByProductID(String productID, double price) throws IOException{

        //DECLARATION
        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);
        String total = "";

        //DO THE THING
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3);

            if (!(data.get(0).equals(productID))) {
                if(total == "") {
                    total = lineData;
                } else {
                    total += "\n" + lineData;
                }
            } else {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," + price + "," + data.get(3);
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," + price + "," + data.get(3);
                }
            }
        }

        //WRITE TO FILE
        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println(productID +" was Updated Successfully!");
        fw.close();
        fileScanner.close();

    }

    public void removeCategory(String category) throws IOException{

        //DECLARATION
        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);
        String total = "";

        //DO THE THING
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3);

            if (!(data.get(3).equals(category))) {
                if(total == "") {
                    total = lineData;
                } else {
                    total += "\n" + lineData;
                }
            } else {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + "None";
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + "None";
                }
            }
        }

        //WRITE TO FILE
        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println(category +" category was removed Successfully!");
        fw.close();
        fileScanner.close();
    }

    public void changeProductCategoryByID(String productID, String category) throws IOException{

        //DECLARATION
        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);
        String total = "";

        //DO THE THING
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3);

            if (!(data.get(0).equals(productID))) {
                if(total == "") {
                    total = lineData;
                } else {
                    total += "\n" + lineData;
                }
            } else {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + category;
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + category;
                }
            }
        }

        //WRITE TO FILE
        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println("Product " + productID + "'s category was updated Successfully!");
        fw.close();
        fileScanner.close();
    }

    public void approve(String orderID) throws IOException{
        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);
        String total = "";
        //DO THE THING
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            String lineData = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7);

            if (!(data.get(0).equals(orderID))) {
                if(total == "") {
                    total = lineData;
                } else {
                    total += "\n" + lineData;
                }
            } else {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + "delivered";
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + "delivered";
                }
            }
        }
        //WRITE TO FILE

        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println("Admin Updated Status of order " + orderID + " Successfully!");
        fw.close();
        fileScanner.close();

    }

    public void removeCustomerByID(String customerID) throws IOException{
        File file = new File("src/database/user.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //CHECK IF USERNAME IS UNIQUE

        Scanner fileScanner = new Scanner(file);

        boolean found = false;
        String total = "";


        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (!(data.get(0).equals(customerID))) {
                if(total == "") {
                    total = data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);
                } else {
                    total += "\n" + data.get(0) + "," + data.get(1) + "," + data.get(2) + "," + data.get(3) + "," + data.get(4) + "," + data.get(5) + "," + data.get(6) + "," + data.get(7) + "," + data.get(8);
                }
            } else {
                found = true;
            }

        }

        if(found == false) {
            FileWriter fw = new FileWriter(file, false);
            fw.write(total);
            System.out.println("Cant' find Customer by ID " + customerID);
            fw.close();
            fileScanner.close();
            return;
        }

        //WRITE TO FILE
        FileWriter fw = new FileWriter(file, false);
        fw.write(total);
        System.out.println("Removed Successfully Customer " + customerID);
        fw.close();
        fileScanner.close();
    }

    public void showAllOrderByDay(String date) throws IOException{

        if(date == "") {
            date = String.valueOf(java.time.LocalDate.now());
        }

        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //CHECK IF USERNAME IS UNIQUE

        Scanner fileScanner = new Scanner(file);



        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (data.get(1).equals(date)) {
                Order order = new Order(data.get(0));
                order.showOrder();
            }

        }
    }

    public Double calculateTotalRevenue() throws IOException{

        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        //CHECK IF USERNAME IS UNIQUE

        Scanner fileScanner = new Scanner(file);


        double total = 0;
        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            total += Double.parseDouble(data.get(6));

        }
        return total;
    }

    public Double calculateRevenueToday() throws IOException {

        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        String date = String.valueOf(java.time.LocalDate.now());

        Scanner fileScanner = new Scanner(file);

        double total = 0;

        while (fileScanner.hasNext()) {

            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if(data.get(1).equals(date)) {
                total += Double.parseDouble(data.get(6));
            }
        }
        return total;
    }

    static final Comparator<Integer> SORT_TEAM_BY_GOALS_DESCENDING = new Comparator<Integer>(){
        public int compare(Integer n1, Integer n2){
            return n2 - n1;
        }
    };

    static final Comparator<Integer> SORT_TEAM_BY_GOALS_ASCENDING = new Comparator<Integer>(){
        public int compare(Integer n1, Integer n2){
            return n1 - n2;
        }
    };

    public void showMostPopularProductAndLeastPopularProduct() throws IOException{
        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        Scanner fileScanner = new Scanner(file);
        HashMap<String, Integer> allProducts = new HashMap();

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if(allProducts.containsKey(data.get(3))) {
                allProducts.put(data.get(3), Integer.parseInt(allProducts.get(data.get(3)).toString()) + 1);
            } else {
                allProducts.put(data.get(3), 1);
            }
        }
        System.out.println("MOST POPULAR");
        allProducts.entrySet().stream().sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue())).forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
        System.out.println("LEAST POPULAR");
        allProducts.entrySet().stream().sorted((k1, k2) -> k1.getValue().compareTo(k2.getValue())).forEach(k -> System.out.println(k.getKey() + ": " + k.getValue()));
    }

    public void showMostSpendingCustomer() throws IOException {
        File file = new File("src/database/user.txt");
        if (!file.exists()) {
            file.createNewFile();
        }


        Scanner fileScanner = new Scanner(file);
        String customerID = "";
        double maxSpending = 0;

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if(Double.parseDouble(data.get(8)) > maxSpending) {
                maxSpending = Double.parseDouble(data.get(8));
                customerID = data.get(0);
            }
        }

        System.out.println(customerID + ": " + maxSpending);
    }

    public void showMembershipInfo() {
        System.out.println("Silver: Spend more than 5000000 VND, gets 5% off for the next orders \n"
                        + "Gold: Spend more than 10000000 VND, gets 10% off for the next orders \n"
                        + "Platinum: Spending more than 25000000 VND, gets 15% off for the next orders");
    }
}
