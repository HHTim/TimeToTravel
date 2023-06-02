package com.tibame.timetotravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftCart {
    private Integer userId;
    private List<GiftCartItem> itemList;

    public void addItem(GiftCartItem item) {
        itemList.removeIf(i -> i.getGiftId().equals(item.getGiftId()));
        itemList.add(item);
    }

    public void removeItem(Integer giftId) {
        itemList.removeIf(item -> item.getGiftId().equals(giftId));
    }

}
