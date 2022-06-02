package service;

import entity.Student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Path("/students")
public class StudentService {

    private static final AtomicInteger nextStudentId = new AtomicInteger(1);
    private static final ConcurrentMap<Integer, Student> studentDb = new ConcurrentHashMap<>();  // kann als interne Datenbank verwendet werden

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matriculate(Student s) {

        if (s != null)
            return Response.ok().entity(studentDb.put(s.getStudentNumber(), s)).build();


        return Response.serverError().entity("Student Body is empty").build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exmatriculate(@PathParam("id") String studentId) {


        if (studentDb.containsKey(Integer.valueOf(studentId)))
            return Response.ok().entity(studentDb.remove(Integer.valueOf(studentId))).build();


        return Response.serverError().entity("Invalid studentId").build();

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") String studentId) {


        if (studentDb.containsKey(Integer.valueOf(studentId)))
            return Response.ok().entity(studentDb.get(Integer.valueOf(studentId))).build();

        return Response.serverError().entity("Invalid studentId").build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudentAccount(@PathParam("id") String studentId, Student newData) {

        if (studentDb.containsKey(Integer.valueOf(studentId)) && newData != null) {
            studentDb.remove(Integer.valueOf(studentId));
            return Response.ok().entity(studentDb.put(Integer.valueOf(studentId), newData)).build();
        }


        return Response.serverError().entity("Invalid studentId or empty body").build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {

        return Response.ok().entity(studentDb.values()).build();


    }

    @GET
    @Path("/by")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsByRange(@QueryParam("from") int fromStudentId, @QueryParam("to") int toStudentId) {

        if (fromStudentId < toStudentId && fromStudentId > 0)
            return Response.ok().entity(studentDb.values().stream().filter(s -> s.getStudentNumber() >= fromStudentId && s.getStudentNumber() <= toStudentId).
                    collect(Collectors.toList())).build();


        return Response.serverError().entity("Invalid parameters").build();

    }
}
