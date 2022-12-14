package com.its.memberboardproject.controller;

import com.its.memberboardproject.dto.BoardDTO;
import com.its.memberboardproject.dto.CommentDTO;
import com.its.memberboardproject.service.BoardService;
import com.its.memberboardproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
//    @GetMapping
//    public String boardForm(){
//        return "main";
//    }

    @GetMapping("/save")
    public String saveForm(){
        return "boardSave";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
        boardService.save(boardDTO);
        return "redirect:/board/";
    }

    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardList = boardService.findAll();
        model.addAttribute("boardList",boardList);
        return "main";
    }

    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable , Model model){
        System.out.println("page"+pageable.getPageNumber());
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardList",boardList);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double) pageable.getPageNumber() / blockLimit))) -1 ) * blockLimit + 1;
        int endPage = ((startPage + blockLimit -1) < boardList.getTotalPages()) ? startPage + blockLimit  - 1: boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return "paging";

    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        if (commentDTOList.size() > 0){
            model.addAttribute("commentList",commentDTOList);

        }else {
            model.addAttribute("commentList","empty");
        }
        model.addAttribute("board",boardDTO);
        return "boardDetail";
     }

}
