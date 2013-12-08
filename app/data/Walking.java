package data;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Walking extends GenericDataObject {

	static ObjectMapper mapper = new ObjectMapper();
	
	String id;
	String name, description;
	Integer level;
	
	List<POI> pois;
	
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List<POI> getPois() {
		if(pois==null)pois=new ArrayList<POI>();
		return pois;
	}
	public void setPois(List<POI> pois) {
		this.pois = pois;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "Walking [name=" + name + ", description=" + description
				+ ", level=" + level + "]";
	}
	
	public static Walking fromJson(String json) throws Exception {
		return mapper.readValue(json, Walking.class);
	}
	

	
	
}
