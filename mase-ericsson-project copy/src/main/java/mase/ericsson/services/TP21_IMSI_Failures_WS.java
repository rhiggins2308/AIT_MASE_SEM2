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
public class TP21_IMSI_Failures_WS {

    @Inject
    private TP21_IMSI_Failures_DAO tP21_IMSI_Failures_DAO;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/imsiCountForServiceRep/{imsinumber}/{fromdate}/{todate}")
    public Response findIMSICount(@PathParam("imsinumber") long imsi,
            @PathParam("fromdate") String fromdate,
            @PathParam("todate") String todate) throws ParseException {

        tP21_IMSI_Failures_DAO = new TP21_IMSI_Failures_DAO();

        Date fdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(fromdate);
        Date tdate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(todate);


        List<Object[]> baseData = tP21_IMSI_Failures_DAO.imsiCountForServiceRep(imsi, fdate, tdate);

        return Response.status(200).entity(baseData).build();
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/findAllImsi")
    public Response findAllImsi() {
        tP21_IMSI_Failures_DAO = new TP21_IMSI_Failures_DAO();
        
        List<Object[]> imsi = tP21_IMSI_Failures_DAO.getAllImsi ();
        
        return Response.status(200).entity(imsi).build();
    }

}
