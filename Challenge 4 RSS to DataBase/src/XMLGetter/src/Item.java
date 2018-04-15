
public class Item {
	
	String Guid, Title, Description, PubDate, Link, Slug, 
	Network, State, Tags;
	
	
	Item(String Guid, String Title, String Description, 
			String PubDate, String Link, String Slug, String Network, String State, String Tags){
		
		this.Guid = Guid;	
		this.Title = Title;
		this.Description = Description;
		this.PubDate = PubDate;
		this.Link = Link;
		this.Slug = Slug;
		this.Network = Network;
		this.State = State;
		this.Tags = Tags;
		
	}


	public String getGuid() {
		return Guid;
	}


	public void setGuid(String guid) {
		Guid = guid;
	}


	public String getTitle() {
		return Title;
	}


	public void setTitle(String title) {
		Title = title;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getPubDate() {
		return PubDate;
	}


	public void setPubDate(String pubDate) {
		PubDate = pubDate;
	}


	public String getLink() {
		return Link;
	}


	public void setLink(String link) {
		Link = link;
	}


	public String getSlug() {
		return Slug;
	}


	public void setSlug(String slug) {
		Slug = slug;
	}


	public String getNetwork() {
		return Network;
	}


	public void setNetwork(String network) {
		Network = network;
	}


	public String getState() {
		return State;
	}


	public void setState(String state) {
		State = state;
	}
	
	public String getTags() {
		return Tags;
	}


	public void setTags(String tags) {
		Tags = tags;
	}
	
	@Override
	public String toString(){
		return Guid + " " + Title  + " " + Description  + " " + PubDate  + " " + Link  + " " + Slug +
				" " + Network  + " " + State  + " " + Tags;
	}
	
	
	
	

}
