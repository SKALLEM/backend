package data;

import play.libs.Json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericDataObject {
	static ObjectMapper mapper = new ObjectMapper();

	public String toJson() {
		return toJson(this);
	}

	public static String toJson(Object p) {
		return Json.toJson(p).toString();
	}

	@JsonAnySetter
	public void handleUnknown(String key, Object value) {
		System.out.println("IGNORED: " + key);
	}
}
