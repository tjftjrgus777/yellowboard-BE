package com.bitcamp.board_back.dto.request.board;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentRequesetDto {

    @NotBlank
    private String content;




    
}
