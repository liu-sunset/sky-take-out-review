package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetmealDish implements Serializable {
    private Long id;
    private Long setmealId;
    private Long dishId;
    private String name;
    private Double price;
    private Integer copies;
}
