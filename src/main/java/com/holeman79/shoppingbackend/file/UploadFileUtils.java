package com.holeman79.shoppingbackend.file;

import com.holeman79.shoppingbackend.product.domain.Product;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadFileUtils {
    public static String uploadFile(String uploadPath, String originalName, byte[] fileData, String directory) throws Exception {

        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        UUID uid = UUID.randomUUID();

        //원본파일 이름과 UUID 결합
        String savedName = uid.toString() + "_" + originalName;

        if(directory == null)
            //파일을 저장할 폴더 생성(년 월 일 기준)
            directory = makeDirectoryByCalendar(uploadPath);

        //저장할 파일준비
        File target = new File(uploadPath + directory, savedName);

        //파일을 저장
        FileCopyUtils.copy(fileData, target);

        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);

        //파일의 확장자에 따라 썸네일(이미지일경우) 또는 아이콘을 생성함.
//        if(MediaUtils.getMediaType(formatName) != null) {
//            makeThumbnail(uploadPath, directory, savedName);
//        }
        return savedName;
    }

    private static String makeDirectoryByCalendar(String uploadPath) {
        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator + cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath + File.separator;
    }

    public static String makeDirectoryByCategory(String uploadPath, Product product) {
        String imagesPath = File.separator + "images";
        String productsPath = imagesPath + File.separator + "products";
        String categoryPath = productsPath + File.separator + product.getCategory().getCode();
        String productTitlePath = categoryPath + File.separator + product.getId() + "." + product.getTitle();
        makeDir(uploadPath, imagesPath, productsPath, categoryPath, productTitlePath);

        return productTitlePath + File.separator;
    }

    //폴더 생성 함수
    private static void makeDir(String uploadPath, String... paths) {
        if(new File(uploadPath + paths[paths.length -1]).exists()) {
            return;
        }
        for(String path : paths) {
            File dirPath = new File(uploadPath + path);
            if(!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }

    //썸네일 이미지 생성
    public static String makeThumbnail(String uploadPath, String directory, String fileName, int size) throws Exception {

        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + directory, fileName));
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, size);

        String thumbnailName = uploadPath + directory + File.separator + "Thumbnail_" + fileName;

        File newFile = new File(thumbnailName);
        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

        ImageIO.write(destImg, formatName.toUpperCase(), newFile);
        //return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
        return "Thumbnail_" + fileName;
    }

    public static void fileCopy(String inFileName, String outFileName) {
        try {
            FileInputStream fis = new FileInputStream(inFileName);
            FileOutputStream fos = new FileOutputStream(outFileName);

            int data = 0;
            while((data=fis.read())!=-1) {
                fos.write(data);
            }
            fis.close();
            fos.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
