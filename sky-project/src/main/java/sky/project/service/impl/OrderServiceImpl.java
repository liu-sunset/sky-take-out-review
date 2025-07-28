package sky.project.service.impl;

import constant.OrderConstant;
import dto.OrderDTO;
import entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.project.mapper.*;
import sky.project.service.OrderService;
import utils.BaseContext;
import vo.LookOrderVO;
import vo.OrderVO;

import java.lang.invoke.CallSite;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    //用户下单
    @Override
    public OrderVO addOrderService(OrderDTO orderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        String number = String.valueOf(System.currentTimeMillis());
        order.setNumber(number);
        order.setStatus(OrderConstant.NO_CHECKOUT);
        order.setUserId(BaseContext.getId());
        order.setOrderTime(LocalDateTime.now());
        order.setPayStatus(Short.valueOf("0"));
        Address address = Address.builder().id(orderDTO.getAddressBookId()).build();
        Address addressResult = addressMapper.selectAddressMapper(address);
        order.setPhone(addressResult.getPhone());
        String addressDetail = addressResult.getProvinceName()+addressResult.getCityName()+addressResult.getDetail();
        order.setAddress(addressDetail);
        User user = userMapper.selectUserMapper(order.getUserId());
        order.setUserName(user.getName());
        order.setConsignee(address.getConsignee());
        orderMapper.addOrderMapper(order);
        //添加购物车详细信息
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.lookCartMapper(BaseContext.getId());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(ShoppingCart shoppingCart : shoppingCartList){
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingCart, orderDetail);
            orderDetail.setOrderId(order.getId());
            orderDetailList.add(orderDetail);
        }
        orderDetailMapper.addOrderDetailMapper(orderDetailList);
        return OrderVO.builder()
                .id(order.getId())
                .orderAmount(order.getAmount())
                .orderNumber(order.getNumber())
                .orderTime(order.getOrderTime())
                .build();
    }

    //查看订单详情
    @Override
    public LookOrderVO lookOrderDetailService(Long id) {
        Order order = orderMapper.lookOrderDetailMapper(id);
        List<OrderDetail> orderDetailList = orderDetailMapper.lookOrderDetailMapper(id);
        LookOrderVO lookOrderVO = new LookOrderVO();
        BeanUtils.copyProperties(order,lookOrderVO);
        lookOrderVO.setOrderDetailList(orderDetailList);
        return lookOrderVO;
    }
}
