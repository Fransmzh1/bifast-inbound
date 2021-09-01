package bifast.mock.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.mock.iso20022.custom.BusinessMessage;

@Component
public class PaymentStatusResponseProcessor implements Processor{

	@Autowired
	private MessageHistory messageHistory;

	@Override
	public void process(Exchange exchange) throws Exception {

		BusinessMessage msg = exchange.getIn().getBody(BusinessMessage.class);
		String reqMsgId = msg.getDocument().getFIToFIPmtStsReq().getTxInf().get(0).getOrgnlEndToEndId();

		BusinessMessage resp = messageHistory.get(reqMsgId);	
		if (null == resp.getAppHdr())  {
			resp = messageHistory.getAny();
			System.out.println("any: " + resp.getAppHdr().getBizMsgIdr());
			resp.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).setOrgnlEndToEndId(reqMsgId);
			resp.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).setTxSts("RJTC");
			resp.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getStsRsnInf().get(0).getRsn().setPrtry("U161");
			resp.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getStsRsnInf().get(0).getAddtlInf().remove(0);
			resp.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getStsRsnInf().get(0).getAddtlInf().add("Tidak ditemukan");
		}

		exchange.getIn().setBody(resp);
		
	}

}
