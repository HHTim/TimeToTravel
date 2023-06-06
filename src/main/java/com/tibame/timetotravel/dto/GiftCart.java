package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 真正和Redis對映的class
public class GiftCart {
    private Integer userId;
    private List<GiftCartItem> itemList;
}
