package com.example.plans.devicehub.repositories;

import com.example.plans.sharedhub.models.IoTDevice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends MongoRepository<IoTDevice, String> {
}
