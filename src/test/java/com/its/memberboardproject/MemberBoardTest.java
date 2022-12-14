package com.its.memberboardproject;

import com.its.memberboardproject.dto.BoardDTO;
import com.its.memberboardproject.dto.MemberDTO;
import com.its.memberboardproject.service.BoardService;
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
    @Autowired
    private BoardService boardService;


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
    public void memberSaveTest() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail("testEmail1");
        memberDTO.setMemberPassword("testPassword1");
        memberDTO.setMemberName("testName1");
        memberDTO.setMemberMobile("010-0000-0000");
        Long savedId = memberService.save(memberDTO);

        MemberDTO saveMember = memberService.findById(savedId);
        assertThat(memberDTO.getMemberEmail()).isEqualTo(saveMember.getMemberEmail());

    }


    @Test
    @Transactional
    @Rollback(value = true)
    @DisplayName("로그인 테스트")
    public void memberLoginTest(){
        String loginEmail = "loginEmail";
        String loginPassword = "loginPassword";

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberEmail(loginEmail);
        memberDTO.setMemberPassword(loginPassword);
        memberDTO.setMemberName("loginName");
        memberDTO.setMemberMobile("testNumber");
        memberService.save(memberDTO);

        MemberDTO loginDTO = new MemberDTO();
        loginDTO.setMemberEmail(loginEmail);
        loginDTO.setMemberPassword(loginPassword);
        MemberDTO loginResult = memberService.login(loginDTO);
        assertThat(loginResult).isNotNull();


    }

    private BoardDTO newBoard(int i){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardWriter("testWriter"+i);
        boardDTO.setBoardTitle("testTitle"+i);
        boardDTO.setBoardContents("testContents"+i);
        return boardDTO;

    }


////    @Test
////    @Transactional
////    @Rollback(value = true)
////    @DisplayName("게시판 생성 테스트")
////    public void boardSaveTest()throws IOException{
////        BoardDTO boardDTO = newBoard(1);
////        Long saveBoard = boardService.save(boardDTO);
////        BoardDTO savedBoard = boardService.findById()
////
//    }


}


