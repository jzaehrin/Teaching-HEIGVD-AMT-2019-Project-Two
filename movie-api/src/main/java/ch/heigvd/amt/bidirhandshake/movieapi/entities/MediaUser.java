package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import ch.heigvd.amt.bidirhandshake.movieapi.entities.keys.MediaUserKey;
import lombok.*;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "media_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("case when rating is null then 'T' else 'W' end")
@DiscriminatorValue("null")
@NamedNativeQuery(name = "media_user.deleteEntry",
        query = "DELETE FROM movieDBidir.media_user WHERE user_id = :user_id AND media_id = :media_id")
public abstract class MediaUser {
    @EmbeddedId
    protected MediaUserKey id;

    @ManyToOne
    @MapsId("userId")
    //@JoinColumn(name = "user_id")
    protected User user;

    @ManyToOne
    @MapsId("mediaId")
    //@JoinColumn(name = "media_id")
    protected Media media;
}

