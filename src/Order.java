import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

public class Order {
    private String orderID;
    private String date;
    private String customerID;
    private String productID;
    private double productPrice;
    private double discount;
    private double total;
    private String status;

    public Order(String orderID) throws IOException {

        File file = new File("src/database/order.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (data.get(0).equals(orderID)) {
                this.orderID = data.get(0);
                this.date = data.get(1);
                this.customerID = data.get(2);
                this.productID = data.get(3);
                this.productPrice = Double.parseDouble(data.get(4));
                this.discount = Double.parseDouble(data.get(5));
                this.total = Double.parseDouble(data.get(6));
                this.status = data.get(7);
                break;
            }
        }

    }

    public void showOrder() {
        System.out.println(
                "Order ID: " + this.orderID + " " +
                "Order Date: " + this.date + " " +
                "Customer ID: " + this.customerID + " " +
                "Product ID: " + this.productID + " " +
                "Product Price:" + this.productPrice + " " +
                "Discount: " + this.discount + " " +
                "Total: " + this.total + " " +
                "Status: " + this.status
        );
    }
}
