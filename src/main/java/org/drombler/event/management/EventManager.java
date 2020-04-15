package org.drombler.event.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.drombler.event.core.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Florian
 */
public class EventManager {

    private static final Logger LOG = LoggerFactory.getLogger(EventManager.class);

//    private final Map<LocalDate, SortedSet<Event>> events = new HashMap<>();
//    private final Comparator<EventDuration> eventDurationComparator = new ImportEventDurationComparator();
//    private final Comparator<Event> eventComparator = Comparator.comparing(Event::getDuration, eventDurationComparator)
//            .thenComparing(Event::getName);
    private final List<Event> events = new ArrayList<>();

    public EventManager() throws IOException {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(EventManager.class.getResourceAsStream("media-event-dir-paths.txt"), Charset.forName("UTF-8")))) {
//            br.lines()
//                    .map(Paths::get)
//                    .forEach(this::updateEventMap);
//        }
    }

    public boolean addEvent(Event event) {
        synchronized (events) {
            return events.add(event);
        }
    }

    public boolean removeEvent(Event event) {
        synchronized (events) {
            return events.remove(event);
        }
    }

//    public void updateEventMap(Path basePath) {
//        try (final Stream<Path> paths = Files.list(basePath)) {
//            paths.filter(Files::isDirectory).map((Path path) -> Event.fullTimeDayEvent(path.getFileName().toString()))
//                    .filter(Optional::isPresent)
//                    .map(Optional::get)
//                    .forEach(this::updateEventMap);
//        } catch (IOException ex) {
//            LOG.error(ex.getMessage(), ex);
//        }
//    }
//
//    private void updateEventMap(Event event) {
//        final FullTimeEventDuration duration = (FullTimeEventDuration) event.getDuration();
//        for (LocalDate date = duration.getStartDateInclusive(); date.isBefore(duration.getEndDateInclusive()) || date.equals(duration.getEndDateInclusive()); date = date.plusDays(1)) {
//            if (!events.containsKey(date)) {
//                events.put(date, new TreeSet<>(eventComparator));
//            }
//            if (!events.get(date).contains(event)) {
//                events.get(date).add(event);
//                LOG.debug(event.getName() + " - " + event.getDirName());
//            }
//        }
//    }
//
//    public Event getFirstEvent(LocalDate date) {
//        updateEventMap(createEvent(date));
//        return events.get(date).first();
//    }
//
//    private Event createEvent(LocalDate date) {
//        return new Event(null, "", new FullTimeEventDuration(date, date));
//    }
//
//    public SortedSet<Event> getAllEvents() {
//        return events.values().stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toCollection(() -> new TreeSet<>(eventComparator)));
//    }
}
