package com.holeman79.shoppingbackend.file;

import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.domain.ProductImage;
import com.holeman79.shoppingbackend.product.domain.ProductImageGroup;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
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

    private static String applicationName;
    private static String defaultPath = System.getProperty("user.home");

    @Value("${imagesPath}")
    private void setImagesPath(String applicationName){
        this.applicationName = applicationName;
    }

    public static void uploadProductImages(Product product) throws IOException {
        for(ProductImageGroup productImageGroup : product.getProductImageGroups()){
            String directory = new StringBuilder().append(defaultPath)
                                                .append(File.separator)
                                                .append(applicationName)
                                                .append(File.separator)
                                                .append(product.getCategory())
                                                .append(File.separator)
                                                .append(product.getId()).append('.').append(product.getName())
                                                .append(File.separator)
                                                .append(productImageGroup.getName())
                                                .toString();
            Path path = makeDirectory(directory);
            for(ProductImage productImage : productImageGroup.getProductImages()){
                uploadFile(path, productImage.getImage());
            }
        }
    }

    private static Path makeDirectory(String directory) throws IOException {
        Path path = Paths.get(directory);
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxr-x---");
        FileAttribute<Set<PosixFilePermission>> attribute = PosixFilePermissions.asFileAttribute(permissions);
        return Files.createDirectories(path, attribute);
    }

    private static void uploadFile(Path path, MultipartFile file) throws IOException {
        File target = new File(path.toString(), file.getOriginalFilename());
        FileCopyUtils.copy(file.getBytes(), target);
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
