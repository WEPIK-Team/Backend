package wepik.backend.module.member.application;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import wepik.backend.module.member.dto.LoginRequest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void adminPwdEncoding() throws Exception {
        //given
        String password = "12345678";

        //when
        String encoded = passwordEncoder.encode(password);

        //then
        System.out.println("Encoded password: " + encoded);

    }

    @Test
    public void adminLoginTest() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest("wepik@gmail.com", "12345678");

        //when
        boolean isLogin = memberService.adminLogin(loginRequest);

        //then
        Assertions.assertThat(isLogin).isTrue();
    }


}