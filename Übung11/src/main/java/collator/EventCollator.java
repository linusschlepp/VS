package collator;

import com.hazelcast.mapreduce.Collator;
import de.othr.vs.xml.Veranstaltung;

import java.util.*;

public class EventCollator implements Collator<Map.Entry<String, List<Veranstaltung>>, List<Veranstaltung>> {


    @Override
    public List<Veranstaltung> collate(Iterable<Map.Entry<String, List<Veranstaltung>>> iterable) {

        List<Veranstaltung> tempList = new ArrayList<>();

        iterable.forEach(iter -> {
            iter.getValue().forEach(event -> {
                if(!tempList.contains(event))
                    tempList.add(event);
            });
                });



        return tempList;

    }
}
