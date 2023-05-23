package io.github.oneincase.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BannerUtil {

    /**
     * show banner
     */
    public static void showBanner(InputStream inputStream) {
        try {
            List<String> strings = IOUtils.readLines(inputStream);
            strings.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
