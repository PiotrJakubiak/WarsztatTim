package pl.edu.wat.tim.webstore.controller;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wat.tim.webstore.model.Product;
import pl.edu.wat.tim.webstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Piotr on 09.05.2017.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static String UPLOADED_FOLDER = "D:\\Uczelnia\\Studia magisterskie\\TIM-Warsztat\\springboot-jsp-master\\src\\main\\webapp\\resources\\images";

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/all")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("products", productService.getAllProducts());
        modelAndView.setViewName("products");
        return modelAndView;
    }

    @RequestMapping("/product")
    public String getProductById(Model model, @RequestParam("id") String productId) {
        Product product = productService.getProductById(Integer.valueOf(productId));
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddNewProductForm(@ModelAttribute("newProduct") Product newProduct) {
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String processAddNewProductForm(@ModelAttribute("newProduct") Product productToBeAdded, BindingResult result, HttpServletRequest request) {

        if(result.hasErrors()) {
            return "addProduct";
        }


        String[] suppressedFields = result.getSuppressedFields();

        if (suppressedFields.length > 0) {
            throw new RuntimeException("Proba wiazania niedozwolonych pol: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }


        MultipartFile productImage = productToBeAdded.getProductImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (productImage!=null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory+"resources\\images\\"+productToBeAdded.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Pr�ba zapisu obrazka zako�czona niepowodzeniem", e);
            }
        }


        productService.addProduct(productToBeAdded);
        return "redirect:/products";
    }
}
