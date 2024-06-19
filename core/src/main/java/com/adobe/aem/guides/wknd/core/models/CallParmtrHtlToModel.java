package com.adobe.aem.guides.wknd.core.models;

        import org.apache.sling.api.SlingHttpServletRequest;
        import org.apache.sling.models.annotations.Default;
        import org.apache.sling.models.annotations.Model;
        import org.apache.sling.models.annotations.Optional;
        import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;

        import javax.annotation.PostConstruct;
        import java.util.regex.Pattern;

@Model(adaptables = SlingHttpServletRequest.class)
public class CallParmtrHtlToModel {

    @RequestAttribute
    private String href;

    @Optional
    @Default(values="_blank")
    @RequestAttribute
    private String target;

    @PostConstruct
    public void init() {
        if (href == null || href.isEmpty())
            return;

        boolean isLinkExternal = Pattern.compile("^https?://.*").matcher(href).matches();
        boolean isLinkInternalAsset = Pattern.compile(".*\\.(\\w){1,5}$").matcher(href).matches();

        if (!isLinkExternal && !isLinkInternalAsset) {
            href = (href.endsWith(".html") || href.contains("?")) ? href : href + ".html";
        }

    }

    public String getHref() {
        return href;
    }

    public String getTarget() {
        return target;
    }
}


//    boolean isLinkExternal = Pattern.compile("^https?://.*").matcher(href).matches();:
//        This checks if href starts with "http://" or "https://", indicating it's an external link.

//^: Asserts the start of the line.
//        https: Matches the literal characters "https".
//        ?: Makes the preceding character s optional, so it can match "http" or "https".
//        ://: Matches the literal characters "://".
//        .*: Matches any character (except for line terminators) zero or more times, effectively

//boolean isLinkInternalAsset = Pattern.compile(".*\\.(\\w){1,5}$").matcher(href).matches();:
// This checks if href ends with a file extension of up to 5 characters, indicating it's an internal asset link (like a file).