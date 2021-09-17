package bifast.mock.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.mock.persist.MockPacs002;
import bifast.mock.persist.MockPacs002Repository;

@Component
public class CreditResponseStoreProcessor implements Processor {

    @Autowired
    private MockPacs002Repository mockPacs002Repo;

    @Override
    public void process(Exchange exchange) throws Exception {

        BusinessMessage responseMsg = exchange.getMessage().getHeader("hdr_ctResponseObj", BusinessMessage.class);
        // String fullMsg = exchange.getMessage().getBody(String.class);

        String sts = exchange.getMessage().getHeader("hdr_ctRespondStatus", String.class);
        
        if (sts.equals("RJCT")) {
        		// simpan sbg history
			MockPacs002 pacs002 = new MockPacs002();
			pacs002.setBizMsgIdr(responseMsg.getAppHdr().getBizMsgIdr());
	
			// pacs002.setFullMessage(encryptedMsg);
			
	        pacs002.setOrgnlEndToEndId(responseMsg.getDocument().getFiToFIPmtStsRpt().getTxInfAndSts().get(0).getOrgnlEndToEndId());
			
	        pacs002.setOrgnlMsgId(responseMsg.getDocument().getFiToFIPmtStsRpt().getOrgnlGrpInfAndSts().get(0).getOrgnlMsgId());
			
	        pacs002.setOrgnlMsgName(responseMsg.getAppHdr().getMsgDefIdr());
            pacs002.setTrxType("CreditConfirmation");
	
	        mockPacs002Repo.save(pacs002);
        }
        
    }
    
}
