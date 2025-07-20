package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpPageDTO implements Serializable {
    private String name;
    private Integer page = 1;
    private Integer pageSize = 10;
}
