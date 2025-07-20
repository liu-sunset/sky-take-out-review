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
public class EditPasswordDTO implements Serializable {
    private Long empId;
    private String newPassword;
    private String oldPassword;
}
