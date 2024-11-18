package com.example.DSA.model;

import com.example.DSA.entity.AlarmData;

import java.time.LocalDateTime;

public class AlarmModel {
    private LocalDateTime createdDate;
    private Integer value;

    public AlarmModel(LocalDateTime createdDate, Integer value) {
        this.createdDate = createdDate;
        this.value = value;
    }

    public AlarmModel(AlarmData tmp) {
        this.createdDate = tmp.getCreatedDate();
        this.value = tmp.getValue();
    }


    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
