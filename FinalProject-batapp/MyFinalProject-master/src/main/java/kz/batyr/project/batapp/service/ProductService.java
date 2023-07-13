package kz.batyr.project.batapp.service;

import kz.batyr.project.batapp.model.Product;
import kz.batyr.project.batapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public List<Product> getProducts(){
    return productRepository.findAll();
  }

  public Product addProduct(Product product){
    return productRepository.save(product);
  }

  public Product getProduct(Long id){
    return productRepository.findById(id).orElse(null);
  }

  public Product updateProduct(Product product){

    return productRepository.save(product);
  }

  public void deleteProduct(Long id){
    productRepository.deleteById(id);
  }


}
