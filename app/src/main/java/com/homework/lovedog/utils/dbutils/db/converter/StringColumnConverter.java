package com.homework.lovedog.utils.dbutils.db.converter;


import com.homework.lovedog.utils.dbutils.db.sqlite.ColumnDbType;

import net.sqlcipher.Cursor;


public class StringColumnConverter implements ColumnConverter<String> {
    @Override
    public String getFieldValue(final Cursor cursor, int index) {
        return cursor.isNull(index) ? null : cursor.getString(index);
    }

    @Override
    public Object fieldValue2DbValue(String fieldValue) {
        return fieldValue;
    }

    @Override
    public ColumnDbType getColumnDbType() {
        return ColumnDbType.TEXT;
    }
}
