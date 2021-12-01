package com.homework.lovedog.http;


import com.homework.lovedog.config.Config;

import rxhttp.wrapper.annotation.DefaultDomain;
import rxhttp.wrapper.annotation.Domain;


public class BaseUrl {

    @DefaultDomain
    public static String PLATFORM_URL = Config.PLATFORM_URL;
}
