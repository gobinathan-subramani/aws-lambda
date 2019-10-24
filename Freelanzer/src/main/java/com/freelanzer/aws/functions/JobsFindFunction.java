package com.freelanzer.aws.functions;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class JobsFindFunction implements RequestHandler<HttpRequest, HttpJobResponse> {
	
	

	@Override
	public HttpJobResponse handleRequest(HttpRequest request, Context context) {
		context.getLogger().log("JobsFindFunction : request: " +request.getPathParameters().get("id"));
		
		// Getting values by query string method
		// String idAsString = (String) request.getQueryStringParameters().get("id");
		// Integer jobId = Integer.parseInt(idAsString);

		// Getting values by path perameter method
		String idAsString = (String) request.getPathParameters().get("id");
		Integer jobId = Integer.parseInt(idAsString);
		context.getLogger().log("JobsFindFunction : idAsString: " + idAsString + " jobId: " + jobId);

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		Job job = null;

		job = mapper.load(Job.class, jobId);

		return new HttpJobResponse(job);
	}


}
