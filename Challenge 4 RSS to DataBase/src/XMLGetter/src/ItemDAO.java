import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAO {

	ItemDAO() {

	}

	/**
	 * This methods connects to the sql database
	 * 
	 * @return database connection
	 * 
	 * 
	 */
	public Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:ItemDatabase.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	/**
	 * This methods inserts new Item
	 * 
	 * @param item this is the item object
	 * @param Category is the item Category
	 * @return int the Primary key of the Item inserted
	 * @throws SQLException
	 */
	public int insertItem(Item item, String Category) throws SQLException {

		// put your code here
		Connection dbConnection = null;
		PreparedStatement statement = null;
		int idInserted = -1;
		String query = "INSERT INTO " + Category
				+ "s(Guid, Title, Description, PubDate, Link, Slug, Network, State, Tags)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";

		dbConnection = getDBConnection();
		statement = dbConnection.prepareStatement(query);
		statement.setString(1, item.getGuid());
		statement.setString(2, item.getTitle());
		statement.setString(3, item.getDescription());
		statement.setString(4, item.getPubDate());
		statement.setString(5, item.getLink());
		statement.setString(6, item.getSlug());
		statement.setString(7, item.getNetwork());
		statement.setString(8, item.getState());
		statement.setString(9, item.getTags());

		System.out.println(query);
		statement.executeUpdate();
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()) {
			idInserted = rs.getInt(1);
		}

		statement.close();
		dbConnection.close();


		return idInserted;

	}

	/**
	 * This methods gets Article
	 * 
	 * @param ID is the ID of the Article
	 * @param Category is the item Category
	 * @return Item the Article requested
	 * @throws SQLException
	 */
	public Item getItem(int ID, String Category) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement statement = null;
		String query = "SELECT * from " + Category + "s WHERE " + Category + "ID = ?;";
		Item item = null;
		dbConnection = getDBConnection();
		statement = dbConnection.prepareStatement(query);
		statement.setInt(1, ID);

		System.out.println(query);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			item = new Item(rs.getString("Guid"), rs.getString("Title"), rs.getString("Description"),
					rs.getString("PubDate"), rs.getString("Link"), rs.getString("Slug"), rs.getString("Network"),
					rs.getString("State"), rs.getString("Tags"));
		}

		statement.close();
		dbConnection.close();

		return item;
	}
	
	/**
	 * This method updates an existing Item
	 * @param Item this is the Item
	 * @return Boolean, true if Item is updated or false if not
	 * @throws SQLException
	 */
	public Boolean updateItem(int ID,Item item, String Category) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement statement = null;
		Boolean result = false;
		String query = "UPDATE+ "+ Category +"s SET Guid=?, Title=?, Description=?, PubDate=?, Link=?, Slug=?, Network=?, State=?, Tags =? WHERE"+ Category +"ID = ?;";

		dbConnection = getDBConnection();
		statement = dbConnection.prepareStatement(query);
		statement.setString(1, item.getGuid());
		statement.setString(2, item.getTitle());
		statement.setString(3, item.getDescription());
		statement.setString(4, item.getPubDate());
		statement.setString(5, item.getLink());
		statement.setString(6, item.getSlug());
		statement.setString(7, item.getNetwork());
		statement.setString(8, item.getState());
		statement.setString(9, item.getTags());
		statement.setInt(10,ID);	
		

		System.out.println(query);

		if (statement.executeUpdate() > 0) {
			result = true;
		}

		statement.close();
		dbConnection.close();


		return result;
	}

	/**
	 * This methods inserts new Video thumbnail
	 * 
	 * @param thumbnail this is the thumbnail object
	 * @param Category is the item Category
	 * @return boolean, true if item is inserted or false if not
	 * @throws SQLException
	 */
	public Boolean insertThumbnail(Thumbnail thumbnail, String Category) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement statement = null;
		Boolean result = false;
		String query = "INSERT INTO " + Category + "Thumbnails(Link, Size , " + Category + "ID, Width, Height)"
				+ "VALUES (?,?,?,?,?)";

		dbConnection = getDBConnection();
		statement = dbConnection.prepareStatement(query);
		statement.setString(1, thumbnail.getLink());
		statement.setString(2, thumbnail.getSize());
		statement.setInt(3, thumbnail.getItemID());
		statement.setInt(4, thumbnail.getWidth());
		statement.setInt(5, thumbnail.getHeight());

		System.out.println(query);

		if (statement.executeUpdate() > 0) {
			result = true;
		}

		statement.close();
		dbConnection.close();

		return result;

	}

	/**
	 * This methods gets Item Thumbnails
	 * 
	 * @param ID is the ID of the Article Thumbnail
	 * @param Category is the item Category
	 * @return Arraylist of thumbnails found
	 * @throws SQLException
	 */
	public ArrayList<Thumbnail> getThumbnails(int ID, String Category) throws SQLException {
		ArrayList<Thumbnail> Thumbnails = new ArrayList<>();
		Connection dbConnection = null;
		PreparedStatement statement = null;	
		String query = "SELECT * from " + Category + "Thumbnails WHERE " + Category + "ID = ?;";	
		dbConnection = getDBConnection();
		statement = dbConnection.prepareStatement(query);
		statement.setInt(1, ID);

		System.out.println(query);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Thumbnail thumbnail = new Thumbnail(rs.getString("Link"), rs.getString("Size"), rs.getInt(Category + "ID"),
					rs.getInt("Width"), rs.getInt("Height"));
			Thumbnails.add(thumbnail);
		}

		statement.close();
		dbConnection.close();

		return Thumbnails;

	}
	
	/**
	 * This method updates an existing Thumbnail
	 * @param Thumbnail this is the thumbnail
	 * @return Boolean, true if thumbnail is updated or false if not
	 * @throws SQLException
	 */
	public Boolean updateThumbnail(int ID, Thumbnail thumbnail, String Category) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement statement = null;
		Boolean result = false;
		String query = "UPDATE+ " + Category + "Thumbnails SET Link=?, Size=?, " + Category
				+ "ID=?, Width=?, Height=?  WHERE ID = ?;";

		dbConnection = getDBConnection();
		statement = dbConnection.prepareStatement(query);
		statement.setString(1, thumbnail.getLink());
		statement.setString(2, thumbnail.getSize());
		statement.setInt(3, thumbnail.getItemID());
		statement.setInt(4, thumbnail.getWidth());
		statement.setInt(5, thumbnail.getHeight());
		statement.setInt(6, ID);

		System.out.println(query);

		if (statement.executeUpdate() > 0) {
			result = true;
		}

		statement.close();
		dbConnection.close();

		return result;
	}

}
