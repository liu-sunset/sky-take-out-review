package sky.project.mapper;

import entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    //添加订单详细信息
    public void addOrderDetailMapper(List<OrderDetail> orderDetailList);
}
