package yazamimDB.helpers;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TimeSerializer implements JsonSerializer<Time> {

	public JsonElement serialize(Time src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(new SimpleDateFormat("HH:mm").format(src));
	}
}
