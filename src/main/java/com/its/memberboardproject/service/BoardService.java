package com.its.memberboardproject.service;

import com.its.memberboardproject.dto.BoardDTO;
import com.its.memberboardproject.entity.BoardEntity;
import com.its.memberboardproject.entity.BoardFileEntity;
import com.its.memberboardproject.entity.MemberEntity;
import com.its.memberboardproject.repository.BoardFileRepository;
import com.its.memberboardproject.repository.BoardRepository;
import com.its.memberboardproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public Long save(BoardDTO boardDTO) throws IOException {
        MemberEntity memberEntity =
                memberRepository.findByMemberEmail(boardDTO.getBoardWriter()).get();

        if (boardDTO.getBoardFile().get(0).isEmpty()){
            System.out.println("파일없음");
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO,memberEntity);
            return boardRepository.save(boardEntity).getId();
        }else {
            System.out.println("파일있음");
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO,memberEntity);
            Long savedId = boardRepository.save(boardEntity).getId();
            BoardEntity entity = boardRepository.findById(savedId).get();
            for (MultipartFile boardFile : boardDTO.getBoardFile()){
                String originalFileName = boardFile.getOriginalFilename();
                String storedFileName = System.currentTimeMillis()+"_"+ originalFileName;
                String savePath = "D:\\springboot_img\\"+storedFileName;
                boardFile.transferTo(new File(savePath));

                BoardFileEntity boardFileEntity = BoardFileEntity.toSaveFileEntity(entity,originalFileName,storedFileName);
                boardFileRepository.save(boardFileEntity);
            }
                return savedId;

        }
    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        List<BoardDTO> boardList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList){
            BoardDTO boardDTO = BoardDTO.boDTO(boardEntity);
            boardList.add(boardDTO);
        }
        return boardList;
    }







}
