package cn.codingstyle.server.application;

import org.hibernate.dialect.MySQL57Dialect;

public class LocalMysqlDialect extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
