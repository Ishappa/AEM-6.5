package com.adobe.aem.guides.wknd.core.graphQL;

        import org.apache.http.HttpEntity;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.util.EntityUtils;
        import org.osgi.service.component.annotations.Component;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.http.HttpClient;

        import static java.net.http.HttpClient.*;

@Component(
        service = {javax.servlet.Servlet.class},
        property = {
                "sling.servlet.methods=POST",
                "sling.servlet.paths=/bin/mygraphqlservlet1"
        }
)
public class GraphQlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String endPoint = "https://me.ooka.com/graphql";
        String graphqlQuery = "{\n" +
                "  \"query\": \"query getProductsBySku($skus: [String]) { products(filter: {sku: {in: $skus}}) { items { __typename id name product_eyebrow_badge product_eyebrow_badge_text sku brand brand_text addtocart_popup_cms is_bulk_product small_image { url } url_key mp_label_data { priority label_template label_image same list_template list_image } short_description { html } price_range { __typename minimum_price { __typename regular_price { __typename value currency } final_price { __typename value currency } discount { __typename amount_off } } maximum_price { final_price { value currency } } } special_price } total_count aggregations { attribute_code count label options { label value count } } }}\"\n" +
                "}";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(endPoint);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("authority", "me.ooka.com");
        httpPost.setHeader("scheme", "https");
        httpPost.setEntity(new StringEntity(graphqlQuery));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity entity = httpResponse.getEntity();

        if (entity != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"))) {
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                resp.getWriter().write(String.valueOf(response));
            } finally {
                // Ensure the entity content is fully consumed/closed
                EntityUtils.consume(entity);

            }

        }

    }
}