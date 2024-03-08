package org.example;


import org.example.models.Order;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String ORDER_TABLE = "book_order";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_BOOK_NAME = "book_name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QTY = "quantity";
    public static void main(String[] args) {

//      displaying the available books to the user with predefined prices
        System.out.println("--------------------WELCOME TO THE BOOK STORE--------------------");
        System.out.println("Available Books: (Please note that the prices are fixed and non negotiable.)");
        System.out.println("Name \t \t \t \t \t Author \t \t \t \t \t \tGenre \t \t \t Price");
        System.out.println("1.Being Edwards \t \t Phil Earle \t \t \t \t \tFiction \t \t Rs.675");
        System.out.println("2.Atomic Habits \t \t James Clear \t \t \t \t \tSelf-Help \t \t Rs.500");
        System.out.println("3.The Art of War \t \t Sun Tzu of China \t \t \t \tTreatise \t \t Rs.800");
        System.out.println("4.Norwegian Wood \t \t Haruki Murakami \t \t \t \tBildungsroman \t Rs.675");
        System.out.println("5.The Antichrist \t \t Friedrich Nietzsche \t \t \tPhilosophy \t \t Rs.450");
        System.out.println("_______________________________________________________________________________");
//      ------------------------------------------------------------------------------------------------------
//      populating objects of classes user, order
        Order o1 = new Order("Atomic Habits", 2, 500,"Add an extra cover, ty.");
        Order o2 = new Order("The Antichrist", 1, 450,"Without the cover, ty.");
        Order o3 = new Order("The Art of War", 3, 800,"An additional cover, ty.");

        ArrayList<Order> io1 = new ArrayList<>();
        io1.add(o1);
        io1.add(o2);

        ArrayList<Order> io2 = new ArrayList<>();
        io2.add(o3);

//      ------------------------------------------------------------------------------------------------------

        User user1 = new User("Ram", "1121", io1);
        User user2 = new User("Shyam", "1122", io2);

//      using the user defined methods to display list and bill
        user1.DisplayList(io1);
        user2.DisplayList(io2);

        user1.DisplayBill(io1);
        user2.DisplayBill(io2);
//      ------------------------------------------------------------------------------------------------------

//        connecting the classes and the values input in the objects to the database in SQLite
            String url = "jdbc:sqlite:mydb.db";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + ORDER_TABLE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_BOOK_NAME + " TEXT, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_QTY + " INTEGER)";
//        sout to check MYSQL syntax
        System.out.println(createTableSQL);
//        inserting values into the table
        String insertRecordSQL = "INSERT INTO " + ORDER_TABLE + "(" +
                COLUMN_NAME + ", " + COLUMN_BOOK_NAME + ", " + COLUMN_PRICE + ", " + COLUMN_QTY +
                ") VALUES (?, ?, ?, ?)";
        System.out.println(insertRecordSQL);

            try {
                Connection connection = DriverManager.getConnection(url);
                System.out.println("Connected");

                Statement stm=connection.createStatement();
                stm.execute(createTableSQL);
                System.out.println("Table Created");
//      ------------------------------------------------------------------------------------------------------
//                INSERTION OF DATA
//                using for loop for each insertion of value due to multiple orders from ArrayList<Order>
                for (Order order : io1) {
                        PreparedStatement preparedStatement1 = connection.prepareStatement(insertRecordSQL);
                        preparedStatement1.setString(1, user1.getName());
                        preparedStatement1.setString(2, order.getBook_name());
                        preparedStatement1.setInt(3, order.getPrice());
                        preparedStatement1.setInt(4, order.getQuantity());

                        preparedStatement1.execute();

                        System.out.println("Values Inserted for User1");
                }

                for (Order order : io2) {
                    PreparedStatement preparedStatement2 = connection.prepareStatement(insertRecordSQL);
                    preparedStatement2.setString(1, user2.getName());
                    preparedStatement2.setString(2, order.getBook_name());
                    preparedStatement2.setInt(3, order.getPrice());
                    preparedStatement2.setInt(4, order.getQuantity());

                    preparedStatement2.execute();

                    System.out.println("Values Inserted for User2");
                }
//      ------------------------------------------------------------------------------------------------------
//                SELECTION OF DATA ACCORDING TO ADMIN'S CHOICE

                String selectOrdersForUserSQL = "SELECT * FROM " + ORDER_TABLE + " WHERE " + COLUMN_NAME + " = ?";
                System.out.println(selectOrdersForUserSQL);
                PreparedStatement preparedStatement3 = connection.prepareStatement(selectOrdersForUserSQL);
                preparedStatement3.setString(1, "Ram");
                preparedStatement3.execute();
                ResultSet rs = stm.executeQuery(selectOrdersForUserSQL);
                while (rs.next()) {
                    // Process the retrieved data
                    int id = rs.getInt(COLUMN_ID);
                    String userName = rs.getString(COLUMN_NAME);
                    String bookName = rs.getString(COLUMN_BOOK_NAME);
                    int price = rs.getInt(COLUMN_PRICE);
                    int quantity = rs.getInt(COLUMN_QTY);
                }
                System.out.println("Values Selected");
//      ------------------------------------------------------------------------------------------------------
//                UPDATING THE TABLE ACC TO ADMIN'S CHOICE

                String updateOrderQuantitySQL = "UPDATE " + ORDER_TABLE + " SET " + COLUMN_QTY + " = ? WHERE " + COLUMN_ID + " = ?";
                System.out.println(updateOrderQuantitySQL);
                PreparedStatement preparedStatement4 = connection.prepareStatement(updateOrderQuantitySQL);
                preparedStatement4.setInt(1,5);
                preparedStatement4.setInt(2,4);
                preparedStatement4.execute();
                System.out.println("Values Updated");
//      ------------------------------------------------------------------------------------------------------
//                DELETION OF VALUES ACC TO ADMIN'S CHOICE

                String deleteOrderSQL = "DELETE FROM " + ORDER_TABLE + " WHERE " + COLUMN_ID + " > ?";
                System.out.println(deleteOrderSQL);
                PreparedStatement preparedStatement5 = connection.prepareStatement(deleteOrderSQL);
                preparedStatement5.setString(1, "5");
                preparedStatement5.execute();
                System.out.println("Values Deleted");
//      ------------------------------------------------------------------------------------------------------
                connection.close();

        }
            catch (SQLException e) {
                System.out.println("error");
                e.printStackTrace();
//            this gives us info about where error is made
            }
    }
}
