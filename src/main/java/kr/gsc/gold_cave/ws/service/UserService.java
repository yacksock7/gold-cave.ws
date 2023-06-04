package kr.gsc.gold_cave.ws.service;

import kr.gsc.gold_cave.ws.exception.GoldCaveException;
import kr.gsc.gold_cave.ws.exception.ErrorCode;
import kr.gsc.gold_cave.ws.model.User;
import kr.gsc.gold_cave.ws.model.support.UserType;
import kr.gsc.gold_cave.ws.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String DEFAULT_ADMIN_EMAIL = "admin@test.com";
    private static final String DEFAULT_ADMIN_PASSWORD = "1234";
    private static final String DEFAULT_ADMIN_NICKNAME = "administrator";
    private static final Map<String, Boolean> notAcceptableIdMap = new HashMap<>();
    static {
        notAcceptableIdMap.put("check", false);
        notAcceptableIdMap.put("signin", false);
        notAcceptableIdMap.put("signout", false);
        notAcceptableIdMap.put("signcheck", false);
        notAcceptableIdMap.put("login", false);
        notAcceptableIdMap.put("logout", false);
        notAcceptableIdMap.put("logincheck", false);
    }

    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void checkAdmin() {
        final List<User> users = getUsers(UserType.Admin);

        if((users == null) || (users.size() < 1)) {
            logger.info("Admin account not exists : create a default admin account");

            final User newAdmin = User.builder()
                    .email(DEFAULT_ADMIN_EMAIL)
                    .password(DEFAULT_ADMIN_PASSWORD)
                    .nickname(DEFAULT_ADMIN_NICKNAME)
                    .type(UserType.Admin)
                    .build();

            createNewUser(newAdmin);
        }
    }

    public User getUser(String id) {
        return repository.selectUser(id);
    }

    public List<User> getUsers(UserType type) {
        return repository.selectUsers(type);
    }

    public User createNewUser(User user) {
        if(isNotAcceptableId(user.getEmail())) {
            throw new GoldCaveException(ErrorCode.NotAcceptableId, "Not acceptable id : " + user.getEmail());
        }
        final String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);
        user.setCreatedDatetime(LocalDateTime.now());
        user.setUpdatedDatetime(LocalDateTime.now());

        repository.insertUser(user);

        return user;
    }

    private boolean isNotAcceptableId(String id) {
        boolean isNotAcceptable = false;

        if((id == null) || (id.length() < 1) || (id.contains(" ")) || (notAcceptableIdMap.containsKey(id.toLowerCase()))) {
            isNotAcceptable = true;
        }

        return isNotAcceptable;
    }
}
