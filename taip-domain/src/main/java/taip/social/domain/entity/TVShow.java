package taip.social.domain.entity;

import taip.commons.entity.PersistentEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Entity
@Table(name = "tv_shows")
@SequenceGenerator(name = "tv_shows_id_seq", sequenceName = "tv_shows_id_seq")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "tv_show")
public class TVShow implements PersistentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tv_shows_id_seq")
    private long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private TVShowGenre genre;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TVShowGenre getGenre() {
        return genre;
    }

    public void setGenre(TVShowGenre genre) {
        this.genre = genre;
    }
}
