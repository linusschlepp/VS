package service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.map.IMap;
import de.othr.vs.xml.Veranstaltung;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.*;

import static app.Server.instance;


@Path("/events")
public class EventService {

    static IMap<String, Veranstaltung> events = instance.getMap("events");

    public EventService() {

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(Veranstaltung veranstaltung) {


        events.put(veranstaltung.getId(), veranstaltung);


        return Response.ok().entity(veranstaltung).build();

    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") String eventId) {


        if (events.containsKey(eventId))
            return Response.ok().entity(events.get(eventId)).build();

        return Response.serverError().entity("Invalid eventId").build();

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEvent(@QueryParam("tipps") String tipps) {

        String[] strings = tipps.split(" ");
        List<Veranstaltung> eventList = new ArrayList<>();


        for (String event : events.keySet()) {
            boolean containsAll = true;
            for (String s : strings) {
                if (!(events.get(event).getTitel().contains(s) || events.get(event).getBeschreibung().contains(s)))
                    containsAll = false;
            }
            if (containsAll)
                eventList.add(events.get(event));

        }

        return Response.ok().entity(eventList).build();
    }
}
