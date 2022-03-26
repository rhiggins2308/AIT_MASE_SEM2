package mase.ericsson.services;

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

import mase.ericsson.dao.TP_29_modelGraphDAO;
import mase.ericsson.entities.BaseData;
import mase.ericsson.entities.UserEquipment;

//@Stateless
//@LocalBean
@Path("/graphmodel")
public class TP_29_modelGraphWS {

	@Inject
	private TP_29_modelGraphDAO newModelDAO;
	


	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAllModel() {
		newModelDAO= new TP_29_modelGraphDAO();
		
		List<Object[]> ue = newModelDAO.getAllModel();
		
		return Response.status(200).entity(ue).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{model}")
	public Response findraphAllErrorsByIMSI(@PathParam("model") String model) {
		newModelDAO= new TP_29_modelGraphDAO();

		List<Object[]> baseData=newModelDAO.getGraphFailureDataByModel(model);
		
		return Response.status(200).entity(baseData).build();
	}
	
	
	

}