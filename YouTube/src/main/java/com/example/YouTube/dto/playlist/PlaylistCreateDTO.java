package com.example.YouTube.dto.playlist;

import com.example.YouTube.enums.PlaylistStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistCreateDTO {
    @NotBlank(message = "Channel id required")
    private String channelId;

    @NotBlank(message = "Playlist name required")
    private String name;

    @NotNull(message = "Playlist description must be not null ")
    private String description;

    @NotNull(message = "Playlist status requires")
    private PlaylistStatus status;
}
