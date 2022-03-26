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

import mase.ericsson.dao.TP_27_Top_10_IMSI_DAO;
import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.EventCause;

@Path("/networkevents")
//@Stateless
//@LocalBean
public class TP_27_Top_10_IMSI_WS {

    @Inject
    private TP_27_Top_10_IMSI_DAO tP27_TOP_10_IMSI_DAO;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/top10_imsiCountforNtwEng/{fromdate}/{todate}")
    public Response TP_27_findTop_10_IMSICount(
    		 @PathParam("fromdate") String fromdate,
    		 @PathParam("todate") String todate) throws ParseException {
    	
    	tP27_TOP_10_IMSI_DAO = new TP_27_Top_10_IMSI_DAO();
    		 

        Date fdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fromdate);
        Date tdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(todate);

        

        List<Object[]> baseData = tP27_TOP_10_IMSI_DAO.TP_27_Top_10_imsiCountforNtwEngineer(fdate, tdate);

        return Response.status(200).entity(baseData).build();
    }

}
