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

    public Long save(MemberDTO memberDTO) throws IOException {
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
}

