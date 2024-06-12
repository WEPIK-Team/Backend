package wepik.backend.module.template.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import wepik.backend.global.common.BaseTimeEntity;
import wepik.backend.module.file.dao.File;
import wepik.backend.module.result.dao.Result;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Template extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @PositiveOrZero
    @ColumnDefault("0")
    @Column(nullable = false)
    private int useCount;

    @OneToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File file;

    @Builder.Default
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateQuestion> templateQuestions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TemplateTag> templateTags = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "template")
    private List<MemTempMapping> memTempMappings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "template")
    private List<Result> results = new ArrayList<>();
    public void setTemplateTag(TemplateTag templateTag) {
        this.templateTags.add(templateTag);
        templateTag.setTemplate(this);
    }
    public void setTemplateQuestion(TemplateQuestion templateQuestion) {
        this.templateQuestions.add(templateQuestion);
        templateQuestion.setTemplate(this);
    }
    public void update(String title, File file, List<TemplateQuestion> templateQuestion, List<TemplateTag> templateTags) {
        this.title = title;
        this.file = file;
        this.templateQuestions.addAll(templateQuestion);
        this.templateTags.addAll(templateTags);
    }

    public void increaseUseCount() {
        this.useCount++;
    }
}
