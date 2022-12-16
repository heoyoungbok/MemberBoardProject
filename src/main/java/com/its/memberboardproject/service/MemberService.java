package com.its.memberboardproject.service;

import com.its.memberboardproject.dto.BoardDTO;
import com.its.memberboardproject.dto.MemberDTO;
import com.its.memberboardproject.entity.BoardEntity;
import com.its.memberboardproject.entity.BoardFileEntity;
import com.its.memberboardproject.entity.MemberEntity;
import com.its.memberboardproject.repository.BoardFileRepository;
import com.its.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(MemberDTO memberDTO) throws IOException {
        if (memberDTO.getMemberProfile().isEmpty()) {
            MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
            memberRepository.save(memberEntity);
        } else {
            MultipartFile memberFile = memberDTO.getMemberProfile();
            String originalFileName = memberFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "D:\\springboot_img\\" + storedFileName;
            memberFile.transferTo(new File(savePath));
            MemberEntity memberEntity = MemberEntity.toSaveMemberFileEntity(memberDTO);
            Long savedId = memberRepository.save(memberEntity).getId();
            MemberEntity entity = memberRepository.findById(savedId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toMemberFileEntity(entity, originalFileName, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }

    }

    @Transactional
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());

        } else {
            return null;
        }
    }

@Transactional
    public MemberDTO findByMemberEmail(String loginEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(loginEmail);

        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());

        } else {
            return null;
        }
    }


    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (optionalMemberEntity.isEmpty()) {
            return "ok";
        } else {
            return null;
        }
    }

    @Transactional
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                MemberDTO dto = MemberDTO.toDTO(memberEntity);
                return dto;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }


    public Long update(MemberDTO memberDTO) throws IOException {

        Long savedId;
        if (memberDTO.getMemberProfile().isEmpty()) {          //없다면 파일 없는채로 수정
            MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);

            return memberRepository.save(memberEntity).getId();
        } else {
            MultipartFile memberFile = memberDTO.getMemberProfile();
            String originalFileName = memberFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "D:\\springboot_img\\" + storedFileName;
            memberFile.transferTo(new File(savePath));
            MemberEntity memberEntity = MemberEntity.toUpdateFileEntity(memberDTO); // 파일 있다면 수정 쿼리
            savedId = memberRepository.save(memberEntity).getId();
            MemberEntity entity = memberRepository.findById(savedId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toUpdateFileEntity(entity, originalFileName, storedFileName);
            boardFileRepository.save(boardFileEntity);

        }
        return savedId;
    }

}
