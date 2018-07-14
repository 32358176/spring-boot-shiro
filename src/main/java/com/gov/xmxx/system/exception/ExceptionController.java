package com.gov.xmxx.system.exception;

import com.gov.xmxx.pojo.Page;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


/**
 * @author hanyong
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     *  捕捉shiro的异常
      */
    @ExceptionHandler(ShiroException.class)
    public Page handle401(ShiroException e) {
        return new Page(401, "你没有权限访问该资源!!!",0,null);
    }

    /**
     * 捕捉自定义异常
     */

    @ExceptionHandler(RoleException.class)
    public Page handle402(RoleException r) {
        return new Page(402, r.getMessage(),0,null);
    }

    /**
     * 数据校验异常
     */

    @ExceptionHandler(ConstraintViolationException.class)
    public Page handle405(ConstraintViolationException c){
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> set = c.getConstraintViolations();
        for (ConstraintViolation<?> item : set) {
            message.append(item.getMessage());
        }
        return new Page(405, message.toString(),0,null);
    }

    /**
     * 捕捉其他异常
     */
    @ExceptionHandler(Exception.class)
    public Page globalException(Exception ex) {
        return new Page(403, ex.getMessage(),0,null);
    }

}


