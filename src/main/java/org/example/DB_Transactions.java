package org.example;

import java.sql.*;
import java.util.Scanner;
import java.util.UUID;

public class DB_Transactions {
    Connection con;
    Scanner in;
    Statement st;

    String uuid;
    enum Status{
        HOLD,IN_HOUSE,CHECK_OUT;
    }

    DB_Transactions() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/hotelkey-assignment";
        con = DriverManager.getConnection(url, "root", "root");
        st=con.createStatement();
        in=new Scanner(System.in);

        //making Auto commit False
        con.setAutoCommit(false);
    }

    String addReservation() throws SQLException {
        System.out.println("For Making a Reservation, Please: \nEnter Check in Date:");
        String checkin=in.nextLine();

        System.out.println("Enter Check Out Date:");
        String checkout=in.nextLine();

        System.out.println("Enter Status of Reservation \n 1 for HOLD \n 2 for IN HOUSE \n 3 for CHECKOUT:");
        int choice=in.nextInt();
        String status;

        uuid =String.valueOf(UUID.randomUUID());

        if(choice==1)
            status = String.valueOf(Status.HOLD);
        else if (choice ==2)
            status= String.valueOf(Status.IN_HOUSE);
        else
            status= String.valueOf(Status.CHECK_OUT);


        PreparedStatement pst=con.prepareStatement("Insert into reservations values (?,?,?,?);");

        pst.setString(1,uuid);
        pst.setString(2,checkin);
        pst.setString(3,checkout);
        pst.setString(4,status);

        int updateCheck=pst.executeUpdate();

        if(updateCheck>0)
            System.out.println("Reservation Made Successfully\n");
        else
            System.out.println("Error Making Reservation\n");

        return uuid;
    }

    void addGuests() throws SQLException {
        System.out.println("Please Add Guests to the Reservation");
        System.out.print("For Adding Guests, Please: \nEnter First Name:\n");

        in.nextLine();

        String fname=in.nextLine();

        System.out.println("Enter Last Name:");
        String lname=in.nextLine();

        //updateCheck=0;
        System.out.println(uuid);
        System.out.println(fname);
        System.out.println(lname);


        PreparedStatement pst1= con.prepareStatement("INSERT INTO guests values(?,?,?,?)");
        //int a=pst.executeUpdate("INSERT INTO guests values(?,?,?,?)");
        pst1.setString(1, String.valueOf(UUID.randomUUID()));
        pst1.setString(2,uuid);
        pst1.setString(3,fname);
        pst1.setString(4,lname);

        int updateCheck=pst1.executeUpdate();

        System.out.println("Commiting Data Now...");
        con.commit();


    }
}
