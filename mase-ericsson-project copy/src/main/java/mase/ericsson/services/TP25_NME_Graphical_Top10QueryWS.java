package mase.ericsson.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mase.ericsson.dao.TP25_NME_Graphical_Top10QueryDAO;

@Path("/nmegraphicaltop10")
//@Stateless
//@LocalBean
public class TP25_NME_Graphical_Top10QueryWS {

	@Inject
	private TP25_NME_Graphical_Top10QueryDAO dao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListOfTop10Failures() throws ParseException {

		dao = new TP25_NME_Graphical_Top10QueryDAO ();

		List<Object[]> obj= dao.getTop10MarketOperatorCellId();
		return Response.status(200).entity(obj).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/details/{country}/{operator}/{cellId}")
    public Response findIMSICount(@PathParam("country") String country,
            @PathParam("operator") String operator,
            @PathParam("cellId") int cellId) throws ParseException {

		dao = new TP25_NME_Graphical_Top10QueryDAO ();

		List<Object[]> obj= dao.getTop10MarketOperatorCellIdDetails(country, operator, cellId);
		return Response.status(200).entity(obj).build();
	}

	
}
