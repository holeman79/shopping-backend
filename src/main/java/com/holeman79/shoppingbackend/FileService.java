package com.holeman79.shoppingbackend;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    public String saveImage(MultipartFile file){
        if (file.isEmpty()) {
            return "is empty";
        }
        try {
            System.out.println("File name=" + file.getOriginalFilename() + " size=" + file.getSize());
            String path = "C:\\upload\\";

            File dir = new File(path);
            if(!dir.isDirectory()){
                dir.mkdir();
            }
            //+ System.currentTimeMillis() + "_"
            //  방법1
            String newFile = path + file.getOriginalFilename();
            file.transferTo(new File(newFile));

            // 방법2
            // Get the file and save it somewhere
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get("C:\\upload\\" + file.getOriginalFilename());
//            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getOriginalFilename() + "success";
    }

}
