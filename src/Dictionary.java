//My Dictionary class
public class Dictionary implements DictionaryADT{
	//my instance variables for this class
	private int size;
	private int numRec;
	private Node [] hashTable;
	
	//My constructor for this class, creates a new s initializes a dictionary with 
	//an empty hash table of the specified size
	public Dictionary(int size) {
		this.hashTable = new Node[size];
		this.numRec = 0;
		this.size = size;
	}
	
	private int hashFunc (String key) {
		int value = 0;
		for(int i = 0; i < key.length(); i++) {
			value = ((key.charAt(i)) * 13 + value*17) % this.size;
		}
		return value;
	}
	
	//put method inserts the given Record object 'rec' into the dictionary, thorws a duplicate key exception if 'rec' already exists in the dictionary
	public int put (Record rec) throws DuplicatedKeyException{
		int position = hashFunc(rec.getKey());
		Node head = hashTable[position];
		
		while(head != null ) {
			if(head.getElement().getKey().equals(rec.getKey())) {
				throw new DuplicatedKeyException("Duplicate Key Exception: This key already exists in the dictionary.");
			}
			head = head.getNext();
		}
		head = hashTable[position];
		Node newNode = new Node(rec);
		
		if(head == null) {
			hashTable[position] = newNode;
			return 0;
		}
		else {
			newNode.setNext(head);
			hashTable[position] = newNode;
			return 1;
		}
	}
	
	//remove method removes the record containing the given key value from the table, throws inexistent key exception if the the table doesnt have a record with the given key
	public void remove (String key) throws InexistentKeyException{
		int position = hashFunc(key);
		Node head = hashTable[position];
		Node previous = null;
		if(head == null) {
			throw new InexistentKeyException("Inexistent Key Exception: This record does not exist in the dictionary");
		}
		while(head != null) {
			if(head.getElement().getKey().equals(key)) {
				break;		
			}
			else{
				previous = head;
				head = head.getNext();
			}
		}
	}
    
	//get method which returns the Record object stored in the hash table containing the given key value
	public Record get(String key) {
		int position = hashFunc(key);
	    Node head = this.hashTable[position];
	    
	    Node temp = head;
	    if(temp == null) {
	    	return null;
	    }
	    while(temp != null) {
			if(temp.getElement().getKey().equals(key)) {
				return temp.getElement();
			}
		temp = temp.getNext();
	    }
	    return null;
	}

	public int numRecords() {
		return this.numRec;
	}
    
	//My node class used to construct and access the linked list associated to the entries of the hash table
    class Node{
	    private Node next;
	    private Record element;
	    
	    public Node(Record element, Node next)
	    {
	        this.next = next;
	        this.element = element;
	    }
	    
	    public Node(Record element)
	    {
	        next = null;
	        this.element = element;
	    }
	    
	    public Node getNext()
	    {
	        return next;
	    }

	    public void setNext (Node node)
	    {
	        this.next = node;
	    }
	    
	    public Record getElement()
	    {
	        return element;
	    }
	    
	    public void setElement (Record element)
	    {
	        this.element = element;
	    }
	}
}