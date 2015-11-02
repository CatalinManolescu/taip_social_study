package taip.commons.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import taip.commons.entity.PersistentEntity;
import taip.commons.errors.BadRequestError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for list related operations.
 *
 * @author Catalin Manolescu <cc.manolescu@gmail.com>
 */
public final class CollectionUtil {
    /**
     * Empty string array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * Add persistent entity to a list.
     *
     * @param destination Destination list
     * @param entity      PersistentEntity to add
     * @param <T>         PersistentEntity type
     * @return List
     */
    public static <T extends PersistentEntity> List<T> addPersistentEntity(
            List<T> destination, T entity) {
        List<T> list = destination != null ? destination : new ArrayList<T>();

        if (entity != null) {
            // check if item in list
            boolean found = false;
            T listItem;
            int index;
            for (index = 0; index < list.size(); index++) {
                listItem = list.get(index);

                if (listItem.getId() == entity.getId()
                        && (entity.getId() > 0 || (entity.getId() <= 0 && listItem
                        .equals(entity)))) {
                    // item found.. exit
                    found = true;
                    break;
                }
            }

            if (!found) {
                list.add(entity);
            } else {
                list.remove(index);
                list.add(entity);
            }
        }

        return list;
    }

    /**
     * Convert a list of arguments to List.
     *
     * @param <T>  Element type
     * @param args Elements to be added to the list
     * @return List without null elements
     */
    @SafeVarargs
    public static <T> List<T> toList(T... args) {
        if (args == null) {
            return Collections.emptyList();
        }

        List<T> list = new ArrayList<>(args.length);

        for (T element : args) {
            if (element == null
                    || (element instanceof String && StringUtils.isEmpty((String) element))) {
                continue;
            }
            list.add(element);
        }

        return list;
    }

    /**
     * Create sort.
     *
     * @param directions Sort directions ASC/DESC
     */
    public static Sort.Direction[] sortDirections(String[] directions) {
        Sort.Direction[] directionList = null;

        if (directions != null) {
            directionList = new Sort.Direction[directions.length];

            // convert string direction to enum instance
            String direction;
            for (int i = 0; i < directions.length; i++) {
                direction = directions[i];

                if (!StringUtils.isEmpty(direction)) {
                    direction = direction.trim();
                }

                Sort.Direction sortDirection = Sort.Direction
                        .fromStringOrNull(direction);
                // add only not null directions to list
                if (sortDirection != null) {
                    directionList[i] = sortDirection;
                }
            }
        }

        return directionList;
    }

    /**
     * Create sort.
     *
     * @param directions List of Sort.Direction
     * @param properties List of properties to sort
     * @return Sort instance
     */
    public static Sort createSort(Sort.Direction[] directions, String... properties) {
        int directionsLength = directions != null ? directions.length : 0;
        int propertiesLength = properties != null ? properties.length : 0;

        if (directionsLength != propertiesLength) {
            throw new BadRequestError(
                    "sort.direction.and.property.differ",
                    new Object[]{directionsLength, propertiesLength},
                    "Invalid sort information. Could not create sort for {0} directions and {1} properties.");
        }

        if (directionsLength == 0) {
            return null;
        }

        List<Sort.Order> orderList = new ArrayList<>(directionsLength);

        for (int i = 0; i < propertiesLength; i++) {
            orderList.add(new Sort.Order(directions[i], properties[i]));
        }

        return new Sort(orderList);
    }

    /**
     * Create page request.
     *
     * @param page       Page index
     * @param size       Page size
     * @param directions Sort.Direction enum list
     * @param properties Sort properties
     * @return Page request instance
     */
    public static Pageable createPageRequest(int page, int size,
            Sort.Direction[] directions, String... properties) {
        // return simple page request if sort info not present
        /*if (directions == null || directions.length <= 0 || properties == null) {
            return new PageRequest(page, size);
        }*/

        return createPageRequest(page, size, createSort(directions, properties));
    }

    /**
     * Create page request.
     *
     * @param page Page index
     * @param size Page size
     * @param sort Sort
     * @return Page request instance
     */
    public static Pageable createPageRequest(int page, int size, Sort sort) {
        // return simple page request if sort info not present
        if (sort == null) {
            return new PageRequest(page, size);
        }

        return new PageRequest(page, size, sort);
    }


    /**
     * Private constructor to prevent instantiation.
     */
    private CollectionUtil() {
    }
}
