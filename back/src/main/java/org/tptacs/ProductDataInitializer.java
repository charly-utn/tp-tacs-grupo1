package org.tptacs;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.tptacs.domain.entities.Product;
import org.tptacs.infraestructure.repositories.ProductRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class ProductDataInitializer implements CommandLineRunner {
	
    private final ProductRepository productRepository;

    @Autowired
    public ProductDataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Product> products = objectMapper.readValue(new File(getRepositoryPath("ProductsData.json")), new TypeReference<List<Product>>() {});
            productRepository.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getRepositoryPath(String name) {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(),"src", "main", "resources", "data", "mocks", name);
        return filePath.toString();
    }

}
