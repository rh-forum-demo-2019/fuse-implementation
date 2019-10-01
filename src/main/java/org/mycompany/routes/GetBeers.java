package org.mycompany.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.mycompany.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetBeers extends RouteBuilder {

  @Autowired
  private BeerService mBeerService;

  @Override
  public void configure() throws Exception {
    from("direct:ListBeers").log("GetBeers").process(new Processor() {

      @Override
      public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
        exchange.getIn().setBody(mBeerService.getBeers());
      }
    }).marshal().json(JsonLibrary.Jackson);
  }
}
