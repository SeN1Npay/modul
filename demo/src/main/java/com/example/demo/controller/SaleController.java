package com.example.demo.controller;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.entity.Sales;
import com.example.demo.repository.SalesRepository;
import com.example.demo.entity.Sellers;
import com.example.demo.repository.SellersRepository;
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
@RequestMapping("/Loktew/sale")
public class SaleController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SellersRepository sellersRepository;

    @GetMapping("/allSale")
    public @ResponseBody List allList() {
        List listSale = new ArrayList();
        for (Sales sale : salesRepository.findAll()) {
            listSale.add(sale.getIdcustomer());
            listSale.add(sale.getIdseller());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("sales");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listSale;
    }

    @PostMapping("/addSale")
    public @ResponseBody boolean addSale(
            @RequestParam(name = "idcustomer") String idcustomer,
            @RequestParam(name = "idseller") String idseller,
            @RequestParam(name = "saledate") String saledate) {
        Sales sale = new Sales();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        Integer idcustomerValue = Integer.parseInt(idcustomer);
        Customers idcustomerObject = customersRepository.findById(idcustomerValue).orElseThrow();
        sale.setIdcustomer(idcustomerObject);
        Integer idsellerValue = Integer.parseInt(idseller);
        Sellers idsellerObject = sellersRepository.findById(idsellerValue).orElseThrow();
        sale.setIdseller(idsellerObject);
        try {
            date = LocalDate.parse(saledate, formatter);
            sale.setSaledate(java.sql.Date.valueOf(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        salesRepository.save(sale);
        Logs logRecord = new Logs();
        logRecord.setMethod("addSale");
        logRecord.setRecordtype("sales");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateSale")
    public @ResponseBody boolean updateSale(
            @RequestParam(name = "idcustomer") String idcustomer,
            @RequestParam(name = "idseller") String idseller,
            @RequestParam(name = "saledate") String saledate,
            @RequestParam(name = "idsale") String idsale) {
        try {
            Integer id = Integer.parseInt(idsale);
            Sales sale = salesRepository.findById(id).orElseThrow();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            Integer idcustomerValue = Integer.parseInt(idcustomer);
            Customers idcustomerObject = customersRepository.findById(idcustomerValue).orElseThrow();
            sale.setIdcustomer(idcustomerObject);
            Integer idsellerValue = Integer.parseInt(idseller);
            Sellers idsellerObject = sellersRepository.findById(idsellerValue).orElseThrow();
            sale.setIdseller(idsellerObject);
            try {
                date = LocalDate.parse(saledate, formatter);
                sale.setSaledate(java.sql.Date.valueOf(date));
            } catch (Exception e) {
                System.out.println(e);
            }
            System.out.println(sale.getIdsale());
            salesRepository.save(sale);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateSale");
        logRecord.setRecordtype("sales");
        logRecord.setRecordtext("Изменена запись, idsale = " + idsale);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteSale")
    public @ResponseBody boolean deleteSale(@RequestParam(name = "idsale") String idsale) {
        salesRepository.deleteById(Integer.parseInt(idsale));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteSale");
        logRecord.setRecordtype("sales");
        logRecord.setRecordtext("Удалена запись, idsale = " + idsale);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
