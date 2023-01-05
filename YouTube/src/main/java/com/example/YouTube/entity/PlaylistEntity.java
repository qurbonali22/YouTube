package com.example.YouTube.entity;

import com.example.YouTube.enums.PlaylistStatus;
import com.example.YouTube.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@Table(name = "playlist")
public class PlaylistEntity {

//    id,channel_id,name,description,status(private,public),order_num

    @Id
    @GeneratedValue(generator = "generator_uuid")
    @GenericGenerator(name = "generator_uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Integer orderNum;

    @Column(name = "channel_id")
    private String channelId;
    @OneToOne
    @JoinColumn(name = "channel_id", insertable = false, updatable = false)
    private ChannelEntity channel;

    @Enumerated(EnumType.STRING)
    @Column
    private PlaylistStatus status;

}
