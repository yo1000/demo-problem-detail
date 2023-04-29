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
    @RequestMapping("/problem-demo")
    public static class ProblemDetailDemoController {
        @GetMapping
        public ResponseEntity get() {
            // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ProblemDetail.html
            ProblemDetail detail = ProblemDetail.forStatus(
                    HttpStatus.BAD_REQUEST
            );
            // When response must contain summary message, refer to following.
            // ProblemDetail detail = ProblemDetail.forStatusAndDetail(
            //         HttpStatus.BAD_REQUEST,
            //         "Params are invalid."
            // );
            detail.setProperty("params", List.of(new Param(
                    "username",
                    "",
                    "required",
                    "Username is required."
            )));

            // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html#of(org.springframework.http.ProblemDetail)
            return ResponseEntity.of(detail).build();
        }
    }

    public record Param(
            String name,
            String value,
            String code,
            String reason
    ) {}
}
