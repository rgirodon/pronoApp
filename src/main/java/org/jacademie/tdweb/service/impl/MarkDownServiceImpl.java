package org.jacademie.tdweb.service.impl;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.service.MarkDownService;
import org.markdown4j.Markdown4jProcessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MarkDownServiceImpl implements MarkDownService {

	private static Logger logger = Logger.getLogger(MarkDownServiceImpl.class);
	
	private Markdown4jProcessor markdown4jProcessor;
	
	public MarkDownServiceImpl() {
	
		this.markdown4jProcessor = new Markdown4jProcessor();
	}
	
	@Override
	public String retrieveHelpContent() {
				
		String helpHtmlContent = null;
		
		try(
				InputStream readmeInputStream = this.getClass().getClassLoader().getResourceAsStream("README.md");
		) {
			StringWriter writer = new StringWriter();
			IOUtils.copy(readmeInputStream, writer);
			String helpMarkDownContent = writer.toString();
			helpHtmlContent = markdown4jProcessor.process(helpMarkDownContent);
		}
		catch(Exception e) {
			logger.error(e);
			helpHtmlContent = "No help, sorry.";
		}
		
		return helpHtmlContent;
	}

}
