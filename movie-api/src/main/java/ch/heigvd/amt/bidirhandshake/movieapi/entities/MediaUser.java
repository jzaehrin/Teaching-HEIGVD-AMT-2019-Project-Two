package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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

