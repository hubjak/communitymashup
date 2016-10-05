
package org.sociotech.communitymashup.source.researchgate.apiwrapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.log.LogService;
import org.sociotech.communitymashup.source.impl.SourceServiceFacadeImpl;
import org.sociotech.communitymashup.source.researchgate.apiwrapper.items.Publication;


public class ResearchGateAPIWrapper {
	
	/**
	 * Reference to the source service for logging.
	 */
	private SourceServiceFacadeImpl sourceService;
	
	/**
	 * Url for accessing the ResearchGate server.
	 */
	private String baseUrl = null;
		
	/**
	 * ResearchGate profile regex pattern
	 */	
	private final static Pattern REGEX_PROFILE_JSON = Pattern.compile("(\\{\"data\":\\{\"profileHeader\".*?initState\":\\{\\}\\})", 
			Pattern.MULTILINE);
	private final static Pattern REGEX_PUB_JSON = Pattern.compile("Widget\\((\\{\"data\":\\{.*?(\\}){3,})\\)",
			Pattern.MULTILINE);
	
	/**
	 * Requests an URL and parses its content as a JSONObject
	 * @param reqUrl URL to request
	 * @return The received JSONObject
	 */
	private static JSONObject getJSON(String reqUrl) {
		try {
			URL url = new URL(reqUrl);
			URLConnection con = (URLConnection) url.openConnection();
			con.setRequestProperty("accept", "application/json");
			con.setRequestProperty("x-requested-with", "XMLHttpRequest");
			con.setRequestProperty("cookie", "id=XPhUOpWYc18JkqyqnT2Nm9xpmpwlzbv9N5GPRYTI1mTByMMzxh0Q4vmfrOrTxl6weJh0kaY4Ljvaox0VjRzHYk8MkTWdd01WVF1NkF67p7eJqwtP9l6rR00P6ec16rlb; cili=_2_ZjgzMWYzMzU2ZDZjN2IzNjUyNjYwNGE3NDc4MGMzMDg3MzA1YTlhNmUxMTU4ZDg1ODAwMmExZjQ5ZDI1MzZlY18yNjk1OzA%3D; cirgu=1; did=kclLEbGIeP0a0sluy7HLdc10ybnpIqpNg63ZZb58iaXsXv0GpusCWEvruIl0FoKO; c1=%DA+%CF%0C%DC%E4D%3D%8B%AB%BBn%92%A2q%FA%F6t3%EE%2C%BE%0Eay%AC%A3%A0%C8%04%21%CD%C5%21%BA%FA%05%D3%E2%1D%D5%06%0C%92%E1%EF%C4LN%E8%B0%7FRE%07%9C%C4V%E2%F5%ED%F9An; c2=%DFf%E0%EE%8A%BF%1F%7E%E299%A5%AD%8D%E9%3E%F9F%CC%FEOW%CA%60%2B%0CQ%C8%91%A3%25%F0%0F%F6F%84%BC%11%EA1p%A6%28a%C9%A8%0E%A47A%3A%C9%ADrnjJ2%A6%EA%E8%C9%3F%B9%A7%DD%23-%93%11%10%A4%A6%A7Y%D1%16%D4i%B4%98%D5%EBDt%80%ED%88%A6%DB%B1%CD%0D%82%D8%7E; ptc=RG1.358298955115891687.1469532251; _gat=1; _gat_UA-58591210-1=1; _ga=GA1.2.908540953.1469533760");

			BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = r.readLine()) != null) {
				response.append(inputLine);
			}
			
			r.close();
			
			return new JSONObject(response.toString());
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Requests an URL and returns the contents.
	 * @param reqUrl URL to request
	 * @return Content as String
	 */
	private static String getURL(String reqUrl) {
		try {
			URLConnection con = (URLConnection) new URL(reqUrl).openConnection();
			
			// Server delivers incomplete content without GZIP!
			con.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch, br");
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			con.setRequestProperty("cookie", "id=XPhUOpWYc18JkqyqnT2Nm9xpmpwlzbv9N5GPRYTI1mTByMMzxh0Q4vmfrOrTxl6weJh0kaY4Ljvaox0VjRzHYk8MkTWdd01WVF1NkF67p7eJqwtP9l6rR00P6ec16rlb; cili=_2_ZjgzMWYzMzU2ZDZjN2IzNjUyNjYwNGE3NDc4MGMzMDg3MzA1YTlhNmUxMTU4ZDg1ODAwMmExZjQ5ZDI1MzZlY18yNjk1OzA%3D; cirgu=1; did=kclLEbGIeP0a0sluy7HLdc10ybnpIqpNg63ZZb58iaXsXv0GpusCWEvruIl0FoKO; c1=%DA+%CF%0C%DC%E4D%3D%8B%AB%BBn%92%A2q%FA%F6t3%EE%2C%BE%0Eay%AC%A3%A0%C8%04%21%CD%C5%21%BA%FA%05%D3%E2%1D%D5%06%0C%92%E1%EF%C4LN%E8%B0%7FRE%07%9C%C4V%E2%F5%ED%F9An; c2=%DFf%E0%EE%8A%BF%1F%7E%E299%A5%AD%8D%E9%3E%F9F%CC%FEOW%CA%60%2B%0CQ%C8%91%A3%25%F0%0F%F6F%84%BC%11%EA1p%A6%28a%C9%A8%0E%A47A%3A%C9%ADrnjJ2%A6%EA%E8%C9%3F%B9%A7%DD%23-%93%11%10%A4%A6%A7Y%D1%16%D4i%B4%98%D5%EBDt%80%ED%88%A6%DB%B1%CD%0D%82%D8%7E; ptc=RG1.358298955115891687.1469532251; _gat=1; _gat_UA-58591210-1=1; _ga=GA1.2.908540953.1469533760");
			
						
			InputStreamReader r = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
			int c;
			
			StringBuffer response = new StringBuffer();
			
			while ((c = r.read()) != -1) {
				response.append((char) c);
			}
			
			r.close();
			
			return response.toString();
		} catch(Exception e) {
			return null;
		}
	}
	
	
	
