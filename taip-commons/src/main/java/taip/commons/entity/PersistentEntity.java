package taip.commons.entity;

/**
 * Interface for persistent entities.
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public interface PersistentEntity {
    public long getId();
    public void setId(long id);
}
