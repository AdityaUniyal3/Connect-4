//My Record class
public class Record {
//private instance variables for this class
private String key;
private int score;
private int level;
	
    //My constructor for this class, used to create records in the hash table
	public Record(String key, int score, int level) {
		this.key = key;
		this.score = score;
		this.level = level;
	}
	
	//retrieves the value stored in 'key'
	public String getKey() {
		return this.key;
	}
	
	//retrieves the value stored in 'score'
	public int getScore() {
		return this.score;
	}
	
	//retrieves the value stored in 'level'
	public int getLevel() {
		return this.level;
	}

}
