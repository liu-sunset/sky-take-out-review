package sky.project.controller.user;

import dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.OrderService;
import vo.LookOrderVO;
import vo.OrderVO;

@Slf4j
@RestController
@RequestMapping("/user/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public Result addOrderController(@RequestBody OrderDTO orderDTO){
        log.info("用户下单：{}",orderDTO);
        OrderVO orderVO = orderService.addOrderService(orderDTO);
        return Result.success(orderVO);
    }

    @GetMapping("/orderDetail/{id}")
    public Result lookOrderDetailController(@PathVariable Long id){
        log.info("查看订单详情:{}",id);
        LookOrderVO lookOrderVO = orderService.lookOrderDetailService(id);
        return Result.success(lookOrderVO);
    }
}
