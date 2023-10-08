package ms.study.kurly.feature.user.model;

import ms.study.kurly.feature.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(SignupModel dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .mobileNumber(dto.getMobileNumber())
                .build();
    }
}
