package sky.project.mapper;

import constant.OrderConstant;
import entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    //用户下单
    public void addOrderMapper(Order order);
}
