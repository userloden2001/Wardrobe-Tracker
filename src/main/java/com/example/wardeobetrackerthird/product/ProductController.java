package com.example.wardeobetrackerthird.product;
import com.example.wardeobetrackerthird.category.Category;
import com.example.wardeobetrackerthird.category.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/products")
    public String listCategories(Model model) {
        List<Product> listProducts = productRepository.findAll();
        model.addAttribute("listProducts",listProducts);
        return "products";
    }
    @GetMapping("/products/new")
    public String showCategoryNewForm(Model model) {
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute("product",new Product());
        model.addAttribute("listCategories",listCategories);
        return "product_form";
    }
    @PostMapping("/products/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            List<Category> listCategories = categoryRepository.findAll();
            request.setAttribute("listCategories", listCategories);
            return "product_form";
        }
        String[] detailIDs = request.getParameterValues("detailID");
        String[] detailNames = request.getParameterValues("detailName");
        String[] detailValues = request.getParameterValues("detailValue");

        for (int i=0;i<detailNames.length;i++) {
            if(detailIDs != null && detailIDs.length>0) {
                product.setDetail(Integer.valueOf(detailIDs[i]), detailNames[i], detailValues[i]);
            } else {
                product.addDetail(detailNames[i], detailValues[i]);
            }
        }
        productRepository.save(product);
        return "redirect:/products";
    }
    @GetMapping("products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Integer id,Model model) {
        Product product = productRepository.findById(id).get();
        List<Category> listCategories = categoryRepository.findAll();
        model.addAttribute(product);
        model.addAttribute("listCategories",listCategories);
        return "product_form";
    }
    @GetMapping("products/delete/{id}")
    public String DeleteProduct(@PathVariable("id") Integer id,Model model) {
        productRepository.deleteById(id);
        return "redirect:/products";
    }
        @ExceptionHandler(MethodArgumentNotValidException.class)    ///pomoc chatu gpt
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public String handleValidationExceptions(MethodArgumentNotValidException ex) {
            StringBuilder errorMessage = new StringBuilder();
            BindingResult result = ex.getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            for (FieldError error : fieldErrors) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(". ");
            }
            return errorMessage.toString();
    }
}