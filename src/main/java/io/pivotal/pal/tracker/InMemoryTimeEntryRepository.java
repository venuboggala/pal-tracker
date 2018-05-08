package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long,TimeEntry> timeEntryMap;

    public InMemoryTimeEntryRepository() {
        timeEntryMap = new HashMap<Long, TimeEntry>();
    }

    @Override
    public TimeEntry create(TimeEntry any) {
        long id = timeEntryMap.keySet().size() + 1;
        any.setId(id);
        timeEntryMap.put(id,any);
        /*
        timeEntryMap.put(id, new TimeEntry(
                id,
                any.getProjectId(),
                any.getUserId(),
                any.getDate(),
                any.getHours()));
        */
        return timeEntryMap.get(id);
    }

    @Override
    public TimeEntry find(long l) {

        return timeEntryMap.get(l);
    }

    @Override
    public List<TimeEntry> list() {
        List list = new ArrayList<TimeEntry>();
        list.addAll(timeEntryMap.values());
        return list;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {
        TimeEntry timeEntry = timeEntryMap.get(eq);
        // add setters for timeEntry, and update timeEntry with any
        if (timeEntry != null) {
            timeEntry.setDate(any.getDate());
            timeEntry.setHours(any.getHours());
            timeEntry.setProjectId(any.getProjectId());
            timeEntry.setUserId(any.getUserId());
        }
        return timeEntry;
    }

    @Override
    public void delete(long l) {
        timeEntryMap.remove(l);
    }
}
