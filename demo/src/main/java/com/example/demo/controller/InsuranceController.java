package com.example.demo.controller;

import com.example.demo.entity.Insurances;
import com.example.demo.repository.InsurancesRepository;
import com.example.demo.entity.Saleitems;
import com.example.demo.repository.SaleitemsRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalTime;

@RestController
@RequestMapping("/Loktew/insurance")
public class InsuranceController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private InsurancesRepository insurancesRepository;

    @Autowired
    private SaleitemsRepository saleitemsRepository;

    @GetMapping("/allInsurance")
    public @ResponseBody List allList() {
        List listInsurance = new ArrayList();
        for (Insurances insurance : insurancesRepository.findAll()) {
            listInsurance.add(insurance.getIdsaleitem());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("insurances");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listInsurance;
    }

    @PostMapping("/addInsurance")
    public @ResponseBody boolean addInsurance(
            @RequestParam(name = "idsaleitem") String idsaleitem,
            @RequestParam(name = "policynumber") String policynumber,
            @RequestParam(name = "startdate") String startdate,
            @RequestParam(name = "enddate") String enddate,
            @RequestParam(name = "cost") Integer cost) {
        Insurances insurance = new Insurances();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        Integer idsaleitemValue = Integer.parseInt(idsaleitem);
        Saleitems idsaleitemObject = saleitemsRepository.findById(idsaleitemValue).orElseThrow();
        insurance.setIdsaleitem(idsaleitemObject);
        insurance.setPolicynumber(policynumber);
        try {
            date = LocalDate.parse(startdate, formatter);
            insurance.setStartdate(java.sql.Date.valueOf(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            date = LocalDate.parse(enddate, formatter);
            insurance.setEnddate(java.sql.Date.valueOf(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        insurance.setCost(cost);
        insurancesRepository.save(insurance);
        Logs logRecord = new Logs();
        logRecord.setMethod("addInsurance");
        logRecord.setRecordtype("insurances");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateInsurance")
    public @ResponseBody boolean updateInsurance(
            @RequestParam(name = "idsaleitem") String idsaleitem,
            @RequestParam(name = "policynumber") String policynumber,
            @RequestParam(name = "startdate") String startdate,
            @RequestParam(name = "enddate") String enddate,
            @RequestParam(name = "cost") Integer cost,
            @RequestParam(name = "idinsurance") String idinsurance) {
        try {
            Integer id = Integer.parseInt(idinsurance);
            Insurances insurance = insurancesRepository.findById(id).orElseThrow();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            Integer idsaleitemValue = Integer.parseInt(idsaleitem);
            Saleitems idsaleitemObject = saleitemsRepository.findById(idsaleitemValue).orElseThrow();
            insurance.setIdsaleitem(idsaleitemObject);
            insurance.setPolicynumber(policynumber);
            try {
                date = LocalDate.parse(startdate, formatter);
                insurance.setStartdate(java.sql.Date.valueOf(date));
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                date = LocalDate.parse(enddate, formatter);
                insurance.setEnddate(java.sql.Date.valueOf(date));
            } catch (Exception e) {
                System.out.println(e);
            }
            insurance.setCost(cost);
            System.out.println(insurance.getIdinsurance());
            insurancesRepository.save(insurance);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateInsurance");
        logRecord.setRecordtype("insurances");
        logRecord.setRecordtext("Изменена запись, idinsurance = " + idinsurance);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteInsurance")
    public @ResponseBody boolean deleteInsurance(@RequestParam(name = "idinsurance") String idinsurance) {
        insurancesRepository.deleteById(Integer.parseInt(idinsurance));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteInsurance");
        logRecord.setRecordtype("insurances");
        logRecord.setRecordtext("Удалена запись, idinsurance = " + idinsurance);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
