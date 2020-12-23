package com.lingyu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    /**
     * 生成token  head.payload.sign
     */


    private static final String sign="asbasdsa";

    public static String creatToken(Map<String,String> map){

        Calendar instance=Calendar.getInstance();
        instance.add(Calendar.DATE,7);

        //创建JWTBuilder
        JWTCreator.Builder builder=JWT.create();
        //遍历payload，加入builder
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())
                        .sign(Algorithm.HMAC256(JWTUtils.sign));
        return token;
    }

    /**
     * 验证token合法性
     * @param token
     */
    public static DecodedJWT verify(String token){
        //要是有异常就会直接通知
        DecodedJWT decodedJWT=JWT.require(Algorithm.HMAC256(sign)).build().verify(token);
        //可以通过此对象获取token信息
        return decodedJWT;
    }




}
