package com.pfe.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.pfe.backend.model.Document;
import com.pfe.backend.repository.DocumentRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootTest
class MsDocumentApplicationTests {
	@Autowired
    private DocumentRepository repo;

    @Test
    void testAddNew() {
        Document document = new Document();
        document.setDescription("hello mongoDBB");

        Document savedDocument = repo.save(document);

        Assertions.assertThat(savedDocument).isNotNull();
        Assertions.assertThat(savedDocument.getId_document()).isNotNull();
    }

    @Test
    void testListAll() {
        Iterable<Document> documents = repo.findAll();
        Assertions.assertThat(documents).hasSizeGreaterThan(0);

        for (Document document : documents) {
            System.out.println(document);
        }
    }

    @Test
    void testUpdate() {
        int documentId = 0;
        Optional<Document> optionalDocument = repo.findById(documentId);
        Document document = optionalDocument.get();
        document.setDescription("hgfd");
        repo.save(document);

        Document updatedDocument = repo.findById(documentId).get();
        Assertions.assertThat(updatedDocument.getDescription()).isEqualTo("hgfd");
    }

    @Test
    void testGet() {
        int documentId = 0;
        Optional<Document> optionalDocument = repo.findById(documentId);
        Assertions.assertThat(optionalDocument).isPresent();
        System.out.println(optionalDocument.get());
    }

    @Test
    void testDelete() {
        int documentId = 0;
        repo.deleteById(documentId);

        Optional<Document> optionalDocument = repo.findById(documentId);
        Assertions.assertThat(optionalDocument).isNotPresent();
    }
}
