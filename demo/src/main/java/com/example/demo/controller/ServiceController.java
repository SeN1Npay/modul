package com.example.demo.controller;

import com.example.demo.entity.Services;
import com.example.demo.repository.ServicesRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Loktew/service")
public class ServiceController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping("/allService")
    public @ResponseBody Iterable<Services> allService() {
        Logs logRecord = new Logs();
        logRecord.setMethod("allService");
        logRecord.setRecordtype("services");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return servicesRepository.findAll();
    }

    @PostMapping("/addService")
    public @ResponseBody boolean addService(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "cost") Integer cost) {
        Services service = new Services();
        service.setName(name);
        service.setCost(cost);
        servicesRepository.save(service);
        Logs logRecord = new Logs();
        logRecord.setMethod("addService");
        logRecord.setRecordtype("services");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateService")
    public @ResponseBody boolean updateService(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "cost") Integer cost,
            @RequestParam(name = "idservice") String idservice) {
        try {
            Integer id = Integer.parseInt(idservice);
            Services service = servicesRepository.findById(id).orElseThrow();
            service.setName(name);
            service.setCost(cost);
            System.out.println(service.getIdservice());
            servicesRepository.save(service);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateService");
        logRecord.setRecordtype("services");
        logRecord.setRecordtext("Изменена запись, idservice = " + idservice);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteService")
    public @ResponseBody boolean deleteService(@RequestParam(name = "idservice") String idservice) {
        servicesRepository.deleteById(Integer.parseInt(idservice));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteService");
        logRecord.setRecordtype("services");
        logRecord.setRecordtext("Удалена запись, idservice = " + idservice);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
