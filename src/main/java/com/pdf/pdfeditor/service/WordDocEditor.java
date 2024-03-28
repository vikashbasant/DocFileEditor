package com.pdf.pdfeditor.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public interface WordDocEditor {

    public ResponseEntity<?> editWordDocs(MultipartFile file);
}
