package handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import result.Result;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
    * 处理业务异常
    * */
    @ExceptionHandler
    public Result exceptionHandler(RuntimeException e){
        log.error("业务异常信息:{}",e.getMessage());
        return Result.error(e.getMessage());
    }

}
