package bifast.mock.processor;

import java.util.Random;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bifast.library.iso20022.custom.BusinessMessage;
import bifast.library.iso20022.custom.Document;
import bifast.library.iso20022.head001.BusinessApplicationHeaderV01;
import bifast.library.iso20022.prxy002.ProxyRegistrationResponseV01;
import bifast.library.iso20022.service.AppHeaderService;
import bifast.library.iso20022.service.Proxy002MessageService;
import bifast.library.iso20022.service.Proxy002Seed;
import bifast.mock.prxy004.Proxy004Seed;

@Component
//@ComponentScan(basePackages = {"bifast.library.iso20022.service", "bifast.library.config"} )
public class ProxyResolutionResponseProcessor implements Processor{

	@Autowired
	private AppHeaderService hdrService;
	@Autowired
	private Proxy002MessageService proxy002MessageService;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		String str = "{\n" 
		+ "  \"BusMsg\" : {\n"
		+ "    \"AppHdr\" : {\n"
		+ "      \"Fr\" : {\n"
		+ "        \"Fiid\" : {\n"
		+ "          \"FinInstnId\" : {\n"
		+ "            \"Othr\" : {\n"
		+ "              \"Id\" : \"FASTIDJA\"\n"
		+ "            }\n"
		+ "          }\n"
		+ "        }\n"
		+ "      },\n"
		+ "      \"To\" : {\n"
		+ "        \"Fiid\" : {\n"
		+ "          \"FinInstnId\" : {\n"
		+ "            \"Othr\" : {\n"
		+ "              \"Id\" : \"INDOIDJA\"\n"
		+ "            }\n"
		+ "          }\n"
		+ "        }\n"
		+ "      },\n"
		+ "      \"BizMsgIdr\" : \"20210301INDOIDJATTTHRB12345678\",\n"
		+ "      \"MsgDefIdr\" : \"prxy.004.001.01\",\n"
		+ "      \"CreDt\" : \"2021-03-01T20:00:00\"\n"
		+ "    },\n"
		+ "    \"Document\" : {\n"
		+ "      \"PrxyLookUpRspn\" : {\n"
		+ "        \"GrpHdr\" : {\n"
		+ "          \"MsgId\" : \"20210301FASTIDJA710HRB12345679\",\n"
		+ "          \"CreDtTm\" : \"2021-09-27T21:00:00\",\n"
		+ "          \"MsgRcpt\" : {\n"
		+ "            \"Agt\" : {\n"
		+ "              \"FinInstnId\" : {\n"
		+ "                \"Nm\" : \"Popular Bank\",\n"
		+ "                \"Othr\" : {\n"
		+ "                  \"Id\" : \"BANK001\"\n"
		+ "                }\n"
		+ "              }\n"
		+ "            }\n"
		+ "          }\n"
		+ "        },\n"
		+ "        \"OrgnlGrpInf\" : {\n"
		+ "          \"OrgnlMsgId\" : \"20210301INDOIDJA610HRB12345678\",\n"
		+ "          \"OrgnlMsgNmId\" : \"prxy.003.001.01\",\n"
		+ "          \"OrgnlCreDtTm\" : \"2021-09-27T18:59:58\"\n"
		+ "        },\n"
		+ "        \"LkUpRspn\" : {\n"
		+ "          \"OrgnlId\" : \"20210301INDOIDJA61012345678\",\n"
		+ "          \"OrgnlPrxyRtrvl\" : {\n"
		+ "            \"Tp\" : \"MSDISDN\",\n"
		+ "            \"Val\" : \"09248753982\"\n"
		+ "          },\n"
		+ "          \"OrgnlPrxyRqstr\" : {\n"
		+ "            \"Tp\" : \"02\",\n"
		+ "            \"Val\" : \"james.brown@example.com\"\n"
		+ "          },\n"
		+ "          \"OrgnlDsplNm\" : \"Mr. James Brown\",\n"
		+ "          \"OrgnlAcctTp\" : {\n"
		+ "            \"Prtry\" : \"CACC\"\n"
		+ "          },\n"
		+ "          \"RegnRspn\" : {\n"
		+ "            \"PrxRspnSts\" : \"ACTC\",\n"
		+ "            \"StsRsnInf\" : {\n"
		+ "              \"Prtry\" : \"U000\"\n"
		+ "            },\n"
		+ "            \"Prxy\" : {\n"
		+ "              \"Tp\" : \"02\",\n"
		+ "              \"Val\" : \"james.brown@example.com\"\n"
		+ "            },\n"
		+ "            \"Regn\" : {\n"
		+ "              \"RegnId\" : \"6789012345\",\n"
		+ "              \"DsplNm\" : \"Mr. James Brown\",\n"
		+ "              \"Agt\" : {\n"
		+ "                \"FinInstnId\" : {\n"
		+ "                  \"Othr\" : {\n"
		+ "                    \"Id\" : \"INDOIDJA\"\n"
		+ "                  }\n"
		+ "                }\n"
		+ "              },\n"
		+ "              \"Acct\" : {\n"
		+ "                \"Id\" : {\n"
		+ "                  \"Othr\" : {\n"
		+ "                    \"Id\" : \"2040606090112\"\n"
		+ "                  }\n"
		+ "                },\n"
		+ "                \"Tp\" : {\n"
		+ "                  \"Prtry\" : \"CACC\"\n"
		+ "                },\n"
		+ "                \"Nm\" : \"James Brown\"\n"
		+ "              }\n"
		+ "            }\n"
		+ "          }\n"
		+ "        },\n"
		+ "        \"SplmtryData\" : [ {\n"
		+ "          \"Envlp\" : {\n"
		+ "            \"Cstmr\" : {\n"
		+ "              \"Tp\" : \"01\",\n"
		+ "              \"Id\" : \"1020304050607080\",\n"
		+ "              \"RsdntSts\" : \"01\",\n"
		+ "              \"TwnNm\" : \"0300\"\n"
		+ "            }\n"
		+ "          }\n"
		+ "        } ]\n"
		+ "      }\n"
		+ "    }\n"
		+ "  }\n"
		+ "}";

		exchange.getMessage().setBody(str);
	}


}
