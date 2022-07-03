package service;

import java.sql.Connection;

import entity.Address;
import entity.Student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import static app.Server.connection;


@Path("/students")
public class StudentService {

    private static final AtomicInteger nextStudentId = new AtomicInteger(1);
    private static final ConcurrentMap<Integer, Student> studentDb = new ConcurrentHashMap<>();


    public StudentService() throws SQLException {

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matriculate(Student s) {

        String query = "Insert into Student values (" + s.getStudentNumber() + ",'" + s.getFirstName() + "','" + s.getLastName() + "',0,'"
                + s.getAddress().getStreet() + "','" + s.getAddress().getCity() + "')";
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            return Response.serverError().entity("Student Body is empty").build();
        }
        return Response.ok().entity(s).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exmatriculate(@PathParam("id") String studentId) {

        String query = "Delete from Student where matrikelNr=" + studentId;

        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Invalid studentId").build();
        }

        return Response.ok().build();


    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") String studentId) {

        String query = "SELECT * from Student where matrikelNr=" + studentId;
        ResultSet rs = null;
        try {
            System.out.println("Bin im try catch");
            rs = connection.createStatement().executeQuery(query);


            if (rs != null) {
                rs.first();
                Student student = new Student(rs.getInt("matrikelNr"), rs.getString("vorname"),
                        rs.getString("nachname"), new Address(rs.getString("strasse"),
                        0, rs.getString("ort")));

                System.out.println("bin drin");
                return Response.ok().entity(student).build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Response.serverError().entity("Invalid studentId").build();

    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudentAccount(@PathParam("id") String studentId, Student newData) {


        String query = "Update Student set vorname='" + newData.getFirstName() + "',nachname='" + newData.getLastName() +
                "',strasse='" + newData.getAddress().getStreet() + "',ort='" + newData.getAddress().getCity() + "'where matrikelNr=" + studentId;
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Invalid studentId or empty body").build();
        }

        return Response.ok().entity(newData).build();


    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {

        String query = "SELECT * from Student";
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery(query);

            List<Student> students = new ArrayList<>();

            while (rs.next()) {

                Student student = new Student(rs.getInt("matrikelNr"), rs.getString("vorname"),
                        rs.getString("nachname"), new Address(rs.getString("strasse"),
                        0, rs.getString("ort")));
                students.add(student);

            }

            return Response.ok().entity(students).build();

        } catch (SQLException e) {
            return Response.serverError().entity("Server error").build();
        }


    }


}
