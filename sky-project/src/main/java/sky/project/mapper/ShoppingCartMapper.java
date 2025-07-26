package sky.project.mapper;

import entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    //查询菜品或者套餐
    public ShoppingCart selectDishOrSetmealMapper(ShoppingCart shoppingCart);
    //添加购物车
    public void addCartMapper(ShoppingCart shoppingCart);
    //查看购物车
    public List<ShoppingCart> lookCartMapper(Long userId);
    //删除某个商品
    public void deleteCartMapper(ShoppingCart shoppingCart);
    //修改购物车
    public void modifyCartMapper(ShoppingCart shoppingCart);
}
