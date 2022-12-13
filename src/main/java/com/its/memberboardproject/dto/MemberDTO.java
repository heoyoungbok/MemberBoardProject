package com.its.memberboardproject.dto;

import com.its.memberboardproject.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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

       return memberDTO;
    }



}
