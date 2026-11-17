package com.example.demo.controller;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
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
@RequestMapping("/Loktew/customer")
public class CustomerController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/allCustomer")
    public @ResponseBody List allList() {
        List listCustomer = new ArrayList();
        for (Customers customer : customersRepository.findAll()) {
            listCustomer.add(customer.getIduser());
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("allList");
        logRecord.setRecordtype("customers");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return listCustomer;
    }

    @PostMapping("/addCustomer")
    public @ResponseBody boolean addCustomer(
            @RequestParam(name = "iduser") String iduser,
            @RequestParam(name = "phone") String phone) {
        Customers customer = new Customers();
        Integer iduserValue = Integer.parseInt(iduser);
        Users iduserObject = usersRepository.findById(iduserValue).orElseThrow();
        customer.setIduser(iduserObject);
        customer.setPhone(phone);
        customersRepository.save(customer);
        Logs logRecord = new Logs();
        logRecord.setMethod("addCustomer");
        logRecord.setRecordtype("customers");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateCustomer")
    public @ResponseBody boolean updateCustomer(
            @RequestParam(name = "iduser") String iduser,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "idcustomer") String idcustomer) {
        try {
            Integer id = Integer.parseInt(idcustomer);
            Customers customer = customersRepository.findById(id).orElseThrow();
            Integer iduserValue = Integer.parseInt(iduser);
            Users iduserObject = usersRepository.findById(iduserValue).orElseThrow();
            customer.setIduser(iduserObject);
            customer.setPhone(phone);
            System.out.println(customer.getIdcustomer());
            customersRepository.save(customer);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateCustomer");
        logRecord.setRecordtype("customers");
        logRecord.setRecordtext("Изменена запись, idcustomer = " + idcustomer);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteCustomer")
    public @ResponseBody boolean deleteCustomer(@RequestParam(name = "idcustomer") String idcustomer) {
        customersRepository.deleteById(Integer.parseInt(idcustomer));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteCustomer");
        logRecord.setRecordtype("customers");
        logRecord.setRecordtext("Удалена запись, idcustomer = " + idcustomer);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
