package service;


import com.hazelcast.map.IMap;
import de.othr.vs.xml.Student;



import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.concurrent.TimeUnit;
import static app.Server.instance;



@Path("/students")
public class StudentService {

    static IMap<Integer, Student> students = instance.getMap("students");
    static int matrNumber = 0;


    public StudentService() {

    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response matriculate(Student s) {

        s.setMatrikelNr(matrNumber);
        students.put(matrNumber, s, 5, TimeUnit.MINUTES);

        // Gets incremented after every put
        matrNumber++;
        return Response.ok().entity(s).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {

        return Response.ok().entity(students.values()).build();

    }

}


