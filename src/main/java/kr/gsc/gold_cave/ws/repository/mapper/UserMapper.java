package kr.gsc.gold_cave.ws.repository.mapper;

import kr.gsc.gold_cave.ws.model.User;
import kr.gsc.gold_cave.ws.model.support.UserType;

import java.util.List;

public interface UserMapper {
    User selectUser(String id);
    List<User> selectUsersWhereType(UserType type);
    int insertUser(User account);
}
