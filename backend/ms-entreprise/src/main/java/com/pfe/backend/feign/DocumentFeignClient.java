package com.pfe.backend.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pfe.backend.feign.Document;


@FeignClient(name = "ms-document",url = "http://localhost:9191/documents")

public interface DocumentFeignClient {

    @GetMapping("/doc/{entrepriseId}")
    List<Document> getDocumentsByEntreprise(@PathVariable("entrepriseId") Long entrepriseId);
    
}
