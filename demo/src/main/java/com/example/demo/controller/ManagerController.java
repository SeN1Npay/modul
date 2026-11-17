package com.example.demo.controller;

import com.example.demo.entity.Managers;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Loktew/manager")
public class ManagerController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private ManagersRepository managersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/allManager")
    public @ResponseBody List allList() {
        List listManager = new ArrayList();
        for (Managers manager : managersRepository.findAll()) {
            listManager.add(manager.getIduser());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("managers");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listManager;
    }

    @PostMapping("/addManager")
    public @ResponseBody boolean addManager(
            @RequestParam(name = "iduser") String iduser) {
        Managers manager = new Managers();
        Integer iduserValue = Integer.parseInt(iduser);
        Users iduserObject = usersRepository.findById(iduserValue).orElseThrow();
        manager.setIduser(iduserObject);
        managersRepository.save(manager);
        Logs logRecord = new Logs();
        logRecord.setMethod("addManager");
        logRecord.setRecordtype("managers");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateManager")
    public @ResponseBody boolean updateManager(
            @RequestParam(name = "iduser") String iduser,
            @RequestParam(name = "idmanager") String idmanager) {
        try {
            Integer id = Integer.parseInt(idmanager);
            Managers manager = managersRepository.findById(id).orElseThrow();
            Integer iduserValue = Integer.parseInt(iduser);
            Users iduserObject = usersRepository.findById(iduserValue).orElseThrow();
            manager.setIduser(iduserObject);
            System.out.println(manager.getIdmanager());
            managersRepository.save(manager);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateManager");
        logRecord.setRecordtype("managers");
        logRecord.setRecordtext("Изменена запись, idmanager = " + idmanager);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteManager")
    public @ResponseBody boolean deleteManager(@RequestParam(name = "idmanager") String idmanager) {
        managersRepository.deleteById(Integer.parseInt(idmanager));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteManager");
        logRecord.setRecordtype("managers");
        logRecord.setRecordtext("Удалена запись, idmanager = " + idmanager);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
