package ch.heigvd.amt.bidirhandshake.movieapi.entities;

import lombok.*;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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
@DiscriminatorValue("2")
public class WatchedMediaUser extends MediaUser {
    @Column(nullable = false)
    protected Integer rating;

    @Column(nullable = false)
    protected Timestamp watched;
}
