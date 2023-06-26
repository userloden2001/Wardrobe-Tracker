package com.example.wardeobetrackerthird.brand;

import com.example.wardeobetrackerthird.category.Category;
import com.example.wardeobetrackerthird.category.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class BrandController {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public BrandController(BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/brands/new")
    public String showCreateNewBrandForm(Model model) {
        List<Category> listCategories= categoryRepository.findAll();
        model.addAttribute("brand",new Brand());
        model.addAttribute("listCategories",listCategories);
        return "brand_form";
    }
    @PostMapping("/brands/save")
    public String saveBrand(Brand brand) {
        brandRepository.save(brand);
        return "redirect:/brands";
    }
    @GetMapping("/brands")
    public String listBrands(Model model) {
        List<Brand> listBrands=brandRepository.findAll();
        model.addAttribute("listBrands",listBrands);
        return "brands";
    }
    @GetMapping("brands/edit/{id}")
    public String showEditBrandForm(@PathVariable("id") Integer id,Model model) {
        Brand brand = brandRepository.findById(id).get();
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute(brand);
        model.addAttribute("listCategories",listCategories);
        return "brand_form";
    }
    @GetMapping("brands/delete/{id}")
    public String DeleteProduct(@PathVariable("id") Integer id,Model model) {
        brandRepository.deleteById(id);
        return "redirect:/brands";
    }
}