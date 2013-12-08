package controllers;

import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.KV;
import utils.ResultObj;
import data.POI;
import data.Walking;
import database.Queries;


public class WalkingController extends Controller {

	public static Result createWalking() {

		try {

			Walking w = Walking.fromJson(request().body().asJson().toString());
			String id = Queries.createWalking(w);

			return ok(ResultObj.json(new KV("id", id)));
		} catch (Exception e) {
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}

	public static Result getPoi(String id) {

		try {

			POI poi = Queries.getPoi(id);
			return ok(poi.toJson());
		} catch (Exception e) {
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}

	public static Result getWalking(String walkingId) {

		try {
			Walking w = Queries.getWalking(walkingId);
			return ok(w.toJson());
		} catch (Exception e) {
			e.printStackTrace();
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}

	public static Result createPOI(String walkingId) {

		try {
			POI poi = POI.fromJson(request().body().asJson().toString());
			String id = Queries.createPoi(walkingId, poi);
			return ok(ResultObj.json(new KV("id", id)));
		} catch (Exception e) {
			e.printStackTrace();
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}
	
	public static Result getWalkingsList() {

		try {
			
			List<Walking> ws = Queries.getWalking();
			return ok(Json.toJson(ws));
		} catch (Exception e) {
			e.printStackTrace();
			return internalServerError(ResultObj.json(new KV("err", e
					.getClass())));
		}

	}
	

}
