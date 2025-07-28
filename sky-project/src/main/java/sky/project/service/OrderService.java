package sky.project.service;

import dto.OrderDTO;
import vo.LookOrderVO;
import vo.OrderVO;

public interface OrderService {
    //用户下单
    public OrderVO addOrderService(OrderDTO orderDTO);
    //查看订单详情
    public LookOrderVO lookOrderDetailService(Long id);
}
