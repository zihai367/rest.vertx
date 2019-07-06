package com.zandero.rest.reader;

import com.zandero.rest.context.ContextProvider;
import com.zandero.rest.data.ClassFactory;
import com.zandero.rest.exception.ClassFactoryException;
import com.zandero.rest.exception.ContextException;
import com.zandero.rest.injection.InjectionProvider;
import io.vertx.ext.web.RoutingContext;

public class GenericBeanReader {

    public Object read(Class<?> dataType,
                       Class<? extends ContextProvider> contextProvider,
                       InjectionProvider injectionProvider,
                       RoutingContext context) {

        try {
            Object bean = ClassFactory.newInstanceOf(dataType, injectionProvider, context);

            // once we have a bean we can populate his properties
            populate(bean);

            return bean;

        } catch (ClassFactoryException | ContextException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private void populate(Object bean) {

    }
}
