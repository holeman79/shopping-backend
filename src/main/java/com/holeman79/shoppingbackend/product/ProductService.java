package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional
    public Product addProduct(Product product, MultipartFile file, MultipartFile[] detailFiles) throws Exception{
//        List<ProductDetailFile> productDetailFiles = new ArrayList<>();
//
//        // save 통해서 product 번호 생성
//        Product savedProduct = productRepository.save(product);
//        List<OptionGroupSpecification> optionList = savedProduct.getOptions();
//        for(OptionGroupSpecification option : optionList){
//            option.setProduct(savedProduct);
//        }
//
//        String directory = UploadFileUtils.makeDirectoryByCategory(savedProduct);
//        String savedFileName = UploadFileUtils.uploadFile(file.getOriginalFilename(), file.getBytes(), directory);
//        ProductImageSpecification productFile = new ProductImageSpecification();
//        productFile.setDirectory(directory);
//        productFile.setOriginalFileName(file.getOriginalFilename());
//        productFile.setSavedFileName(savedFileName);
//        productFile.setFileSize(file.getSize());
//        productFile.setProduct(savedProduct);
//        productFile.setThumbnailSavedFileName(UploadFileUtils.makeThumbnail(directory, savedFileName, 500));
//        savedProduct.setProductFile(productFile);
//
//        for(MultipartFile detailFile : detailFiles){
//            savedFileName = UploadFileUtils.uploadFile(detailFile.getOriginalFilename(), detailFile.getBytes(), directory);
//            ProductDetailFile productDetailFile = new ProductDetailFile();
//            productDetailFile.setDirectory(directory);
//            productDetailFile.setOriginalFileName(detailFile.getOriginalFilename());
//            productDetailFile.setSavedFileName(savedFileName);
//            productDetailFile.setFileSize(detailFile.getSize());
//            productDetailFile.setProduct(savedProduct);
//            productDetailFiles.add(productDetailFile);
//        }
//        savedProduct.setProductDetailFiles(productDetailFiles);
        return null;
    }

    public Product getProduct(Long id){
        Product product = productRepository.getOne(id);
        return product;
    }

    public Page<Product> getProductList(Pageable pageable, String categoryCode){
        pageable = getPageable(pageable);
        if(categoryCode == null) return productRepository.findAll(pageable);
        return null;
        //return productRepository.findByCategoryCode(pageable, categoryCode);
    }

    private Pageable getPageable(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize()-1);
        return pageable;
    }

//    static class CompareSizeAsc implements Comparator<OptionGroupSpecification> {
//        @Override
//        public int compare(OptionGroupSpecification o1, OptionGroupSpecification o2) {
//            return o1.getSize().getSizeOrder() < o2.getSize().getSizeOrder() ? -1 : o1.getSize().getSizeOrder() > o2.getSize().getSizeOrder() ? 1:0;
//        }
//    }

}
