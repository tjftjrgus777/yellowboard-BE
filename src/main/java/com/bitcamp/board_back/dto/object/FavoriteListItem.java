package com.bitcamp.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteListItem {
    private String email;
    private String nickname;
    private String profileImage;
}
