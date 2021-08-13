package uz.pdp.controller;

import lombok.SneakyThrows;
import uz.pdp.model.Result;
import uz.pdp.model.User;
import uz.pdp.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendRedirect("register.html");
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String firstName = req.getParameter("FirstName");
      String lastName = req.getParameter("LastName");
      String username = req.getParameter("username");
      String phoneNumbr = req.getParameter("phoneNumbr");
      String password = req.getParameter("password");
      String prePassword = req.getParameter("prePassword");
        PrintWriter printWriter = resp.getWriter();

        if (password.equals(prePassword)){
         DbService dbService = new DbService();
         User user = new User();
         user.setUsername(username);
         user.setFirstName(firstName);
         user.setLastName(lastName);
         user.setPhoneNumber(phoneNumbr);
         user.setPassword(password);
         Result result = dbService.registerUser(user);
         if (result.isSucsess()){
             printWriter.write("<h1 color='green'>"+result.getMassage()+"</h1>");
         } else {
             printWriter.write("<h1 color='red'>"+result.getMassage()+"</h1>");
         }
     } else{
            printWriter.write("passwords not equal");
        }
    }
}
