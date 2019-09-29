package org.mycompany.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.mycompany.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.beer.dto.Beer;

@Component
public class GetBeerByNameRoute extends RouteBuilder {

  @Autowired
  private BeerService mBeerService;

  @Override
  public void configure() throws Exception {
    from("direct:GetBeer").log("GetBeer - Beer name requested: ${header.name}").process(new Processor() {

      @Override
      public void process(Exchange exchange) throws Exception {
        String name = exchange.getIn().getHeader("name", String.class);
        if (name == null) {
          throw new IllegalArgumentException("must provide a name");
        }
        Beer beer = mBeerService.getBeerByName(name);
        if(beer == null) {
          exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 204);
          exchange.getIn().setBody(constant("{}"));
        } else {
          exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
          exchange.getIn().setBody(beer);
        }
      }
    }).marshal().json(JsonLibrary.Jackson);
  }
}
