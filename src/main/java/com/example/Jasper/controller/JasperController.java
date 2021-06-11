package com.example.Jasper.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Jasper.service.JasperService;

@RestController
@RequestMapping("jasper")
public class JasperController {
	@Autowired
	private JasperService service;
	
	@GetMapping
	public void exibirRelatorio(@RequestParam("code") String code,
			@RequestParam("acao") String acao,
			HttpServletResponse responsne) throws IOException {
		byte[] bytes = service.exportarPDF(code);
		responsne.setContentType(MediaType.APPLICATION_PDF_VALUE);
		if(acao.equals("v")) {
			responsne.setHeader("Content-disposition", "inline; filename=relatorio" + code + ".pdf");
		}else {
			responsne.setHeader("Content-disposition", "attachment; filename=relatorio" + code + ".pdf");
		}
		responsne.getOutputStream().write(bytes);
	}

}
