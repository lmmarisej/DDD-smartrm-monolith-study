package com.smartrm.smartrmmonolith.commodity.domain.model;

import org.apache.commons.lang3.math.NumberUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;

public class DateParser {
    
    private static final DateTimeFormatter BASIC_LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(ISO_LOCAL_TIME)
            .toFormatter();
    private static final DateTimeFormatter INDEX_FILE_TIME_FORMAT = DateTimeFormatter
            .ofPattern("dd-MMM-yyyy HH:mm")
            .withLocale(Locale.ENGLISH);
    private static final DateTimeFormatter OTHER_TIME_FORMAT = DateTimeFormatter
            .ofPattern("yyyy/M/dd HH:mm");
    private static final DateTimeFormatter OTHER_TIME_FORMAT_1 = DateTimeFormatter
            .ofPattern("yyyy/M/dd H:mm:ss");
    private static final DateTimeFormatter OTHER_TIME_FORMAT_2 = DateTimeFormatter
            .ofPattern("yyyy/M/dd HH:mm:ss");
    private static final DateTimeFormatter OTHER_TIME_FORMAT_3 = DateTimeFormatter
            .ofPattern("yyyy/MM/dd HH:mm:ss");
    
    public static LocalDate asLocalDate(String text) {
        try {
            return LocalDate.parse(text, ISO_LOCAL_DATE);
        } catch (Exception e) {
            try {
                return LocalDate.parse(text, DateTimeFormatter.BASIC_ISO_DATE);
            } catch (Exception e1) {
                LocalDateTime dateTime = defaultToLocalDateTime(text);
                return dateTime == null ? null : dateTime.toLocalDate();
            }
        }
    }
    
    public static LocalDateTime asLocalDateTime(String text) {
        try {
            return LocalDateTime.parse(text, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (Exception e) {
            try {
                return LocalDateTime.parse(text, BASIC_LOCAL_DATE_TIME);
            } catch (Exception e1) {
                try {
                    return LocalDateTime.parse(text, INDEX_FILE_TIME_FORMAT);
                } catch (Exception e2) {
                    try {
                        return LocalDateTime.parse(text, OTHER_TIME_FORMAT);
                    } catch (Exception e3) {
                        try {
                            return LocalDateTime.parse(text, OTHER_TIME_FORMAT_1);
                        } catch (Exception e4) {
                            try {
                                return LocalDateTime.parse(text, OTHER_TIME_FORMAT_2);
                            } catch (Exception e5) {
                                try {
                                    return LocalDateTime.parse(text, OTHER_TIME_FORMAT_3);
                                } catch (Exception e6) {
                                    return defaultToLocalDateTime(text);
                                }
                            }
                        }
                        
                    }
                }
            }
        }
    }
    
    private static LocalDateTime defaultToLocalDateTime(String text) {
        if (NumberUtils.isDigits(text)) {
            long timestamp = NumberUtils.toLong(text, -1);
            if (timestamp > 0) {
                // seconds to milliseconds
                if (timestamp <= Integer.MAX_VALUE) {
                    timestamp *= 1000;
                }
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
            }
        }
        return null;
    }
}
