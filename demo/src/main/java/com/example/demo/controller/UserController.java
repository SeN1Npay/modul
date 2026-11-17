package com.example.demo.controller;

import com.example.demo.entity.Customers;
import com.example.demo.repository.CustomersRepository;
import com.example.demo.entity.Managers;
import com.example.demo.repository.ManagersRepository;
import com.example.demo.entity.Sellers;
import com.example.demo.repository.SellersRepository;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.time.LocalTime;

@RestController
@RequestMapping("/Loktew/user")
public class UserController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ManagersRepository managersRepository;

    @Autowired
    private SellersRepository sellersRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/allUser")
    public @ResponseBody Iterable<Users> allUser() {
        Logs logRecord = new Logs();
        logRecord.setMethod("allUser");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return usersRepository.findAll();
    }

    @PostMapping("/addUser")
    public @ResponseBody boolean addUser(
            @RequestParam(name = "lastname") String lastname,
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "middlename") String middlename,
            @RequestParam(name = "birthdate") String birthdate,
            @RequestParam(name = "employmentdate") String employmentdate,
            @RequestParam(name = "passport") String passport,
            @RequestParam(name = "snils") String snils,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "photolink") String photolink,
            @RequestParam(name = "login") String login,
            @RequestParam(name = "passwordhash") String passwordhash,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "registrationcode") String registrationcode) {
        Users user = new Users();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        user.setLastname(lastname);
        user.setFirstname(firstname);
        user.setMiddlename(middlename);
        try {
            date = LocalDate.parse(birthdate, formatter);
            user.setBirthdate(java.sql.Date.valueOf(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            date = LocalDate.parse(employmentdate, formatter);
            user.setEmploymentdate(java.sql.Date.valueOf(date));
        } catch (Exception e) {
            System.out.println(e);
        }
        user.setPassport(passport);
        user.setSnils(snils);
        user.setEmail(email);
        user.setPhotolink(photolink);
        user.setLogin(login);
        user.setPasswordhash(passwordhash);
        user.setStatus(status);
        user.setRegistrationcode(registrationcode);
        System.out.println(user.getLogin());
        usersRepository.save(user);
        System.out.println(user.getLogin());
        Logs logRecord = new Logs();
        logRecord.setMethod("addUser");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateUser")
    public @ResponseBody boolean updateUser(
            @RequestParam(name = "lastname") String lastname,
            @RequestParam(name = "firstname") String firstname,
            @RequestParam(name = "middlename") String middlename,
            @RequestParam(name = "birthdate") String birthdate,
            @RequestParam(name = "employmentdate") String employmentdate,
            @RequestParam(name = "passport") String passport,
            @RequestParam(name = "snils") String snils,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "photolink") String photolink,
            @RequestParam(name = "login") String login,
            @RequestParam(name = "passwordhash") String passwordhash,
            @RequestParam(name = "status") String status,
            @RequestParam(name = "registrationcode") String registrationcode,
            @RequestParam(name = "iduser") String iduser) {
        try {
            Integer id = Integer.parseInt(iduser);
            Users user = usersRepository.findById(id).orElseThrow();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            user.setLastname(lastname);
            user.setFirstname(firstname);
            user.setMiddlename(middlename);
            try {
                date = LocalDate.parse(birthdate, formatter);
                user.setBirthdate(java.sql.Date.valueOf(date));
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                date = LocalDate.parse(employmentdate, formatter);
                user.setEmploymentdate(java.sql.Date.valueOf(date));
            } catch (Exception e) {
                System.out.println(e);
            }
            user.setPassport(passport);
            user.setSnils(snils);
            user.setEmail(email);
            user.setPhotolink(photolink);
            user.setLogin(login);
            user.setPasswordhash(passwordhash);
            user.setStatus(status);
            user.setRegistrationcode(registrationcode);
            System.out.println(user.getIduser());
            usersRepository.save(user);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateUser");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Изменена запись, iduser = " + iduser);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteUser")
    public @ResponseBody boolean deleteUser(@RequestParam(name = "iduser") String iduser) {
        usersRepository.deleteById(Integer.parseInt(iduser));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteUser");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Удалена запись, iduser = " + iduser);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @GetMapping("/setMail")
    public @ResponseBody
    boolean setMail(@RequestParam(name = "textMess") String textMess,
                    @RequestParam(name = "itemMess") String itemMess) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bsg030485@yandex.ru");
        for (Users user : usersRepository.findAll()) {
            message.setTo(user.getEmail());
            message.setSubject(textMess); //Тема письма
            message.setText(itemMess);   //Текст письма
            mailSender.send(message);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("setMail");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Рассылка письма всем пользователям, тема: " + textMess);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @GetMapping("/setMailOne")
    public @ResponseBody
    boolean setMailOne(@RequestParam(name = "textMess") String textMess,
                       @RequestParam(name = "itemMess") String itemMess,
                       @RequestParam(name = "iduser") String iduser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bsg030485@yandex.ru");
        Integer id = Integer.parseInt(iduser);
        Users user = usersRepository.findById(id).orElseThrow();
        message.setTo(user.getEmail());
        message.setSubject(textMess); //Тема письма
        message.setText(itemMess);   //Текст письма
        mailSender.send(message);
        Logs logRecord = new Logs();
        logRecord.setMethod("setMailOne");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Письмо пользователю, iduser = " + iduser + ", тема: " + textMess);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @GetMapping("/setMailFile")
    public @ResponseBody
    boolean setMailFile(@RequestParam(name = "textMess") String textMess,
                        @RequestParam(name = "itemMess") String itemMess,
                        @RequestParam(name = "filePath") String filePath) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("bsg030485@yandex.ru");
        for (Users user : usersRepository.findAll()) {
            if (user.getEmail() != null) {
                helper.setTo(user.getEmail());
                helper.setSubject(textMess);//Тема письма
                helper.setText(itemMess);//Текст письма
                FileSystemResource file = new FileSystemResource(new File(filePath));//Адрес прикрепляемого файла
                helper.addAttachment(file.getFilename(), file);
                mailSender.send(message);
            }
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("setMailFile");
        logRecord.setRecordtype("users");
        logRecord.setRecordtext("Рассылка письма с файлом всем пользователям, тема: " + textMess);
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
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFRow row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    Users user = new Users();
                    user.setLastname(row.getCell(0).getStringCellValue());
                    user.setFirstname(row.getCell(1).getStringCellValue());
                    user.setMiddlename(row.getCell(2).getStringCellValue());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date;
                    try {
                        date = LocalDate.parse(row.getCell(3).getStringCellValue(), formatter);
                        user.setBirthdate(java.sql.Date.valueOf(date));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {
                        date = LocalDate.parse(row.getCell(4).getStringCellValue(), formatter);
                        user.setEmploymentdate(java.sql.Date.valueOf(date));
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    user.setPassport(row.getCell(5).getStringCellValue());
                    user.setSnils(row.getCell(6).getStringCellValue());
                    user.setEmail(row.getCell(7).getStringCellValue());
                    user.setPhotolink(row.getCell(8).getStringCellValue());
                    user.setLogin(row.getCell(9).getStringCellValue());
                    user.setPasswordhash(row.getCell(10).getStringCellValue());
                    user.setStatus(row.getCell(11).getStringCellValue());
                    user.setRegistrationcode(row.getCell(12).getStringCellValue());
                    usersRepository.save(user);
                    if (row.getCell(13).getStringCellValue().equals("manager")) {
                        Managers manager = new Managers();
                        manager.setIduser(user);
                        managersRepository.save(manager);
                    } else if (row.getCell(13).getStringCellValue().equals("seller")) {
                        Sellers seller = new Sellers();
                        seller.setIduser(user);
                        sellersRepository.save(seller);
                    } else {
                        Customers customer = new Customers();
                        customer.setIduser(user);
                        customer.setPhone(row.getCell(14).getStringCellValue());
                        customersRepository.save(customer);
                    }
                } else {
                    break;
                }
            }
            Logs logRecord = new Logs();
            logRecord.setMethod("setImportXLS");
            logRecord.setRecordtype("users");
            logRecord.setRecordtext("Импорт пользователей из файла " + filePath);
            logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
            logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            logsRepository.save(logRecord);
            return true;
        } catch (IOException | InvalidFormatException ex) {
            return false;
        }
    }
}
