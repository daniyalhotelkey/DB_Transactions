package org.example;

import java.sql.*;
import java.util.Scanner;

public class DB_Statements {
    Connection con;
    Scanner in;
    Statement st;

    String uuid;


    DB_Statements() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1/hotelkey-assignment";
        con = DriverManager.getConnection(url, "root", "root");
        st=con.createStatement();
        in=new Scanner(System.in);
    }

    void getData() throws SQLException {

        ResultSet resultSet= st.executeQuery("SELECT * from guests");
        while(resultSet.next())
        {
            System.out.print(resultSet.getString(1)+" ");
            System.out.print(resultSet.getString(2)+" ");
            System.out.print(resultSet.getString(3)+" ");
            System.out.print(resultSet.getString(4)+"\n");
        }
    }


    void insertData() throws SQLException {

        int rs=0;
        rs=st.executeUpdate("Insert into guests values(UUID(),'4c2927ec-fdca-11ec-b461-8a3776a9eba0','Ahmad','Rehman')");

        if(rs>0)
            System.out.println("Data Inserted Successfully");
        else
            System.out.println("Insertion Failed");
    }


    void updateData() throws SQLException {
        int rs=-1;
        rs=st.executeUpdate("UPDATE guests SET last_name='Ahmad' WHERE first_name='Ahmad'");

        if(rs>0)
            System.out.println("Data Updated Successfully");
        else
            System.out.println("Error; Updating Data Failed");
    }


    void deleteData() throws SQLException {
        int rs=-1;
        rs=st.executeUpdate("DELETE from guests where first_name='Ahmad'");

        if(rs>0)
            System.out.println("Data Deleted Successfully");
        else
            System.out.println("Error; Deleted Data Failed");
    }


}
