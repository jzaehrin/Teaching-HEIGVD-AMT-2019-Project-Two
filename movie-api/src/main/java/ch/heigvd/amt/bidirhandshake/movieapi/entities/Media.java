package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "medias")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Timestamp release;
    private Integer duration;
    private String mainGenre;
    private Integer rating;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "media", cascade = {CascadeType.ALL} )
    @ToString.Exclude
    private List<WatchedMediaUser> watchedMediaUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "media", cascade = {CascadeType.ALL} )
    @ToString.Exclude
    private List<ToWatchMediaUser> toWatchMediaUser;
}
