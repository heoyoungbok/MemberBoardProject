package com.its.memberboardproject;

import com.its.memberboardproject.dto.MemberDTO;
import com.its.memberboardproject.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MemberBoardTest {

    @Autowired
    private MemberService memberService;


    private MemberDTO newMember(int i) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("testEmail"+i);
        memberDTO.setMemberPassword("testPassword"+i);
        memberDTO.setMemberName("testName"+i);
        memberDTO.setMemberMobile("010-1111-1111"+i);
        return memberDTO;

    }

    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("회원가입 테스트")
    public void memberSaveTest() throws IOException {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("testEmail1");
        memberDTO.setMemberPassword("testPassword1");
        memberDTO.setMemberName("testName1");
        memberDTO.setMemberMobile("010-0000-0000");
        Long savedId = memberService.save(memberDTO);

        MemberDTO saveMember = memberService.findById(savedId);
        assertThat(memberDTO.getMemberEmail()).isEqualTo(saveMember.getMemberEmail());

    }



}


