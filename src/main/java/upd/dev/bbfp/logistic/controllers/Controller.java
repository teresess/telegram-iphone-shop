package upd.dev.bbfp.logistic.controllers;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import upd.dev.bbfp.logistic.model.Condition;
import upd.dev.bbfp.logistic.model.Product;
import upd.dev.bbfp.logistic.model.User;
import upd.dev.bbfp.logistic.service.ConditionService;
import upd.dev.bbfp.logistic.service.ProductService;
import upd.dev.bbfp.logistic.service.UserService;

import java.util.List;

@RestController
public class Controller {

    @Getter
    static Services services;
    private final ConditionService conditionService;
    private final UserService userService;
    private final ProductService productService;

    public Controller(ConditionService conditionService, UserService userService, ProductService productService) {
        this.conditionService = conditionService;
        this.userService = userService;
        this.productService = productService;
        services = new Services(conditionService, productService, userService);
    }

    @GetMapping("/cond/get/{id}")
    public String getCond(@PathVariable() Long id) {
        return conditionService.getCond(id);
    }

    @GetMapping("/cond/update")
    public String updateCond(@RequestParam Long id, @RequestParam String cond) {
        conditionService.updateCond(id, cond);
        return "Update done!";
    }

    @GetMapping("/cond/getall")
    public List<Condition> getAllCond() {
        return conditionService.getAllCond();
    }

    @GetMapping("/user/getall")
    private List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/get/{id}")
    public User getUser(@PathVariable() Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user/setadmin/{id}")
    public String setAdmin(@PathVariable() Long id) {
        userService.setAdmin(id);
        return "Done!";
    }

    @GetMapping("/prod/create")
    public Product createProduct(
            @RequestParam() String productId,
            @RequestParam() String productModel,
            @RequestParam() String productBio,
            @RequestParam() String color,
            @RequestParam() Long price,
            @RequestParam() Long ownerId,
            @RequestParam() boolean used) {

        return productService.createProduct(new Product(productId, productModel, productBio, color, price, ownerId, used));
    }

    @GetMapping("/prod/getall")
    public List<Product> getAllProducts() {
        return  productService.getAllProducts();
    }
}