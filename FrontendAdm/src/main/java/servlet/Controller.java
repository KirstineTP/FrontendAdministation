package servlet;

import dk.pba2sem.frontendadm.Command;
import dk.pba2sem.frontendadm.Factory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet
{
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/index.html";
        String commandString = request.getParameter("command");

        Command command = Factory.getInstance().getCommand(commandString, request);
        path = command.execute(request);
        
        System.out.println("COMMAND STRING: " + commandString);
    }
}
