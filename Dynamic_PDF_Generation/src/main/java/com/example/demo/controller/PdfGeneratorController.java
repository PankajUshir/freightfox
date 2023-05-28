package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Item;
import com.example.demo.entity.Transaction;
import com.example.demo.service.PdfGeneratorService;

@RestController
public class PdfGeneratorController {

	@Autowired
	private PdfGeneratorService pdfGeneratorService;
	
//	http://localhost:8080/createPdf
	@GetMapping("/createPdf")
	public void createPdf(@RequestBody Transaction transaction) {
		System.out.println(transaction.toString());
		System.out.println(transaction.getItems()); 
			
			
		String data = pdfGeneratorService.parseThymeleafTemplate(transaction);
		pdfGeneratorService.generatePdfFromHtml(data);
	}
}
