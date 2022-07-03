package service;


import entity.ExamPerformance;
import jakarta.ws.rs.Path;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static app.Server.connection;

@Path("/exams")
public class ExamService {



    public ExamService(){
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertExam(@QueryParam("eId") String examId, @QueryParam("mId") int studentId,
                               @QueryParam("attempt") int attempt, @QueryParam("grade") float grade) {

        // Insert into Pruefungsleistung
        String queryExamPerformance = "Insert into Pruefungsleistung (pruefungId, matrikelNr, versuch, note) values "
                + "('" + examId + "'" + "," + studentId + "," + attempt + "," + grade + ")";

        try {
            connection.createStatement().executeUpdate(queryExamPerformance);
            System.out.println(queryExamPerformance);

            if (grade != 5.0) {
                //Get ects of exam
                String queryExam = "Select ects from Pruefung where pruefungId='" + examId + "'";
                ResultSet rs = connection.createStatement().executeQuery(queryExam);
                rs.first();
                String queryStudent = "Update Student set ects=ects+" + rs.getInt("ects") + " where matrikelNr=" + studentId;
                System.out.println(queryStudent);
                connection.createStatement().executeUpdate(queryStudent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("Error occurred").build();
        }

        return Response.ok().build();

    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExamById(@PathParam("id") String examId) {

        String query = "SELECT * from Pruefungsleistung where pruefungId="+"'"+examId+"'";
        ResultSet rs;
        try {
            rs = connection.createStatement().executeQuery(query);


            List<ExamPerformance> examPerformances = new ArrayList<>();

            if (rs != null) {

                rs.first();
                while (rs.next()) {

                    ExamPerformance examPerfomance = new ExamPerformance(rs.getInt("id"), rs.getString("pruefungId"),
                            rs.getInt("matrikelNr"), rs.getInt("versuch"), rs.getFloat("note"));
                    examPerformances.add(examPerfomance);

                }

                return Response.ok().entity(examPerformances).build();


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Response.serverError().entity("Invalid examId").build();

    }
}
