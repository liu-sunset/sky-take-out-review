package sky.project.service;

import dto.OrderDTO;
import vo.OrderVO;

public interface OrderService {
    //用户下单
    public OrderVO addOrderService(OrderDTO orderDTO);
}
