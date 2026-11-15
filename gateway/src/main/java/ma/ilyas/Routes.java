package ma.ilyas;
import org.springframework.context.annotation.*;import org.springframework.cloud.gateway.route.*;import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
@Configuration public class Routes {
@Bean @Profile("!static") RouteLocator dynamic(RouteLocatorBuilder b){return routes(b,"lb://");}
@Bean @Profile("static") RouteLocator fixed(RouteLocatorBuilder b){return b.routes().route(r->r.path("/customers/**").uri("http://localhost:8082")).route(r->r.path("/products/**").uri("http://localhost:8083")).route(r->r.path("/bills/**").uri("http://localhost:8084")).build();}
private RouteLocator routes(RouteLocatorBuilder b,String x){return b.routes().route(r->r.path("/customers/**").uri(x+"customer-service")).route(r->r.path("/products/**").uri(x+"inventory-service")).route(r->r.path("/bills/**").uri(x+"billing-service")).build();}}
