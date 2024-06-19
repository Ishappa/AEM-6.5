package com.adobe.aem.guides.wknd.core.listeners;

        import org.apache.sling.jcr.api.SlingRepository;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Deactivate;
        import org.osgi.service.component.annotations.Reference;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.jcr.Session;
        import javax.jcr.observation.Event;
        import javax.jcr.observation.EventIterator;
        import javax.jcr.observation.EventListener;

@Component(immediate = true, service = EventListener.class)
public class EventHandlerOne implements EventListener {
    private static final Logger log = LoggerFactory.getLogger(EventHandlerOne.class);
    private Session session;

    @Reference
    SlingRepository slingRepository;

    @Activate
    public void activate() {
        try {
            session = slingRepository.loginService("admin", null);
            if (session != null) {
                session.getWorkspace().getObservationManager().addEventListener(
                        this,
                        Event.NODE_ADDED | Event.PROPERTY_ADDED,
                        "/content/wknd/us/article-page",
                        true,
                        null,
                        null,
                        false
                );
                log.info("Event listener activated successfully");
            } else {
                log.error("Session could not be obtained");
            }
        } catch (Exception e) {
            log.error("Exception during event listener activation: {}", e.getMessage());
            if (session != null) {
                session.logout();
            }
        }
    }

    @Override
    public void onEvent(EventIterator eventIterator) {
        try {
            while (eventIterator.hasNext()) {
                Event event = eventIterator.nextEvent();
                log.info("Type: {}, Resource41: {}", event.getType(), event.getPath());
                log.info("Event occurred in EventHandlerOne");
            }
        } catch (Exception e) {
            log.error("Error while handling event: {}", e.getMessage());
        }
    }

    @Deactivate
    public void deactivate() {
        if (session != null) {
            session.logout();
            log.info("Session logged out in deactivate()");
        }
    }
}


