package com.empserver.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class WriteReadRoutingDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<DataSourceMode> dataSourceMode = new ThreadLocal<DataSourceMode>();

    public static enum DataSourceMode {
        write,
        read
    }

    public static void writable() {
        dataSourceMode.set(DataSourceMode.write);
    }

    public static void readable() {
        dataSourceMode.set(DataSourceMode.read);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceMode.get();
    }

    public static void clear() {
        dataSourceMode.remove();
    }
}
