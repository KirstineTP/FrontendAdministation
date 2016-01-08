package dk.pba2sem.frontendadm;

import DTOs.AllAboutCustomerDTO;
import DTOs.AllForBookingDTO;
import DTOs.BookingDTO;
import DTOs.CustomerDTO;
import DTOs.DiscountDTO;
import DTOs.FerryDTO;
import DTOs.HarborDTO;
import DTOs.OrderDTO;
import DTOs.RouteDTO;
import DTOs.ScheduleDTO;
import DTOs.VehicleDTO;
import ETOs.NoConnectionETO;
import ETOs.NoCustomerETO;
import ETOs.NoDiscountETO;
import ETOs.NoFerryETO;
import ETOs.NoHarborETO;
import ETOs.NoRouteETO;
import ETOs.NoScheduleETO;
import ETOs.NoTransactionETO;
import ETOs.NoVehicleETO;
import contracts.AdminContract;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class Factory implements AdminContract
{
    private static final Factory INSTANCE = new Factory();
    private Map<String, Command> commands;
    private AdminContract admContractBean = lookupAdminContractBeanRemote();
    
    private Factory()
    {
        commands = new HashMap();
        commands.put("createroute", new CreateRouteCommand("/index.html"));
    }
    
    public static Factory getInstance() {
        return INSTANCE;
    }
    
    public AdminContract getDataModel() {
        return admContractBean;
    }
    
    private AdminContract lookupAdminContractBeanRemote() {
        try {
            Context c = new InitialContext();
            return (AdminContract) c.lookup("java:global/YOLO_ferry_contract_v2/contracts.AdminContract");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public Command getCommand(String cmdStr, HttpServletRequest request) {
        if (cmdStr == null) {
            cmdStr = "index";
        } else if (!commands.containsKey(cmdStr)) {
            cmdStr = "index";
        }
        Command cmd = commands.get(cmdStr);
        return cmd;
    }

    @Override
    public Collection<FerryDTO> getAllFerries() throws NoFerryETO 
    {
        return null;
    }

    @Override
    public boolean createFerry(FerryDTO ferry) throws NoTransactionETO 
    {
        return false;
    }

    @Override
    public boolean editFerry(FerryDTO editedFerry, int ferryId) throws NoFerryETO 
    {
        return false;
    }

    @Override
    public boolean createRoute(RouteDTO route) throws NoTransactionETO 
    {
        return admContractBean.createRoute(route);
    }

    @Override
    public Collection<RouteDTO> getAllRoutes() throws NoRouteETO 
    {
        return null;
    }

    @Override
    public Collection<HarborDTO> getAllHarbors() throws NoHarborETO 
    {
        return null;
    }

    @Override
    public Collection<VehicleDTO> getAllVehicles() throws NoVehicleETO 
    {
        return null;
    }

    @Override
    public ScheduleDTO getSchedule(int scheduleId) throws NoScheduleETO 
    {
        return null;
    }

    @Override
    public Collection<DiscountDTO> getAllDiscounts() throws NoDiscountETO 
    {
        return null;
    }

    @Override
    public AllForBookingDTO getAllForBooking() throws NoConnectionETO 
    {
        return null;
    }

    @Override
    public boolean book(BookingDTO booking) throws NoTransactionETO 
    {
        return false;
    }

    @Override
    public boolean createCustomer(CustomerDTO customer) throws NoTransactionETO 
    {
        return false;
    }

    @Override
    public boolean editCustomer(CustomerDTO editedCustomer, int customerId) throws NoCustomerETO 
    {
        return false;
    }

    @Override
    public AllAboutCustomerDTO getAllAboutCustomer(String email) throws NoCustomerETO 
    {
        return null;
    }

    @Override
    public Collection<OrderDTO> getCustomerHistory(int customerId)
    {
        return null;
    }

    @Override
    public void editBooking(BookingDTO editedBooking, int bookingId) throws NoTransactionETO
    {
    }
}
