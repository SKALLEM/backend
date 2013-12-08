package controllers;

import database.Queries;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.Result;
import utils.KV;
import utils.ResultObj;

public class SessionController extends Controller {
	public static Result register() {
		return TODO;
	}
	public static Result login() {
		
		
		String username = request().body().asJson().get("username").asText();
		String password = request().body().asJson().get("password").asText();
		
		if(Queries.login(username, password)) {
			String token = username+":"+Crypto.sign(username);
			return ok(ResultObj.json(new KV("token", token)));
		}
		
		return internalServerError(ResultObj.json(new KV("err", "login failed")));
	}
}
