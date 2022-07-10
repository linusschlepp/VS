package mapper;

import com.hazelcast.mapreduce.*;
import de.othr.vs.xml.Veranstaltung;


public class EventMapper implements Mapper<String, Veranstaltung, String, Veranstaltung> {


    private final String[] keywords;

    public EventMapper(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public void map(String s, Veranstaltung veranstaltung, Context<String, Veranstaltung> context) {


        for (String keyword : keywords) {
            if (veranstaltung.getBeschreibung().toLowerCase().contains(keyword.toLowerCase()) ||
                    veranstaltung.getTitel().toLowerCase().contains(keyword.toLowerCase()))
                context.emit(keyword, veranstaltung);

        }
    }
}
