package com.example.demo.controller;

import com.example.demo.entity.Sellers;
import com.example.demo.repository.SellersRepository;
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
@RequestMapping("/Loktew/seller")
public class SellerController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private SellersRepository sellersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/allSeller")
    public @ResponseBody List allList() {
        List listSeller = new ArrayList();
        for (Sellers seller : sellersRepository.findAll()) {
            listSeller.add(seller.getIduser());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("sellers");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listSeller;
    }

    @PostMapping("/addSeller")
    public @ResponseBody boolean addSeller(
            @RequestParam(name = "iduser") String iduser) {
        Sellers seller = new Sellers();
        Integer iduserValue = Integer.parseInt(iduser);
        Users iduserObject = usersRepository.findById(iduserValue).orElseThrow();
        seller.setIduser(iduserObject);
        sellersRepository.save(seller);
        Logs logRecord = new Logs();
        logRecord.setMethod("addSeller");
        logRecord.setRecordtype("sellers");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateSeller")
    public @ResponseBody boolean updateSeller(
            @RequestParam(name = "iduser") String iduser,
            @RequestParam(name = "idseller") String idseller) {
        try {
            Integer id = Integer.parseInt(idseller);
            Sellers seller = sellersRepository.findById(id).orElseThrow();
            Integer iduserValue = Integer.parseInt(iduser);
            Users iduserObject = usersRepository.findById(iduserValue).orElseThrow();
            seller.setIduser(iduserObject);
            System.out.println(seller.getIdseller());
            sellersRepository.save(seller);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateSeller");
        logRecord.setRecordtype("sellers");
        logRecord.setRecordtext("Изменена запись, idseller = " + idseller);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteSeller")
    public @ResponseBody boolean deleteSeller(@RequestParam(name = "idseller") String idseller) {
        sellersRepository.deleteById(Integer.parseInt(idseller));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteSeller");
        logRecord.setRecordtype("sellers");
        logRecord.setRecordtext("Удалена запись, idseller = " + idseller);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
