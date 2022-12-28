package com.cainiao.training.util;

import java.util.Objects;

public class DemoUtil {
    public static final int INT_ZERO = 0;

    public static boolean isPositive(Integer value) {
        return Objects.nonNull(value) && Integer.compare(value, INT_ZERO) > 0;
    }

    public static boolean isLargerThan(Integer input, Integer compareTo) {
        if (!Objects.nonNull(input) || !Objects.nonNull(compareTo)) {
            return false;
        }

        return input.compareTo(compareTo) > 0;

    }
}
