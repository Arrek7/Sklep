package com.comarch.szkolenia.sklep.weryfikacja;

import com.comarch.szkolenia.sklep.database.IUserRepository;
import com.comarch.szkolenia.sklep.exceptions.FailedAuthenticationException;
import com.comarch.szkolenia.sklep.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Authenticator implements IAuthenticator{
    @Autowired
    private IUserRepository userRepository;
    private final String seed = "{B0;g;2PS)d.i!$}x)_v2;%RRxZGru2k-^n!QneR+!4jLpG0Fi";
    public static User.Role currentUserRole;

    @Override
    public void authenticate(String login, String password) {
        User user = this.userRepository.findUser(login);

        if(user == null || !user.getPassword().equals(DigestUtils.md5Hex(password+seed))) {
            throw new FailedAuthenticationException();
        }

       currentUserRole = user.getRole();
    }

    public String hashedPassword(String password) {
        return DigestUtils.md5Hex(password + seed);
    }
}
