package com.example.demo.controller;

import com.example.demo.entity.Sales;
import com.example.demo.repository.SalesRepository;
import com.example.demo.entity.Saleservices;
import com.example.demo.repository.SaleservicesRepository;
import com.example.demo.entity.Services;
import com.example.demo.repository.ServicesRepository;
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
@RequestMapping("/Loktew/saleservice")
public class SaleserviceController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SaleservicesRepository saleservicesRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping("/allSaleservice")
    public @ResponseBody List allList() {
        List listSaleservice = new ArrayList();
        for (Saleservices saleservice : saleservicesRepository.findAll()) {
            List list = new ArrayList();
            list.add(saleservice);
            list.add(saleservice.getIdsale());
            list.add(saleservice.getIdservice());
            listSaleservice.add(list);
        }

        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("saleservices");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listSaleservice;
    }

    @PostMapping("/addSaleservice")
    public @ResponseBody boolean addSaleservice(
            @RequestParam(name = "idsale") String idsale,
            @RequestParam(name = "idservice") String idservice,
            @RequestParam(name = "quantity") Integer quantity) {

        Saleservices saleservice = new Saleservices();

        Integer idsaleValue = Integer.parseInt(idsale);
        Sales idsaleObject = salesRepository.findById(idsaleValue).orElseThrow();
        saleservice.setIdsale(idsaleObject);

        Integer idserviceValue = Integer.parseInt(idservice);
        Services idserviceObject = servicesRepository.findById(idserviceValue).orElseThrow();
        saleservice.setIdservice(idserviceObject);

        saleservice.setQuantity(quantity);

        saleservicesRepository.save(saleservice);
        Logs logRecord = new Logs();
        logRecord.setMethod("addSaleservice");
        logRecord.setRecordtype("saleservices");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateSaleservice")
    public @ResponseBody boolean updateSaleservice(
            @RequestParam(name = "idsale") String idsale,
            @RequestParam(name = "idservice") String idservice,
            @RequestParam(name = "quantity") Integer quantity,
            @RequestParam(name = "idsaleservice") String idsaleservice) {

        try {
            Integer id = Integer.parseInt(idsaleservice);
            Saleservices saleservice = saleservicesRepository.findById(id).orElseThrow();

            Integer idsaleValue = Integer.parseInt(idsale);
            Sales idsaleObject = salesRepository.findById(idsaleValue).orElseThrow();
            saleservice.setIdsale(idsaleObject);

            Integer idserviceValue = Integer.parseInt(idservice);
            Services idserviceObject = servicesRepository.findById(idserviceValue).orElseThrow();
            saleservice.setIdservice(idserviceObject);

            saleservice.setQuantity(quantity);

            System.out.println(saleservice.getIdsaleservice());
            saleservicesRepository.save(saleservice);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        Logs logRecord = new Logs();
        logRecord.setMethod("updateSaleservice");
        logRecord.setRecordtype("saleservices");
        logRecord.setRecordtext("Изменена запись, idsaleservice = " + idsaleservice);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteSaleservice")
    public @ResponseBody boolean deleteSaleservice(
            @RequestParam(name = "idsaleservice") String idsaleservice) {

        saleservicesRepository.deleteById(Integer.parseInt(idsaleservice));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteSaleservice");
        logRecord.setRecordtype("saleservices");
        logRecord.setRecordtext("Удалена запись, idsaleservice = " + idsaleservice);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}