package main.export;

import j2html.tags.Tag;
import main.entities.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static j2html.TagCreator.*;

/**
 * Class to export events to a HTML file
 */
public class HTMLExport {

    public HTMLExport() {

    }

    /**
     * Exports list of events to a HTML file
     *
     * @param events list of Event entities
     */
    public void exportEvents(List<Event> events) {

        FileWriter fileWriter = this.getFileWriter();

        String HTML = document(html(
                head(
                        link().withRel("stylesheet").withHref("./src/main/export/index.css")
                ),
                body(
                        div(h2("Event List"),
                                each(events, event -> eventTag(event))).withClass("container")

                )
        ));

        try {
            fileWriter.write(HTML);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            return;
        }


    }

    /**
     * Obtains a file writer
     *
     * @return a FileWriter
     */
    private FileWriter getFileWriter() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("./event-list.html", false);
        } catch (IOException e) {
            return null;
        }
        return fileWriter;

    }

    /**
     * Create an event entry
     *
     * @param event Event entity
     * @return event component
     */
    private Tag eventTag(Event event) {
        return
                div(
                        h3(event.getTitle()),
                        div("Type: " + event.getType()).withClass("event-row"),
                        div("Time: " + event.getTime().toString()).withClass("event-row"),
                        div("Room: " + event.getRoomID()).withClass("event-row"),
                        div("Duration: " + event.getDuration() + " minutes").withClass("event-row")

                ).withClass("event-entry");

    }


}
