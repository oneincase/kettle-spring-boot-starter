package io.github.oneincase.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Objects;

public class IOUtil {

    public static String resourceFileToString(String resource) {
        try {
            return IOUtils.toString(Objects.requireNonNull(IOUtil.class.getClassLoader().getResourceAsStream(resource)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
