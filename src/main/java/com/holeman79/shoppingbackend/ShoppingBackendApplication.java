package com.holeman79.shoppingbackend;

import com.holeman79.shoppingbackend.common.file.UploadFileUtils;
import com.holeman79.shoppingbackend.product.ProductRepository;
import com.holeman79.shoppingbackend.product.domain.Category;
import com.holeman79.shoppingbackend.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class ShoppingBackendApplication implements CommandLineRunner {
	@Value("${property.uploadPath}")
	private String uploadPath;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingBackendApplication.class, args);
		//ShoppingBackendApplication app = new ShoppingBackendApplication();
		//app.generateSampleData(context);



//		String directory = UploadFileUtils.makeDirectoryByCategory(uploadPath, product);
//		String savedFileName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes(), directory);
//		ProductFile productFile = new ProductFile();
//		productFile.setUploadPath(uploadPath);
//		productFile.setDirectory(directory);
//		productFile.setOriginalFileName(file.getOriginalFilename());
//		productFile.setSavedFileName(savedFileName);
//		productFile.setFileSize(file.getSize());
//		productFile.setProduct(savedProduct);
//		productFile.setThumbnailSavedFileName(UploadFileUtils.makeThumbnail(uploadPath, directory, savedFileName, 500));
//		savedProduct.setProductFile(productFile);

	}

	public void generateSampleData(ConfigurableApplicationContext context){
//		File file = new File("resources/static/knit1_type1.PNG");
//		File file2 = new File("static/knit1_type1.PNG");
//		File file3 = new File("no_image.jpg");

//		ClassLoader classLoader = getClass().getClassLoader();
//		File readFile = new File(classLoader.getResource("static/sample/knit/1/knit1_type1.PNG").getFile());
//
//		//System.out.println(testfile.getName() + " : " + testfile.getAbsolutePath());
//		ProductRepository productRepository = context.getBean(ProductRepository.class);
//		List<Product> productList = productRepository.findAll();
//		String filePath = uploadPath + productList.get(0).getProductFile().getDirectory() + productList.get(0).getProductFile().getOriginalFileName();
//		UploadFileUtils.fileCopy(classLoader.getResource("static/sample/knit/knit1_type1.PNG").getFile(), filePath);
//
//		UploadFileUtils.makeDirectoryByCategory(productList.get(0).getProductFile().getUploadPath(), productList.get(0));

//		for(Product product : productList){
//			UploadFileUtils.makeDirectoryByCategory(product.getProductFile().getUploadPath(), product);
//
//		}
	}


	@Override
	public void run(String... args) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		//File readFile = new File(classLoader.getResource("static/sample/knit/1/knit1_type1.PNG").getFile());

		//System.out.println(testfile.getName() + " : " + testfile.getAbsolutePath());
		//ProductRepository productRepository = context.getBean(ProductRepository.class);
		Category category = new Category("outer", "아우터");
		List<Product> productList = productRepository.findByCategory(category);

		for(int i = 0; i < productList.size(); i++){
			String imagePathFrom = "static/sample";
			Product product = productList.get(i);
			imagePathFrom = imagePathFrom + "/" + product.getCategory().getCode() + "/" + (i+1) + "/" + product.getProductFile().getSavedFileName();
			File readFile = new File(classLoader.getResource(imagePathFrom).getFile());

			String filePath = uploadPath + product.getProductFile().getDirectory() + product.getProductFile().getSavedFileName();
			UploadFileUtils.makeDirectoryByCategory(uploadPath, product);
			UploadFileUtils.fileCopy(classLoader.getResource(imagePathFrom).getFile(), filePath);
		}


		//String filePath = uploadPath + productList.get(0).getProductFile().getDirectory() + productList.get(0).getProductFile().getOriginalFileName();
		//UploadFileUtils.fileCopy(classLoader.getResource("static/sample/knit/knit1_type1.PNG").getFile(), filePath);
		//UploadFileUtils.makeDirectoryByCategory(productList.get(0).getProductFile().getUploadPath(), productList.get(0));
	}
}

