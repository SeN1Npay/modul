package com.example.demo.controller;

import com.example.demo.entity.Characteristics;
import com.example.demo.repository.CharacteristicsRepository;
import com.example.demo.entity.Productcharacteristics;
import com.example.demo.repository.ProductcharacteristicsRepository;
import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;
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
@RequestMapping("/Loktew/productcharacteristic")
public class ProductcharacteristicController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private CharacteristicsRepository characteristicsRepository;

    @Autowired
    private ProductcharacteristicsRepository productcharacteristicsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/allProductcharacteristic")
    public @ResponseBody List allList() {
        List listProductcharacteristic = new ArrayList();
        for (Productcharacteristics productcharacteristic : productcharacteristicsRepository.findAll()) {
            listProductcharacteristic.add(productcharacteristic.getIdproduct());
            listProductcharacteristic.add(productcharacteristic.getIdcharacteristic());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("productcharacteristics");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listProductcharacteristic;
    }

    @PostMapping("/addProductcharacteristic")
    public @ResponseBody boolean addProductcharacteristic(
            @RequestParam(name = "idproduct") String idproduct,
            @RequestParam(name = "idcharacteristic") String idcharacteristic,
            @RequestParam(name = "value") String value) {
        Productcharacteristics productcharacteristic = new Productcharacteristics();
        Integer idproductValue = Integer.parseInt(idproduct);
        Products idproductObject = productsRepository.findById(idproductValue).orElseThrow();
        productcharacteristic.setIdproduct(idproductObject);
        Integer idcharacteristicValue = Integer.parseInt(idcharacteristic);
        Characteristics idcharacteristicObject = characteristicsRepository.findById(idcharacteristicValue).orElseThrow();
        productcharacteristic.setIdcharacteristic(idcharacteristicObject);
        productcharacteristic.setValue(value);
        productcharacteristicsRepository.save(productcharacteristic);
        Logs logRecord = new Logs();
        logRecord.setMethod("addProductcharacteristic");
        logRecord.setRecordtype("productcharacteristics");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateProductcharacteristic")
    public @ResponseBody boolean updateProductcharacteristic(
            @RequestParam(name = "idproduct") String idproduct,
            @RequestParam(name = "idcharacteristic") String idcharacteristic,
            @RequestParam(name = "value") String value,
            @RequestParam(name = "idproductcharacteristic") String idproductcharacteristic) {
        try {
            Integer id = Integer.parseInt(idproductcharacteristic);
            Productcharacteristics productcharacteristic = productcharacteristicsRepository.findById(id).orElseThrow();
            Integer idproductValue = Integer.parseInt(idproduct);
            Products idproductObject = productsRepository.findById(idproductValue).orElseThrow();
            productcharacteristic.setIdproduct(idproductObject);
            Integer idcharacteristicValue = Integer.parseInt(idcharacteristic);
            Characteristics idcharacteristicObject = characteristicsRepository.findById(idcharacteristicValue).orElseThrow();
            productcharacteristic.setIdcharacteristic(idcharacteristicObject);
            productcharacteristic.setValue(value);
            System.out.println(productcharacteristic.getIdproductcharacteristic());
            productcharacteristicsRepository.save(productcharacteristic);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateProductcharacteristic");
        logRecord.setRecordtype("productcharacteristics");
        logRecord.setRecordtext("Изменена запись, idproductcharacteristic = " + idproductcharacteristic);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteProductcharacteristic")
    public @ResponseBody boolean deleteProductcharacteristic(@RequestParam(name = "idproductcharacteristic") String idproductcharacteristic) {
        productcharacteristicsRepository.deleteById(Integer.parseInt(idproductcharacteristic));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteProductcharacteristic");
        logRecord.setRecordtype("productcharacteristics");
        logRecord.setRecordtext("Удалена запись, idproductcharacteristic = " + idproductcharacteristic);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
