package sky.project.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sky.project.mapper.OrderMapper;

import java.time.LocalDateTime;

@Slf4j
@Component
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0/5 * * * * ?")
    public void orderDetailTime(){
        log.info("定时处理超时未支付订单");
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(-15);
        orderMapper.updateOrderByTimeMapper(localDateTime);
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void orderDetail(){
        log.info("闭店时处理未完成订单");
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(-60);
        orderMapper.updateOrderMapper(localDateTime);
    }
}
