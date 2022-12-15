package com.its.memberboardproject.dto;

import com.its.memberboardproject.entity.BoardFileEntity;
import com.its.memberboardproject.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberMobile;

    private MultipartFile memberProfile;

    private  int fileAttached;
    private String originalFileName;

    private String storedFileName;
    private LocalDateTime memberCreatedDate;
    private LocalDateTime memberUpCreatedDate;

    public static MemberDTO toDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberMobile(memberEntity.getMemberMobile());
        memberDTO.setMemberCreatedDate(memberEntity.getCreatedTime());
        memberDTO.setMemberUpCreatedDate(memberEntity.getUpdatedTime());

        if (memberEntity.getFileAttached() == 0){
            memberDTO.setFileAttached(memberEntity.getFileAttached());

        }else{
            memberDTO.setFileAttached(memberEntity.getFileAttached());
            memberDTO.setOriginalFileName(memberEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            memberDTO.setStoredFileName(memberEntity.getBoardFileEntityList().get(0).getStoredFileName());
        }

       return memberDTO;
    }

}
