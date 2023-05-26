package com.tibame.timetotravel.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_pic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_PIC_ID", insertable = false, updatable = false)
    private Integer roomPicId;

    @Column(name = "ROOM_ID", nullable = false)
    private Integer roomId;

    @Column(name = "ROOM_PIC")
    private byte[] roomPic;
}
