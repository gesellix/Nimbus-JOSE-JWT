package com.nimbusds.jwt;


import junit.framework.TestCase;

import com.nimbusds.jose.Algorithm;

import com.nimbusds.jose.util.Base64URL;


/**
 * Tests plain JWT object. Uses test vectors from JWT spec.
 *
 * @author Vladimir Dzhuvinov
 * @version $version$ (2012-09-28)
 */
public class PlainJWTTest extends TestCase {

	
	public void testBase64URLConstructor()
		throws Exception {
	
		// {"alg":"none"}
		Base64URL part1 = new Base64URL("eyJhbGciOiJub25lIn0");
		
		// {"iss":"joe","exp":1300819380,"http://example.com/is_root":true}
		Base64URL part2 = new Base64URL("eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFt" +
		                                 "cGxlLmNvbS9pc19yb290Ijp0cnVlfQ");
	
		PlainJWT jwt = new PlainJWT(part1, part2);
		
		assertEquals(Algorithm.NONE, jwt.getHeader().getAlgorithm());
		assertNull(jwt.getHeader().getType());
		assertNull(jwt.getHeader().getContentType());
		
		ReadOnlyClaimsSet cs = jwt.getClaimsSet();
		
		assertEquals("joe", cs.getIssuerClaim());
		assertEquals(1300819380, cs.getExpirationTimeClaim());
		assertTrue((Boolean)cs.getCustomClaim("http://example.com/is_root"));
	}
	
	
	public void testParse()
		throws Exception {
	
		String s = "eyJhbGciOiJub25lIn0" +
		           "." +
			   "eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFt" +
			   "cGxlLmNvbS9pc19yb290Ijp0cnVlfQ" +
			   ".";
	
		PlainJWT jwt = PlainJWT.parse(s);
		
		assertNotNull(jwt);
		
		assertEquals(Algorithm.NONE, jwt.getHeader().getAlgorithm());
		assertNull(jwt.getHeader().getType());
		assertNull(jwt.getHeader().getContentType());
		
		ReadOnlyClaimsSet cs = jwt.getClaimsSet();
		
		assertEquals("joe", cs.getIssuerClaim());
		assertEquals(1300819380, cs.getExpirationTimeClaim());
		assertTrue((Boolean)cs.getCustomClaim("http://example.com/is_root"));
	}
}
