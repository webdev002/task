package uz.pdp.controller;

import uz.pdp.model.User;
import uz.pdp.service.DbService;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        DbService dbService = new DbService();
        User user = dbService.login(username, password);
        PrintWriter printWriter = resp.getWriter();
        if (user==null){
            printWriter.write("<h1>password or login error</h1>");

        }else {
            printWriter.write("<h1>Welcome to cabinet"+user.getFirstName()+""+user.getLastName()+"</h1>");
            printWriter.write("<h1>Your phone Number"+user.getFirstName()+"</h1>");
        }
    }
}
