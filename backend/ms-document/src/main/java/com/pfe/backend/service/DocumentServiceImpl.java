package com.pfe.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.backend.model.Document;
import com.pfe.backend.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private  DocumentRepository repository;





    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }



     @Override
    public Document uploadDocument(Document document) {
        return repository.save(document);
    }

     @Override
    public List<Document> getAllDocuments() {
        return repository.findAll();
    }

   /* @Override
    public Optional<Document> getDocumentById(int id) {
        return repository.findById(id);
    }
*/
    @Override
    public void deleteDocument(int id) {
        repository.deleteById(id);
    }
    @Override
    public Document changeDocumentStatus(int id, Document document) {
        Optional<Document> optionalDocument = repository.findById(id);
        if (optionalDocument.isPresent()) {
            Document existingDocument = optionalDocument.get();
            existingDocument.setDescription(document.getDescription());
            return repository.save(existingDocument);
        } else {
            throw new NoSuchElementException("Document not found with id: " + id);
        }
    }
    @Override
    public List<Document> getDocumentsByEntreprise(Long entrepriseId) {
        return repository.findByEntrepriseId(entrepriseId);
    }

}
