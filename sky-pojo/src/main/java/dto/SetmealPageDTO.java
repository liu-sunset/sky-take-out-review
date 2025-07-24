package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetmealPageDTO implements Serializable {
    private Long categoryId;
    private String name;
    private Integer page;
    private Integer pageSize;
    private Integer status;
}
