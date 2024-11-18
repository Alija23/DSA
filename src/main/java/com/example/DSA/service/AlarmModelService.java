package com.example.DSA.service;

import com.example.DSA.entity.AlarmData;
import com.example.DSA.model.AlarmModel;
import com.example.DSA.repository.AlarmModelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlarmModelService {
    private final AlarmModelRepository alarmModelRepository;

    public AlarmModelService(AlarmModelRepository alarmModelRepository) {
        this.alarmModelRepository = alarmModelRepository;
    }

    public List<AlarmModel> getAlarmData() {

       List<AlarmData> alarmData = this.alarmModelRepository.findAll();
       List<AlarmModel> alarmModel = new ArrayList<>();
       for (AlarmData tmp : alarmData) {
           alarmModel.add(new AlarmModel(tmp));
       }
       return alarmModel;
    }

    public void saveAlarmModel(AlarmModel alarmModel) {
        AlarmData alarm = new AlarmData(alarmModel);
        alarm.setId(0L);
        this.alarmModelRepository.save(alarm);
    }
}
