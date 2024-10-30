package com.projeto.repository;

import com.projeto.domain.entity.HttpLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HttpLogRepository extends MongoRepository<HttpLog, String> {
}
