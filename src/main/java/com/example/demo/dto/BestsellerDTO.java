package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BestsellerDTO {
    private Long potrawaId;
    private String potrawaNazwa;
    private Long sumaSprzedanych; 
}