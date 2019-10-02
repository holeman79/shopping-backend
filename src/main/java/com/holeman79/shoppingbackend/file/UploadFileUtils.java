package com.holeman79.shoppingbackend.file;

import com.holeman79.shoppingbackend.product.domain.Product;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Component
public class UploadFileUtils {

    private static String imagesPath;
    private static String defaultPath = System.getProperty("user.home");

    @Value("${imagesPath}")
    private void setImagesPath(String imagesPath){
        this.imagesPath = imagesPath;
    }

    public static String uploadFile(String originalName, byte[] fileData, String directory) throws Exception {

        //겹쳐지지 않는 파일명을 위한 유니크한 값 생성
        UUID uid = UUID.randomUUID();

        //원본파일 이름과 UUID 결합
        String savedName = uid.toString() + "_" + originalName;

        if(directory == null)
            //파일을 저장할 폴더 생성(년 월 일 기준)
            directory = makeDirectoryByCalendar();

        //저장할 파일준비
        File target = new File(directory + File.separator, savedName);

        //파일을 저장
        FileCopyUtils.copy(fileData, target);

        String formatName = originalName.substring(originalName.lastIndexOf(".")+1);

        //파일의 확장자에 따라 썸네일(이미지일경우) 또는 아이콘을 생성함.
//        if(MediaUtils.getMediaType(formatName) != null) {
//            makeThumbnail(uploadPath, directory, savedName);
//        }
        return savedName;
    }

    private static String makeDirectoryByCalendar() throws IOException {
        LocalDate localDate = LocalDate.now();
        String datePath = defaultPath + File.separator + localDate.getYear() + new DecimalFormat("00").format(localDate.getMonthValue())
                + new DecimalFormat("00").format(localDate.getDayOfMonth());

        Set<PosixFilePermission> permis = PosixFilePermissions.fromString("rwxrwxr-x");
        // 파일 속성
        FileAttribute<Set<PosixFilePermission>> attrib= PosixFilePermissions.asFileAttribute(permis);
        Path path = Paths.get(datePath);
        Files.createDirectories(path, attrib);

        return datePath;
    }

    public static String makeDirectoryByCategory(Product product) throws IOException {
        String productsPath = defaultPath + File.separator + imagesPath;
        String categoryPath = productsPath + File.separator + product.getCategory().getCode();
        String productTitlePath = categoryPath + File.separator + product.getId() + "." + product.getTitle();

        // linux 이미지 저장방법1
        File targetFile = new File(productTitlePath);
        targetFile.setReadable(true, true);
        targetFile.setWritable(true, true);
        targetFile.setExecutable(true, true);
        boolean result = targetFile.mkdirs();

        // linux 이미지 저장방법2
//        // 디렉토리 권한 설정
//        Set<PosixFilePermission> permis = PosixFilePermissions.fromString("rwxrwxr-x");
////        // 파일 속성
//        FileAttribute<Set<PosixFilePermission>> attrib= PosixFilePermissions.asFileAttribute(permis);
//        Path path = Paths.get(productTitlePath);
//        Files.createDirectories(path, attrib);

        return productTitlePath + File.separator;
    }


    //썸네일 이미지 생성
    public static String makeThumbnail(String directory, String fileName, int size) throws Exception {

        BufferedImage sourceImg = ImageIO.read(new File(directory, fileName));
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, size);

        String thumbnailName = directory + File.separator + "Thumbnail_" + fileName;

        File newFile = new File(thumbnailName);
        String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

        ImageIO.write(destImg, formatName.toUpperCase(), newFile);
        return "Thumbnail_" + fileName;
    }
}
