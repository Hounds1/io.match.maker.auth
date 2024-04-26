package io.exam.match.auth.domain.member.controller;

import io.exam.match.auth.domain.member.persist.domain.dto.request.MemberJoinRequest;
import io.exam.match.auth.domain.member.persist.domain.dto.response.SimpleJoinResponse;
import io.exam.match.auth.domain.member.application.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<SimpleJoinResponse> join(@RequestBody MemberJoinRequest joinRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.join(joinRequest.toEntity()));
    }

}
