package com.adobe.aem.guides.wknd.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = Runnable.class, immediate = true)
@Designate(ocd = Demo2Config.class)
public class Demo2Imple implements Runnable {
    private static final Logger log= LoggerFactory.getLogger(Demo2Imple.class);
    @Reference
    Scheduler scheduler;

    public void scheduler(Demo2Config config){
            ScheduleOptions options=scheduler.EXPR(config.cronExpresion());
            options.name(config.schedulerName());
            options.canRunConcurrently(false);
            scheduler.schedule(this,options);
                   }
    @Override
    public void run() {
        log.info("Running ");
    }



    @Activate

    public void active(Demo2Config config){
        log.info("Scheduler is activated");
        scheduler(config);
    }



    protected void remove(Demo2Config config){
        scheduler.unschedule(config.schedulerName());

    }
    @Deactivate
    protected void deactivate(Demo2Config config){
        remove(config);
        log.info("deactivated");
    }



}
