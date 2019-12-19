package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import lombok.*;
import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
@Table(name = "media_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorFormula("case when watched is null then 1 else 2 end")
public class MediaUser {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    protected User user;

    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    protected Media media;
}

