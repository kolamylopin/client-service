package com.hatim.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.PostConstruct;
import java.net.URI;

@Configuration
@ConditionalOnClass({ConfigServicePropertySourceLocator.class, RestTemplate.class})
public class CustomServiceBootstrapConfig {
    private final ConfigServicePropertySourceLocator locator;

    @Autowired
    public CustomServiceBootstrapConfig(ConfigServicePropertySourceLocator locator) {
        this.locator = locator;
    }

    @PostConstruct
    public void init() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(new CustomUriTemplateHandler());
        locator.setRestTemplate(restTemplate);
    }

    private class CustomUriTemplateHandler extends DefaultUriBuilderFactory {
        @Override
        public URI expand(String uriTemplate, Object... uriVars) {
            String configUriTemplate = uriTemplate.replaceAll(":[\\d]+\\/\\/", "$0config-service/");
            return super.expand(configUriTemplate, uriVars);
        }
    }
}
