package mase.ericsson.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mase.ericsson.dao.NetworkEventsDAO;
import mase.ericsson.dao.TP_29_graphI_MSI_ReportDAO;
import mase.ericsson.dao.TP_29_modelGraphDAO;

//@Stateless
//@LocalBean
@Path("/graphimsiReport")
public class TP_29_IMSIcallDurationGraphWS {

	@Inject
	private TP_29_graphI_MSI_ReportDAO graphimsiDAO;
	
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{imsinumber}/{fromdate}/{todate}")
    public Response findIMSICount(@PathParam("imsinumber") long imsi, 
            @PathParam("fromdate") String fromdate,
            @PathParam("todate" ) String todate) throws ParseException {
		System.out.println("%%%%%%%%%%%INWA&&&&&&&&");
        
		graphimsiDAO = new TP_29_graphI_MSI_ReportDAO();
        
        Date fdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fromdate);      
        Date tdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(todate);  
      
     // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        
        System.out.println("-------------------");
        
        List<Object[]> baseData = graphimsiDAO.GraphimsiReport(imsi, fdate, tdate);
        
        return Response.status(200).entity(baseData).build();
    }
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/findAllImsiForNetwork")
    public Response findAllImsiForNetwork() {
		graphimsiDAO = new TP_29_graphI_MSI_ReportDAO ();
        
        List<Object[]> imsi = graphimsiDAO.getAllImsiForNetwork();
        
        return Response.status(200).entity(imsi).build();
    }

}
