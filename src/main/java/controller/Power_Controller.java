package controller;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;
import org.json.simple.*;

import model.*;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/power")
public class Power_Controller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String txt_json)
	{
		power_consumption power =new power_consumption();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("powerConID").getAsString()!=""&&data.get("pname").getAsString()!=""&&data.get("pdistrict").getAsString()!=""&&data.get("pzipCode").getAsString()!=""&&data.get("punits").getAsString()!=""&&data.get("ptotal").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", power.addPowerModel("mi","h1","h","kk",1,2));
			
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "validation_error");
			
			return json.toString();
			
		}
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String txt_json)
	{

		power_consumption power =new power_consumption();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();

		if(data.get("id").getAsString()!=""&&data.get("powerConID").getAsString()!=""&&data.get("pname").getAsString()!=""&&data.get("pdistrict").getAsString()!=""&&data.get("pzipCode").getAsString()!=""&&data.get("punits").getAsString()!=""&&data.get("ptotal").getAsString()!="") {

			JSONObject json = new JSONObject();
			json.put("success", power.editPowerModel(Integer.parseInt(data.get("id").getAsString()),data.get("powerConID").getAsString(),data.get("pname").getAsString(),data.get("pdistrict").getAsString(),data.get("pzipCode").getAsString(),Integer.parseInt(data.get("punits").getAsString()),Integer.parseInt(data.get("ptotal").getAsString())));
			
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "validation_error");
			
			return json.toString();
			
		}
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String txt_json)
	{

		power_consumption power =new power_consumption();
		JsonObject data = new JsonParser().parse(txt_json).getAsJsonObject();
		if(data.get("id").getAsString()!="") {
			
			JSONObject json = new JSONObject();
			json.put("success", power.deletePowerModel(Integer.parseInt(data.get("id").getAsString())));
	
			return json.toString();
			
		}else {
			
			JSONObject json = new JSONObject();
			json.put("success", "validation_error");
			
			return json.toString();
			
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String txt_json)
	{
		
		power_consumption power =new power_consumption();
		return power.getPowerModel();
	}
	
}
