package service;

import collator.EventCollator;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MultiMap;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import de.othr.vs.xml.Veranstaltung;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mapper.EventMapper;
import reducer.EventReducerFactory;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
    public Response addEvent(@QueryParam("tipps") String tipps) throws ExecutionException, InterruptedException {

        String[] keywords = tipps.split(" ");

        JobTracker jobTracker = instance.getJobTracker("tippsJobTracker");
        Job<String, Veranstaltung> job = jobTracker.newJob(KeyValueSource.fromMap(events));

        ICompletableFuture<List<Veranstaltung>> future = job.mapper(new EventMapper(keywords))
                .reducer(new EventReducerFactory())
                .submit(new EventCollator());

        return Response.ok().entity(future.get()).build();
    }
}
