package com.example.plans.iotdatahub.repositories;

import com.example.plans.sharedhub.models.IoTDeviceData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IoTDataRepository extends MongoRepository<IoTDeviceData, String> {
    List<IoTDeviceData> findByEui(String deviceId);
}
