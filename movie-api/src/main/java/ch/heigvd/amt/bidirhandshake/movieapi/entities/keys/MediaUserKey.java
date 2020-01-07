package ch.heigvd.amt.bidirhandshake.movieapi.entities.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class MediaUserKey implements Serializable {
    @Column(name = "media_user.user_id")
    private Integer userId;

    @Column(name = "media_user.media_id")
    private Integer mediaId;
}
