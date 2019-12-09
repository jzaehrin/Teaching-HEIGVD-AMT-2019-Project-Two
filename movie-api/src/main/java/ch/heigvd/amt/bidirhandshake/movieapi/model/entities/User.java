package ch.heigvd.amt.bidirhandshake.movieapi.model.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;
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
    private String firstName;
    private String lastName;
    private String email;
    private Timestamp memberSince;

    @OneToMany
    private List<Media> watchedMedia;
}
