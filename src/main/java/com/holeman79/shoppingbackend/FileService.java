package com.holeman79.shoppingbackend;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class FileService {
    @Value("${property.uploadPath}")
    private String uploadPath;

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

    public void uploadProductFile(MultipartFile[] productFiles) throws Exception{
        for(MultipartFile file : productFiles){
            uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
        }
    }
    public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        UUID uid = UUID.randomUUID();

        //원본파일 이름과 UUID 결합
        String savedName = uid.toString() + "_" + originalName;

        //파일을 저장할 폴더 생성(년 월 일 기준)
        String savedPath = calcPath(uploadPath);

        //저장할 파일준비
        File target = new File(uploadPath + savedPath, savedName);

        //파일을 저장
        FileCopyUtils.copy(fileData, target);

        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);

        String uploadedFileName = null;

        //파일의 확장자에 따라 썸네일(이미지일경우) 또는 아이콘을 생성함.
        if(MediaUtils.getMediaType(formatName) != null) {
            uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
        }else {
            uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
        }

        //uploadedFileName는 썸네일명으로 화면에 전달된다.
        return uploadedFileName;
    }//

    //폴더 생성 함수
    @SuppressWarnings("unused")
    private static String calcPath(String uploadPath) {

        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator + cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        makeDir(uploadPath, yearPath, monthPath, datePath);

        return datePath;
    }

    //폴더 생성 함수
    @SuppressWarnings("unused")
    private static String categoryPath(String uploadPath) {

        String imagesPath = File.separator + "images";
        String productsPath = imagesPath + File.separator + "products";
        makeDir(uploadPath, imagesPath, productsPath);

        return productsPath;
    }

    //폴더 생성 함수
    private static void makeDir(String uploadPath, String... paths) {

        if(new File(uploadPath + paths[paths.length -1]).exists()) {
            return;
        }//if

        for(String path : paths) {

            File dirPath = new File(uploadPath + path);

            if(!dirPath.exists()) {
                dirPath.mkdir();
            }//if

        }//for

    }//makeDir

    private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
        String iconName = uploadPath + path + File.separator + fileName;
        return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    //썸네일 이미지 생성
    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

        File newFile = new File(thumbnailName);
        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

        ImageIO.write(destImg, formatName.toUpperCase(), newFile);
        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

}
