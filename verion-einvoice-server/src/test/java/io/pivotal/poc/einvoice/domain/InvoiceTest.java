package io.pivotal.poc.einvoice.domain;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InvoiceTest {

	@Test
	public void test() throws Exception {
		Invoice invoice = new Invoice();
		invoice.setSignature("hello");
		invoice.setId(1l);
		System.out.println(new ObjectMapper().writeValueAsString(invoice));
	}

}
