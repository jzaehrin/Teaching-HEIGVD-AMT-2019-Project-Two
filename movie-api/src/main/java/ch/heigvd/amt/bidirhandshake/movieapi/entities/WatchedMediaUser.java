package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "media_user")
public class WatchedMediaUser extends MediaUser {
    @Column(nullable = false)
    protected Integer rating;

    @Column(nullable = false)
    protected Timestamp watched;
}
