package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "media_user")
public class MediaUser {
    @EmbeddedId
    protected MediaUserKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    protected User user;

    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    protected Media media;
}

