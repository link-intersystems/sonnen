/*
 * Copyright (C) 2022 Link Intersystems GmbH
 *
 * This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License 2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *   SPDX-License-Identifier: EPL-2.0
 *
 *   Contributors:
 *       Link Intersystems GmbH - initial API and implementation
 */

package com.link_intersystems.sonnen.client.api.java;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.nCopies;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class JsonFormatter {
    private static final List<Class<?>> UNQUOTED_CLASSES = Arrays.asList(Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class, Number.class);
    private static final List<Class<?>> DEFAULT_TYPES = new ArrayList<Class<?>>(UNQUOTED_CLASSES) {
        {
            add(String.class);
        }
    };
    private Object object;

    public JsonFormatter(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        if (object == null) {
            return "";
        }
        return toString(object);
    }

    protected String toString(Object bean) {
        StringBuilder sb = new StringBuilder();


        try {
            tryAppend(bean, sb);

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            try (PrintWriter pw = new PrintWriter(sw)) {
                e.printStackTrace(pw);
            }
            sb.append(sw);
        }

        return sb.toString();
    }

    private void tryAppend(Object object, Appendable sb) throws Exception {
        Class<?> beanClass = object.getClass();
        if (List.class.isAssignableFrom(beanClass)) {
            appendList((List<?>) object, sb);
        } else {
            appendObject(object, sb, beanClass);
        }
    }

    private void appendObject(Object object, Appendable sb, Class<?> beanClass) throws IntrospectionException, IOException, IllegalAccessException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        Iterator<PropertyDescriptor> propertyDescriptorIterator = Arrays.asList(propertyDescriptors).iterator();

        sb.append("{\n");

        while (propertyDescriptorIterator.hasNext()) {
            PropertyDescriptor pd = propertyDescriptorIterator.next();
            String propertyName = pd.getName();

            sb.append("    ");
            sb.append('"');
            sb.append(propertyName);
            sb.append('"');
            sb.append(": ");

            Object propertyValue = pd.getReadMethod().invoke(object);

            Class<?> propertyType = pd.getPropertyType();
            if (DEFAULT_TYPES.contains(propertyType)) {
                sb.append(quote(propertyValue));
            } else {
                String subMapDataJson = toString(propertyValue);
                sb.append(indent(subMapDataJson));
            }

            if (propertyDescriptorIterator.hasNext()) {
                sb.append(',');
                sb.append("\n");
            }
        }

        sb.append("\n");
        sb.append("}\n");
    }

    private void appendList(List<?> objects, Appendable sb) throws IOException {
        Iterator<?> beans = objects.iterator();

        sb.append("[\n");
        while (beans.hasNext()) {
            Object element = beans.next();
            sb.append(toString(element));
            if (beans.hasNext()) {
                sb.append(",\n");
            }
        }
        sb.append("]\n");
    }

    private String quote(Object value) {
        if (value == null) {
            return null;
        }
        String valueAsString = String.valueOf(value);

        Class<?> valueType = value.getClass();
        if (!UNQUOTED_CLASSES.contains(valueType)) {
            valueAsString = "\"" + valueAsString + "\"";
        }

        return valueAsString;
    }

    private String indent(String text) {
        StringBuilder sb = new StringBuilder();

        String indention = String.join("", nCopies(4, " "));

        try (BufferedReader reader = new BufferedReader(new StringReader(text))) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(indention);
                sb.append(line);
                sb.append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
