package com.example.DSA.controller;

import com.example.DSA.configuration.AlarmHandler;
import com.example.DSA.model.AlarmModel;
import com.example.DSA.service.AlarmModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarm-api/state")
public class AlarmModelController {
    private final AlarmModelService alarmModelService;
    public AlarmModelController(AlarmModelService alarmModelService) {
        this.alarmModelService = alarmModelService;
    }
    @GetMapping
    public ResponseEntity<List<AlarmModel>> getAlarmModel() {
        List<AlarmModel> alarm = this.alarmModelService.getAlarmData();
        return ResponseEntity.status(HttpStatus.OK).body(alarm);
    }

    @PostMapping
    public ResponseEntity<AlarmModel> postDmData(@RequestBody AlarmModel alarmModel) throws Exception {
       this.alarmModelService.saveAlarmModel(alarmModel);
        AlarmHandler.broadcastAlarmModel(alarmModel);
       return ResponseEntity.status(HttpStatus.OK).body(alarmModel);
    }
}
