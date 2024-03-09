package com.hendi.banktransfersystem.infrastructure.config.web.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.hendi.banktransfersystem.usecase.userrole.SeederUserRolesUseCase;

@Component
public class ApplicationStartupListener {

    private final SeederUserRolesUseCase seederUserRolesUseCase;

    public ApplicationStartupListener(SeederUserRolesUseCase seederUserRolesUseCase) {
        this.seederUserRolesUseCase = seederUserRolesUseCase;
    }

    @EventListener(classes = ApplicationReadyEvent.class)
    public void handleApplicationStartup(ApplicationReadyEvent event) {
        seederUserRolesUseCase.execute();
    }

}
