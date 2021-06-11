package com.example.Jasper.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.Jasper.FuncionnariosRepository;
import com.example.Jasper.banco.Funcionarios;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperService {
	
	private static final String JASPER_DIRETORIO = "classpath:jasper/";
	private static final String JASPER_PREFIXO = "relatorio-";
	private static final String JASPER_SUFIXO = ".jasper";

	@Autowired
	private FuncionnariosRepository repostory;
	
	
	private Map<String, Object> params = new HashMap<>();
	
	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}
	
	public byte[] exportarPDF(String code)   {
		List<Funcionarios> funcioarios = repostory.getAll();
		byte[] bytes = null;
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(funcioarios);
		try {
			File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, dataSource);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
}
