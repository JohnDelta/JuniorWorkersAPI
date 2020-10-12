package com.junior_workers.services;

import com.junior_workers.bodies.SearchRequest;
import com.junior_workers.bodies.SearchResponse;
import com.junior_workers.database_controllers.QueryDatabase;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/search")
public class SearchService {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAll(SearchRequest searchRequest) throws Exception {
		
		SearchResponse searchResponse = new QueryDatabase().searchAll(searchRequest.getRole(), searchRequest.getKey());

		return Response.status(200).entity(searchResponse).build();
	}
	
}