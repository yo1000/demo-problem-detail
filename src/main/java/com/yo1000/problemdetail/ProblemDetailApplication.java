package com.yo1000.problemdetail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class ProblemDetailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProblemDetailApplication.class, args);
	}

	@RestController
	@RequestMapping("/problem")
	public static class ProblemDetailDemoController {
		@GetMapping
		public ResponseEntity get() {
			// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ProblemDetail.html
			ProblemDetail detail = ProblemDetail.forStatusAndDetail(
					HttpStatus.BAD_REQUEST,
					"Summary message"
			);
			detail.setProperty("reasons", List.of(new Reason(
					"required",
					"Username is required",
					"username",
					""
			)));

			// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html#of(org.springframework.http.ProblemDetail)
			return ResponseEntity.of(detail).build();
		}
	}

	public record Reason(
			String code,
			String message,
			String property,
			String value
	) {}
}
