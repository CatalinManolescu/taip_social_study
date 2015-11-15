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
@Table(name = "books")
@SequenceGenerator(name = "books_id_seq", sequenceName = "books_id_seq")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "book")
public class Book implements PersistentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_seq")
    private long id;

    @Column(name = "isbn", length = 13, nullable = true)
    private String isbn;

    @Column(name = "author", length = 40, nullable = true)
    private String author;

    @Column(name = "publisher", length = 40, nullable = true)
    private String publisher;

    @Column(name = "release_year", nullable = true)
    private Integer releaseYear;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private BookGenre genre;


    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }
}
