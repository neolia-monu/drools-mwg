package org.monu.droolsmwg.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.monu.droolsmwg.DroolsMwgApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DroolConfig {

    private KieServices kieServices = KieServices.Factory.get();
    private Logger logger = LoggerFactory.getLogger(DroolsMwgApplication.class);

    private void getKieRepository(){
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("rules/offer.drl"));
        return kieFileSystem;
    }

    @Bean
    public KieContainer getContainer() throws IOException {
        logger.info("... Container started ...");
        getKieRepository();
        KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
        kieBuilder.buildAll();

        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kContainer;
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        logger.info("... Session Created ...");
        return getContainer().newKieSession();
    }
}
