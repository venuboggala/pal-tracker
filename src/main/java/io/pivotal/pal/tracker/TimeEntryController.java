package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<TimeEntry>(
                timeEntryRepository.create(timeEntryToCreate),
                HttpStatus.CREATED);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") Long id) {
        TimeEntry entry = timeEntryRepository.find(id);

        return new ResponseEntity<TimeEntry>(
                entry,
                (entry == null ? HttpStatus.NOT_FOUND:HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(
                timeEntryRepository.list(),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody TimeEntry expected) {
        TimeEntry entry = timeEntryRepository.update(id, expected);

        return new ResponseEntity<TimeEntry>(
                entry,
                (entry == null ? HttpStatus.NOT_FOUND:HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
