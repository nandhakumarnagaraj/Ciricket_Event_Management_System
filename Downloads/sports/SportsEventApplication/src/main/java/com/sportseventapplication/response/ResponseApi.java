package com.sportseventapplication.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseApi {
	
	private String message;
	private Object data;
}
