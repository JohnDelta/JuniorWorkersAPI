package com.junior_workers.services;

import java.util.List;

import com.junior_workers.authentication.JWTAuthenticate;
import com.junior_workers.authentication.JWTUtils;
import com.junior_workers.database_controllers.EducationDatabase;
import com.junior_workers.database_controllers.ExperienceDatabase;
import com.junior_workers.database_controllers.JobPostDatabase;
import com.junior_workers.database_controllers.LanguageDatabase;
import com.junior_workers.database_controllers.SkillDatabase;
import com.junior_workers.database_controllers.UserDatabase;
import com.junior_workers.models.Education;
import com.junior_workers.models.Experience;
import com.junior_workers.models.JobPost;
import com.junior_workers.models.Language;
import com.junior_workers.models.Skill;
import com.junior_workers.models.User;
import com.junior_workers.bodies.*;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserService {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UserLoginRequest userLoginRequest) throws Exception {
	
		User requestUser = new User();
		requestUser.setEmail(userLoginRequest.getEmail());
		requestUser.setPassword(userLoginRequest.getPassword());
		
		User user = new UserDatabase().matchCredentials(requestUser);
		
		if(user != null) {
			String jwt = JWTUtils.createJWT(
					String.valueOf(user.getUserId()),
					user.getEmail(),
					user.getRole(),
					"JUNIO_WORKERS_API",
					"LOGIN_REQUEST",
					3600*1000);
			
			UserLoginResponse userLoginResponse = new UserLoginResponse();
			userLoginResponse.setJwt(jwt);
			userLoginResponse.setEmail(user.getEmail());
			userLoginResponse.setRole(user.getRole());
			
			return Response.status(200).entity(userLoginResponse).build();
		}
		return Response.status(400).build();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(UserAddRequest userAddRequest) throws Exception {
		
		User user = new User();
		user.setEmail(userAddRequest.getEmail());
		user.setFirstname(userAddRequest.getFirstname());
		user.setLastname(userAddRequest.getLastname());
		user.setRole(userAddRequest.getRole());
		user.setPassword(userAddRequest.getPassword());
		
		if(new UserDatabase().create(user)) {
			return Response.status(200).build();
		}
		
		return Response.status(400).build();
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(UpdateCandidateRequest updateCandidateRequest) throws Exception {
		
		String email = JWTAuthenticate.getUsername(updateCandidateRequest.getJwt());
		if(email == null) {
			return Response.status(401).build();
		}
		
		String mode = JWTAuthenticate.getMode(updateCandidateRequest.getJwt());
		
		if(mode.equals("candidate")) {
			
			// make sure the account you update is the jwt owners
			updateCandidateRequest.getUser().setEmail(email);
			
			new UserDatabase().update(updateCandidateRequest.getUser());
			
			new SkillDatabase().deleteByUser(updateCandidateRequest.getUser());
			new SkillDatabase().addToUser(updateCandidateRequest.getUser(),
					updateCandidateRequest.getSkills());
			
			new LanguageDatabase().deleteByUser(updateCandidateRequest.getUser());
			new LanguageDatabase().addToUser(updateCandidateRequest.getUser(),
					updateCandidateRequest.getLanguages());
			
			new EducationDatabase().deleteByUser(updateCandidateRequest.getUser());
			new EducationDatabase().addToUser(updateCandidateRequest.getUser(),
					updateCandidateRequest.getAllEducation());
			
			new ExperienceDatabase().deleteByUser(updateCandidateRequest.getUser());
			new ExperienceDatabase().addToUser(updateCandidateRequest.getUser(),
					updateCandidateRequest.getExperiences());
			
			return Response.status(200).build();
			
		} else if(mode.equals("hirer")) {
			
		}
		
		return Response.status(401).build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(JwtRequest jwtRequest) throws Exception {
		
		String email = JWTAuthenticate.getUsername(jwtRequest.getJwt());
		if(email == null) {
			return Response.status(401).build();
		}
		
		String mode = JWTAuthenticate.getMode(jwtRequest.getJwt());
		User user = new UserDatabase().find(email);
		
		if(mode.equals("candidate")) {
		
			new SkillDatabase().deleteByUser(user);
			new LanguageDatabase().deleteByUser(user);
			new EducationDatabase().deleteByUser(user);
			new ExperienceDatabase().deleteByUser(user);
			new UserDatabase().delete(user); // must be last one to delete

			return Response.status(200).build();

		} else if(mode.equals("hirer")) {
			
		}
		
		return Response.status(401).build();
	}
	
	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(GetUserRequest getUserRequest) throws Exception {
		
		String email = JWTAuthenticate.getUsername(getUserRequest.getJwt());
		if(email == null) {
			return Response.status(401).build();
		}
		
		User user = new UserDatabase().find(getUserRequest.getEmail());
		
		if(user.getRole().equals("candidate")) {
			
			List<Skill> skills = new SkillDatabase().getByUser(user);
			List<Language> languages = new LanguageDatabase().getLanguageAndLevelByUser(user);
			List<Education> allEducation = new EducationDatabase().getEducationAndLevelByUser(user);
			List<Experience> experiences = new ExperienceDatabase().getByUser(user);
			
			CandidateResponse candidateResponse = new CandidateResponse();
			candidateResponse.setSkills(skills);
			candidateResponse.setUser(user);
			candidateResponse.setLanguages(languages);
			candidateResponse.setAllEducation(allEducation);
			candidateResponse.setExperiences(experiences);
			
			return Response.status(200).entity(candidateResponse).build();
		
		} else if(user.getRole().equals("hirer")) {
			
			List<JobPost> jobPosts = new JobPostDatabase().getByUser(user);
			
			HirerResponse hirerResponse = new HirerResponse();
			hirerResponse.setUser(user);
			hirerResponse.setJobPosts(jobPosts);
			
			return Response.status(200).entity(hirerResponse).build();
		}
		
		return Response.status(401).build();
	}
	
}