package handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import result.Result;

import java.sql.SQLIntegrityConstraintViolationException;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*
    * 处理所有异常
    * mybatis会将SQLIntegrityConstraintViolationException封装成runtimeexception向上抛出
    * */
    @ExceptionHandler
    public Result exceptionHandler(RuntimeException e){
        if (e.getCause() instanceof SQLIntegrityConstraintViolationException){
            SQLIntegrityConstraintViolationException se = (SQLIntegrityConstraintViolationException) e.getCause();
            String[] splits = se.getMessage().split(" ");
            log.error("SQL异常信息:{}","已存在"+splits[2]);
            return Result.error("已存在"+splits[2]);
        }
        else{
            log.error("业务异常信息:{}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
