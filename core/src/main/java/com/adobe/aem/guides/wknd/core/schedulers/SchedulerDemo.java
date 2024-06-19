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

@Component(service = Runnable.class, immediate = true )
@Designate( ocd = SchedulerDemoConfig.class)
public class SchedulerDemo implements Runnable {

    public static final Logger log= LoggerFactory.getLogger(SchedulerDemo.class);
    @Reference
    Scheduler scheduler;


    public void aemScheduler(SchedulerDemoConfig config)
    {
        if(config.concurrent()) {
            ScheduleOptions options = scheduler.EXPR(config.cronexpression());
            options.name(config.schedularname());
            options.canRunConcurrently(config.concurrent());
            scheduler.schedule(this,options);
            log.error("SchedulerDemo is Added");
        }
        else {
            log.error("SchedulerDemo is not Added");
        }

    }
    @Activate
    protected void start(SchedulerDemoConfig config)
    {

        aemScheduler(config);
    }
    @Override
    public void run() {
        log.info("SchedulerDemo is Running");
    }

    protected void remove(SchedulerDemoConfig config)
    {
        scheduler.unschedule(config.schedularname());
    }
    @Deactivate
    protected void deactivate(SchedulerDemoConfig config)
    {
        remove(config);
    }



}
