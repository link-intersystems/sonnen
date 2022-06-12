package com.link_intersystems.sonnen.client.api.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.nCopies;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public class AbstractJsonMapData {

    private static final List<Class<?>> UNQUOTED_CLASSES = Arrays.asList(Short.class, Integer.class, Long.class, Float.class, Double.class, Boolean.class);

    protected Map<String, Object> properties;

    public AbstractJsonMapData(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return toString(properties);
    }

    @SuppressWarnings("unchecked")
    private String toString(Map<String, Object> properties) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n");

        Iterator<Map.Entry<String, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> property = iterator.next();
            Object value = property.getValue();

            sb.append("    ");
            sb.append('"');
            sb.append(property.getKey());
            sb.append('"');
            sb.append(": ");

            if (value instanceof Map) {
                Map<String, Object> propertyData = (Map<String, Object>) value;
                String subMapDataJson = toString(propertyData);
                sb.append(indent(subMapDataJson));
            } else {
                sb.append(quote(value));
            }

            if (iterator.hasNext()) {
                sb.append(',');
                sb.append("\n");
            }
        }
        sb.append("\n");
        sb.append("}\n");

        return sb.toString();
    }

    private String quote(Object value) {
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
