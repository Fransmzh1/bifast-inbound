package bifast.outbound.processor;

import java.lang.reflect.Method;
import java.util.Optional;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.outbound.model.FaultClass;
import bifast.outbound.pojo.ChnlFailureResponsePojo;
import bifast.outbound.pojo.ResponseMessageCollection;
import bifast.outbound.repository.FaultClassRepository;

@Component
public class ExceptionToFaultMapProcessor implements Processor {
	@Autowired 
	private FaultClassRepository faultClassRepo;

	@Override
	public void process(Exchange exchange) throws Exception {

		Object objException = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Object.class);
		String exceptionClassName = objException.getClass().getName();
		Optional<FaultClass> oFaultClass = faultClassRepo.findByExceptionClass(exceptionClassName);
		
		ChnlFailureResponsePojo fault = new ChnlFailureResponsePojo();
	
		ResponseMessageCollection responseCol = exchange.getMessage().getHeader("hdr_response_list", ResponseMessageCollection.class);
		
		int statusCode = 500;
		exchange.getMessage().setHeader("hdr_error_status", "ERROR-CICONN");
		fault.setFaultCategory("ERROR-CICONN");

		try {
			Method getStatusCode = objException.getClass().getMethod("getStatusCode");
			statusCode = (int) getStatusCode.invoke(objException);
		} catch(NoSuchMethodException noMethodE) {}
		
		if (exceptionClassName.equals("java.net.SocketTimeoutException"))
			fault.setFaultCategory("TIMEOUT-CICONN");
		else if (statusCode == 504) {
			fault.setFaultCategory("TIMEOUT-CICONN");
		}
		else 
			fault.setFaultCategory("ERROR-CICONN");

		String description = "Check error log";
		try {
			Method getMessage = objException.getClass().getMethod("getMessage");
			description = (String) getMessage.invoke(objException);
			description = objException.getClass().getSimpleName() + ": " + description;
			if (description.length()>250)
				description = description.substring(0,249);
		}
		catch(NoSuchMethodException noMethodE) {
			description = "Check error log";
		}
		fault.setDescription(description);

		if (statusCode == 504) {
			fault.setErrorCode("K000");
		}
		else if (oFaultClass.isPresent()) {
			fault.setErrorCode(oFaultClass.get().getReason());
		}
		else {
			fault.setErrorCode("U220");
		}
		
		responseCol.setFault(fault);
		responseCol.setLastError(description);
		exchange.getMessage().setHeader("hdr_response_list", responseCol);

		exchange.getMessage().setBody(fault, ChnlFailureResponsePojo.class);
	}

}
