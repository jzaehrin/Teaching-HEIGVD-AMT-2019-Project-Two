package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "media_user")
public class ToWatchMediaUser extends MediaUser {
    @ToString.Exclude
    protected Integer rating;
    @ToString.Exclude
    protected Timestamp watched;
}
