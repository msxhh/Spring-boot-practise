package top.sxmeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxmeng.enums.DrinkType;

@RestController
@RequestMapping("/drinks")
public class DrinkController {
    @GetMapping("/{type}")
    public String getDrink(@PathVariable DrinkType type){
        return "你点的是：" + type.getLabel() + "，价格："+type.getPrice();
    }
}
