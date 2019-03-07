package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.common.file.*;
import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductFileRepository productFileRepository;

    @Autowired
    ProductDetailFileRepository productDetailFileRepository;

    @Value("${property.uploadPath}")
    private String uploadPath;

    @Transactional
    public Product addProduct(Product product, MultipartFile file, MultipartFile[] detailFiles) throws Exception{

        Product savedProduct = productRepository.save(product);
        String directory = UploadFileUtils.makeDirectoryByCategory(uploadPath, product);
        String savedFileName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes(), directory);
        ProductFile productFile = new ProductFile();
        productFile.setUploadPath(uploadPath);
        productFile.setDirectory(directory);
        productFile.setOriginalFileName(file.getOriginalFilename());
        productFile.setSavedFileName(savedFileName);
        productFile.setFileSize(file.getSize());
        //productFile.setProduct(savedProduct);
        productFile.setThumbnailSavedFileName(UploadFileUtils.makeThumbnail(uploadPath, directory, savedFileName));
        productFileRepository.save(productFile);
        for(MultipartFile detailFile : detailFiles){
            savedFileName = UploadFileUtils.uploadFile(uploadPath, detailFile.getOriginalFilename(), detailFile.getBytes(), directory);
            ProductDetailFile productDetailFile = new ProductDetailFile();
            productDetailFile.setUploadPath(uploadPath);
            productDetailFile.setDirectory(directory);
            productDetailFile.setOriginalFileName(detailFile.getOriginalFilename());
            productDetailFile.setSavedFileName(savedFileName);
            productDetailFile.setFileSize(detailFile.getSize());
            productDetailFile.setProductId(savedProduct.getId());

            productDetailFileRepository.save(productDetailFile);
        }
        return savedProduct;
    }

    public Product getProduct(Long id){
        return productRepository.getOne(id);
    }
}
