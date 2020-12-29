package com.responsywnie.tasks.controller;

import com.responsywnie.tasks.config.TasksConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    private TasksConfigurationProperties myProp;
    private DataSourceProperties properties;

    private InfoController(final TasksConfigurationProperties myProp,final DataSourceProperties properties) {
        this.myProp = myProp;
        this.properties = properties;
    }
    @GetMapping("/info/url")
    String url(){
        return properties.getUrl();
    }
    @GetMapping("/info/prop")
    boolean myProp(){
        return myProp.getTemplate().isAllowMultipleTasks();
    }
}
