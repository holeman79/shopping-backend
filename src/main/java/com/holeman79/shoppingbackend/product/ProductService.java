package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.common.file.*;
import com.holeman79.shoppingbackend.product.domain.Option;
import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.domain.ProductDetailFile;
import com.holeman79.shoppingbackend.product.domain.ProductFile;
import com.holeman79.shoppingbackend.product.repository.ProductDetailFileRepository;
import com.holeman79.shoppingbackend.product.repository.ProductFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Value("${property.uploadPath}")
    private String uploadPath;

    @Autowired
    ProductFileRepository productFileRepository;

    @Autowired
    ProductDetailFileRepository productDetailFileRepository;

    @Transactional
    public Product addProduct(Product product, MultipartFile file, MultipartFile[] detailFiles) throws Exception{
        List<ProductDetailFile> productDetailFiles = new ArrayList<>();

        // save 통해서 product 번호 생성
        Product savedProduct = productRepository.save(product);
        String directory = UploadFileUtils.makeDirectoryByCategory(uploadPath, savedProduct);
        String savedFileName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes(), directory);
        ProductFile productFile = new ProductFile();
        productFile.setUploadPath(uploadPath);
        productFile.setDirectory(directory);
        productFile.setOriginalFileName(file.getOriginalFilename());
        productFile.setSavedFileName(savedFileName);
        productFile.setFileSize(file.getSize());
        productFile.setProduct(savedProduct);
        productFile.setThumbnailSavedFileName(UploadFileUtils.makeThumbnail(uploadPath, directory, savedFileName, 500));
        savedProduct.setProductFile(productFile);

        for(MultipartFile detailFile : detailFiles){
            savedFileName = UploadFileUtils.uploadFile(uploadPath, detailFile.getOriginalFilename(), detailFile.getBytes(), directory);
            ProductDetailFile productDetailFile = new ProductDetailFile();
            productDetailFile.setUploadPath(uploadPath);
            productDetailFile.setDirectory(directory);
            productDetailFile.setOriginalFileName(detailFile.getOriginalFilename());
            productDetailFile.setSavedFileName(savedFileName);
            productDetailFile.setFileSize(detailFile.getSize());
            //productDetailFile.setProductId(savedProduct.getId());
            productDetailFile.setProduct(savedProduct);
            productDetailFiles.add(productDetailFile);
            //productDetailFileRepository.save(productDetailFile);
        }
        savedProduct.setProductDetailFiles(productDetailFiles);
        savedProduct = productRepository.save(savedProduct);

        return savedProduct;
    }

    public Product getProduct(Long id){
        //List<CommonCode> commonCodeSizeList = commonCodeRepository.findByCommonCodeIdGroupCode("PROD003");
        Product product = productRepository.getOne(id);
//        List<Option> options = product.getOptions();
//        for(Option option : options){
//            for(CommonCode commonCode : commonCodeSizeList){
//                if(commonCode.getCodeValue().equals(option.getSize()))
//                    option.setSizeOrder(commonCode.getCommonCodeId().getCode());
//            }
//        }
//        Collections.sort(options, new CompareSizeAsc());
        List<Option> options = product.getOptions();
        Collections.sort(options, new CompareSizeAsc());
        return product;
    }

    public Page<Product> getProductList(Pageable pageable ){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
        return productRepository.findAll(pageable);
    }

    static class CompareSizeAsc implements Comparator<Option> {
        @Override
        public int compare(Option o1, Option o2) {
            return o1.getSize().getSizeOrder() < o2.getSize().getSizeOrder() ? -1 : o1.getSize().getSizeOrder() > o2.getSize().getSizeOrder() ? 1:0;
        }
    }

}
