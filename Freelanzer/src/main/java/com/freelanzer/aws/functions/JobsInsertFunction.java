package com.freelanzer.aws.functions;

import java.util.List;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

public class JobsInsertFunction implements RequestHandler<HttpRequest, HttpJobResponse> {

	@Override
	public HttpJobResponse handleRequest(HttpRequest request, Context context) {
		context.getLogger().log("JobsInsertFunction : request: " + request);

		String body = request.getBody();

		Gson gson = new Gson();
		Job jobToAdd = gson.fromJson(body, Job.class);

		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);

		List<Job> jobLst = mapper.scan(Job.class, new DynamoDBScanExpression());
		jobToAdd.setId(jobLst.size() + 1);
		mapper.save(jobToAdd);

		return new HttpJobResponse(jobToAdd);
	}

}
