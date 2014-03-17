package io.pivotal.poc.einvoice.web;

import io.pivotal.poc.einvoice.domain.Invoice;
import io.pivotal.poc.einvoice.domain.InvoiceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eInvoice")
public class InvoiceEndPoint {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Invoice> findAll() {
		return invoiceRepository.findAll();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Invoice findOne(@PathVariable Long id) {
		return invoiceRepository.findOne(id);
	}
	
	@RequestMapping(value="/{id}/existsRemotely",method=RequestMethod.GET)
	public boolean existsRemotely(@PathVariable Long id) {
		//ping the "central repository" if the file exists
		return false;
	}
}
