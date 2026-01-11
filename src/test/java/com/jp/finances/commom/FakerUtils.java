package com.jp.finances.commom;

import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;

public class FakerUtils {

    private final static Faker faker = new Faker(Locale.of("pt", "BR"));
    private static final Logger log = LoggerFactory.getLogger(FakerUtils.class);

    public static <T> T entity(Class<?> clazz) {
        T target;
        try {
            target = (T) clazz.getDeclaredConstructor().newInstance();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                Object value = null;
                field.setAccessible(true);
                if (field.getType().isAssignableFrom(String.class)) {
                    value = faker.lorem().word();
                } else if (field.getType().isAssignableFrom(Integer.class)) {
                    value = faker.number().randomDigitNotZero();
                } else if (field.getType().isAssignableFrom(Boolean.class)) {
                    value = faker.bool().bool();
                } else if (field.getType().isAssignableFrom(Long.class)) {
                    value = faker.number().randomNumber();
                } else if (field.getType().isAssignableFrom(Double.class)) {
                    value = faker.number().randomDouble(2, 1, 100);
                } else if (field.getType().isAssignableFrom(LocalDate.class)) {
                    value = faker.timeAndDate().birthday();
                } else if (field.getType().isAssignableFrom(BigDecimal.class)){
                    value = BigDecimal.valueOf(faker.number().randomDigitNotZero());
                }

                if(field.getName().equals("id"))
                    value = null;

                field.set(target, value);
            }

            return target;
        } catch (IllegalAccessException | InvocationTargetException |
                 InstantiationException | NoSuchMethodException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static <T> Page<T> page(Class<?> clazz, Integer size, Integer pageNumber) {
        List<T> content = new ArrayList<>();
        for (int i = 0; i < size; i++)
            content.add(entity(clazz));

        return new PageImpl<>(content, PageRequest.of(pageNumber, size), content.size());
    }

}
