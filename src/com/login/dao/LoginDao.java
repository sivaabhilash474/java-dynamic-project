package com.login.dao;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.login.bean.LoginBean;
import com.login.util.DBConnection;
 
public class LoginDao {
     public String authenticateUser(LoginBean loginBean)
     {
         String userName = loginBean.getUserName(); //Assign user entered values to temporary variables.
         String password = loginBean.getPassword();
         
         System.out.println("User name from LoginDAO: "+userName);
 
         Connection con = null;
         Statement statement = null;
         ResultSet resultSet = null;
 
         String userNameDB = "";
         String passwordDB = "";
 
         try
         {
             con = DBConnection.createConnection(); //Fetch database connection object
             if(con != null) {
            	 System.out.println("Connection is not null...");
             }
             statement = con.createStatement(); //Statement is used to write queries. Read more about it.
             resultSet = statement.executeQuery("select userName,password from users"); //the table name is users and userName,password are columns. Fetching all the records and storing in a resultSet.
 
             while(resultSet.next()) // Until next row is present otherwise it return false
             {
              userNameDB = resultSet.getString("userName"); //fetch the values present in database
              passwordDB = resultSet.getString("password");
              
              System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
 
               if(userName.equals(userNameDB) && password.equals(passwordDB))
               {
                  return "SUCCESS"; ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
               } else {
            	   System.out.println("Invalid user credentials...");
            	   
               }
             }
             return "Invalid user credentials";
         }
             catch(SQLException e)
             {
                e.printStackTrace();
             }
             return "Invalid user credentials"; // Return appropriate message in case of failure
         
     }
}