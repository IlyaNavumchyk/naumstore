package com.naumstore.security;

import com.naumstore.domain.role.Role;
import com.naumstore.domain.role.UserRoles;
import com.naumstore.domain.user.User;
import com.naumstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> searchResult = userRepository.findByCredentialsLogin(username);

        if (searchResult.isPresent()) {

            User user = searchResult.get();

            return new org.springframework.security.core.userdetails.User(
                    user.getCredentials().getLogin(),
                    user.getCredentials().getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(
                            user.getRoles()
                                    .stream()
                                    .map(Role::getRoleName)
                                    .map(UserRoles::name)
                                    .collect(Collectors.joining(","))
                    )
            );
        } else {
            throw new UsernameNotFoundException(String.format("User with login \"%s\" not found", username));
        }
    }
}
