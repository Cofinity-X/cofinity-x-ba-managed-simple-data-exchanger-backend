package org.eclipse.tractusx.sde.test.utils;

import java.time.Instant;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestUtils {

    private static final String UNIQUE_SEPARATOR = ":-:";

    public static String wrapStringWithTimestamp(String string) {

        return UNIQUE_SEPARATOR + string + UNIQUE_SEPARATOR + Instant.now();
    }

    public static String unWrapStringWithTimestamp(String string) {
        String[] array = string.split(UNIQUE_SEPARATOR);
        return array[1];
    }

    public static Map<String, String> normalize(Map<String, String> input) {
        return input.entrySet().stream().map(entry -> Map.entry(normalizeString(entry.getKey()), normalizeString(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static String normalizeString(String input) {
        Pattern r = Pattern.compile("\"(.+)\"");

        Matcher m = r.matcher(input);

        if (m.matches()) {
            return m.group(1);
        } else {
            return "";
        }

    }
}
