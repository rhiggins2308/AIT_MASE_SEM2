package mase.ericsson.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mase.ericsson.dao.ApachePOIExcelRead;
import mase.ericsson.dao.NetworkEventsDAO;

@Path("/database")
public class PopulateDatabaseWS {
	
	@Inject
//	private NetworkEventsDAO populate;
	private ApachePOIExcelRead makeList;
	
	
	
	 @GET
	 @Produces({ MediaType.APPLICATION_JSON }) 
	 @Path("/populate")
	 public Response saveUserpopulateDB() throws IOException{
		 //		 File file= new File("/mase-ericsson-project/uploadData/AIT Group Project - Sample Dataset.xls");
		 
		 // Robbie Mac filepath
	 String filePath = "/Users/robbie/eclipse-workspace/AIT_MASE_SEM2/team-possible/mase-ericsson-project/uploadData/AIT Group Project - Sample Dataset.xls";
		 //String filePath2 = "/Users/robbie/eclipse-workspace/AIT_MASE_SEM2/team-possible/mase-ericsson-project/uploadData/AIT Group Project 2022- Dataset 3B.xls";
		 
		 // Robbies Windows filepath
		// String filePath = "C:\\Users\\rhigg\\eclipse-workspace\\AIT_MASE_SEM2\\team-possible\\mase-ericsson-project\\uploadData\\AIT Group Project 2022- Dataset 3A.xls";
//		 String filePath = "C:\\Users\\rhigg\\eclipse-workspace\\AIT_MASE_SEM2\\team-possible\\mase-ericsson-project\\uploadData\\AIT Group Project 2022- Dataset 3B.xls";

		 // Davids windows filepath
	//	 String filePath ="C:\\Users\\David\\git\\team-possible\\mase-ericsson-project\\uploadData\\AIT Group Project - Sample Dataset.xls";

//		 String filePath ="C:\\Users\\David\\git\\team-possible\\mase-ericsson-project\\uploadData\\AIT Group Project 2022- Dataset 3A.xls";
//		 String filePath ="C:\\Users\\David\\git\\team-possible\\mase-ericsson-project\\uploadData\\AIT Group Project 2022- Dataset 3B.xls";

//		 File file= new File("/mase-ericsson-project/uploadData/AIT Group Project - Sample Dataset.xls");
		 
		// String filePath="/Users/swarnimayadav/git/team-possible/mase-ericsson-project/uploadData/AIT Group Project - Sample Dataset.xls";
			//DEPLOYMENT PATH
		 //String filePath="resources/uploadData/AIT Group Project 2022- Dataset 3A.xls";
		 Boolean listMade =makeList.readExcel(filePath);
		 if(listMade) {
//		 populate.addData(dbList);
			 System.out.println("successful list made and upload to DB");
			 return Response.status(201).entity(listMade).build(); }
		 else {
			 return Response.status(409).build(); }
		 }
	 }
	



