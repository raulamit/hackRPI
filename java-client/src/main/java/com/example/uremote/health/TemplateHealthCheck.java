package com.example.uremote.health;

import com.codahale.metrics.health.HealthCheck;

import java.util.Optional;

public class TemplateHealthCheck extends HealthCheck {
    private final com.example.uremote.core.Template template;

    public TemplateHealthCheck(com.example.uremote.core.Template template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("woo"));
        template.render(Optional.empty());
        return Result.healthy();
    }
}
