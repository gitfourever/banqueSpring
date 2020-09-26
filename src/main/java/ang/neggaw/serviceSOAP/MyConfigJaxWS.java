package ang.neggaw.serviceSOAP;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

//@Configuration
public class MyConfigJaxWS {

    //@Bean
    public SimpleJaxWsServiceExporter getJaxWS() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        exporter.setBaseAddress("http://localhost:10000/myBanqueWS");

        return exporter;
    }
}
