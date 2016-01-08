package dk.pba2sem.frontendadm;

import DTOs.FerryDTO;
import DTOs.HarborDTO;
import DTOs.RestrictionDTO;
import DTOs.RouteDTO;
import DTOs.ScheduleDTO;
import ETOs.NoTransactionETO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class CreateRouteCommand extends TargetCommand
{
    public CreateRouteCommand(String target) {
        super(target);
    }
    
    @Override
    public String execute(HttpServletRequest request)
    {
        String price = request.getParameter("routePrice");
        String depHarbor = request.getParameter("depHarborName");
        String arrHarbor = request.getParameter("arrHarborName");
        String maxPeople = request.getParameter("ferryMaxPeople");
        String maxCar = request.getParameter("ferryMaxCar");
        String maxLorry = request.getParameter("ferryMaxLorry");
        String maxMachinery = request.getParameter("ferryMaxMachinery");
        String ferryId = request.getParameter("ferryOwnerId");
        String ferryNote = request.getParameter("ferryNote");
        String restrName = request.getParameter("restrictionName");
        String restrDescr = request.getParameter("restrictionDescription");
        String scheduleDepTime = request.getParameter("scheduleDepartTime");
        String scheduleArrTime = request.getParameter("scheduleArrivalTime");
        HarborDTO depHarborDTO = new HarborDTO(1, depHarbor);
        HarborDTO arrHarborDTO = new HarborDTO(2, arrHarbor);
        FerryDTO ferryDTO = new FerryDTO(3, Integer.parseInt(maxPeople), Integer.parseInt(maxCar), Integer.parseInt(maxLorry), Integer.parseInt(maxMachinery), true, false, ferryNote, Integer.parseInt(ferryId));
        RestrictionDTO restriction = new RestrictionDTO(1, restrName, restrDescr);
        ScheduleDTO schedule = new ScheduleDTO(1, Timestamp.valueOf(scheduleDepTime), Timestamp.valueOf(scheduleArrTime));
                
        RouteDTO dto = new RouteDTO(1, depHarborDTO, arrHarborDTO,Double.valueOf(price), ferryDTO, restriction, schedule);
        try {
            Factory.getInstance().createRoute(dto);
        } catch (NoTransactionETO ex) {
            Logger.getLogger(CreateRouteCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.execute(request);
//        Map<String, String> submittedValues = new HashMap();
//        submittedValues.put("routePrice", price);
//        submittedValues.put("depHarborName", depHarbor);
//        submittedValues.put("arrHarborName", arrHarbor);
//        submittedValues.put("ferryMaxPeople", maxPeople);
//        submittedValues.put("ferryMaxCar", maxCar);
//        submittedValues.put("ferryMaxLorry", maxLorry);
//        submittedValues.put("ferryMaxMachinery", maxMachinery);
//        submittedValues.put("ferryOwnerId", ferryId);
//        submittedValues.put("ferryNote", ferryNote);
//        submittedValues.put("restrictionName", restrName);
//        submittedValues.put("restrictionDescription", restrDescr);
//        submittedValues.put("scheduleDepartTime", scheduleDepTime);
//        submittedValues.put("scheduleArrTime", scheduleArrTime);
//        return super.execute(request);
    }
}
