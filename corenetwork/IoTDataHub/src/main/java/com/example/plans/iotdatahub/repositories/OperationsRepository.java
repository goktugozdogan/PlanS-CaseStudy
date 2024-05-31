package com.example.plans.iotdatahub.repositories;

import com.example.plans.sharedhub.models.OperationData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationsRepository extends MongoRepository<OperationData, String> {
}
