package com.oraclejava.dao;

import com.oraclejava.model.QPhone;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class PhonePredicate {
    public static Predicate search(String name) {
        QPhone phone = QPhone.phone;
        // BooleanBuilder builder = new BooleanBuilder();

        Predicate predicate = null;
        if (name != null) {
            // builder.and(phone.name.like("%" + name + "%"));
            predicate = phone.name.like("%" + name + "%");
        }
        // return builder;
        return predicate;
    }
}
