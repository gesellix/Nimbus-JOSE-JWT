package com.nimbusds.jose;


import net.minidev.json.JSONAware;
import net.minidev.json.JSONObject;


/**
 * Javascript Object Signing and Encryption (JOSE) algorithm name, with optional
 * use and requirement properties. This class is immutable.
 *
 * @author Vladimir Dzhuvinov 
 * @version $version$ (2012-09-17)
 */
public final class Algorithm implements JSONAware {

	
	/**
	 * Enumeration of algorithm uses.
	 */
	public static enum Use {
	
		
		/**
		 * Signature use.
		 */
		SIGNATURE,
		
		
		/**
		 * Encryption use.
		 */
		ENCRYPTION;
	}
	
	
	/**
	 * Enumeration of implementation requirements.
	 */
	public static enum Requirement {
	
		
		/**
		 * Required implementation.
		 */
		REQUIRED,
		
		
		/**
		 * Recommended implementation.
		 */
		RECOMMENDED,
		
		
		/**
		 * Optional implementation.
		 */
		OPTIONAL;
	}
		 
		 
	/**
	 * No algorithm (plain JOSE object).
	 */
	public static final Algorithm NONE = new Algorithm("none", Use.SIGNATURE, Requirement.REQUIRED);
	
	
	/**
	 * HMAC using SHA-256 hash algorithm (required).
	 */
	public static final Algorithm HS256 = new Algorithm("HS256", Use.SIGNATURE, Requirement.REQUIRED);
	
	
	/**
	 * HMAC using SHA-384 hash algorithm (optional).
	 */
	public static final Algorithm HS384 = new Algorithm("HS384", Use.SIGNATURE, Requirement.OPTIONAL);
	
	
	/**
	 * HMAC using SHA-512 hash algorithm (optional).
	 */
	public static final Algorithm HS512 = new Algorithm("HS512", Use.SIGNATURE, Requirement.OPTIONAL);
	

	/**
	 * The algorithm name.
	 */
	private final String name;
	
	
	/**
	 * The algorithm use.
	 */
	private final Use use;
	
	
	/**
	 * The implementation requirement.
	 */
	private final Requirement requirement;
	
	
	/**
	 * Creates a new JOSE algorithm with the specified name.
	 *
	 * @param name The algorithm name. Must not be {@code null}.
	 * @param use  The algorithm use. Must not be {@code null}.
	 * @param req  The implementation requirement. Must not be
	 *             {@code null}.
	 */
	public Algorithm(final String name, final Use use, final Requirement req) {
	
		if (name == null)
			throw new IllegalArgumentException("The algorithm name must not be null");
		
		this.name = name;
		
		
		if (use == null)
			throw new IllegalArgumentException("The algorithm use must not be null");
		
		this.use = use;
		
		requirement = req;
	}
	
	
	/**
	 * Gets the algorithm name.
	 *
	 * @return The algorithm name.
	 */
	public String getName() {
	
		return name;
	}
	
	
	/**
	 * Gets the algorithm use.
	 *
	 * @return The algorithm use.
	 */
	public Use getUse() {
	
		return use;
	}
	
	
	/**
	 * Gets the implementation requirement.
	 *
	 * @return The implementation requirement.
	 */
	public Requirement getRequirement() {
	
		return requirement;
	}
	
	
	/**
	 * Overrides {@code Object.hashCode()}.
	 *
	 * @return The object hash code.
	 */
	@Override
	public int hashCode() {
	
		return name.hashCode();
	}
	
	
	/**
	 * Overrides {@code Object.equals()}.
	 *
	 * @param object The object to compare to.
	 *
	 * @return {@code true} if the objects have the same value, otherwise
	 *         {@code false}.
	 */
	@Override
	public boolean equals(final Object object) {
	
		return object instanceof Algorithm && this.toString().equals(object.toString());
	}
	
	
	/**
	 * Returns the string representation of algorithm.
	 *
	 * @see #getName
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString() {
	
		return name;
	}
	
	
	/**
	 * Returns the JSON string representation of this algorithm.
	 * 
	 * @return The JSON string representation.
	 */
	@Override
	public String toJSONString() {
	
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		sb.append(JSONObject.escape(name));
		sb.append('"');
		return sb.toString();
	}
}