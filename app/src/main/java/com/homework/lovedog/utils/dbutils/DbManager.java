package com.homework.lovedog.utils.dbutils;

import android.text.TextUtils;

import com.homework.lovedog.utils.dbutils.common.util.KeyValue;
import com.homework.lovedog.utils.dbutils.db.Selector;
import com.homework.lovedog.utils.dbutils.db.sqlite.SqlInfo;
import com.homework.lovedog.utils.dbutils.db.sqlite.WhereBuilder;
import com.homework.lovedog.utils.dbutils.db.table.DbModel;
import com.homework.lovedog.utils.dbutils.db.table.TableEntity;
import com.homework.lovedog.utils.dbutils.ex.DbException;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface DbManager extends Closeable {

    DaoConfig getDaoConfig();

    SQLiteDatabase getDatabase();

    boolean saveBindingId(Object entity) throws DbException;

    void saveOrUpdate(Object entity) throws DbException;

    void save(Object entity) throws DbException;

    void replace(Object entity) throws DbException;

    ///////////// delete
    void deleteById(Class<?> entityType, Object idValue) throws DbException;

    void delete(Object entity) throws DbException;

    void delete(Class<?> entityType) throws DbException;

    int delete(Class<?> entityType, WhereBuilder whereBuilder) throws DbException;

    ///////////// update
    void update(Object entity, String... updateColumnNames) throws DbException;

    int update(Class<?> entityType, WhereBuilder whereBuilder, KeyValue... nameValuePairs) throws DbException;

    ///////////// find
    <T> T findById(Class<T> entityType, Object idValue) throws DbException;

    <T> T findFirst(Class<T> entityType) throws DbException;

    <T> List<T> findAll(Class<T> entityType) throws DbException;

    <T> Selector<T> selector(Class<T> entityType) throws DbException;

    DbModel findDbModelFirst(SqlInfo sqlInfo) throws DbException;

    List<DbModel> findDbModelAll(SqlInfo sqlInfo) throws DbException;

    ///////////// table

    <T> TableEntity<T> getTable(Class<T> entityType) throws DbException;

    void dropTable(Class<?> entityType) throws DbException;

    void addColumn(Class<?> entityType, String column) throws DbException;

    ///////////// db

    void dropDb() throws DbException;

    void close() throws IOException;

    ///////////// custom
    int executeUpdateDelete(SqlInfo sqlInfo) throws DbException;

    int executeUpdateDelete(String sql) throws DbException;

    void execNonQuery(SqlInfo sqlInfo) throws DbException;

    void execNonQuery(String sql) throws DbException;

    Cursor execQuery(SqlInfo sqlInfo) throws DbException;

    Cursor execQuery(String sql) throws DbException;

    public interface DbOpenListener {
        void onDbOpened(DbManager db);
    }

    public interface DbUpgradeListener {
        void onUpgrade(DbManager db, int oldVersion, int newVersion);
    }

    public interface TableCreateListener {
        void onTableCreated(DbManager db, TableEntity<?> table);
    }

    public static class DaoConfig {
        private File dbDir;
        private String dbName = "jbt.db"; // default db name
        private int dbVersion = 1;
        private boolean allowTransaction = true;
        private DbUpgradeListener dbUpgradeListener;
        private TableCreateListener tableCreateListener;
        private DbOpenListener dbOpenListener;
        private String password="jbt";// default db pwd
        public DaoConfig() {

        }

        public DaoConfig setDbDir(File dbDir) {
            this.dbDir = dbDir;
            return this;
        }

        public DaoConfig setDbName(String dbName) {
            if (!TextUtils.isEmpty(dbName)) {
                this.dbName = dbName;
            }
            return this;
        }

        public DaoConfig setDbVersion(int dbVersion) {
            this.dbVersion = dbVersion;
            return this;
        }

        public DaoConfig setAllowTransaction(boolean allowTransaction) {
            this.allowTransaction = allowTransaction;
            return this;
        }

        public DaoConfig setDbOpenListener(DbOpenListener dbOpenListener) {
            this.dbOpenListener = dbOpenListener;
            return this;
        }

        public DaoConfig setDbUpgradeListener(DbUpgradeListener dbUpgradeListener) {
            this.dbUpgradeListener = dbUpgradeListener;
            return this;
        }

        public DaoConfig setTableCreateListener(TableCreateListener tableCreateListener) {
            this.tableCreateListener = tableCreateListener;
            return this;
        }
        public DaoConfig setPassword(String password) {
            this.password=password;
            return this;
        }
        public String getPassword(){
            return password;
        }
        public File getDbDir() {
            return dbDir;
        }

        public String getDbName() {
            return dbName;
        }

        public int getDbVersion() {
            return dbVersion;
        }

        public boolean isAllowTransaction() {
            return allowTransaction;
        }

        public DbOpenListener getDbOpenListener() {
            return dbOpenListener;
        }

        public DbUpgradeListener getDbUpgradeListener() {
            return dbUpgradeListener;
        }

        public TableCreateListener getTableCreateListener() {
            return tableCreateListener;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DaoConfig daoConfig = (DaoConfig) o;

            if (!dbName.equals(daoConfig.dbName)) return false;
            return dbDir == null ? daoConfig.dbDir == null : dbDir.equals(daoConfig.dbDir);
        }

        @Override
        public int hashCode() {
            int result = dbName.hashCode();
            result = 31 * result + (dbDir != null ? dbDir.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return String.valueOf(dbDir) + "/" + dbName;
        }
    }
}
