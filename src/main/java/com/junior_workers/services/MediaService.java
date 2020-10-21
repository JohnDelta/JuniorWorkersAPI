package com.junior_workers.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.junior_workers.authentication.JWTAuthenticate;
import com.junior_workers.database_controllers.UserDatabase;
import com.junior_workers.models.User;

import jakarta.activation.MimetypesFileTypeMap;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/media")
public class MediaService {
	
	private final String UPLOADS_PATH = "C:\\Users\\john\\Documents\\junior_workers_uploads\\";
	
	@Context ServletContext servletContext;
	
	@GET
	@Path("images/get/{image_path}")
	@Produces("image/png")
	public Response getMedia(@PathParam("image_path") String imagePath) throws Exception {
		
		String requestedPath = UPLOADS_PATH+imagePath;
        
		try {
			File f = new File(requestedPath);
			if (!f.exists()) {
				return Response.status(404).build();
			}
			String mt = new MimetypesFileTypeMap().getContentType(f);
			return Response.ok(f, mt).build();
	
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		return Response.status(404).build();
	}
	
	@POST
	@Path("images/update")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response updateMedia(
			//@FormDataParam("jwt") String jwt,
			@FormDataParam("file") FormDataContentDisposition fileMetaData,
			@FormDataParam("file") InputStream inputStream
	) throws Exception {
		
		System.out.println(fileMetaData.getFileName()+" "+fileMetaData.getType());
		
//		try
//	    {
//	        int read = 0;
//	        byte[] bytes = new byte[1024];
//	 
//	        OutputStream out = new FileOutputStream(new File(UPLOADS_PATH + imagePath));
//	        while ((read = inputStream.read(bytes)) != -1) 
//	        {
//	            out.write(bytes, 0, read);
//	        }
//	        
//	        out.flush();
//	        out.close();   
//	    } catch (IOException e) {
//	    	e.printStackTrace();
//	    	return Response.status(404).build();
//	    }
		
		
		
//		String jwtUnserializedFromDataBody = jwt.split("\n")[3];
//		
//		String email = JWTAuthenticate.getUsername(jwtUnserializedFromDataBody);
//		if(email == null) {
//			return Response.status(401).build();
//		}
//		
//		User user = new UserDatabase().find(email);
//		int rnd = new Random().nextInt(99999-1000) + 1000;
//		String imagePath = "user_" + user.getUserId() + "_" + rnd;
//		
//		try
//	    {
//	        int read = 0;
//	        byte[] bytes = new byte[1024];
//	 
//	        OutputStream out = new FileOutputStream(new File(UPLOADS_PATH + imagePath));
//	        while ((read = inputStream.read(bytes)) != -1) 
//	        {
//	            out.write(bytes, 0, read);
//	        }
//	        
//	        out.flush();
//	        out.close();
//	        
//	        user.setImagePath(imagePath);
//			new UserDatabase().update(user);
//	    
//	    } catch (IOException e) {
//	    	e.printStackTrace();
//	    	return Response.status(404).build();
//	    }
		
	    return Response.ok().build();
	}
}













