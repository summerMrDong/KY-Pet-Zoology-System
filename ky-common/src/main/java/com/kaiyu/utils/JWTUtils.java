package com.kaiyu.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.error.KyBigException;
import lombok.extern.slf4j.Slf4j;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtils {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    /**
     * payload = 自定义加密数据
     */
    public static final String PAYLOAD = "payload";

    /**
     * 有限期
     */
    public static final String IN_DATE = "inDate";

    /**
     * 时间戳
     */
    public static final String IAT = "iat";

    /**
     * 过期时间
     */
    public static final String EXP = "exp";

    /**
     * 唯一标识
     */
    public static final String JTI = "jti";

    private static JWTSigner signer = new JWTSigner(SECRET);

    private static JWTVerifier verifier = new JWTVerifier(SECRET);


    /**
     * 短期token
     *
     * @param object 加密的内容
     * @param maxAge maxAge秒后过期
     * @return
     */
    public static String encrypt(Object object, int maxAge) {
        Map<String, Object> map = initialize(object);
        map.put(IN_DATE, maxAge);
        JWTSigner.Options options = getOption().setExpirySeconds(maxAge);
        return signer.sign(map, options);
    }

    /**
     * 永久有效token
     *
     * @param object 加密的内容
     * @return
     */
    public static String encrypt(Object object) {
        Map<String, Object> map = initialize(object);
        return signer.sign(map, getOption());
    }

    /**
     * 更新token
     *
     * @param oldToken
     * @return
     */
    public static String update(String oldToken) throws KyBigException {
        Map<String, Object> decode = decode(oldToken);
        Object payload = decode.get(PAYLOAD);
        return encrypt(payload, (int) decode.get(IN_DATE));
    }

    /**
     * 解密
     *
     * @param jwt
     * @return
     */
    public static Map<String, Object> decode(String jwt) throws KyBigException {
        return tryException(jwt);
    }

    /**
     * 初始化
     *
     * @param object
     * @return
     */
    private static Map<String, Object> initialize(Object object) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(PAYLOAD, object);//传输相关信息
        return claims;
    }

    /**
     * 获取jwt设置
     *
     * @return
     */
    private static JWTSigner.Options getOption() {
        return new JWTSigner.Options().setIssuedAt(true).setJwtId(true);
    }


    /**
     * 捕捉异常
     *
     * @param jwt
     * @return
     */
    private static Map<String, Object> tryException(String jwt) throws KyBigException {
        try {
            return verifier.verify(jwt);
        } catch (JWTExpiredException e) {
            log.info("过期的token");
            throw new KyBigException(ExceptionEnum.TOKEN_OVERDUE_ERROR);
        } catch (SignatureException e) {
            log.warn("无效的token");
            throw new KyBigException(ExceptionEnum.TOKEN_INVALID_ERROR);
        } catch (Exception e) {
            log.error("解析token发生异常,可能是无效的token");
            e.printStackTrace();
            throw new KyBigException(ExceptionEnum.SERVER_UNKNOWN_ERROR);
        }
    }

    public static void main(String[] args) throws KyBigException {
        JSONObject info = new JSONObject();
        info.put("openId", 1231);
        info.put("unionId", 1232);
        info.put("userId",1);
        String encrypt = JWTUtils.encrypt(info);
        System.out.println(encrypt);
        JSONObject jsonObject = new JSONObject(JWTUtils.decode(encrypt));
        System.out.println(jsonObject);
        System.out.println(jsonObject.getJSONObject("payload"));
    }

}
