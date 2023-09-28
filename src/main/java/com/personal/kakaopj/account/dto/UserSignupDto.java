package com.personal.kakaopj.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class UserSignupDto {

    @PositiveOrZero
    @NotNull(message = "필수 항목")
    private Long id;

    @Email(message = "email 형식이 아닙니다")
    private String email;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 8자~20자의 비밀번호여야 합니다.")
    private String password;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    @Pattern(regexp = "010(\\\\d{3,4})(\\\\d{4})")
    private String phoneNumber;

    @NotNull(message = "필수 항목")
    @NotBlank(message = "빈칸 입력 불가")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "이름은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String name;

    @NotNull(message = "필수 항목")
    @DateTimeFormat
    private LocalDate birthday;
}
