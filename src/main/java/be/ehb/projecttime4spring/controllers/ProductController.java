package be.ehb.projecttime4spring.controllers;

import be.ehb.projecttime4spring.model.DAO.ProductDAO;
import be.ehb.projecttime4spring.model.DAO.VerkoperDAO;
import be.ehb.projecttime4spring.model.entities.Product;
import be.ehb.projecttime4spring.model.entities.Verkoper;
import jakarta.validation.Valid;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.commons.collections4.IterableUtils.size;

@Controller
public class ProductController {

    private final ProductDAO mProductDAO;
    private final VerkoperDAO mVerkoperDAO;

    @Autowired
    public ProductController(ProductDAO mProductDAO, VerkoperDAO mVerkoperDAO) {
        this.mProductDAO = mProductDAO;
        this.mVerkoperDAO = mVerkoperDAO;
    }

    @ModelAttribute("products")
    public Iterable<Product> getAllProducts() {
        return mProductDAO.findAll();
    }

    @GetMapping({"/","/index"})
    public String displayIndexPage(Model model) {
        Iterable<Product> productList = mProductDAO.findAll();
        model.addAttribute("products", productList);
        return "index";
    }

    @ModelAttribute("verkopers")
    public List<String> getAllVerkopers() {
        return mVerkoperDAO.findAllVerkopersNamen();
    }


    @GetMapping("/search")
    public String displayIndexSearch(Product product, Model model, String keyword) {

        System.out.println(keyword);
        if (!keyword.equals("")) {
            Iterable<Product> productList = mProductDAO.findProductByNaamContaining(keyword);
            System.out.println(productList.toString());
            model.addAttribute("searchInfo", size(productList) + " resulaten voor '" + keyword + "'");
            model.addAttribute("products", productList);
        } else {
            return "redirect:/index";
        }
        return "index";
    }


    @GetMapping("/addproduct")
    public String displayAddProductPage(Model model) {
        return "addProduct";
    }

    //pass drink to form data
    @ModelAttribute("newProductPOST")
    public Product productForForm() {
        return new Product();
    }

    //PostMethods
    @PostMapping("/addproduct")
    public String insertProduct(@Valid @ModelAttribute("newProductPOST") Product newProduct,
                                BindingResult result,
                                Model model) {
        //Voeg error toe indien de verkoper niet gevonden is:
        if ((newProduct.getProductVerkoper() == null) || !mVerkoperDAO.existsById(newProduct.getProductVerkoper().getNaam())) {
            result.rejectValue("productVerkoper.naam", "verkoperNotFound", "Verkoper niet gevonden ");
        }

        System.out.println("Errors=" + result.hasErrors());
        if (result.hasErrors()) {
            return "/addProduct";
        }

        //indien de meegegeven verkoper niet zou bestaan:


        mProductDAO.save(newProduct);

        return "redirect:/index";
    }

    @GetMapping("/products/{id}")
    public String displayProductInformationPage(Model model,
                                                @PathVariable int id) {
        //kijk na of product bestaat
        if(mProductDAO.existsById(id)) {
            model.addAttribute("product", mProductDAO.findById(id).get());
        } else model.addAttribute("errorMsg", "No product with id " + id + " available");

        return "productDetails";
    }

    @GetMapping("/about")
    public String displayAboutPage() {
        return "about";
    }

}
