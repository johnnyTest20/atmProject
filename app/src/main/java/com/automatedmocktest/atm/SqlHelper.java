package com.automatedtellermachine.android.atmproject;

import android.accounts.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by matthewkim on 12/21/17.
 */

public class SqlHelper {
    public static final String sqlTable = "MAIN MOBILETEST " +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " PASSWORD       CHAR(20) NOT NULL UNIQUE);";

    public static final String sqlTable1 = "CREATE TABLE ACCOUNT" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "USER_ID INTEGER NOT NULL UNIQUE,"+  // Keep it simple, only allows for one Checking and Savings account
            "CHECKING REAL NOT NULL,"+
            "SAVINGS REAL NOT NULL);";


    public SqlHelper()
    {
        Connection mConnection = null;
        Statement mStatement = null;

        // Here is where we will Intiate the Table If Necessary
        try {
            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();

            mStatement.execute(sqlTable);
            mStatement.execute(sqlTable1);
            mStatement.close();
            mConnection.close();

        }catch(Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }


    // User Table Operations
    // Insert New User
    public boolean insertNewuser(String name, String password)
    {
        boolean toReturn = true;
        if(!(testPassword(password) || checkAccountExist(name,password)))
        {
            Connection mConnect = null;
            Statement mStatement = null;

            try
            {
                Class.forName("org.sqlite.JDBC");
                mConnect = DriverManager.getConnection("jdbc:sqlite:test.db");
                System.out.println("Opened database successfully");
                mStatement = mConnect.createStatement();
                System.out.println("Opened database successfully");

                mStatement = mConnect.createStatement();

                String Query = "INSERT INTO USER (NAME,PASSWORD) " +
                        "VALUES ("+ name +", " + password +" );";

                mStatement.execute(Query);
                mStatement.close();
                mConnect.close();

            }catch (Exception ex)
            {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                System.exit(0);
            }

        }else
        {
            toReturn= false;
        }
        return toReturn;
    }

    public void insertAccount(Integer id)
    {
        Connection mConnect = null;
        Statement mStatement = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            mConnect = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            mStatement = mConnect.createStatement();
            System.out.println("Opened database successfully");

            mStatement = mConnect.createStatement();

            String Query = "INSERT INTO ACCOUNT (USER_ID,CHECKING,SAVINGS) " +
                    "VALUES ("+ id.toString() +",0.00, 0.00 );";  // Account initialized to zero.  Upto you to update.

            mStatement.execute(Query);
            mStatement.close();
            mConnect.close();

        }catch (Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }

    public boolean testPassword(String password)
    {
        Connection mConnection = null;
        Statement  mStatement = null;
        Boolean toReturn = true;

        try
        {
            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            System.out.println("Opened database successfully");

            // Query
            ResultSet result = mStatement.executeQuery("SELECT * FROM USER WHERE PASSWORD =" +password +";");

            if (result.getFetchSize() !=0)
                toReturn = true;
            else
                toReturn = false;

        }catch (Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }

        return  toReturn;
    }

    public boolean checkAccountExist(String name, String Password)
    {
        Connection mConnection = null;
        Statement  mStatement = null;
        Boolean toReturn = true;

        try {

            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            System.out.println("Opened database successfully");

            // Query
            ResultSet result = mStatement.executeQuery("SELECT * FROM USER WHERE PASSWORD =" + Password +" AND NAME =" + name + ";");

            if (result.getFetchSize() !=0)
                toReturn = true;
            else
                toReturn = false;

        }catch (Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }

        return  toReturn;
    }

    // This method here retrives the user POJO.  Made Public for you to acces.
    public com.example.michaeliverson.sqllite.User retreiveUser(String Name, String Password)
    {
        Connection mConnection = null;
        Statement  mStatement = null;
        com.example.michaeliverson.sqllite.User returnUserInfo = null;
        Account userAccount = null;
        try {

            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            System.out.println("Opened database successfully");

            // Query
            ResultSet result = mStatement.executeQuery("SELECT * FROM USER WHERE PASSWORD =" + Password +"AND NAME =" + Name + ";");

            while (result.next())
            {
                returnUserInfo = new com.example.michaeliverson.sqllite.User();
                returnUserInfo.setId(result.getInt("ID"));
                returnUserInfo.setName(result.getString("NAME"));
                returnUserInfo.setPassword(result.getString("PASSWORD"));
            }
            result.close();
            mStatement.close();
            mConnection.close();

            // Now get account
            returnUserInfo.setAccountInfo(retriveAccountInfo(returnUserInfo.getId()));

        }catch (Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
        return returnUserInfo;
    }

    // Access you account through your USER pojo
    private Account retriveAccountInfo(Integer userId)
    {
        Connection mConnection = null;
        Statement  mStatement = null;
        com.example.michaeliverson.sqllite.User returnUserInfo = new com.example.michaeliverson.sqllite.User();
        Account userAccount = null;
        try {

            Class.forName("org.sqlite.JDBC");
            mConnection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Opened database successfully");
            mStatement = mConnection.createStatement();
            System.out.println("Opened database successfully");

            // Query
            ResultSet result = mStatement.executeQuery("SELECT * FROM ACCOUNT WHERE USER_ID =" + userId.toString() +";");

            while (result.next())
            {
                userAccount = new Account();
                userAccount.setId(result.getInt("ID"));
                userAccount.setUserId(result.getInt("USER_ID"));
                userAccount.setChecking(result.getFloat("CHECKING"));
                userAccount.setSavings(result.getFloat("SAVINGS"));
            }
            result.close();
            mStatement.close();;
            mConnection.close();

        }catch (Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
        return userAccount;
    }


    public void updateAccount(Account accountInfo)
    {
        Connection mConnection = null;
        Statement  mStatement = null;

        try
        {
            Class.forName("org.sqlite.JDBC");
            mConnection= DriverManager.getConnection("jdbc:sqlite:test.db");
            mConnection.setAutoCommit(false);
            System.out.println("Opened database successfully");

            // Update Queries
            mStatement = mConnection.createStatement();
            String sql = "UPDATE ACCOUNT set CHECKING ="+ accountInfo.getChecking().toString() +", SAVINGS =" +accountInfo.getSavings().toString() + " where ID =" + accountInfo.getId() +";";
            mStatement.executeUpdate(sql);
            mConnection.commit();
            mStatement.close();
            mConnection.close();

        }catch (Exception ex)
        {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
    }
}
