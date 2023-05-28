package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.example.demo.entity.Transaction;
import com.lowagie.text.DocumentException;

@Service
public class PdfGeneratorService {
	
	
	public String parseThymeleafTemplate(Transaction transaction) {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);

		Context context = new Context();
		context.setVariable("transaction", transaction);
		
		return templateEngine.process("PdfTemplate", context);
	}

	public void generatePdfFromHtml(String html) {
		String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
		System.out.println(outputFolder);
		try {
			FileOutputStream outputStream = new FileOutputStream(outputFolder);
			
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html);
			renderer.layout();
			renderer.createPDF(outputStream);
			outputStream.close();
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
