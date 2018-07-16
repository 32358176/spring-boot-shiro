package com.gov.xmxx.system.jwt;

import com.gov.xmxx.pojo.Users;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @author hanyong
 * @date 2018/7/13 20:05
 * 注册密码加盐处理
 */
@Component
public class SystemCredentials {

    public String sysCredentials(Users users){
        String hashAlgorithmName ="MD5";//加密方式
        ByteSource salt = ByteSource.Util.bytes(users.getUsername());//盐值为账号
        int hashIterations = 1024;//加盐次数
        //生成加盐凭证
        SimpleHash credentialsSalt = new SimpleHash(hashAlgorithmName,users.getPassword(),salt,hashIterations);
        return credentialsSalt.toString();
    }
}
