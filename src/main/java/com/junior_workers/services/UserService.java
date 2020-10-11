package com.junior_workers.services;

import java.util.List;

import com.junior_workers.authentication.JWTAuthenticate;
import com.junior_workers.authentication.JWTUtils;
import com.junior_workers.database_controllers.SkillDatabase;
import com.junior_workers.database_controllers.UserDatabase;
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
			return Response.status(200).entity(jwt).build();
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
	public Response update(UserUpdateRequest userUpdateRequest) throws Exception {
		
		String email = JWTAuthenticate.authenticate(userUpdateRequest.getJwt());
		
		if(email != null) {
			User user = new User();
			user.setEmail(email);
			user.setFirstname(userUpdateRequest.getFirstname());
			user.setLastname(userUpdateRequest.getLastname());
			user.setTitle(userUpdateRequest.getTitle());
			user.setBio(userUpdateRequest.getBio());
			user.setImagePath(userUpdateRequest.getImagePath());
			user.setVideoPath(userUpdateRequest.getVideoPath());
			user.setResumePath(userUpdateRequest.getResumePath());
			user.setAvailability(userUpdateRequest.getAvailability());
			
			if(new UserDatabase().update(user)) {
				return Response.status(200).build();
			}
			
			return Response.status(400).build();
		}
		
		return Response.status(401).build();
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(JwtRequest jwtRequest) throws Exception {
		
		String email = JWTAuthenticate.authenticate(jwtRequest.getJwt());
		User user = new UserDatabase().find(email);
		
		if(user != null) {
			
			if(new UserDatabase().delete(user)) {
				return Response.status(200).build();
			}
			
			return Response.status(400).build();
		}
		
		return Response.status(401).build();
	}
	
	@POST
	@Path("/get-owner")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(JwtRequest jwtRequest) throws Exception {
		
		String email = JWTAuthenticate.authenticate(jwtRequest.getJwt());
		User user = new UserDatabase().find(email);
		
		if(user != null) {
			List<Skill> skills = new SkillDatabase().getByUser(user);
			
			CandidateResponse candidateResponse = new CandidateResponse();
			candidateResponse.setSkills(skills);
			candidateResponse.setUser(user);
			
			return Response.status(200).entity(candidateResponse).build();
		}
		
		return Response.status(401).build();
	}
	
}