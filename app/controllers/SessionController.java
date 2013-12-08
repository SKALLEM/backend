package controllers;

import database.Queries;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.Result;
import utils.KV;
import utils.ResultObj;

public class SessionController extends Controller {
	public static Result register() {
		String username = request().body().asJson().get("username").asText();
		String password = request().body().asJson().get("password").asText();
		String name = request().body().asJson().get("name").asText();
		
		boolean result = Queries.registerUser(name, username, password);
		
		if (result)
			return ok(ResultObj.json(new KV("success", true)));
		else
			return internalServerError(ResultObj.json(new KV("success", false), new KV("err", "user already exists")));
	}
	public static Result login() {
		
		
		String username = request().body().asJson().get("username").asText();
		String password = request().body().asJson().get("password").asText();
		
		if(Queries.login(username, password)) {
			String token = username+":"+Crypto.sign(username);
			return ok(ResultObj.json(new KV("success", true), new KV("token", token)));
		}
		
		return internalServerError(ResultObj.json(new KV("success", false), new KV("err", "Login failed")));
	}
}
