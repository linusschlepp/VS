package reducer;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;
import de.othr.vs.xml.Veranstaltung;
import java.util.*;

public class EventReducerFactory implements ReducerFactory<String, Veranstaltung, List<Veranstaltung>> {
    @Override
    public Reducer<Veranstaltung, List<Veranstaltung>> newReducer(String s) {
        return new myReducer(s);
    }

    private static class myReducer extends Reducer<Veranstaltung, List<Veranstaltung>> {

        private final String key;
        public myReducer(String key) {
            this.key = key;
        }

        List<Veranstaltung> eventList = new ArrayList<>();

        @Override
        public void reduce(Veranstaltung veranstaltung) {

            if(!eventList.contains(veranstaltung)){
                eventList.add(veranstaltung);
            }

        }

        @Override
        public List<Veranstaltung> finalizeReduce() {
            return eventList;
        }
    }
}
