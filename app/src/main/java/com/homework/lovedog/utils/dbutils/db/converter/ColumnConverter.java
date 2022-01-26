package com.homework.lovedog.utils.dbutils.db.converter;


import com.homework.lovedog.utils.dbutils.db.sqlite.ColumnDbType;

import net.sqlcipher.Cursor;


public interface ColumnConverter<T> {

    T getFieldValue(final Cursor cursor, int index);

    Object fieldValue2DbValue(T fieldValue);

    ColumnDbType getColumnDbType();
}
