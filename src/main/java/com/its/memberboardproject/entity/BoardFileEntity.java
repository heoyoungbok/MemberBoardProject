package com.its.memberboardproject.entity;

import com.its.memberboardproject.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board_file_table")
public class BoardFileEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private MemberEntity MemberEntity;

    public static BoardFileEntity toSaveFileEntity(BoardEntity entity, String originalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(entity);

        return boardFileEntity;
    }

    public static BoardFileEntity toMemberFileEntity(MemberEntity entity, String originalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setMemberEntity(entity);

        return boardFileEntity;

    }
    public static  BoardFileEntity toUpdateFileEntity(MemberEntity entity,String originalFileName ,String storedFileName){
      BoardFileEntity boardFileEntity =new BoardFileEntity();
      boardFileEntity.setOriginalFileName(originalFileName);
      boardFileEntity.setStoredFileName(storedFileName);
      boardFileEntity.setMemberEntity(entity);

      return boardFileEntity;
    }
    public static BoardFileEntity toUpdatdBoardFileEntity(BoardEntity entity, String originalFileName, String storedFileName) {
        BoardFileEntity boardFileEntity = new BoardFileEntity();
        boardFileEntity.setOriginalFileName(originalFileName);
        boardFileEntity.setStoredFileName(storedFileName);
        boardFileEntity.setBoardEntity(entity);

        return boardFileEntity;
    }


}