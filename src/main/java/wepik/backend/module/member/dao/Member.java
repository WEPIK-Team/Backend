package wepik.backend.module.member.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import wepik.backend.global.common.BaseTimeEntity;
import wepik.backend.module.template.dao.MemTempMapping;
import wepik.backend.module.question.dao.Question;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(nullable = false, length = 10, unique = true)
    private String nickname;

    @NotNull
    @Size(max = 30)
    @Email
    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

//    @NotNull
//    @Column(nullable = false)
//    private Boolean active;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemTempMapping> memTempMappings = new ArrayList<>();
}
