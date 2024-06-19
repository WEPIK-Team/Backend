package wepik.backend.module.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import wepik.backend.module.member.dao.Member;
import wepik.backend.module.member.dao.Provider;
import wepik.backend.module.member.dao.Role;

@Data
@Builder
@AllArgsConstructor
public class JoinRequest {

    @Schema(description = "닉네임", example = "유저")
    private String nickname;

    @Schema(description = "이메일", example = "test1234@gmail.com")
    private String email;

    @Schema(description = "비밃번호", example = "test1234")
    private String password;

    public Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(Role.USER)
                .provider(Provider.CREDENTIAL)
                .build();
    }
}
