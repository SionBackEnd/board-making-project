package project.boardMaking.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.boardMaking.dto.MemberDto;
import project.boardMaking.entity.Member;
import project.boardMaking.mapper.MemberMapper;
import project.boardMaking.service.MemberService;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping
    public ResponseEntity memberPost(@Valid @RequestBody MemberDto.Post post) {
        Member member = memberMapper.PostToMember(post);

        Member member1 = memberService.createMember(member);

        return new ResponseEntity<>(memberMapper.MemberToResponse(member1), HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity userGet() {
        Member member = memberService.findMember(1L);
        return new ResponseEntity<>(memberMapper.MemberToResponse(member),HttpStatus.OK);
    }
    @GetMapping("/manager")
    public ResponseEntity managerGet() {
        Member member = memberService.findMember(1L);
        return new ResponseEntity<>(memberMapper.MemberToResponse(member),HttpStatus.OK);
    }
    @GetMapping("/admin")
    public ResponseEntity adminGet() {
        Member member = memberService.findMember(1L);
        return new ResponseEntity<>(memberMapper.MemberToResponse(member),HttpStatus.OK);
    }

}
