package com.example.DSA.repository;

import com.example.DSA.entity.AlarmData;
import com.example.DSA.entity.AlarmData;
import com.example.DSA.model.AlarmModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmModelRepository extends JpaRepository<AlarmData, Long> {
}
