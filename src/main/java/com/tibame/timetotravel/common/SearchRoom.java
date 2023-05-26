package com.tibame.timetotravel.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRoom {
    Integer comId;
    String comName;
    String comAddress;
    String roomDesc;
    byte[] roomPhoto;
    List<Integer> orderRanks;
}
