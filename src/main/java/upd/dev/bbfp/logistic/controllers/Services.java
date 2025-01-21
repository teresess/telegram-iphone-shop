package upd.dev.bbfp.logistic.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import upd.dev.bbfp.logistic.service.ConditionService;
import upd.dev.bbfp.logistic.service.ProductService;
import upd.dev.bbfp.logistic.service.UserService;

@Getter
@AllArgsConstructor
public class Services {
    ConditionService conditionService;
    ProductService productService;
    UserService userService;
}