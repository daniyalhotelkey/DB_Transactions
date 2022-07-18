package org.example;

import java.sql.*;
import java.util.Scanner;
import java.util.UUID;

public class DB_PreparedStatements {
    Connection con;
    Scanner in;
    Statement st;

    String uuid;
    enum Status{
        HOLD,IN_HOUSE,CHECK_OUT;
    }

    DB_PreparedStatements() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/hotelkey-assignment";
        con = DriverManager.getConnection(url, "root", "root");
        st = con.createStatement();
        in = new Scanner(System.in);
    }

    void insertData() throws SQLException {
        PreparedStatement preSt=con.prepareStatement("INSERT INTO guests values(?,?,?,?) ");

        preSt.setString(1, String.valueOf(UUID.randomUUID()));
        preSt.setString(2,"4c2927ec-fdca-11ec-b461-8a3776a9eba0");
        preSt.setString(3,"Mehrooz");
        preSt.setString(4,"Tahir");


        int rs=preSt.executeUpdate();

        if(rs>0)
            System.out.println("Data Inserted Successfully");
        else
            System.out.println("Insertion Failed");
    }

    void updateData() throws SQLException {
        //Updating Data using prepared statements
        PreparedStatement preSt= con.prepareStatement("Update guests set last_name=? where last_name=?");
        preSt.setString(1,"Tariq");
        preSt.setString(2,"Tahir");

        int rs=preSt.executeUpdate();

        if(rs>0)
            System.out.println("Data Updated Successfully");
        else
            System.out.println("Updating Data Failed");
    }

    void deleteData() throws SQLException {
        PreparedStatement preSt=con.prepareStatement("DELETE FROM guests WHERE first_name=? ");

        preSt.setString(1, "Tariq");

        int rs=preSt.executeUpdate();

        if(rs>0)
            System.out.println("Data Deleted Successfully");
        else
            System.out.println("Deletion Failed");
    }



}
