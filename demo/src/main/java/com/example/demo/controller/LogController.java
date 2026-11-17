package com.example.demo.controller;

import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalTime;

@RestController
@RequestMapping("/Loktew/log")
public class LogController {

    @Autowired
    private LogsRepository logsRepository;

    @GetMapping("/allLog")
    public @ResponseBody Iterable<Logs> allLog() {
        Logs logRecord = new Logs();
        logRecord.setMethod("allLog");
        logRecord.setRecordtype("logs");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return logsRepository.findAll();
    }

    @PostMapping("/addLog")
    public @ResponseBody boolean addLog(
            @RequestParam(name = "method") String method,
            @RequestParam(name = "recordtype") String recordtype,
            @RequestParam(name = "recordtext") String recordtext,
            @RequestParam(name = "actiondate") String actiondate,
            @RequestParam(name = "actiontime") String actiontime) {
        Logs log = new Logs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        log.setMethod(method);
        log.setRecordtype(recordtype);
        log.setRecordtext(recordtext);
        try {
            date = LocalDate.parse(actiondate, formatter);
            log.setActiondate(java.sql.Date.valueOf(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        log.setActiontime(actiontime);
        logsRepository.save(log);
        Logs logRecord = new Logs();
        logRecord.setMethod("addLog");
        logRecord.setRecordtype("logs");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateLog")
    public @ResponseBody boolean updateLog(
            @RequestParam(name = "method") String method,
            @RequestParam(name = "recordtype") String recordtype,
            @RequestParam(name = "recordtext") String recordtext,
            @RequestParam(name = "actiondate") String actiondate,
            @RequestParam(name = "actiontime") String actiontime,
            @RequestParam(name = "idlog") String idlog) {
        try {
            Integer id = Integer.parseInt(idlog);
            Logs log = logsRepository.findById(id).orElseThrow();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            log.setMethod(method);
            log.setRecordtype(recordtype);
            log.setRecordtext(recordtext);
            try {
                date = LocalDate.parse(actiondate, formatter);
                log.setActiondate(java.sql.Date.valueOf(date));
            } catch (Exception e) {
                System.out.println(e);
            }
            log.setActiontime(actiontime);
            System.out.println(log.getIdlog());
            logsRepository.save(log);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateLog");
        logRecord.setRecordtype("logs");
        logRecord.setRecordtext("Изменена запись, idlog = " + idlog);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteLog")
    public @ResponseBody boolean deleteLog(@RequestParam(name = "idlog") String idlog) {
        logsRepository.deleteById(Integer.parseInt(idlog));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteLog");
        logRecord.setRecordtype("logs");
        logRecord.setRecordtext("Удалена запись, idlog = " + idlog);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
