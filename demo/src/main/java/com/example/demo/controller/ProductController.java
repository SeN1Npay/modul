package com.example.demo.controller;

import com.example.demo.entity.Interfaces;
import com.example.demo.repository.InterfacesRepository;
import com.example.demo.entity.Locations;
import com.example.demo.repository.LocationsRepository;
import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Loktew/product")
public class ProductController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private InterfacesRepository interfacesRepository;

    @Autowired
    private LocationsRepository locationsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/allProduct")
    public @ResponseBody List allList() {
        List listProduct = new ArrayList();
        for (Products product : productsRepository.findAll()) {
            listProduct.add(product.getIdlocation());
            listProduct.add(product.getIdinterface());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("products");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listProduct;
    }

    @PostMapping("/addProduct")
    public @ResponseBody boolean addProduct(
            @RequestParam(name = "idlocation") String idlocation,
            @RequestParam(name = "idinterface") String idinterface,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") Integer price,
            @RequestParam(name = "quantity") Integer quantity,
            @RequestParam(name = "peripheral") Integer peripheral,
            @RequestParam(name = "photolink") String photolink) {
        Products product = new Products();
        Integer idlocationValue = Integer.parseInt(idlocation);
        Locations idlocationObject = locationsRepository.findById(idlocationValue).orElseThrow();
        product.setIdlocation(idlocationObject);
        Integer idinterfaceValue = Integer.parseInt(idinterface);
        Interfaces idinterfaceObject = interfacesRepository.findById(idinterfaceValue).orElseThrow();
        product.setIdinterface(idinterfaceObject);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setPeripheral(peripheral);
        product.setPhotolink(photolink);
        productsRepository.save(product);
        Logs logRecord = new Logs();
        logRecord.setMethod("addProduct");
        logRecord.setRecordtype("products");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateProduct")
    public @ResponseBody boolean updateProduct(
            @RequestParam(name = "idlocation") String idlocation,
            @RequestParam(name = "idinterface") String idinterface,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") Integer price,
            @RequestParam(name = "quantity") Integer quantity,
            @RequestParam(name = "peripheral") Integer peripheral,
            @RequestParam(name = "photolink") String photolink,
            @RequestParam(name = "idproduct") String idproduct) {
        try {
            Integer id = Integer.parseInt(idproduct);
            Products product = productsRepository.findById(id).orElseThrow();
            Integer idlocationValue = Integer.parseInt(idlocation);
            Locations idlocationObject = locationsRepository.findById(idlocationValue).orElseThrow();
            product.setIdlocation(idlocationObject);
            Integer idinterfaceValue = Integer.parseInt(idinterface);
            Interfaces idinterfaceObject = interfacesRepository.findById(idinterfaceValue).orElseThrow();
            product.setIdinterface(idinterfaceObject);
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setPeripheral(peripheral);
            product.setPhotolink(photolink);
            System.out.println(product.getIdproduct());
            productsRepository.save(product);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateProduct");
        logRecord.setRecordtype("products");
        logRecord.setRecordtext("Изменена запись, idproduct = " + idproduct);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteProduct")
    public @ResponseBody boolean deleteProduct(@RequestParam(name = "idproduct") String idproduct) {
        productsRepository.deleteById(Integer.parseInt(idproduct));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteProduct");
        logRecord.setRecordtype("products");
        logRecord.setRecordtext("Удалена запись, idproduct = " + idproduct);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @GetMapping("/setImportXLS")
    public @ResponseBody
    boolean setImportXLS(@RequestParam(name = "filePath") String filePath) {
        try {
            OPCPackage oPCPackage = OPCPackage.open(new File(filePath));
            XSSFWorkbook wb = new XSSFWorkbook(oPCPackage);
            XSSFSheet sheet = wb.getSheetAt(1);
            XSSFRow row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    Products product = new Products();
                    Integer idlocationValue = Integer.parseInt(row.getCell(0).getStringCellValue());
                    Locations idlocationObject = locationsRepository.findById(idlocationValue).orElseThrow();
                    product.setIdlocation(idlocationObject);
                    Integer idinterfaceValue = Integer.parseInt(row.getCell(1).getStringCellValue());
                    Interfaces idinterfaceObject = interfacesRepository.findById(idinterfaceValue).orElseThrow();
                    product.setIdinterface(idinterfaceObject);
                    product.setName(row.getCell(2).getStringCellValue());
                    product.setPrice(Integer.parseInt(row.getCell(3).getStringCellValue()));
                    product.setQuantity(Integer.parseInt(row.getCell(4).getStringCellValue()));
                    product.setPeripheral(Integer.parseInt(row.getCell(5).getStringCellValue()));
                    product.setPhotolink(row.getCell(6).getStringCellValue());
                    productsRepository.save(product);
                } else {
                    break;
                }
            }
            Logs logRecord = new Logs();
            logRecord.setMethod("setImportXLS");
            logRecord.setRecordtype("products");
            logRecord.setRecordtext("Импорт товаров из файла " + filePath);
            logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
            logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            logsRepository.save(logRecord);
            return true;
        } catch (IOException | InvalidFormatException ex) {
            return false;
        }
    }
}
