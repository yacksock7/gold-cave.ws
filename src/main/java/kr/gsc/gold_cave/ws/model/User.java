package kr.gsc.gold_cave.ws.model;

import kr.gsc.gold_cave.ws.model.support.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private UserType type;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDatetime;
}
