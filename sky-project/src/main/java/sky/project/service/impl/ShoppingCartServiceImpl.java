package sky.project.service.impl;

import dto.AddShoppingCartDTO;
import entity.Dish;
import entity.Setmeal;
import entity.ShoppingCart;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.project.mapper.DishMapper;
import sky.project.mapper.SetmealMapper;
import sky.project.mapper.ShoppingCartMapper;
import sky.project.service.CategoryService;
import sky.project.service.DishService;
import sky.project.service.SetmealService;
import sky.project.service.ShoppingCartService;
import utils.BaseContext;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;
    //添加购物车
    @Override
    public void addCartService(AddShoppingCartDTO addShoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(addShoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(BaseContext.getId());
        //查询数据库判断是否此菜品已经存在
        ShoppingCart cart = shoppingCartMapper.selectDishOrSetmealMapper(shoppingCart);
        //如果菜品不存在,添加菜品
        if(cart==null){
            //查询商品名称
            if(shoppingCart.getDishId()!=null){
                Dish dish = dishMapper.selectDishByIdService(shoppingCart.getDishId());
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getName());
                shoppingCart.setNumber(1);
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setCreateTime(LocalDateTime.now());
            }else {
                Setmeal setmeal = setmealMapper.selectByIdMapper(shoppingCart.getSetmealId());
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setImage(setmeal.getImage());
                shoppingCart.setNumber(1);
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setCreateTime(LocalDateTime.now());
            }
            shoppingCartMapper.addCartMapper(shoppingCart);
        }else {
            cart.setNumber(cart.getNumber()+1);
            shoppingCartMapper.modifyCartMapper(cart);
        }
    }

    //查看购物车
    @Override
    public List<ShoppingCart> lookCartService() {
        return shoppingCartMapper.lookCartMapper(BaseContext.getId());
    }

    //删除某个商品
    @Override
    public void subCartService(AddShoppingCartDTO addShoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(addShoppingCartDTO,shoppingCart);
        shoppingCart.setUserId(BaseContext.getId());
        //查看数据库，看是否数量是1
        ShoppingCart shoppingCart1 = shoppingCartMapper.selectDishOrSetmealMapper(shoppingCart);
        //如果数量是一
        if(shoppingCart1.getNumber()==1){
            shoppingCartMapper.deleteCartMapper(shoppingCart1);
        }else {
            //如果数量不是一
            shoppingCart1.setNumber(shoppingCart1.getNumber()-1);
            shoppingCartMapper.modifyCartMapper(shoppingCart1);
        }
    }

    //清空购物车
    @Override
    public void cleanCartService() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getId())
                .build();
        shoppingCartMapper.deleteCartMapper(shoppingCart);
    }
}