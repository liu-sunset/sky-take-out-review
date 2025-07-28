package sky.project.mapper;

import constant.OrderConstant;
import entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface OrderMapper {
    //用户下单
    public void addOrderMapper(Order order);
    //查看订单基本信息
    public Order lookOrderDetailMapper(Long id);
    //超时未支付订单处理
    public void updateOrderByTimeMapper(LocalDateTime localDateTime);
    //闭店时处理订单
    public void updateOrderMapper(LocalDateTime localDateTime);
}
