package top.sxmeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.sxmeng.enums.ExpressStatus;

@RestController
@RequestMapping("/express")
public class ExpressController {
    @GetMapping("/{status}")
    public String checkExpressInfo(@PathVariable ExpressStatus status) {
        return "当前快递状态：" + status.getLabel();
    }

}
