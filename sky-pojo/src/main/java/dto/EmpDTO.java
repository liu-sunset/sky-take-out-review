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
public class EmpDTO implements Serializable {
    private Long id;
    private String idNumber;
    private String name;
    private String phone;
    private String sex;
    private String username;
}
