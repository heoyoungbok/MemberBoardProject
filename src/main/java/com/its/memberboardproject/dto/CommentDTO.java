package com.its.memberboardproject.dto;

import com.its.memberboardproject.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Long id;

    private String commentWriter;

    private String commentContents;

    private LocalDateTime commentCreatedDate;

    private LocalDateTime commentUpCreatedDate;
    private Long boardId;

    public static CommentDTO coDTO(CommentEntity commentEntity){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentCreatedDate(commentEntity.getCreatedTime());
        commentDTO.setCommentUpCreatedDate(commentEntity.getUpdatedTime());

        return commentDTO;
    }

}
