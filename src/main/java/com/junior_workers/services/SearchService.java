package com.junior_workers.services;

import java.util.List;

import com.junior_workers.bodies.SearchBody;
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
		
		if(searchRequest.getRole().equals("candidate") || searchRequest.getRole().equals("hirer")) {
			List<SearchBody> searchBodies = new QueryDatabase().searchAll(searchRequest.getRole(), searchRequest.getKey());
			SearchResponse searchResponse = new SearchResponse();
			searchResponse.setResults(searchBodies);
			
			return Response.status(200).entity(searchResponse).build();
		}

		return Response.status(400).build();
	}
	
}