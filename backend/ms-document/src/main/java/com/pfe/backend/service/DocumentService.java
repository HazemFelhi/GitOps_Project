package com.pfe.backend.service;

import java.util.List;
import java.util.Optional;

import com.pfe.backend.model.Document;

public interface DocumentService {
    Document uploadDocument(Document document);
    List<Document> getAllDocuments();
    /* Optional<Document> getDocumentById(int id); */
    void deleteDocument(int id);
    Document changeDocumentStatus(int id, Document document);
    List<Document> getDocumentsByEntreprise(Long entrepriseId) ;

}

