package com.pdf.pdfeditor.service;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class WordDocEditorImpl implements WordDocEditor {

    @Override
    public ResponseEntity<?> editWordDocs(MultipartFile file) {

        try {
            // Convert MultipartFile to InputStream
            InputStream inputStream = file.getInputStream();

            // Create a temporary file and copy the content of the InputStream to it
            Path tempFile = Files.createTempFile("temp", ".docx");
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Load the Word document from the temporary file
            Document document = new Document();
            document.loadFromFile(tempFile.toString());

            // Replace different words with different words
            document.replace("Lorem", "Vikky", true, true);
            document.replace("@Ship_no@", "John Doe", true, true);
            document.replace("@Company@", "New York", true, true);

            // Save the modified document to the resources folder
            Path outputFile = Paths.get("src/main/resources/modify.docx");
            document.saveToFile(outputFile.toString(), FileFormat.Docx);
            document.close();

            // Delete the temporary file
            Files.deleteIfExists(tempFile);

            System.out.println("Word document edited successfully.");

            return new ResponseEntity<>("Word document edited successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error editing Word document.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
