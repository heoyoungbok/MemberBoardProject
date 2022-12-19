package com.its.memberboardproject.controller;

import com.its.memberboardproject.dto.BoardDTO;
import com.its.memberboardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final BoardService boardService;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/search")
    public String search(@RequestParam ("type") String type, @RequestParam("q") String q ,
                         Model model){
        List<BoardDTO> searchList = boardService.search(type,q);
        model.addAttribute("boardList",searchList);
        return "main";
    }
}
