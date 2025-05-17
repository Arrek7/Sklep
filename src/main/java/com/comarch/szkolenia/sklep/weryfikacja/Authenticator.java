package com.comarch.szkolenia.sklep.weryfikacja;

import com.comarch.szkolenia.sklep.database.UserRepository;
import com.comarch.szkolenia.sklep.exceptions.FailedAuthenticationException;
import com.comarch.szkolenia.sklep.model.User;
import lombok.Getter;
import org.apache.commons.codec.digest.DigestUtils;

public class Authenticator {
    private final UserRepository userRepository = UserRepository.getInstance();
    private final String seed = "{B0;g;2PS)d.i!$}x)_v2;%RRxZGru2k-^n!QneR+!4jLpG0Fi";
    public static User.Role currentUserRole;
    @Getter
    private final static Authenticator instance = new Authenticator();

    private Authenticator(){
    }

    public void authenticate(String login, String password) {
        User user = this.userRepository.findUser(login);

        if(user == null || !user.getPassword().equals(DigestUtils.md5Hex(password+seed))) {
            throw new FailedAuthenticationException();
        }

       currentUserRole = user.getRole();
    }

}
