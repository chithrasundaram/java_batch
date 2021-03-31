package jrout.tutorial.batch35.dvdapp.dao;

import jrout.tutorial.batch35.dvdapp.domain.UserInformation;
import org.postgresql.Driver;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {


      public Connection connectDB()
      {
          String connectionURL = "jdbc:postgresql://localhost:5432/dvdrental";
          Driver driver = new Driver();
          try {

              System.out.println("Driver is loaded.");
              Class.forName("org.postgresql.Driver");
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }

          Connection connection = null;
          try {
              connection = DriverManager.getConnection
                      (connectionURL, "postgres", "postgres");
              System.out.println("Connection is established.");
          }catch (SQLException e) {
              e.printStackTrace();
          }

          return connection;
      }

      public UserInformation fetchCustomer(int customerId) throws SQLException {


          UserInformation userInformation = new UserInformation();
          System.out.println("fetch");
          Connection connection = connectDB();

          try {

          PreparedStatement preparedStatement = connection.
                  prepareStatement("select * from customer where customer_id = ?");
          preparedStatement.setInt(1,customerId);
          ResultSet resultSet = preparedStatement.executeQuery();

          while(resultSet.next()) {
              userInformation.setEmail(resultSet.getString("email"));
              userInformation.setFirst_name(resultSet.getString("first_name"));
              userInformation.setCustomer_id(resultSet.getInt("customer_id"));
              userInformation.setLast_name(resultSet.getString("last_name"));
          }
          }
          catch (SQLException e) {
              e.printStackTrace();
          }finally {
              try {
                  connection.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }

          return userInformation;


      }

    public List<UserInformation> fetchCustomersByFirstName(String  firstName) throws SQLException {


        Connection connection = connectDB();
        List<UserInformation> userList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from customer where first_name like ?");
            preparedStatement.setString(1, firstName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                UserInformation userInformation = new UserInformation();
                userInformation.setEmail(resultSet.getString("email"));
                userInformation.setFirst_name(resultSet.getString("first_name"));
                userInformation.setCustomer_id(resultSet.getInt("customer_id"));
                userInformation.setLast_name(resultSet.getString("last_name"));
                userInformation.setLast_name(resultSet.getString("store_id"));
                userInformation.setActive(resultSet.getString("active"));
                userInformation.setAddress_id(resultSet.getString("address_id"));
                userList.add(userInformation);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void insertCustomer(UserInformation userInformation) throws SQLException {


        Connection connection = connectDB();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select max(customer_id) as high_customer_id from customer");

            ResultSet resultSet = preparedStatement.executeQuery();
            int next_customer_id = 0;
            while (resultSet.next()) {
                next_customer_id = resultSet.getInt("high_customer_id");
            }
            next_customer_id = next_customer_id + 1;
            String email = userInformation.getEmail();
            String fname = userInformation.getFirst_name();
            String lname = userInformation.getLast_name();
            String active = userInformation.getActive();

            int active1 = 0;
            if (active != null) {
                active1 = 1;
            }

            PreparedStatement preparedStatement1 = connection.
                    prepareStatement("insert into customer(customer_id, first_name, last_name,email, store_id, address_id,active) values (?,?,?,?,?,?,?)");
            preparedStatement1.setInt(1, next_customer_id);
            preparedStatement1.setString(2, fname);
            preparedStatement1.setString(3, lname);
            preparedStatement1.setString(4, email);
            preparedStatement1.setInt(5, 2);
            preparedStatement1.setInt(6, 100);
            //   int active;

            preparedStatement1.setInt(7, active1);
            System.out.println(preparedStatement1);
            preparedStatement1.executeUpdate();
        }
         catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public UserInformation deleteCustomer(int customerId) throws SQLException {

        UserInformation userInformation = new UserInformation();
        Connection connection = connectDB();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from customer where customer_id = ?");
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userInformation.setEmail(resultSet.getString("email"));
                userInformation.setFirst_name(resultSet.getString("first_name"));
                userInformation.setCustomer_id(resultSet.getInt("customer_id"));
                userInformation.setLast_name(resultSet.getString("last_name"));
            }
            System.out.println(userInformation);
            PreparedStatement preparedStatement1 = connection.
                    prepareStatement("delete from customer where customer_id = ?");
            preparedStatement1.setInt(1, customerId);

            System.out.println(preparedStatement1);
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userInformation;
    }

    public void updateCustomer(UserInformation userInformation, int customerId) throws SQLException {


        Connection connection = connectDB();
        String email_upate = userInformation.getEmail();
        String fname_update = userInformation.getFirst_name();
        String lname_update = userInformation.getLast_name();
       // String email_update = userInformation.getEmail();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from customer where customer_id = ? ");
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                userInformation.setEmail(resultSet.getString("email"));
                userInformation.setFirst_name(resultSet.getString("first_name"));
                userInformation.setCustomer_id(resultSet.getInt("customer_id"));
                userInformation.setLast_name(resultSet.getString("last_name"));

            }
            PreparedStatement preparedStatement1 = connection.
                    prepareStatement("update customer set first_name = ?, last_name = ?, email = ? where customer_id = ?");
            preparedStatement1.setString(1, fname_update);
            preparedStatement1.setString(2, lname_update);
            preparedStatement1.setString(3, email_upate);
            preparedStatement1.setInt(4, customerId);

            System.out.println(preparedStatement1);
            preparedStatement1.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}



