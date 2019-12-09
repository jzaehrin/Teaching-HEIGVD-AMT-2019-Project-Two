package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer authId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Where(clause = "watched is not null")
    @ToString.Exclude
    private List<WatchedMediaUser> watchedMediaUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @Where(clause = "watched is null")
    @ToString.Exclude
    private List<ToWatchMediaUser> toWatchMediaUser;
}
