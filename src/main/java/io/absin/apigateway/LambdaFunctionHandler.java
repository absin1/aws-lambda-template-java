package io.absin.apigateway;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class LambdaFunctionHandler
		implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
		Map<String, String> quMap = input.getQueryStringParameters();
		String requestBody = input.getBody();
		String httpMethod = input.getHttpMethod();
		context.getLogger().log("Queryparams: " + quMap);
		context.getLogger().log("Body: " + requestBody);
		context.getLogger().log("method: " + httpMethod);
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setBody("So we reached lambda from api gateway");
		responseEvent.setStatusCode(200);
		return responseEvent;
	}

}