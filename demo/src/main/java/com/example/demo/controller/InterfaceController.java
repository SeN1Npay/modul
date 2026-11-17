package com.example.demo.controller;

import com.example.demo.entity.Interfaces;
import com.example.demo.repository.InterfacesRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Loktew/interface")
public class InterfaceController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private InterfacesRepository interfacesRepository;

    @GetMapping("/allInterface")
    public @ResponseBody Iterable<Interfaces> allInterface() {
        Logs logRecord = new Logs();
        logRecord.setMethod("allInterface");
        logRecord.setRecordtype("interfaces");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return interfacesRepository.findAll();
    }

    @PostMapping("/addInterface")
    public @ResponseBody boolean addInterface(
            @RequestParam(name = "name") String name) {
        Interfaces iface = new Interfaces();
        iface.setName(name);
        interfacesRepository.save(iface);
        Logs logRecord = new Logs();
        logRecord.setMethod("addInterface");
        logRecord.setRecordtype("interfaces");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateInterface")
    public @ResponseBody boolean updateInterface(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "idinterface") String idinterface) {
        try {
            Integer id = Integer.parseInt(idinterface);
            Interfaces iface = interfacesRepository.findById(id).orElseThrow();
            iface.setName(name);
            System.out.println(iface.getIdinterface());
            interfacesRepository.save(iface);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateInterface");
        logRecord.setRecordtype("interfaces");
        logRecord.setRecordtext("Изменена запись, idinterface = " + idinterface);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteInterface")
    public @ResponseBody boolean deleteInterface(@RequestParam(name = "idinterface") String idinterface) {
        interfacesRepository.deleteById(Integer.parseInt(idinterface));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteInterface");
        logRecord.setRecordtype("interfaces");
        logRecord.setRecordtext("Удалена запись, idinterface = " + idinterface);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}