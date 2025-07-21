package utils;

import constant.MethodConstant;
import enums.OperStat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class AutoFillAspect {

    @Pointcut("@annotation(annotation.AutoFillAspect)")
    public void autoFillPointCut(){}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("开始自动填充字段");
        //获取方法上的注解
       MethodSignature methodSignature =  (MethodSignature)joinPoint.getSignature();
        annotation.AutoFillAspect annotation = methodSignature.getMethod().getAnnotation(annotation.AutoFillAspect.class);
        OperStat operStat = annotation.value();

        //获取方法上面的参数列表
        Object[] args = joinPoint.getArgs();
        if(args==null||args.length==0){
            return ;
        }
        //获取第一个参数，默认employee是第一个参数
        Object arg = args[0];
        LocalDateTime now = LocalDateTime.now();
        Long id = BaseContext.getId();
        if(operStat==OperStat.INSERT){
            Method createTimeMethod = arg.getClass().getDeclaredMethod(MethodConstant.SET_CREATE_TIME, now.getClass());
            Method updateTimeMethod = arg.getClass().getDeclaredMethod(MethodConstant.SET_UPDATE_TIME, now.getClass());
            Method createUserMethod = arg.getClass().getDeclaredMethod(MethodConstant.SET_CREATE_USER, id.getClass());
            Method updateUserMethod = arg.getClass().getDeclaredMethod(MethodConstant.SET_UPDATE_USER, id.getClass());

            createTimeMethod.invoke(arg,now);
            updateTimeMethod.invoke(arg,now);
            createUserMethod.invoke(arg,id);
            updateUserMethod.invoke(arg,id);
        }
        else if (operStat==OperStat.UPDATE){
            Method updateTimeMethod = arg.getClass().getDeclaredMethod(MethodConstant.SET_UPDATE_TIME,now.getClass());
            Method updateUserMethod = arg.getClass().getDeclaredMethod(MethodConstant.SET_UPDATE_USER,id.getClass());

            updateTimeMethod.invoke(arg,now);
            updateUserMethod.invoke(arg,id);
        }
    }
}
