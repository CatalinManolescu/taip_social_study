package taip.social.algorithms;

import org.springframework.stereotype.Component;
import taip.social.domain.entity.Person;

import java.util.List;

/**
 * Match persons based on social preferences
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
@Component
public interface SocialMatch {
    /**
     * Find best match, for specified person.
     *
     * @param personId Id of the person for whom to find possible friends.
     * @param precision Limit of the results.
     * @return List of persons that match the user preferences.
     */
    List<Person> match(Long personId, int precision);
}
