import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Controller {

	public static void main(String[] args) {

		ItemDAO itemDAO = new ItemDAO();

		

		try {
			System.out.println(itemDAO.getItem(1,"Video"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Finished");

	}
	
	public static void InsertData(ItemDAO itemDAO){
		String url = "https://ign-apis.herokuapp.com/content/feed.rss";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Document doc = db.parse(new URL(url).openStream());
			NodeList items = doc.getElementsByTagName("item");

			for (int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i);

				if (item.getNodeType() == Node.ELEMENT_NODE) {
					Element itemElement = (Element) item;

					int ID;
					String Guild = itemElement.getElementsByTagName("guid").item(0).getTextContent();
					String Category = itemElement.getElementsByTagName("category").item(0).getTextContent();
					String Title = itemElement.getElementsByTagName("title").item(0).getTextContent();
					String Description = itemElement.getElementsByTagName("description").item(0).getTextContent();
					String PubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
					String Link = itemElement.getElementsByTagName("link").item(0).getTextContent();
					String Slug = itemElement.getElementsByTagName("ign:slug").item(0).getTextContent();
					String Network = itemElement.getElementsByTagName("ign:networks").item(0).getTextContent();
					String State = itemElement.getElementsByTagName("ign:state").item(0).getTextContent();
					String Tags = itemElement.getElementsByTagName("ign:tags").item(0).getTextContent();

					Item tempItem = new Item(Guild, Title, Description, PubDate, Link, Slug, Network, State, Tags);

					int ItemID = ItemInsert(itemDAO, tempItem, Category);

					NodeList thumbnails = itemElement.getElementsByTagName("ign:thumbnail");
					for (int j = 0; j < thumbnails.getLength(); j++) {
						Node thumbnail = thumbnails.item(j);

						if (thumbnail.getNodeType() == Node.ELEMENT_NODE) {
							Element thumbnailElement = (Element) thumbnail;
							String ThumbLink = thumbnailElement.getAttribute("link");
							String ThumbSize = thumbnailElement.getAttribute("size");
							int ThumbWidth = Integer.parseInt(thumbnailElement.getAttribute("width"));
							int ThumbHeight = Integer.parseInt(thumbnailElement.getAttribute("height"));

							Thumbnail tempThumbnail = new Thumbnail(ThumbLink, ThumbSize, ItemID, ThumbWidth,
									ThumbHeight);
							ThumbnailInsert(itemDAO, tempThumbnail, Category);

						}
					}
				}
			}

		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int ItemInsert(ItemDAO itemDAO, Item item, String Category) {
		int ID = -1;
		try {

			ID = itemDAO.insertItem(item, Category);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ID;

	}

	public static void ThumbnailInsert(ItemDAO itemDAO, Thumbnail thumbnail, String Category) {

		try {

			itemDAO.insertThumbnail(thumbnail, Category);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
