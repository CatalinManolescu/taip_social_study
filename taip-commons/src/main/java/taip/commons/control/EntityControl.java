package taip.commons.control;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import taip.commons.entity.PersistentEntity;

import java.util.List;

/**
 * Interface for generic CRUD operations for a specific entity.
 *
 * @param <T> Entity type
 * 
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public interface EntityControl<T extends PersistentEntity> {
    /**
     * Create entity.
     *
     * @param entity The entity to create
     * @return persisted entity
     */
    T create(T entity);

    /**
     * Create entities.
     *
     * @param entityList List of entities to create
     * @return List of persisted entities
     */
    List<T> create(List<T> entityList);

    /**
     * Create entities.
     *
     * @param entityList     List of entities to create
     * @param excludeInvalid Exclude invalid entities
     * @return List of persisted entities
     */
    List<T> create(List<T> entityList, boolean excludeInvalid);

    /**
     * Update entity.
     *
     * @param entity The entity to update
     * @return Updated entity
     */
    T update(T entity);

    /**
     * Remove entity.
     *
     * @param entityId The entity ID
     */
    void remove(long entityId);

    /**
     * Find entity by ID.
     *
     * @param entityId The entity ID
     * @return Entity from db
     */
    T findById(long entityId);

    /**
     * Find all.
     *
     * @return All entities
     */
    List<T> findAll();

    /**
     * Find entities using sort and pagination.
     *
     * @param page       Page index
     * @param size       Page size
     * @param directions Sort direction ASC/DESC
     * @param properties Sort properties
     * @return Page
     */
    Page<T> findAll(int page, int size, String[] directions,
            String... properties);

    /**
     * Find entities using sort and pagination.
     *
     * @param page       Page index
     * @param size       Page size
     * @param directions Sort direction ASC/DESC
     * @param properties Sort properties
     * @return Page
     */
    Page<T> findAll(int page, int size, Sort.Direction[] directions,
            String... properties);
}
