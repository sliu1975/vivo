package com.nplatform.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录Token的生成和解析
 *
 * @author ysh
 */
public class JwtToken {
    public static final String SECRET = "things";
    public static final int calendarField = Calendar.DATE;
    public static final int calendarInterval = 30;

    /**
     * JWT生成Token
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public static String createToken(Long userId) throws Exception {
        if (userId == null || userId < 0) {
            return null;
        }

        Date iatDate = new Date();

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create().withHeader(map)
                .withClaim("iss", "Service")
                .withClaim("aud", "APP").withClaim("user_id", userId.toString())
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    /**
     * 解密Token
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return
     */
    public static Integer getUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim userIdClaim = claims.get("user_id");
        if (null == userIdClaim || StringUtils.isEmpty(userIdClaim.asString())) {
            return null;
        }
        return Integer.valueOf(userIdClaim.asString());
    }

    public static void main(String[] args) throws Exception {
        String token = createToken(1L);
        System.out.println(token);

        Map<String, Claim> map = verifyToken(token);
        if (map != null) {
            for (Map.Entry<String, Claim> entry : map.entrySet()) {
                if (entry.getKey().equals("iat") || entry.getKey().equals("exp")) {
                    System.out.println(entry.getKey() + " " + entry.getValue().asDate());
                } else {
                    System.out.println(entry.getKey() + " " + entry.getValue().asString());
                }
            }
        }

        Integer userId = getUID(token);
        System.out.println(userId);
    }

}
