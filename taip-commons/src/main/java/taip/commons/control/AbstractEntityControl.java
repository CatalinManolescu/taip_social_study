package taip.commons.control;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import taip.commons.entity.PersistentEntity;
import taip.commons.entity.repository.EntityRepository;
import taip.commons.errors.ServerError;
import taip.commons.utils.CollectionUtil;

import java.util.List;

/**
 * Abstract class for generic CRUD operations for a specific entity.
 *
 * @param <R> repository interface type.
 * @param <E> the entity type
 * 
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public abstract class AbstractEntityControl<R extends EntityRepository<E>, E extends PersistentEntity> implements EntityControl<E> {
    public static final int MIN_ENTITY_VERSION = 1;
    private static Logger logger = LoggerFactory.getLogger(EntityControl.class);

    /**
     * Entity repository.
     *
     * @return Repository instance
     */
    protected abstract R getRepository();

    @Override
    public E create(E entity) {
        if (entity == null) {
            throw new ServerError("system.create.empty.object.error", "Unable to create empty object.");
        }

        if (entity.getId() > 0) {
            throw new ServerError("system.create.id.set.error", "The object to create has the 'id' specified.");
        }

        validate(entity);
        
        return getRepository().save(entity);
    }

    @Override
    public List<E> create(List<E> entityList) {
        return create(entityList, false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> create(List<E> entityList, boolean ignoreInvalid) {
        throw new ServerError("system.not.implemented", "FUnctionality not implemented");
    }

    @Override
    public E update(E entity) {
        if (entity == null) {
            throw new ServerError("system.update.empty.object.error", "Unable to update empty object.");
        }

        if (entity.getId() <= 0) {
            throw new ServerError("system.update.id.not.set.error", "Object to update does not have the 'id' specified.");
        }

        validate(entity);

        // save method returns the same instance it received
        // return valid entity data
        return findById(entity.getId());
    }

    @Override
    public void remove(long entityId) {
        getRepository().delete(entityId);

    }

    @Override
    public E findById(long entityId) {
        return getRepository().getOne(entityId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<E> findAll() {
        return IteratorUtils.toList(getRepository().findAll().iterator());
    }

    @Override
    public Page<E> findAll(int page, int size, String[] directions, String... properties) {
        return getRepository().findAll(
                CollectionUtil.createPageRequest(page, size, CollectionUtil.sortDirections(directions), properties));
    }

    @Override
    public Page<E> findAll(int page, int size, Sort.Direction[] directions, String... properties) {
        return getRepository().findAll(
                CollectionUtil.createPageRequest(page, size, directions, properties));
    }

    /**
     * Validate the entity before create/update.
     *
     * @param entity Entity to be validated
     */
    protected void validate(E entity) {

    }
}
