package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "medias")
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private Timestamp release;
    private Integer duration;
    private String mainGenre;
    private Integer rating;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "media")
    @Where(clause = "watched is not null")
    @ToString.Exclude
    private List<WatchedMediaUser> watchedMediaUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "media")
    @Where(clause = "watched is null")
    @ToString.Exclude
    private List<ToWatchMediaUser> toWatchMediaUser;
}
