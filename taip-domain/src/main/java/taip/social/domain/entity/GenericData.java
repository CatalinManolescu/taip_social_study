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
@Table(name = "generic_data")
@SequenceGenerator(name = "generic_data_id_seq", sequenceName = "generic_data_id_seq")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "generic_data")
public class GenericData implements PersistentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generic_data_id_seq")
    private long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DataSource type;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private DataCategory category;

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

    public DataSource getType() {
        return type;
    }

    public void setType(DataSource type) {
        this.type = type;
    }

    public DataCategory getCategory() {
        return category;
    }

    public void setCategory(DataCategory category) {
        this.category = category;
    }
}
