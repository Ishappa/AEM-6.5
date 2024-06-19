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


@Component(service = Runnable.class,immediate = true)
@Designate(ocd = Demo1SchedularConfig.class)
public class Demo1Schedular implements Runnable {

    public static final Logger log= LoggerFactory.getLogger(Demo1Schedular.class);

    @Reference
    Scheduler scheduler;

public void schedulardemo(Demo1SchedularConfig config){
    if (config.concurrent()){
        ScheduleOptions options= scheduler.EXPR(config.cronexp());
        options.name(config.schedularName());
        options.canRunConcurrently(config.concurrent());
        scheduler.schedule(this,options);
        log.error("scheduler is activated");
    }

}
    @Activate
    public void activate(Demo1SchedularConfig config){
    schedulardemo(config);
        log.error("scheduler is Activated");
}


    @Override
    public void run() {
        log.error("scheduler is Running");
    }

    protected void remove(Demo1SchedularConfig config){
        scheduler.unschedule(config.schedularName());

    }
    @Deactivate
    protected void deactivate(Demo1SchedularConfig config){
    remove(config);
    }
}
