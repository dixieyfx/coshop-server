package com.coshop.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/29 23:30
 */
public class JWTUtils {

    private static final String SING="DIXIEY";

    public static String creatToken(Map<String,String> payload, int expireTime){
        JWTCreator.Builder builder= JWT.create();
        //获取日历对象
        Calendar instance=Calendar.getInstance();
        if(expireTime <=0) {
            //默认一小时
            instance.add(Calendar.SECOND,3600);
        } else {
            instance.add(Calendar.SECOND,expireTime);
        }
        //为了方便只放入了一种类型
        payload.forEach(builder::withClaim);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));
    }
    public static Map<String, Object> getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        Map<String, Claim> claims = verify.getClaims();
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String expired= dateTime.format(verify.getExpiresAt());
        Map<String,Object> m=new HashMap<>();
        claims.forEach((k,v)-> m.put(k,v.asString()));
        m.put("exp",expired);
        return m;

    }


}
