package com.junior_workers.services;

import com.junior_workers.bodies.ModelsResponse;
import com.junior_workers.database_controllers.*;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/model")
public class ModelService {

	@GET
	@Path("/get/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getModels() throws Exception {
	
		ModelsResponse modelsResponse = new ModelsResponse();
		modelsResponse.setSkills(new SkillDatabase().getAll());
		modelsResponse.setAllEducation(new EducationDatabase().getEducationAll());
		modelsResponse.setEducationLevels(new EducationDatabase().getEducationLevelAll());
		modelsResponse.setLanguages(new LanguageDatabase().getLanguageAll());
		modelsResponse.setLanguageLevels(new LanguageDatabase().getLanguageLevelAll());
		modelsResponse.setProfessions(new ProfessionDatabase().getAll());

		return Response.status(200).entity(modelsResponse).build();
	}
	
}
