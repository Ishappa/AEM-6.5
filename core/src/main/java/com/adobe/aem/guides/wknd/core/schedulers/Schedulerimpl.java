package com.adobe.aem.guides.wknd.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class , immediate = true)
@Designate( ocd = SchedularConfig.class)
public class Schedulerimpl implements Runnable {

    public static final Logger log= LoggerFactory.getLogger(Schedulerimpl.class);
    @Reference
    Scheduler scheduler;
    @Activate
    protected void start(SchedularConfig config)
    {
        log.info("Scheduler is activated");
        aemScheduler(config);
    }
    @Deactivate
    protected void deactivate(SchedularConfig config)
    {
        remove(config);
    }

    protected void remove(SchedularConfig config)
    {
        scheduler.unschedule(config.schedularname());
    }
    public void aemScheduler(SchedularConfig config)
    {
        if(config.concurrent()) {
            ScheduleOptions options = scheduler.EXPR(config.cornexpression());
            options.name(config.schedularname());
            options.canRunConcurrently(config.concurrent());
            scheduler.schedule(this,options);
            log.error("This Scheduler is Activated");
        }
        else {
            log.error("This not activated");
        }

    }
    @Override
    public void run() {
        log.error("Training Scheduler");
    }
}
