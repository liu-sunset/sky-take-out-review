package dto;

import entity.Dish;
import entity.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddSetmealDTO implements Serializable {
    private Long categoryId;
    private String description;
    private Long id;
    private String image;
    private String name;
    private Double price;
    private List<SetmealDish> setmealDishes;
    private Integer status;
}
