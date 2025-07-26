package sky.project.service;


import dto.AddShoppingCartDTO;
import entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    //添加购物车
    public void addCartService(AddShoppingCartDTO addShoppingCartDTO);
    //查看购物车
    public List<ShoppingCart> lookCartService();
    //删除某个商品
    public void subCartService(AddShoppingCartDTO addShoppingCartDTO);
    //清空购物车
    public void cleanCartService();
}
