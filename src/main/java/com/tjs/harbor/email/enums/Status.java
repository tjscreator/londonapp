package com.tjs.harbor.email.enums;

import java.util.HashMap;
import java.util.Map;

import com.tjs.common.modelenums.ModelEnum;

/**
 * This enum is used to maintain email/sms/push notification status. 
 * @author Dhruvang.Joshi
 * @since 26/07/2017
 */
public enum Status implements ModelEnum {
	
	NEW(0, "NEW"),
	INPROCESS(1, "INPROCESS"),
	FAILED(2, "FAILED"),
	SENT(3, "SENT");
	
	private final Integer id;
    private final String name;
    
    public static final Map<Integer, Status> MAP = new HashMap<>();
    
    static{
    	for(Status status : values()){
    		MAP.put(status.getId(), status);
    	}
    }
    
    Status(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    
    public static Status fromId(Integer id) {
    	return MAP.get(id);
    }

}
