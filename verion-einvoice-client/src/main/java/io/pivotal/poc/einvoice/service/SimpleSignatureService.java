package io.pivotal.poc.einvoice.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class SimpleSignatureService implements SignatureService {
	
	private static final Logger logger = Logger.getLogger(SimpleSignatureService.class);
	
	@Override
	public String generateSignature(File file) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			int i;
			while ((i = fis.read()) != -1) {
				bos.write(i);
			}//end while
			bos.close();	
			fis.close();
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		//return
		return DigestUtils.md5DigestAsHex(bos.toByteArray());
	}

}
