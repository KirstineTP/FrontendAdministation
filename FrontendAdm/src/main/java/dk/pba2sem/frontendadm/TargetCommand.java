package dk.pba2sem.frontendadm;

import javax.servlet.http.HttpServletRequest;

public class TargetCommand implements Command
{
    protected String target;
    
    public TargetCommand(String target)
    {
        this.target = target;
    }
    
    @Override
    public String execute(HttpServletRequest request) {
        return target;
    }
}
