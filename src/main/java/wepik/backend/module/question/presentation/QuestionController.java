package wepik.backend.module.question.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wepik.backend.aop.RequestMember;
import wepik.backend.module.member.dao.Member;
import wepik.backend.module.question.application.QuestionService;
import wepik.backend.module.question.dto.QuestionRequest;
import wepik.backend.module.question.dto.QuestionResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("questions")
@Tag(name = "Question", description = "질문 API")
public class QuestionController {

    private final QuestionService questionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "질문 생성", description = "질문을 생성한다.")
    @RequestMember
    public QuestionResponse createQuestion(HttpServletRequest request, @RequestBody QuestionRequest questionRequest, Member member) {
        return questionService.save(questionRequest, member);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @Operation(summary = "질문 조회", description = "id 값에 해당하는 질문을 조회한다.")
    public QuestionResponse findQuestion(@PathVariable Long id) {
        return questionService.findQuestion(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Operation(summary = "모든 질문 조회", description = "모든 질문을 조회한다.")
    public List<QuestionResponse> findQuestions() {
        return questionService.findQuestions();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "질문 삭제", description = "id 값에 해당하는 질문을 삭제한다.")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.delete(id);
        return "질문이 정상적으로 삭제되었습니다.";
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    @Operation(summary = "질문 수정", description = "id 값에 해당하는 질문을 수정한다.")
    public String updateQuestion(@PathVariable Long id, @RequestBody QuestionRequest questionRequest) {
        questionService.updateQuestion(id , questionRequest);
        return "질문이 정상적으로 수정되었습니다.";
    }
}
