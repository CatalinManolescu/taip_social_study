package taip.social.api.comparators;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.ResourceComparator;
import org.apache.cxf.jaxrs.impl.UriInfoImpl;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.model.URITemplate;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import java.lang.reflect.Method;

/**
 * Simple comparator to use multiple resources with the same @Path.
 *
 * @author Laura Asoltanei <laura.asoltanei@gmail.com>
 */
public class SimpleResourceComparator implements ResourceComparator {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleResourceComparator.class);

    public static final String PATH_SEPARATOR = "/";

    private String getRequestPath(URITemplate uriTemplate, Message message) {
        String basePath = StringUtils.startsWith(uriTemplate.getValue(), PATH_SEPARATOR) ?
                uriTemplate.getValue().substring(1) : uriTemplate.getValue();

        UriInfoImpl uriInfo = new UriInfoImpl(message);
        int index = uriInfo.getPath().indexOf(basePath);
        if (index >= 0) {
            return uriInfo.getPath().substring(index + basePath.length());
        }

        return uriInfo.getPath();
    }
    @Override
    public int compare(ClassResourceInfo cri1, ClassResourceInfo cri2, Message message) {
        int result = 0;
        if (cri1 != cri2) {
            result = URITemplate.compareTemplates(cri1.getURITemplate(), cri2.getURITemplate());
            if (result == 0) {
                //same base path so let's check the methods
                String path = getRequestPath(cri1.getURITemplate(), message);

                Method[] methods = cri1.getServiceClass().getMethods();
                for (Method method : methods) {
                    Path annotationPath = method.getAnnotation(javax.ws.rs.Path.class);
                    if (annotationPath != null) {
                        String resourcePath = StringUtils.startsWith(annotationPath.value(), PATH_SEPARATOR) ?
                                annotationPath.value().substring(1) : annotationPath.value();
                        String[] parts = resourcePath.split(PATH_SEPARATOR);
                        if (path.startsWith(parts[0])) {
                            result = -1;
                        }
                    }
                }

                if (result != -1) {
                    return 1;
                }
            }
        }
        return result;
    }

    @Override
    public int compare(OperationResourceInfo oper1, OperationResourceInfo oper2, Message message) {
        return 0;
    }
}
