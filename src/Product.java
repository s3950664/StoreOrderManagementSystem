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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Product {
    private String productID;
    private String productName;
    private double productPrice;
    private String category;

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getCategory() {
        return category;
    }

    public Product(String productID) throws IOException {

        this.productID = productID;

        File file = new File("src/database/product.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            ArrayList<String> data = new ArrayList<>(Arrays.asList(line.split(",")));

            if (data.get(0).equals(productID)) {
                this.productName = data.get(1);
                this.productPrice = Double.parseDouble(data.get(2));
                this.category = data.get(3);
                break;
            }
        }

    }
}
