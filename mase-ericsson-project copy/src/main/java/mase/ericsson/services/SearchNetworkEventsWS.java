package mase.ericsson.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mase.ericsson.dao.NetworkEventsDAO;
import mase.ericsson.dao.TP21_IMSI_Failures_DAO;
import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;

@Path("/networkevents")
//@Stateless
//@LocalBean
public class SearchNetworkEventsWS {

	@Inject
	private NetworkEventsDAO networkEventsDAO;
	
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{imsi}")
	public Response findAllErrorsByIMSI(@PathParam("imsi") String imsi) {
		
	    networkEventsDAO = new NetworkEventsDAO ();
	    List<Object[]> baseData=networkEventsDAO.getNetworkEventsByImsi(Long.valueOf(imsi));
		
		
		return Response.status(200).entity(baseData).build();
	}
	
	@GET
	@Path("/queryimsi/{fromdate}/{todate}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListOfIMSIDateRange(
	@PathParam("fromdate") String fromdate,
	@PathParam("todate") String todate) throws ParseException {

	//2020-01-11 17:15:00
		networkEventsDAO = new NetworkEventsDAO ();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");


	List<Object[]> obj= networkEventsDAO.getListOfIMSIForDateRange(formatter.parse(fromdate), formatter.parse(todate));

	//System.out.println(obj.size());
	return Response.status(200).entity(obj).build();
	}

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/imsireport/{imsinumber}/{fromdate}/{todate}")
    public Response findIMSICount(@PathParam("imsinumber") long imsi, 
            @PathParam("fromdate") String fromdate,
            @PathParam("todate" ) String todate) throws ParseException {
        
      networkEventsDAO = new NetworkEventsDAO();
        
        Date fdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fromdate);      
        Date tdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(todate);  
      
     // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        
        List<Object[]> baseData = networkEventsDAO.imsiReport(imsi, fdate, tdate);
        
        return Response.status(200).entity(baseData).build();
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/findAllImsiForNetwork")
    public Response findAllImsiForNetwork() {
        networkEventsDAO = new NetworkEventsDAO();
        
        List<Object[]> imsi = networkEventsDAO.getAllImsiForNetwork();
        
        return Response.status(200).entity(imsi).build();
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/UEreport/{uenumber}/{fromdate}/{todate}")
    public Response findUECount(@PathParam("uenumber") int ue, 
            @PathParam("fromdate") String fromdate,
            @PathParam("todate" ) String todate) throws ParseException {
    	networkEventsDAO = new NetworkEventsDAO();
        Date fdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fromdate);      
        Date tdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(todate);  
        List<BaseData> baseData = networkEventsDAO.ueReport(ue, fdate, tdate);
        return Response.status(200).entity(baseData).build();
    }
//    @GET
//    @Produces({ MediaType.APPLICATION_JSON })
//    @Path("/TACreport/{ueType}/{fromdate}/{todate}")
//    
//    public Response findFailureCountByTAC(@PathParam("ueType") long ueType, 
//            @PathParam("fromdate") String fromdate,
//            @PathParam("todate" ) String todate) throws ParseException {
//        
//      networkEventsDAO = new NetworkEventsDAO();
//      System.out.println("Initila values__________");
//      System.out.println(fromdate);
//      System.out.println(todate);
//        
//      Date fdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fromdate);
//      System.out.println(fromdate);
//      Date tdate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(todate);    
//      System.out.println(todate);
//     // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
//        
//        List<BaseData> baseData = networkEventsDAO.getFailureDataByModelAndTimePeriod(ueType, fdate, tdate);
//        System.out.println(baseData);
//        return Response.status(200).entity(baseData).build();
//    }
//	

    @GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/allimsi/")
	public Response findAllImsi() {
		
	    networkEventsDAO = new NetworkEventsDAO ();
	    List<Object[]> baseData=networkEventsDAO.getAllImsi();
		
		
		return Response.status(200).entity(baseData).build();
	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/allfailureclass/")
	public Response findAllFailureClass() {
		
	    networkEventsDAO = new NetworkEventsDAO ();
	    List<Object[]> baseData=networkEventsDAO.getAllFailureClass();
		
		
		return Response.status(200).entity(baseData).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/causecode/{imsi}")
	public Response findCauseCodeByIMSI(@PathParam("imsi") String imsi) {
		
	    networkEventsDAO = new NetworkEventsDAO ();
	    List<Object[]> baseData=networkEventsDAO.getCauseCodeByImsi(Long.valueOf(imsi));
		
		
		return Response.status(200).entity(baseData).build();
	}


    
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/allbyimsi/")
    public Response findAllByImsi() {

    networkEventsDAO = new NetworkEventsDAO ();
    List<Object[]> baseData=networkEventsDAO.getAllByImsi();


    return Response.status(200).entity(baseData).build();
    }    
    
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
	@Path("/failureclass/{failureClass}")
	public Response findImsiByFailureClass(@PathParam("failureClass") int failureClass) {
		
	    networkEventsDAO = new NetworkEventsDAO ();
	    List<Object[]> baseData=networkEventsDAO.getImsiByFailureClass(failureClass);
		
		
		return Response.status(200).entity(baseData).build();
	}
  
    
}