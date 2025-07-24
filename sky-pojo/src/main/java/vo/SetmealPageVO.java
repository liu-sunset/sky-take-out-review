package vo;

import entity.Setmeal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetmealPageVO {
    private Long total;
    private List<SetmealVO> records;
}
