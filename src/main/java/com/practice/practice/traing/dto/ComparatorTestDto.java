package com.practice.practice.traing.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComparatorTestDto {

    public Integer num;
    public String name;
    public String info;
}