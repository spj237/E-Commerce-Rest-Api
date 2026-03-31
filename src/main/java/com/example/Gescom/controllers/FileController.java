package com.example.Gescom.controllers;


import com.example.Gescom.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {


    private  final FileService fileService;

    @Value("${poster}")
    private  String path;



    public FileController(FileService fileService) {
        this.fileService = fileService;

    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {

        // Vérification de la taille du fichier (2 Mo = 2 * 1024 * 1024 octets)
        if (file.getSize() > 10 * 1024 * 1024) {
            return new ResponseEntity<>(new Error("l'image ne doit pas depasse 10mo"), HttpStatus.BAD_REQUEST);

        }
        String uploadedFileName = fileService.uploadFile(path, file);
        return ResponseEntity.ok("File uploaded : " + uploadedFileName);
    }

    @GetMapping("/{fileName}")
    public  void serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        InputStream resourceFile = fileService.getRessourceFile(path, fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }





}
