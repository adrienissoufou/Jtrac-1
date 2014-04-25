package info.jtrac.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ServerSettings implements Serializable {

	private long id;
	private String url;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
