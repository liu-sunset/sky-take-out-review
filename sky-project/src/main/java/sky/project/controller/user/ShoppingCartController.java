package sky.project.controller.user;

import dto.AddShoppingCartDTO;
import entity.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.ShoppingCartService;
import utils.BaseContext;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result addCartController(@RequestBody AddShoppingCartDTO addShoppingCartDTO){
        log.info("添加购物车的商品:{}",addShoppingCartDTO);
        shoppingCartService.addCartService(addShoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result lookCartController(){
        log.info("用户{}查看购物车", BaseContext.getId());
        List<ShoppingCart> shoppingCartList = shoppingCartService.lookCartService();
        return Result.success(shoppingCartList);
    }

    @PostMapping("/sub")
    public Result subCartController(@RequestBody AddShoppingCartDTO addShoppingCartDTO){
        log.info("减少的商品:{}",addShoppingCartDTO);
        shoppingCartService.subCartService(addShoppingCartDTO);
        return Result.success();
    }

    @DeleteMapping("/clean")
    public Result cleanCartController(){
        log.info("用户：{}清空了购物车",BaseContext.getId());
        shoppingCartService.cleanCartService();
        return Result.success();
    }
}
