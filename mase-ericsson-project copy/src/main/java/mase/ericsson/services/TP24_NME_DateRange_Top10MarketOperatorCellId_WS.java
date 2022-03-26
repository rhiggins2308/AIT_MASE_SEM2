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

import mase.ericsson.dao.NetworkEventsDAO;
import mase.ericsson.dao.TP24_NME_DateRange_Top10MarketOperatorCellId_DAO;

@Path("/nmetop10")
//@Stateless
//@LocalBean
public class TP24_NME_DateRange_Top10MarketOperatorCellId_WS {

	@Inject
	private TP24_NME_DateRange_Top10MarketOperatorCellId_DAO dao;
	
	@GET
	@Path("/{fromDate}/{toDate}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getListOfIMSIDateRange(
									@PathParam("fromDate") String fromDate,
									@PathParam("toDate") String toDate) throws ParseException {

		//rest/nmetop10/2020-02-21T09:00/2020-05-30T09:00
		dao = new TP24_NME_DateRange_Top10MarketOperatorCellId_DAO();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		List<Object[]> obj= dao.getTop10MarketOperatorCellId(formatter.parse(fromDate), formatter.parse(toDate));
		return Response.status(200).entity(obj).build();
	}
}