package com.pdf.pdfeditor.controller;

import com.pdf.pdfeditor.service.WordDocEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EditorController {


    @Autowired
    private WordDocEditor wEditor;


    @PostMapping("/edit")
    public ResponseEntity<?> editDocs(@RequestParam(name = "file") MultipartFile file) {
        return wEditor.editWordDocs(file);

    }
}
