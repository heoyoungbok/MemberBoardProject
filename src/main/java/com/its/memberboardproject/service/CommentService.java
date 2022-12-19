package com.its.memberboardproject.service;

import com.its.memberboardproject.dto.CommentDTO;
import com.its.memberboardproject.entity.BoardEntity;
import com.its.memberboardproject.entity.CommentEntity;
import com.its.memberboardproject.repository.BoardRepository;
import com.its.memberboardproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public Long save(CommentDTO commentDTO) {
    BoardEntity boardEntity = boardRepository.findById(commentDTO.getBoardId()).get();
    CommentEntity commentEntity = CommentEntity.toSaveEntity(boardEntity,commentDTO);
    Long id = commentRepository.save(commentEntity).getId();
    return id;

    }


    @Transactional
    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList){
            commentDTOList.add(CommentDTO.coDTO(commentEntity));

        }

        return commentDTOList;
    }


}
