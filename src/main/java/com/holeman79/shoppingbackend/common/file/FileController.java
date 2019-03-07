package com.holeman79.shoppingbackend.common.file;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class FileController {
    @Value("${property.uploadPath}")
    private String uploadPath;

    @GetMapping
    public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
            MediaType mType = MediaUtils.getMediaType(formatName);
            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream(uploadPath + fileName);

            if(mType != null) {
                headers.setContentType(mType);
            }else {
                fileName = fileName.substring(fileName.indexOf("_")+1);
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            }
            entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }
        return entity;
    }
}
