package io.absin.alb;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;

public class LambdaAlbTrigger implements RequestHandler<Map<Object, Object>, Object> {

	@Override
	public Object handleRequest(Map<Object, Object> event, Context context) {
		Gson gson = new Gson();
		LambdaLogger logger = context.getLogger();
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/html; charset=utf-8");
		response.put("statusDescription", "200 OK");
		response.put("statusCode", 200);
		response.put("isBase64Encoded", false);
		response.put("body", "Hello from Lambda!");
		response.put("headers", headers); // log execution details
		logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
		logger.log("CONTEXT: " + gson.toJson(context));
		// process event
		logger.log("EVENT: " + gson.toJson(event));
		logger.log("EVENT TYPE: " + event.getClass().toString());
		Map<String, String> map = (Map<String, String>) event.get("queryStringParameters");
		if (!map.containsKey("task_id")) {
			response.put("statusCode", 400);
			response.put("body", "Must supply task_id as query parameter");
		} else {
			response.put("body", "Hello from Lambda for " + map.get("task_id"));
		}
		String json = new Gson().toJson(response);
		logger.log("Response stringified: " + json);
		logger.log("Response raw: " + response);
		return response;
	}

}