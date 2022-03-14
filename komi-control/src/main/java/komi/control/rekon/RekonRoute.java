package komi.control.rekon;

import java.time.format.DateTimeFormatter;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import komi.control.balanceinq.EnrichmentAggregator;

@Component
public class RekonRoute extends RouteBuilder{
    @Autowired RekonProcessor rekonPrc;
    
    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("HHmmss");

	@Override
	public void configure() throws Exception {
		
		rest("/rekon")
			.get("/daily")
				.produces("text/plain")
				.to("direct:dailyrekon")
		;

		
		from("direct:dailyrekon")
			.setProperty("pr_periodType", constant("daily"))
			.process(rekonPrc)
		;

	

	}

}
