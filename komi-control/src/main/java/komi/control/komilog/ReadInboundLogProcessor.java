package komi.control.komilog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import komi.control.komilog.filefilter.InboundCurentLogFileFilter;
import komi.control.komilog.filefilter.InboundZipLogFileFilter;

@Component
public class ReadInboundLogProcessor implements Processor{
	@Autowired InboundZipLogFileFilter inboundFilter;
	@Autowired InboundCurentLogFileFilter inboundCurrentLogFilter;
	@Autowired ReadLogFile readLogfile;
	
	@Value("${inbound.log.dir}")
	String inboundlogdir;
	
	@Override
	public void process(Exchange exchange) throws Exception {

        File logDir = new File(inboundlogdir);
        
        String tgl = exchange.getMessage().getHeader("tanggal", String.class);

        StringBuffer sb = new StringBuffer();
        List<File> files = new ArrayList<File>();
        List<File> sortedFiles = new ArrayList<File>();
        
        if (null == tgl) {
        	File[] fs = logDir.listFiles(inboundCurrentLogFilter);
        	if (fs.length>0) {
        		sortedFiles.add(fs[0]);
        	}
        }
        
        else {
            for (File f : logDir.listFiles(inboundFilter)) 
            	files.add(f);
            sortedFiles = files.stream().sorted().collect(Collectors.toList());
        }
        
        if (sortedFiles.size()==0) 
        	sb.append("<--- Logfile tidak ditemukan di " + inboundlogdir + " --->");
        
        for (File f : sortedFiles) {
        	sb.append(readLogfile.read(f));
        }

        exchange.getMessage().setBody(sb);
	}

	
}
