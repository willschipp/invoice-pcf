package io.pivotal.poc.einvoice.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	@Secured("ROLE_SERVICE")
	Invoice save(Invoice invoice);
	
	@Secured("ROLE_USER")
	List save(Iterable invoices);

	@Secured({"ROLE_SERVICE","ROLE_USER"})
	Invoice findBySignature(String signature);
}
