package vo;

import entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;

import java.io.Serializable;
import java.lang.invoke.CallSite;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatePageVO implements Serializable {
    private Long total;
    private List<Category> records;
}
