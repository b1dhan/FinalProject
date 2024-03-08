package org.example.models;

import java.util.ArrayList;

public class Order {
    String book_name;
    int quantity;
    int price;
    String add_description;
    User user;

    public Order(String book_name, int quantity, int price, String add_description) {
        this.book_name = book_name;
        this.quantity = quantity;
        this.price = price;
        this.add_description = add_description;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAdd_description() {
        return add_description;
    }

    public void setAdd_description(String add_description) {
        this.add_description = add_description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //----------------------------------------------------------------------------------------------------------------------

}
