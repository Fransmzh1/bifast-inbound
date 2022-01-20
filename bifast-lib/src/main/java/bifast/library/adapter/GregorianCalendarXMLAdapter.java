package bifast.library.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GregorianCalendarXMLAdapter extends XmlAdapter<String, XMLGregorianCalendar> {
	private static Logger logger = LoggerFactory.getLogger(GregorianCalendarXMLAdapter.class);

	@Override
	public XMLGregorianCalendar unmarshal(String v) throws Exception {
		GregorianCalendar cal = new GregorianCalendar();
		if (v.length() == 23)
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(v));
		else 
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(v));

        XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar( cal);
		return calendar;
	}

	@Override
	public String marshal(XMLGregorianCalendar v) throws Exception {
		String strTime = String.format("%04d-%02d-%02dT%02d:%02d:%02d.%03dZ", 
							v.getYear(),v.getMonth(), v.getDay(),
							v.getHour(), v.getMinute(), v.getSecond(), v.getMillisecond());
		
		return strTime;

	}

}
