package com.naumstore.security.util;

import com.naumstore.domain.role.UserRoles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

public class PrincipalUtil {

    private PrincipalUtil() {
    }

    public static String getUsername(Principal principal) {
        Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ((User) castedPrincipal).getUsername();
    }

    public static String getPassword(Principal principal) {
        Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ((User) castedPrincipal).getPassword();
    }

    public static Collection<GrantedAuthority> getAuthorities(Principal principal) {
        Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ((User) castedPrincipal).getAuthorities();
    }

    public static boolean isUserHaveAuthority(Principal principal, UserRoles... roles) {

        Collection<GrantedAuthority> authorities = getAuthorities(principal);

        return authorities.stream().map(GrantedAuthority::getAuthority).anyMatch(authority ->
                Arrays.stream(roles).map(UserRoles::name).anyMatch(roleName -> roleName.equals(authority)));
    }
}
