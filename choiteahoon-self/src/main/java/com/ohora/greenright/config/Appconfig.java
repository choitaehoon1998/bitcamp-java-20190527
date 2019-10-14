package com.ohora.greenright.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@ComponentScan(
    basePackages ="com.ohora.greenright",
    excludeFilters = {
        @Filter(type = FilterType.REGEX ,pattern ="com.ohora.greenright.web.*" )
    })
public class Appconfig {
}
