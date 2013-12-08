package data;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class POI extends GenericDataObject {

	
	String id, name, description, locationType;
	Double [] coords;
	Question question;
	
	static ObjectMapper mapper = new ObjectMapper();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Double[] getCoords() {
		return coords;
	}
	public void setCoords(Double[] coords) {
		this.coords = coords;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "POI [id=" + id + ", name=" + name + ", description="
				+ description + ", locationType=" + locationType + ", loc="
				+ Arrays.toString(coords) + ", question=" + question + "]";
	}
	public static POI fromJson(String json) throws Exception {
		return mapper.readValue(json, POI.class);
	}
	

	public static void main(String[] args) throws Exception {
		System.out.println(POI.toJson(POI.fromJson("{\"name\":\"test\",\"description\":\"description\",\"locationType\":\"locationType\",\"loc\":[1,3],\"question\":{\"points\":145,\"text\":\"dfdfjjkjkf\",\"answers\":[{\"text\":\"bla\",\"isCorrect\":true}]}}")));
	}
	
}
