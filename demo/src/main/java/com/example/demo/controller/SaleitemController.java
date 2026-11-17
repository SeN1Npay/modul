package com.example.demo.controller;

import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.entity.Saleitems;
import com.example.demo.repository.SaleitemsRepository;
import com.example.demo.entity.Sales;
import com.example.demo.repository.SalesRepository;
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
@RequestMapping("/Loktew/saleitem")
public class SaleitemController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private SaleitemsRepository saleitemsRepository;

    @Autowired
    private SalesRepository salesRepository;

    @GetMapping("/allSaleitem")
    public @ResponseBody List allList() {
        List listSaleitem = new ArrayList();
        for (Saleitems saleitem : saleitemsRepository.findAll()) {
            listSaleitem.add(saleitem.getIdsale());
            listSaleitem.add(saleitem.getIdproduct());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("saleitems");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listSaleitem;
    }

    @PostMapping("/addSaleitem")
    public @ResponseBody boolean addSaleitem(
            @RequestParam(name = "idsale") String idsale,
            @RequestParam(name = "idproduct") String idproduct,
            @RequestParam(name = "quantity") Integer quantity) {
        Saleitems saleitem = new Saleitems();
        Integer idsaleValue = Integer.parseInt(idsale);
        Sales idsaleObject = salesRepository.findById(idsaleValue).orElseThrow();
        saleitem.setIdsale(idsaleObject);
        Integer idproductValue = Integer.parseInt(idproduct);
        Products idproductObject = productsRepository.findById(idproductValue).orElseThrow();
        saleitem.setIdproduct(idproductObject);
        saleitem.setQuantity(quantity);
        saleitemsRepository.save(saleitem);
        Logs logRecord = new Logs();
        logRecord.setMethod("addSaleitem");
        logRecord.setRecordtype("saleitems");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateSaleitem")
    public @ResponseBody boolean updateSaleitem(
            @RequestParam(name = "idsale") String idsale,
            @RequestParam(name = "idproduct") String idproduct,
            @RequestParam(name = "quantity") Integer quantity,
            @RequestParam(name = "idsaleitem") String idsaleitem) {
        try {
            Integer id = Integer.parseInt(idsaleitem);
            Saleitems saleitem = saleitemsRepository.findById(id).orElseThrow();
            Integer idsaleValue = Integer.parseInt(idsale);
            Sales idsaleObject = salesRepository.findById(idsaleValue).orElseThrow();
            saleitem.setIdsale(idsaleObject);
            Integer idproductValue = Integer.parseInt(idproduct);
            Products idproductObject = productsRepository.findById(idproductValue).orElseThrow();
            saleitem.setIdproduct(idproductObject);
            saleitem.setQuantity(quantity);
            System.out.println(saleitem.getIdsaleitem());
            saleitemsRepository.save(saleitem);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateSaleitem");
        logRecord.setRecordtype("saleitems");
        logRecord.setRecordtext("Изменена запись, idsaleitem = " + idsaleitem);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteSaleitem")
    public @ResponseBody boolean deleteSaleitem(@RequestParam(name = "idsaleitem") String idsaleitem) {
        saleitemsRepository.deleteById(Integer.parseInt(idsaleitem));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteSaleitem");
        logRecord.setRecordtype("saleitems");
        logRecord.setRecordtext("Удалена запись, idsaleitem = " + idsaleitem);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
