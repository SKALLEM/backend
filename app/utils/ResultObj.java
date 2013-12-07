package utils;

import java.util.HashMap;

import play.libs.Json;

public class ResultObj extends HashMap<String, Object>{
	private static final long serialVersionUID = 2577574039391180231L;
	public static ResultObj make(KV... p) {
		ResultObj r = new ResultObj();
		for (int i = 0; i < p.length; i++)
			r.put(p[i].k, p[i].v);
		return r;
	}
	
	public static String json(KV... p) {
		return Json.toJson(make(p)).toString();
	}
}
