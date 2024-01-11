package com.pfe.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pfe.backend.model.Document;
import com.pfe.backend.repository.DocumentRepository;
import com.pfe.backend.service.DocumentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Document")

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/addDocument")
    public ResponseEntity<String> uploadDocument(@RequestBody Document document) {
        try {
            Document savedDocument = documentService.uploadDocument(document);
            return ResponseEntity.status(HttpStatus.CREATED).body("Added document with id: " + savedDocument.getId_document());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Document>> getDocuments() {
        try {
            List<Document> documents = documentService.getAllDocuments();
            return ResponseEntity.ok(documents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable int id) {
        try {
            documentService.deleteDocument(id);
            return ResponseEntity.ok("Document deleted with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/changeDocumentStatus/{id}")
    public ResponseEntity<String> changeDocumentStatus(@PathVariable int id, @RequestBody Document document) {
        try {
            Document updatedDocument = documentService.changeDocumentStatus(id, document);
            if (updatedDocument != null) {
                return ResponseEntity.ok("Document updated with id: " + id);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/doc/{entrepriseId}")
    public List<Document> getDocumentsByEntreprise(@PathVariable("entrepriseId") Long entrepriseId) {
        return documentService.getDocumentsByEntreprise(entrepriseId);
    }
}
