package com.neusoft.mall.common.config;

/**
 * Created by chenyingmiao on 10/10/2017 AD.
 */
public final class RouteConstants {

    private static final int VERSION = 1;

    private static final String URI = "/api";

    private static final String MOUNT = "/v" + VERSION;

    public static final String PREFIX = URI + MOUNT; // /api/v1/

    private RouteConstants() {
    }
}

