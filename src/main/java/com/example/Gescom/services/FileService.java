package com.example.Gescom.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService{

    public String uploadFile(String path, MultipartFile file) throws IOException {


        // obtenir le nom du fichier
        String originalFileName= file.getOriginalFilename();

//        assert originalFileName != null;
        originalFileName=originalFileName.replaceAll("[^a-zA-Z0-9\\.\\-_]","_");
        String fileName = UUID.randomUUID()+"_"+Paths.get(originalFileName).getFileName().toString();

        //  pour obtenir le chemin du fichier
        String filePath = path + File.separator + fileName;

        // créer un objet fichier
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        // Copiez le fichier ou téléchargez le fichier sur le chemin
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);



        return fileName;
    }


    public InputStream getRessourceFile(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;

        return  new FileInputStream(filePath);


    }
}
