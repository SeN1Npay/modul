package com.example.demo.controller;

import com.example.demo.entity.Characteristics;
import com.example.demo.repository.CharacteristicsRepository;
import com.example.demo.entity.Logs;
import com.example.demo.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/Loktew/characteristic")
public class CharacteristicController {

    @Autowired
    private LogsRepository logsRepository;

    @Autowired
    private CharacteristicsRepository characteristicsRepository;

    @GetMapping("/allCharacteristic")
    public @ResponseBody Iterable<Characteristics> allCharacteristic() {
        Logs logRecord = new Logs();
        logRecord.setMethod("allCharacteristic");
        logRecord.setRecordtype("characteristics");
        logRecord.setRecordtext("Выведены все записи");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return characteristicsRepository.findAll();
    }

    @PostMapping("/addCharacteristic")
    public @ResponseBody boolean addCharacteristic(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "unit") String unit) {
        Characteristics characteristic = new Characteristics();
        characteristic.setName(name);
        characteristic.setUnit(unit);
        characteristicsRepository.save(characteristic);
        Logs logRecord = new Logs();
        logRecord.setMethod("addCharacteristic");
        logRecord.setRecordtype("characteristics");
        logRecord.setRecordtext("Добавлена запись");
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/updateCharacteristic")
    public @ResponseBody boolean updateCharacteristic(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "unit") String unit,
            @RequestParam(name = "idcharacteristic") String idcharacteristic) {
        try {
            Integer id = Integer.parseInt(idcharacteristic);
            Characteristics characteristic = characteristicsRepository.findById(id).orElseThrow();
            characteristic.setName(name);
            characteristic.setUnit(unit);
            System.out.println(characteristic.getIdcharacteristic());
            characteristicsRepository.save(characteristic);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        Logs logRecord = new Logs();
        logRecord.setMethod("updateCharacteristic");
        logRecord.setRecordtype("characteristics");
        logRecord.setRecordtext("Изменена запись, idcharacteristic = " + idcharacteristic);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }

    @PostMapping("/deleteCharacteristic")
    public @ResponseBody boolean deleteCharacteristic(@RequestParam(name = "idcharacteristic") String idcharacteristic) {
        characteristicsRepository.deleteById(Integer.parseInt(idcharacteristic));
        Logs logRecord = new Logs();
        logRecord.setMethod("deleteCharacteristic");
        logRecord.setRecordtype("characteristics");
        logRecord.setRecordtext("Удалена запись, idcharacteristic = " + idcharacteristic);
        logRecord.setActiondate(java.sql.Date.valueOf(LocalDate.now()));
        logRecord.setActiontime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logsRepository.save(logRecord);
        return true;
    }
}
