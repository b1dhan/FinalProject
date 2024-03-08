package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String id;
    List<Order> orderList;

    public User(String name, String id, List<Order> orderList) {
        this.name = name;
        this.id = id;
        this.orderList = orderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    //------------------------------------------------------------------------------------------------------
//  making a method that displays the list of books the user has ordered
    public void DisplayList(ArrayList<Order> orders) {
        System.out.println("----------------------------List of Books Ordered by " + this.getName()+ "----------------------------");
        System.out.println("Name \t \t \t \t Quantity \t Price \t Additional Description");
        for (Order order : orders) {
            System.out.printf("%s \t \t \t %d \t \t %d \t \t %s%n",
                    order.getBook_name(), order.getQuantity(), order.getPrice(), order.getAdd_description());
        }
    }
    //------------------------------------------------------------------------------------------------------
//   making a method to display bill of the user
    public void DisplayBill(ArrayList<Order> orders) {
        System.out.println("----------------------------Bill for " + this.getName() + "----------------------------");
        System.out.println("Name \t \t \t \t Quantity \t Price \t \tTotal");

        double grandTotal = 0;

        for (Order order : orders) {
            int quantity = order.getQuantity();
            int price = order.getPrice();
            double total = quantity * price;
            double vat = total*0.17;
            double totalfr=total+vat;

            System.out.printf("%s \t \t \t %d \t \t %d \t \t%.2f%n",
                    order.getBook_name(), quantity, price, total);

            grandTotal += totalfr;
        }

        System.out.printf("\t \t \t \t \t Grand Total(with VAT): %.2f%n", grandTotal);
    }

}
