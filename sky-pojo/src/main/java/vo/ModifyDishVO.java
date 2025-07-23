package vo;

import entity.DishFlavor;
import jdk.jfr.Description;
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
public class ModifyDishVO implements Serializable {
    private Long categoryId;
    private String categoryName;
    private String description;
    private List<DishFlavor> flavors;
    private Long id;
    private String image;
    private String name;
    private Double price;
    private Integer status;
    private LocalDateTime updateTime;
}