	/**
	 * Creates the api wrapper. 
	 * 
	 * @param baseUrl API base url
	 */
	public ResearchGateAPIWrapper(String baseUrl, String cacheFilePrefix, SourceServiceFacadeImpl sourceService) {
		this.sourceService = sourceService;
		this.baseUrl = baseUrl;
	}
	
	/**
	 * Returns the base URL.
	 * @return The base URL.
	 */
	public String getBaseUrl() {
		return baseUrl;
	}
	
	/**
	 * Fetches all ResearchGate user IDs who are members in the given institute/department ID.
	 * 
	 * @param institute The ResearchGate institute key the department corresponds to
	 * @param department The ResearchGate department key which should be searched for authors
	 * @return All user IDs
	 */
	public List<String> getAuthorsFromDepartment(String institute, String department)
	{
		LinkedList<String> users = new LinkedList<>();
		
		int offset = 0;
		int count;
		
		do {
			JSONObject json = getJSON(this.baseUrl + "publicinstitutions.DepartmentMembersContent.html?institutionKey=" 
					+ institute + "&departmentKey=" + department + "&offset=" + offset);
		
			JSONArray results = json.getJSONObject("result").getJSONObject("data").getJSONArray("items");
			
			for(int i = 0; i < results.length(); i++) {
				if(!results.getJSONObject(i).getJSONObject("data").isNull("accountKey") && results.getJSONObject(i).getJSONObject("data").getString("accountKey")
						.length() > 0)
					users.add(results.getJSONObject(i).getJSONObject("data").getString("accountKey"));				
			}
			
			count = results.length();			
			
			offset += count;
		} while(count > 0);
		
		return users;
	}
	
	/**
	 * Fetches all ResearchGate user IDs who are members in the given institute/department ID.
	 * 
	 * @param user The ID of the ResearchGate user to search for publications
	 * @return Found publications
	 */
	public List<Publication> getPublicationsFromUser(String user)
	{
		LinkedList<Publication> publications = new LinkedList<>();
		
		int page = 1;
		int pages = 1;
		
		do {
			// Get the user's publication list
			String html = getURL(this.baseUrl + "profile/" + user + "/publications?sorting=newest&page=" + page);
			Matcher m = REGEX_PROFILE_JSON.matcher(html);
						
			if(!m.find()) break;
			JSONObject result = null;
			
			try {				
				result = new JSONObject(html.substring(m.start(), m.end())).getJSONObject("data").getJSONObject("profileContent")
						.getJSONObject("data").getJSONArray("profileContent").getJSONObject(0).getJSONObject("data");
			} catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
			// Calculate total number of pages
			if(page == 1 && result.get("pager") instanceof JSONObject) 
				pages = (int) Math.ceil((1.0 * result.getJSONObject("pager").getInt("totalItems"))
						/ result.getJSONObject("pager").getInt("pageEndItemNum"));
			
			sourceService.log(user + " -- Page " + page + " / " + pages, LogService.LOG_INFO);
			
			JSONArray items = result.getJSONArray("publicationListItems");
			
			// Iterate over all publications found on the current page
			for(int i = 0; i < items.length(); i++) {
				JSONObject item = items.getJSONObject(i).getJSONObject("data");
				Publication p = new Publication("" + item.getInt("publicationUid"), item.getString("title"));	
				sourceService.log("Item " + (i+1) + " of " + items.length() + ": " + p.getTitle(), LogService.LOG_INFO);
				
				// Fetching details of the publication
				String pubDetailHTML = getURL(this.baseUrl + item.getString("publicationUrl"));
				
				Matcher m2 = REGEX_PUB_JSON.matcher(pubDetailHTML);
				
				m2.find();
				
				// Extract the publication details
				JSONObject pubData = new JSONObject(m2.group(1)).getJSONObject("initState").getJSONObject("publicationData");
				
				// Abstract
				p.setAbstractText(pubData.get("abstract") instanceof String ? pubData.getString("abstract") : "");
				
				// Publication date
				if(pubData.get("publicationDate") instanceof String)
					p.setCreationDate(pubData.getString("publicationDate"));
				else
					p.setCreationDate("");
			
				JSONObject pubAuthors = new JSONObject(m2.group(1)).getJSONObject("initState").getJSONObject("publicationAuthors");
				JSONArray authors = pubAuthors.getJSONObject(pubAuthors.keys().next()).getJSONArray("loadedItems");
				
				// Add all authors
				for(int j = 0; j < authors.length(); j++) {					
					String[] author = authors.getJSONObject(j).getString("nameOnPublication").split(" ");
					p.addAuthor(String.join(" ", Arrays.copyOfRange(author, 0, author.length - 1)), author[author.length - 1], 
							authors.getJSONObject(j).has("accountID") ? "" + authors.getJSONObject(j).getInt("accountId") : null);
				}
				
				publications.add(p);

			}
			
		} while(++page <= pages);
		
		return publications;
	}

}
