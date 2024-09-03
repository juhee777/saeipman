package com.saeipman.app.member.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDTO {
	@Length(min = 6, max = 18, message = "아이디: 6자리 이상, 18자리 이하 ")
	@NotBlank(message = "아이디: 필수입니다.")
	private String id; // 임대인 아이디, 로그인 아이디, 임차인 아이디(전화번호)
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}", message = "비밀번호: 8~16자 영문 대 소문자, 숫자를 사용하세요.")
	@NotBlank(message = "비밀번호: 필수입니다.")
	private String pw; // 로그인 비번
	@NotBlank(message = "이름: 필수입니다.")
	private String name; // 임대인 이름, 임차인 이름
	@Email(message = "이메일: 올바르지 않은 형식입니다.")
	@NotBlank(message = "이메일: 필수입니다.")
	private String email; // 임대인 이메일
	@Past(message = "생년월일: 유효하지 않은 날짜입니다.")
	private Date birth;  // 임대인 생년월일
	private String phone; // 임대인 전화번호
	
}
