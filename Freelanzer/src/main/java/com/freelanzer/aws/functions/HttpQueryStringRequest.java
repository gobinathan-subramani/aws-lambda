package com.freelanzer.aws.functions;

import java.util.Map;

public class HttpQueryStringRequest {

	private Map<String, String> queryStringParameters;

	public Map<String, String> getQueryStringParameters() {
		return queryStringParameters;
	}

	public void setQueryStringParameters(Map<String, String> queryStringParameters) {
		this.queryStringParameters = queryStringParameters;
	}

}
