package wepik.backend.module.question.dao;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import wepik.backend.global.common.BaseTimeEntity;
import wepik.backend.module.file.dao.File;
import wepik.backend.module.member.dao.Member;
import wepik.backend.module.template.dao.Template;
import wepik.backend.module.template.dao.TemplateQuestion;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AnswerType type;

    @Column(nullable = false)
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SelectQuestion> selectQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateQuestion> templateQuestions = new ArrayList<>();
  
    public void update(String title, AnswerType type, List<SelectQuestion> selectQuestions, File file) {
        this.title = title;
        this.type = type;
        this.selectQuestions.addAll(selectQuestions);
        this.file = file;
    }

    public void delete() {
        this.active = false;
    }

    @PrePersist // 테이블 생성될 때 값을 할당
    public void init() {
        this.active = true;
    }

}
