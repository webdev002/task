package uz.pdp.service;

import uz.pdp.model.Result;
import uz.pdp.model.User;

import java.sql.*;

public class DbService {
    String url = "jdbc:postgresql://localhost:5432/app-auth";
    String dbUser = "postgres";
    String dbPasword= "name1234";

    public Result registerUser(User user)  {
       try {
           Class.forName("org.postgresql.Driver");
           Connection connection = DriverManager.getConnection(url,dbUser,dbPasword);
           Statement statement = connection.createStatement();

           String checkPhoneNumberQuary = "select count(*) from users where phone_number='"+user.getPhoneNumber()+"'";
           ResultSet resultSet = statement.executeQuery(checkPhoneNumberQuary);
           int countUserByFields=0;
           while (resultSet.next()){
               countUserByFields = resultSet.getInt(1);
           }
           if (countUserByFields>0){
               return new Result("Phone Number already exist", false);
           }
           String checkUsernameQuary= "select count(*) from users where username='"+user.getUsername()+"'";
           ResultSet resultSetUserName = statement.executeQuery(checkUsernameQuary);
           while(resultSetUserName.next()){
               countUserByFields=resultSetUserName.getInt(1);
           }
           if (countUserByFields>0){
               return new Result("username already exist", false);
           }
            String query ="insert into users(username,first_name,last_name,phone_number,password)\n" +
                    "values('"+user.getUsername()+"','"+user.getFirstName()+"','"+user.getLastName()+"','"+user.getPhoneNumber()+"','"+user.getPassword()+"');";
           boolean execute = statement.execute(query);
           System.out.println(execute);
           return new Result("Successfully registered", true);
       }catch (SQLException | ClassNotFoundException throwables){
           throwables.printStackTrace();

           return new Result("Error in server", false);
       }
    }
public User login(User user)  {
    try  {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, dbUser, dbPasword)
   String query = "select * from users where username=? and password=?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(1, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
          String  username = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String phoneNumber = resultSet.getString(5);
            User user1 =new User(
                    firstName,
                    lastName,
                    phoneNumber,
                    username );
            return user;


        }






    }
    catch (SQLException | ClassNotFoundException throwables){
        throwables.printStackTrace();
    }
    return null;
}
}
