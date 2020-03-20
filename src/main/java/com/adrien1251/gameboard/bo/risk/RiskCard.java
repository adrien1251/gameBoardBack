package com.adrien1251.gameboard.bo.risk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskCard implements Serializable {
    private String name;
    private String detail;
    private String image;
}