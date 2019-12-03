package com.holeman79.shoppingbackend.product.service;

import com.holeman79.shoppingbackend.file.UploadFileUtils;
import com.holeman79.shoppingbackend.product.domain.Product;
import com.holeman79.shoppingbackend.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(Product product) throws IOException {
        Product savedProduct = productRepository.save(product);
        UploadFileUtils.uploadProductImages(savedProduct);
        return savedProduct;
    }

    public Product getProduct(Long id){
        Product product = productRepository.getOne(id);
        product.setSavedUri();
        product.setValue();
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
