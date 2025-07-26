package dto;

import entity.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddShoppingCartDTO {
    private String dishFlavor;
    private Long dishId;
    private Long setmealId;
}
