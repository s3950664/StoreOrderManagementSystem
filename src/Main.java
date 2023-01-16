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

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //REGISTER
        Guest guest = new Guest();
        guest.register("maol70", "passw0rd", "Nguyen Nam", "0909265150", "email", "address");
        guest.register("maol71", "passw0rd", "Nguyen Nam", "0909265150", "email", "address");
        System.out.println("=================");

        //LOGIN
        Customer customer = guest.customerLogin("dragonball67","passw0rd");
        Admin admin = guest.adminLogin("admin","admin123");
        System.out.println("=================");

        //VIEW AND UPDATE INFORMATION
        customer.viewInformation();
        System.out.println("=================");
        System.out.println("CUSTOMER UPDATE THEIR INFORMATION");
        customer.updateInformation("Tran Thien Tan","09090900295124","tan@gmail.com","31 A Street");
        customer.viewInformation();
        System.out.println("=================");

        //CHECK MEMBERSHIP
        System.out.println("CHECK MEMBERSHIP");
        customer.checkMembership();
        System.out.println("=================");

        //ADMIN VIEW ALL USERS AND TRY TO REMOVE ONE USER
        System.out.println("ADMIN VIEW ALL USERS AND TRY TO REMOVE ONE USER");
        admin.viewAllUser();
        System.out.println("=================");
        admin.removeCustomerByID("C5");
        admin.viewAllUser();
        System.out.println("=================");

        //VIEW AND UPDATE PRODUCT
        admin.viewAllProduct();
        System.out.println("=================");

        admin.addProduct("Rubik",8, "Rubik");
        admin.addProduct("E-DRA",10, "Mouse");
        admin.viewAllProduct();
        System.out.println("=================");

        admin.removeProductByID("P3");
        admin.viewAllProduct();
        System.out.println("=================");

        admin.updatePriceByProductID("P5", 20000000);
        admin.viewAllProduct();
        System.out.println("=================");

        //REMOVE AND CHANGE PRODUCT CATEGORY
        admin.removeCategory("Rubik");
        admin.viewAllProduct();
        System.out.println("=================");

        admin.changeProductCategoryByID("P5", "Rubik 2.0");
        admin.viewAllProduct();
        System.out.println("=================");

        //VIEW PRODUCT BY CATEGORY AND PRICE
        System.out.println("SORT BY ASCENDING PRICE ORDER");
        customer.viewAllProductByCategoryAndPrice("","ASC");
        System.out.println("=================");

        System.out.println("SORT BY DESCENDING PRICE ORDER WITH A SPECIFIC CATEGORY");
        customer.viewAllProductByCategoryAndPrice("Keyboard","DES");
        System.out.println("=================");

        System.out.println("SORT IN PRICE RANGE WITH A SPECIFIC CATEGORY");
        customer.viewAllProductByCategoryAndPriceRange("Keyboard", 7,9);
        System.out.println("=================");

        //PLACE ORDER AND CHECK MEMBERSHIP
        System.out.println("MEMBERSHIP BEFORE BUYING");
        customer.checkMembership();
        System.out.println("=================");
        System.out.println("PLACE ORDER");
        customer.placeOrder("P5");
        System.out.println("=================");
        System.out.println("MEMBERSHIP AFTER BUYING");
        customer.checkMembership();
        System.out.println("=================");
        System.out.println("SHOW ORDER BY ID");
        customer.showOrderByID("O4");
        System.out.println("=================");
        System.out.println("SHOW ALL ORDER BY THIS USER");
        customer.showAllOrder();
        System.out.println("=================");

        //ADMIN APPROVES ORDER
        admin.viewAllOrder();
        System.out.println("=================");
        admin.approve("O3");
        admin.viewAllOrder();
        System.out.println("=================");

        //ADMIN SHOW ORDER BY DATE

        //TODAY
        System.out.println("SHOW ALL ORDER BY TODAY");
        admin.showAllOrderByDay("");
        System.out.println("=================");

        //SPECIFIC DAY
        System.out.println("SHOW ALL ORDER BY SPECIFIC DATE");
        admin.showAllOrderByDay("2023-01-14");
        System.out.println("=================");

        //ADMIN CALCULATE TOTAL REVENUE
        System.out.println("CALCULATE TOTAL REVENUE");
        System.out.println(admin.calculateTotalRevenue());
        System.out.println("=================");

        //ADMIN CALCULATE REVENUE TODAY
        System.out.println("CALCULATE REVENUE TODAY");
        System.out.println(admin.calculateRevenueToday());
        System.out.println("=================");

        //ADMIN SHOW MOST POPULAR AND LEAST POPULAR PRODUCT
        System.out.println("SHOW MOST POPULAR AND LEAST POPULAR PRODUCT");
        admin.showMostPopularProductAndLeastPopularProduct();
        System.out.println("=================");

        //ADMIN SHOW MOST SPENDING CUSTOMER
        System.out.println("SHOW MOST SPENDING CUSTOMER");
        admin.showMostSpendingCustomer();
        System.out.println("=================");

        //ADMIN SHOWING MEMBERSHIP INFO
        System.out.println("SHOW MEMBERSHIP INFO");
        admin.showMembershipInfo();
        System.out.println("=================");

        //FINAL MESSAGE
        System.out.println("COSC2081 GROUP ASSIGNMENT\n" +
                            "STORE ORDER MANAGEMENT SYSTEM\n" +
                            "Instructor: Mr. Tom Huynh & Dr. Phong Ngo\n" +
                            "Group: Group Seven\n" +
                            "S3950664, Nguyen Phu Nhat Nam\n" +
                            "S3956465, Nguyen Tan Tan\n" +
                            "s3864004, Tran Thien Tan\n" +
                            "s3912395, Nguyen Duc Quy");
    }
}