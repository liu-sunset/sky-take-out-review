package result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
    public static Result success(){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("success");
        return result;
    }

    public static Result success(Object data){
        Result result = Result.success();
        result.setData(data);
        return result;
    }

    public static Result error(Object data){
        return new Result(0,"error",data);
    }

//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public Result(Integer code, String msg, Object data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }
//    public Result(){
//
//    }
}
