package taip.social.domain.entity;

import taip.commons.entity.PersistentEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Entity
@Table(name = "persons")
@SequenceGenerator(name = "persons_id_seq", sequenceName = "persons_id_seq")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "person")
public class Person implements PersistentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persons_id_seq")
    private long id;

    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;
    
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToOne(mappedBy = "person")
    private User user;
    
    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lasttName) {
        this.lastName = lasttName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
