package com.holeman79.shoppingbackend.product;

import com.holeman79.shoppingbackend.FileService;
import com.holeman79.shoppingbackend.common.CommonCode;
import com.holeman79.shoppingbackend.common.CommonCodeService;
import com.holeman79.shoppingbackend.payload.ProductRequest;
import com.holeman79.shoppingbackend.product.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final static Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @Autowired
    FileService fileService;

    @Autowired
    CommonCodeService commonCodeService;

    @GetMapping("/commonCode")
    public ResponseEntity<List<List<CommonCode>>> getAllProductCommonCodeList(){
        return new ResponseEntity<List<List<CommonCode>>>(commonCodeService.getAllProductCommonCodeList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product, @RequestPart("productFiles") MultipartFile[] productFiles) throws Exception{
        fileService.uploadProductFile(productFiles);

        Product savedProduct = productService.addProduct(product);


        Product getProduct = productService.getProduct(savedProduct.getId());
        if(savedProduct != null)
            return new ResponseEntity("{}", HttpStatus.OK);
        return new ResponseEntity("{}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/test4")
    public ResponseEntity<?> addProductTest(ProductRequest productRequest){
        ResponseEntity result = new ResponseEntity("{}", HttpStatus.OK);
        return result;
    }

    @PostMapping("/test")
    public ResponseEntity<?> createProduct(@RequestParam(value="files") List<MultipartFile> files){
        ResponseEntity result = new ResponseEntity("{}", HttpStatus.OK);
        for(MultipartFile file : files){
            String uploadResult = fileService.saveImage(file);
            log.info(uploadResult);
        }
        return result;
    }
    @PostMapping("/test2")
    public ResponseEntity<?> createProduct222(ProductRequest product){
        ResponseEntity result = new ResponseEntity("{}", HttpStatus.OK);
        String ApplicationPath =
                ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/resources/static/images/");
//        for(MultipartFile tempFile : files){
//            System.out.println(tempFile);
//
//        }
        return result;
    }
//MultipartHttpServletRequest
    @PostMapping("/test3")
    public ResponseEntity<?> createProductTest(@RequestBody ProductRequest product){
        ResponseEntity result = new ResponseEntity("{}", HttpStatus.OK);
//        for(MultipartFile tempFile : files){
//            System.out.println(tempFile);
//
//        }
//        List<MultipartFile> fileList = files.getFiles("file");
//        for (MultipartFile mf : fileList) {
//
//        }

        return result;
    }
}
