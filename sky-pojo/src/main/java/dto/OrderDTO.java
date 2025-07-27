package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.source.tree.PatternTree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long addressBookId;
    private Double amount;
    private Short deliveryStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;
    private Integer packAmount;
    private Integer payMethod;
    private String remark;
    private Integer tablewareNumber;
    private Short tablewareStatus;
}
