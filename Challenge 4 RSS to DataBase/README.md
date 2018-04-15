# Back-End
To store the data into an SQL database I used java to get the data from the API, read the xml and store each item into the database.

To normalise the data, I decided to store the item into two tables depending on their category, either article or video. Having two tables means that that queries will be much quicker for specific categories.

For each category there was a thumbnail table, as each item could have many thumbnails but one thumbnail could only belong to one item ( 1 to many relationship)

I indexed the title and the tags of each table, this allows for users to query a specific title or tag extremely quickly.

# Bonus
Unfortunaly I didnt have time to create a bonus application that would use the database. One idea I had was to create an application that allowed users to pick their interests and the kind of meterial they wanted to see.

The interest could be compared to the tags each item has, the user then could view each item. 
