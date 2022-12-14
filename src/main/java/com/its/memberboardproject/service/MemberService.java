package com.its.memberboardproject.service;

import com.its.memberboardproject.dto.BoardDTO;
import com.its.memberboardproject.dto.MemberDTO;
import com.its.memberboardproject.entity.BoardEntity;
import com.its.memberboardproject.entity.BoardFileEntity;
import com.its.memberboardproject.entity.MemberEntity;
import com.its.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
      Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
      return savedId;
    }
        public MemberDTO findById(Long id){
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
            if (optionalMemberEntity.isPresent()){
                return MemberDTO.toDTO(optionalMemberEntity.get());

            }else {
                return null;
            }
        }


        public MemberDTO findByMemberEmail(String loginEmail){
            Optional<MemberEntity>  optionalMemberEntity = memberRepository.findByMemberEmail(loginEmail);

            if (optionalMemberEntity.isPresent()){
                return MemberDTO.toDTO(optionalMemberEntity.get());

            }else {
                return null;
            }
        }



        public String emailCheck(String memberEmail){
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
            if (optionalMemberEntity.isEmpty()){
                return "ok";
            }else {
                return null;
            }
        }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()){
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                MemberDTO dto = MemberDTO.toDTO(memberEntity);
                return dto;
            }else {
                return null;
            }
        }else {
            return null;
        }

    }
}

