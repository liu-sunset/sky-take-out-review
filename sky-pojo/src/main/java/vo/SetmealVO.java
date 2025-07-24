package vo;

import entity.SetmealDish;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetmealVO implements Serializable {
    private Long id;
    private Long categoryId;
    private String name;
    private Double price;
    private Integer status;
    private String description;
    private String image;
    private LocalDateTime updateTime;
    private String categoryName;

    private List<SetmealDish> setmealDishes;
}
