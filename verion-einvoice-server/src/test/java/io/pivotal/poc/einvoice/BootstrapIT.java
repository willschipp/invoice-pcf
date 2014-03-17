package io.pivotal.poc.einvoice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import io.pivotal.poc.einvoice.domain.Invoice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes=Bootstrap.class)
public class BootstrapIT {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Before
	public void before() {
		mockMvc = webAppContextSetup(context).build();
	}
	
	@Test
	public void testMain() throws Exception {
		//run the app --> connect to the service and "save" an object
		Invoice invoice = new Invoice();
		invoice.setSignature("signature");
		String payload = new ObjectMapper().writeValueAsString(invoice);
//		mockMvc.perform(put("/eInvoice").contentType(MediaType.APPLICATION_JSON).content(payload)).andExpect(status().isOk());
		mockMvc.perform(get("/eInvoice/{id}",1l)).andExpect(status().isOk());
	}

}
