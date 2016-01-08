package dk.pba2sem.frontendadm;

import javax.servlet.http.HttpServletRequest;

public interface Command 
{
    String execute(HttpServletRequest request);
}
