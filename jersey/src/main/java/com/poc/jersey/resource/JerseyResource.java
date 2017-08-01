package com.poc.jersey.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.poc.jersey.dao.DataRepo;
import com.poc.jersey.vo.UserVo;

@Path("/poc")
public class JerseyResource {

	private Response errorResponse = Response.status(500).entity("It Seems Server Problem, Please Try Again later!!!")
			.type(MediaType.TEXT_PLAIN_TYPE).build();

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello Jersey";
	}

	/**
	 * To get all users.
	 */
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserVo> toGetAll() {
		return DataRepo.getAll();
	}

	/**
	 * To Save user.
	 */
	@PUT
	@Path("/saveUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean saveUser(UserVo userVo) {
		try {
			DataRepo.insert(userVo);
		} catch (Exception ex) {
			throw new WebApplicationException(errorResponse);
		}
		return true;
	}

	/**
	 * To Update user.
	 */
	@POST
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateUser(UserVo userVo) {
		try {
			DataRepo.update(userVo);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new WebApplicationException(errorResponse);
		}
		return true;
	}

	/**
	 * To Delete user.
	 */
	@DELETE
	@Path("/deleteUser/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean updateUser(@PathParam("id") int userId) {
		try {
			DataRepo.delete(userId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new WebApplicationException(errorResponse);
		}
		return true;
	}

	/**
	 * To Download File.
	 */
	@GET
	@Path("/downloadFile")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(@Context HttpServletRequest httpRequest) {
		StreamingOutput fileStream = new StreamingOutput() {
			@Override
			public void write(java.io.OutputStream output) throws IOException, WebApplicationException {
				try {
					@SuppressWarnings("deprecation")
					String strPath = httpRequest.getRealPath("/WEB-INF/files/download.txt");
					java.nio.file.Path path = Paths.get(strPath);
					byte[] data = Files.readAllBytes(path);
					output.write(data);
					output.flush();
				} catch (Exception e) {
					throw new WebApplicationException("File Not Found !!");
				}
			}
		};
		return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename = jerseyFileDownload.pdf").build();
	}

	/**
	 * To Upload File.
	 */
	@POST
	@Path("/uploadFile")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response uploadPdfFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception {
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream out = new FileOutputStream(new File("c:/temp/upload.txt"));
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error while uploading file. Please try again !!");
		}
		return Response.ok("Data uploaded successfully !!").build();
	}

}
