package taip.commons.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import taip.commons.entity.PersistentEntity;

/**
 * Extension of {@link JpaRepository} to provide additional methods to retrieve entities.
 *
 * @param <E> the entity type
 * 
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@NoRepositoryBean
public interface EntityRepository<E extends PersistentEntity> extends JpaRepository<E, Long> {

}
