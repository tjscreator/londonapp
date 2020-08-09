package com.emotome.common.setting.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.emotome.common.model.IdentifierModel;

/**
 * @author Jaydip
 * @since 29/03/2019
 */
public class AllowedDomainsModel extends IdentifierModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3098508145094157178L;

	private String domain;

	private static Map<Long, String> MAP = new ConcurrentHashMap<>();
	

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static Map<Long, String> getMAP() {
		return MAP;
	}

	public static boolean getValue(String domain) {
		return MAP.values().stream().filter(check -> check.contains(domain)).findAny().isPresent();
	}
}
