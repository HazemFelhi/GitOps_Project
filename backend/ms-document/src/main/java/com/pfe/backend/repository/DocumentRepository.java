package com.pfe.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pfe.backend.model.Document;

@Repository
public interface DocumentRepository extends MongoRepository <Document, Integer>{
    List<Document> findByEntrepriseId(Long entrepriseId);
}
