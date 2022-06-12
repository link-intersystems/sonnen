package com.link_intersystems.sonnen.client.api.java;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.nCopies;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class BeanJsonFormatter {
    private static final List<Class<?>> UNQUOTED_CLASSES = Arrays.asList(Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class);
    private static final List<Class<?>> DEFAULT_TYPES = new ArrayList<Class<?>>(UNQUOTED_CLASSES) {
        {
            add(String.class);
        }
    };
    private Object bean;

    public BeanJsonFormatter(Object bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        if (bean == null) {
            return "";
        }
        return toString(bean);
    }

    protected String toString(Object bean) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n");

        try {
            tryAppendBeanProperties(bean, sb);

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            try (PrintWriter pw = new PrintWriter(sw)) {
                e.printStackTrace(pw);
            }
            sb.append(sw);
        }

        return sb.toString();
    }

    private void tryAppendBeanProperties(Object bean, Appendable sb) throws Exception {
        Class<?> beanClass = bean.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, Object.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        Iterator<PropertyDescriptor> propertyDescriptorIterator = Arrays.asList(propertyDescriptors).iterator();

        while (propertyDescriptorIterator.hasNext()) {
            PropertyDescriptor pd = propertyDescriptorIterator.next();
            String propertyName = pd.getName();

            sb.append("    ");
            sb.append('"');
            sb.append(propertyName);
            sb.append('"');
            sb.append(": ");

            Object propertyValue = pd.getReadMethod().invoke(bean);

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
