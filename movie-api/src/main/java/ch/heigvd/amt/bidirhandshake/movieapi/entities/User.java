package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    private Integer id;

    private String username;
    private String firstname;
    private String lastname;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @ToString.Exclude
    private List<WatchedMediaUser> watchedMediaUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @ToString.Exclude
    private List<ToWatchMediaUser> toWatchMediaUser;

    public enum Role {
        admin,
        user
    }
}
