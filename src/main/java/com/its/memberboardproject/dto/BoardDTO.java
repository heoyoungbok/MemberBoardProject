package com.its.memberboardproject.dto;

import com.its.memberboardproject.entity.BaseEntity;
import com.its.memberboardproject.entity.BoardEntity;
import com.its.memberboardproject.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardTitle;

    private String boardWriter;

    private String boardContents;

    private int boardHits;

    private LocalDateTime boardCreatedTime;

    private LocalDateTime boardUpCreatedTime;

    private List<MultipartFile> boardFile;
    private int fileAttached;
    private List<String> originalFileName;
    private List<String> storedFileName;


    public BoardDTO(Long id, String boardWriter, String boardTitle, LocalDateTime boardCreatedTime, int boardHits) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardCreatedTime = boardCreatedTime;
        this.boardHits = boardHits;
    }


    public static BoardDTO boDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpCreatedTime(boardEntity.getUpdatedTime());
        boardDTO.setBoardHits(boardEntity.getBoardHits());

        if (boardEntity.getFileAttached() == 1) {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();

            for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()){
                originalFileNameList.add(boardFileEntity.getOriginalFileName());
                originalFileNameList.add(boardFileEntity.getStoredFileName());
            }
           boardDTO.setOriginalFileName(originalFileNameList);
            boardDTO.setStoredFileName(storedFileNameList);
        }else {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
        }
        return boardDTO;
    }
}