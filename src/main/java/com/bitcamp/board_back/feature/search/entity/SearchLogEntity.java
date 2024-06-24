package com.bitcamp.board_back.feature.search.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "search_log")
@Table(name = "search_log") // 여기서 table 어노테이션을 사용하지 않으면 클래스 이름을 찾아서 매핑하는데  클래스 이름이  SearchLogEntity 여서 찾을 수가 없음 그래서 지정해야 함
public class SearchLogEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private String searchWord;
    private String relationWord;
    private boolean relation;

    public SearchLogEntity(String searchWord,String relationWord,boolean relation){
        this.searchWord = searchWord;
        this.relationWord = relationWord;
        this.relation = relation;
    }
}
