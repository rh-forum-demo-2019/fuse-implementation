package org.mycompany.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import com.example.beer.dto.Beer;

@Component
public class GetBeerByNameRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("direct:GetBeer").log("GetBeer - Beer name requested: ${header.name}").process(new Processor() {

      @Override
      public void process(Exchange exchange) throws Exception {
        String name = exchange.getIn().getHeader("name", String.class);
        if (name == null) {
          throw new IllegalArgumentException("must provide a name");
        }
        Beer b = new Beer();
        b.setName(name);
        b.setCountry("Denmark");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
        exchange.getIn().setBody(b);
      }
    }).marshal().json(JsonLibrary.Jackson);
  }
}
