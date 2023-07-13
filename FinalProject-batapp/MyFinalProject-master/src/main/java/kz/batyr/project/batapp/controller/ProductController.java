package kz.batyr.project.batapp.controller;

import kz.batyr.project.batapp.model.Category;
import kz.batyr.project.batapp.model.Product;
import kz.batyr.project.batapp.service.CategoryService;
import kz.batyr.project.batapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final CategoryService categoryService;

  @GetMapping
  public String productPage(Model model){
    model.addAttribute("products", productService.getProducts());
    return "marketPage";
  }

  @PostMapping(value = "/add-product")
  public String addProduct(Product product, Model model){
    productService.addProduct(product);
    model.addAttribute("categories", categoryService.getCategories());
    return "redirect:/seller-panel";
  }

  @GetMapping(value = "{id}")
  public String productDetails(@PathVariable(name = "id") Long id, Model model){
    model.addAttribute("product", productService.getProduct(id));
    model.addAttribute("categories", categoryService.getCategories());
    return "productDetails";
  }

  @PostMapping(value = "/update-product")
  public String updateProduct(Product product, @RequestParam(name = "id") Long id){
   productService.updateProduct(product);
   return "redirect:/products/" + id;
  }

  @PostMapping(value = "/delete-product")
  public String deleteProduct(@RequestParam(name = "id") Long id){
    productService.deleteProduct(id);
    return "redirect:/products";
  }

  @PostMapping(value = "/add-category")
  public String addCategory(Category category){
    categoryService.addCategory(category);
    return "redirect:/admin-panel";
  }

  @PostMapping(value = "/delete-category")
  public String deleteCategory(@RequestParam(name = "id") Long id){
    categoryService.deleteCategory(id);
    return "redirect:/admin-panel";
  }

}
