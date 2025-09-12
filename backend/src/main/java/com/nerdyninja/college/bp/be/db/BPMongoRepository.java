package com.nerdyninja.college.bp.be.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface BPMongoRepository extends MongoRepository<BPMongoData, String> {
    Iterable<BPMongoData> findAllByEmail(@Param("email") String email);
}