package taip.social.domain.entity;

import taip.commons.entity.PersistentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private String lasttName;
    
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
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

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
