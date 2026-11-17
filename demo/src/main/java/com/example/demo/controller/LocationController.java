package com.example.demo.controller;

import com.example.demo.entity.Locations;
import com.example.demo.repository.LocationsRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Loktew/location")
public class LocationController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @GetMapping("/allLocation")
    public @ResponseBody Iterable<Locations> allLocation() {
        Logs logRecord = new Logs();
        logRecord.setMethod("allLocation");
        logRecord.setRecordtype("locations");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return locationsRepository.findAll();
    }

    @PostMapping("/addLocation")
    public @ResponseBody boolean addLocation(
            @RequestParam(name = "room") String room,
            @RequestParam(name = "rack") String rack,
            @RequestParam(name = "shelf") String shelf) {
        Locations location = new Locations();
        location.setRoom(room);
        location.setRack(rack);
        location.setShelf(shelf);
        locationsRepository.save(location);
        Logs logRecord = new Logs();
        logRecord.setMethod("addLocation");
        logRecord.setRecordtype("locations");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateLocation")
    public @ResponseBody boolean updateLocation(
            @RequestParam(name = "room") String room,
            @RequestParam(name = "rack") String rack,
            @RequestParam(name = "shelf") String shelf,
            @RequestParam(name = "idlocation") String idlocation) {
        try {
            Integer id = Integer.parseInt(idlocation);
            Locations location = locationsRepository.findById(id).orElseThrow();
            location.setRoom(room);
            location.setRack(rack);
            location.setShelf(shelf);
            System.out.println(location.getIdlocation());
            locationsRepository.save(location);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateLocation");
        logRecord.setRecordtype("locations");
        logRecord.setRecordtext("Изменена запись, idlocation = " + idlocation);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteLocation")
    public @ResponseBody boolean deleteLocation(@RequestParam(name = "idlocation") String idlocation) {
        locationsRepository.deleteById(Integer.parseInt(idlocation));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteLocation");
        logRecord.setRecordtype("locations");
        logRecord.setRecordtext("Удалена запись, idlocation = " + idlocation);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
