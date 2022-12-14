package com.its.memberboardproject.controller;

import com.its.memberboardproject.dto.MemberDTO;
import com.its.memberboardproject.entity.MemberEntity;
import com.its.memberboardproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.IIOException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm() {
        return "memberSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "memberLogin";
    }

    @PostMapping("/emailCheck")
    public ResponseEntity emailCheck(@RequestParam("inputEmail") String memberEmail) {
        String checkResult = memberService.emailCheck(memberEmail);
        if (checkResult != null) {
            return new ResponseEntity<>("사용 해도 됩니다", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("사용할 수 없습니다.", HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/login")
    public String loginForm(){
        return "memberLogin";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null){
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            return "main";
        }else {
            return "memberLogin";
        }

    }
}