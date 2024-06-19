package com.adobe.aem.guides.wknd.core.listeners;
        import org.apache.sling.api.resource.ResourceResolverFactory;
        import org.apache.sling.jcr.api.SlingRepository;
        import org.osgi.service.component.ComponentContext;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.jcr.RepositoryException;
        import javax.jcr.Session;
        import javax.jcr.SimpleCredentials;
        import javax.jcr.observation.Event;
        import javax.jcr.observation.EventIterator;
        import javax.jcr.observation.EventListener;
        import javax.jcr.observation.ObservationManager;
@Component(service = EventListener.class,immediate = true)
public class JcrEvent implements EventListener {

    private static final Logger LOGGER= LoggerFactory.getLogger(JcrEvent.class);
    @Reference
    private SlingRepository repository;
    @Reference
    ResourceResolverFactory resolverFactory;

    private ObservationManager observationManager;

    private final String[] nodeType=new String[] {"cq:Page","cq:pageContent","nt:unstructured","nt:folder"};

    protected void activate(ComponentContext context) throws RepositoryException {
        Session session=repository.login(new SimpleCredentials("admin","admin".toCharArray()));

        session.getWorkspace().getObservationManager().addEventListener(
                this,
                Event.NODE_ADDED|Event.PROPERTY_ADDED,
                "/content/wknd/us/en",
                true,
                null,
                null,
                false);
        LOGGER.info(">>>>Property added or changed added");
    }
    protected void deactivate(){
        if(observationManager!=null){
            try {
                observationManager.removeEventListener(this);
                LOGGER.info(">>>>listener is removed");
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onEvent(EventIterator events) {

        try {
            while (events.hasNext()) {
                LOGGER.info("on EventPath='{}'", events.nextEvent().getPath());
            }
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Activating JcrEvent listener...");
    }

}
