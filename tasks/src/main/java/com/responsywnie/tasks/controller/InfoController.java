package com.responsywnie.tasks.controller;

import com.responsywnie.tasks.config.TasksConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final TasksConfigurationProperties myProp;
    private final DataSourceProperties properties;

    private InfoController(final TasksConfigurationProperties myProp,final DataSourceProperties properties) {
        this.myProp = myProp;
        this.properties = properties;
    }
    @GetMapping("/url")
    String url(){
        return properties.getUrl();
    }
    @GetMapping("/prop")
    boolean myProp(){
        return myProp.getTemplate().isAllowMultipleTasks();
    }
}
