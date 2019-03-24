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
public class ShoppingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingBackendApplication.class, args);
	}
}

