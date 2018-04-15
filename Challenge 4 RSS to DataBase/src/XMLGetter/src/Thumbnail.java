
public class Thumbnail {
	
	String Link, Size;
	int Width, Height, ItemID;
	
	Thumbnail(String Link, String Size, int ItemID, int Width, int Height){
		this.Link = Link;
		this. Size = Size;
		this.ItemID = ItemID;
		this.Width = Width;
		this. Height = Height;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public int getItemID() {
		return ItemID;
	}

	public void setItemID(int itemID) {
		ItemID = itemID;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}
	
	@Override
	public String toString(){
		return Link + "  " + Size + "  " + Width + "  " + Height + "  " + ItemID;
	}
	
	

}
