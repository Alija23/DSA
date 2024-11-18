package com.example.DSA.entity;

import com.example.DSA.model.AlarmModel;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AlarmData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private Integer value;

    public AlarmData() {
        this.createdDate = LocalDateTime.now();
        this.value = 0;
    }
    public AlarmData(LocalDateTime createdDate, Integer value) {
        this.createdDate = createdDate;
        this.value = value;
    }
    public AlarmData(AlarmModel alarmModel) {
        this.createdDate = alarmModel.getCreatedDate();
        this.value = alarmModel.getValue();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Integer getValue() {
        return value;
    }
}
