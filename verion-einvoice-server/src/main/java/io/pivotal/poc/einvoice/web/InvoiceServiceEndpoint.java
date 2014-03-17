package io.pivotal.poc.einvoice.web;

import io.pivotal.poc.einvoice.domain.FileEntity;
import io.pivotal.poc.einvoice.domain.FileEntityRepository;
import io.pivotal.poc.einvoice.domain.Invoice;
import io.pivotal.poc.einvoice.domain.InvoiceRepository;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eInvoice")
public class InvoiceServiceEndpoint {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private FileEntityRepository fileRepository;
	
	@Transactional
	@RequestMapping(method=RequestMethod.PUT)
	public Invoice save(@RequestBody Invoice invoice) {
		//return
		return invoiceRepository.save(invoice);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Invoice findInvoice(@PathVariable("id") Long id) {
		return invoiceRepository.findOne(id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.POST)
	public void putFile(@PathVariable Long id,@RequestParam("file") MultipartFile file) {
		//retrieve the file as bytes and persist
		if (!file.isEmpty()) {
			//retrieve the invoice
			Invoice invoice = invoiceRepository.findOne(id);
			if (invoice == null) {
				throw new IllegalArgumentException("incorrect id");
			}//end 
			//process
			FileEntity entity = new FileEntity();
			entity.setInvoice(invoice);
			entity.setMimeType(file.getContentType());
			entity.setName(file.getOriginalFilename());
			try {
				entity.setContent(file.getBytes());
			} catch (IOException e) {
				throw new IllegalArgumentException("failure to write bytes",e);
			}
			//save
			fileRepository.save(entity);
		}//end if
	}
	
	@RequestMapping(value="/{signature}/exists",method=RequestMethod.GET)
	public boolean exists(@PathVariable("signature") String signature) {
		//check if exists in the repository		
		return (invoiceRepository.findBySignature(signature) != null);
	}
}
