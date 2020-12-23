package com.lingyu.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Object error(Exception ex){
        ex.printStackTrace();
        Map<String,String> map = new HashMap<>();
        map.put("cuowu", ex.getMessage());
        /*map.put("error", ex.getMessage());
        map.put("code", "500");*/
        return map;
    }
}
