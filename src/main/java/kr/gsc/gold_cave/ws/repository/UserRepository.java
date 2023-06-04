package kr.gsc.gold_cave.ws.repository;

import kr.gsc.gold_cave.ws.model.User;
import kr.gsc.gold_cave.ws.model.support.UserType;
import kr.gsc.gold_cave.ws.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private UserMapper mapper;

    @Autowired
    public UserRepository(UserMapper mapper) {
        this.mapper = mapper;
    }

    public User selectUser(String id) {
        return mapper.selectUser(id);
    }

    public List<User> selectUsers(UserType type) {
        return mapper.selectUsersWhereType(type);
    }

    public int insertUser(User user) {
        return mapper.insertUser(user);
    }
}
