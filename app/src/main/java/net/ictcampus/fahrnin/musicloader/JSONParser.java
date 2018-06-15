package net.ictcampus.fahrnin.musicloader;

public class JSONParser {

	public static String parseURL(String json) {

		String url = null;

		String[] formats = json.split("\\}");

		for (String format : formats) {

			String[] lines = format.split("\n");
			
			if(valueByKey("itag", lines).equals("18")){
				
				url = valueByKey("url", lines);
				
			}
		}

		return url;
	}
	
	private static String valueByKey(String key, String[] lines){
		
		String out = "";
		
		for (String line : lines) {

			if (!(line.contains("{") || line.contains("}") || line.contains("[") || line.contains("]")) && line.contains(":")) {
				String part = line.split(":")[0];
				String k = part.substring(9, part.length() - 1);
				if (k.equals(key)) {
					String[] parts = line.split(":");
					String value = "";
					if(k.equals("url")){
						String p = parts[2];
						if(p.charAt(p.length()-1) == ','){
							p = parts[2].substring(0, parts[2].length()-2);
						}else{
							p = parts[2].substring(0, parts[2].length()-1);
						}
						value = "https:" + p;
					}else{
						value = parts[1].substring(2, parts[1].length()-2);
					}
					out = value.replaceAll("\\\\", "");
				}
			}
		}
		
		return out;
	}
}
