package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "media_user")
@DiscriminatorValue("T")
@Where(clause = "watched is null")
@NamedNativeQueries({
        @NamedNativeQuery(name = "media_user.changeType",
                query = "UPDATE movieDBidir.media_user SET watched = :watched, rating = :rating WHERE user_id = :user_id AND media_id = :media_id")
})
public class ToWatchMediaUser extends MediaUser {
    @ToString.Exclude
    protected Integer rating;

    @ToString.Exclude
    protected Timestamp watched;
}
