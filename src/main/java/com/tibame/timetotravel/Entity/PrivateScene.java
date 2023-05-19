package com.tibame.timetotravel.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRIVATE_SCENE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateScene {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRIVATE_SCENE_ID", nullable = false)
    private Integer privateSceneId;

    @Column(name = "COM_ID", nullable = false)
    private Integer comId;

    @Column(name = "PRIVATE_SCENE_NAME", nullable = false)
    private String privateSceneName;

    @Column(name = "PRIVATE_SCENE_DESC", nullable = false)
    private String privateSceneDesc;

    @Column(name = "PRIVATE_SCENE_PIC")
    private byte[] privateScenePic;
}
