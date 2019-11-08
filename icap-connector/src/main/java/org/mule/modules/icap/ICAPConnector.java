package org.mule.modules.icap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Default;
import org.mule.modules.icap.config.ConnectorConfig;
import org.mule.modules.icap.processor.ICAP;
import org.mule.modules.icap.processor.ICAPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Connector(name="icap", friendlyName="ICAP")
public class ICAPConnector {

	private static final Logger logger = LoggerFactory.getLogger(ICAPConnector.class);
	
    @Config
    ConnectorConfig config;

    /**
     * Custom processor
     *
     * @param friend Name to be used to generate a greeting message.
     * @return A greeting message
     */
    @Processor
    public boolean scan(@Default("#[payload]") InputStream payload) {
        
    	try {
			ICAP icapProcessor = new ICAP(config.getHost(), config.getPort(), config.getService());
			
			return icapProcessor.scanStream(payload);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("scan():", e);
		} catch (ICAPException e) {
			// TODO Auto-generated catch block
			logger.error("scan():", e);
		}
    	
    	return false;
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}