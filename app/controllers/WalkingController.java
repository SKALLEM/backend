package controllers;

import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.mongodb.DBObject;

import database.Queries;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.KV;
import utils.Req;
import utils.ResultObj;
import views.html.index;

public class WalkingController extends Controller {

	public static Gson gson = new Gson();
	
	
	//YES, QUICK AND DIRTY
	public static Result createWalking() {

		try {

			String bodyString = request().body().asText();
			
			HashMap<String, Object> walking = gson.fromJson(bodyString, HashMap.class);
			
			String id = Queries.createWalking(walking);

			return ok(ResultObj.json(new KV("id", id)));
		} catch (Exception e) {
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}
	
	public static Result getPoi(String id) {

		try {

			//String bodyString = request().body().asText();
			
			//HashMap<String, Object> walking = gson.fromJson(bodyString, HashMap.class);
			
			DBObject poi = Queries.getPoi(id);

			return ok(gson.toJson(poi));
		} catch (Exception e) {
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}

	public static Result createPoi(String walkingId) {

		try {

			String bodyString = request().body().asText();
			HashMap<String, Object> poi = gson.fromJson(bodyString, HashMap.class);
			//JsonNode body = Json.parse(bodyString);
/*
			String name = body.get("name").asText();
			String descr = body.get("description").asText();
			
			Double lat = body.get("description").get("coords").get("lat").asDouble();
			Double lon= body.get("description").get("coords").get("lon").asDouble();
			
			//Boolean isStatic = body.get("isStatic").asBoolean();
			String locationType = body.get("locationType").asText();
			
			
			JsonNode question = body.get("question");
			if(question!=null) {
				
				
				
				
			}
			

*/

			String id = Queries.createPoi(walkingId, poi);

			return ok(ResultObj.json(new KV("id", id)));
		} catch (Exception e) {
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}

}
