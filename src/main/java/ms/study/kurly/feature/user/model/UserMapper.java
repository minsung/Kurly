package ms.study.kurly.feature.user.model;

import ms.study.kurly.feature.user.User;

public interface UserMapper {

    User toEntity(SignupModel dto);
}
