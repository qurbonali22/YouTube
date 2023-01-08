package com.example.YouTube.dto.playlistVideo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistVideoDeleteDTO {
    @NotNull(message = "Playlist Id Required")
    private Integer playlistId;

    @NotBlank(message = "Video Id Required")
    private String videoId;
}
