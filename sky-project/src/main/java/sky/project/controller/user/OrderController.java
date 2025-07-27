package sky.project.controller.user;

import dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;
import sky.project.service.OrderService;

@Slf4j
@RestController
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Result addOrderController(@RequestBody OrderDTO orderDTO){
        log.info("用户下单：{}",orderDTO);
        orderService.addOrderService(orderDTO);
        return Result.success();
    }

}
