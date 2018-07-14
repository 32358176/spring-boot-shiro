package com.gov.xmxx.system.jwt;

import com.gov.xmxx.dao.PermissionsMapper;
import com.gov.xmxx.dao.RolesMapper;
import com.gov.xmxx.dao.UsersMapper;
import com.gov.xmxx.pojo.Users;
import com.gov.xmxx.system.exception.RoleException;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;

/**
 * @author hanyong
 */
@Component
public class SystemRealm extends AuthorizingRealm {


    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private PermissionsMapper permissionsMapper;

    /**
     * 获取用户权限/角色
     * @param principalCollection
     * @return
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo =
                new SimpleAuthorizationInfo();
        Users users =  (Users)principalCollection.getPrimaryPrincipal();
        String username = users.getUsername();
        Set<String> roles = rolesMapper.selectRolesNameByUsername(username);

       simpleAuthorizationInfo.addRoles(roles);
       Set<String> permissions = permissionsMapper.selectPermissionValueByUsername(username);
       simpleAuthorizationInfo.addStringPermissions(permissions);

       return simpleAuthorizationInfo;
    }

    /**
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //2. 从 UsernamePasswordToken 中来获取 username
        String username = token.getUsername();
        Users  user = usersMapper.selectUserByUsername(username);
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        if (user == null) {
            return null;
        }
        //4). 盐值.
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt,getName());
        return info;
    }

}
