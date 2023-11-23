package huff;

public class HuffmanNode implements Comparable<HuffmanNode>{
	private HuffmanNode rChild;
	private HuffmanNode lChild;
	private String s;
	private int count;
	public HuffmanNode(){}
	
	
	public HuffmanNode(String s, int count){
		this(s, count, null, null);
	}
	
	
	public HuffmanNode(String s, int count, HuffmanNode rChild, HuffmanNode lChild){
		this.s = s;
		this.count = count;
		this.rChild = rChild;
		this.lChild = lChild;
	}
	
	
	public void setRLChild(HuffmanNode rChild, HuffmanNode lChild)
	{
		this.rChild = rChild;
		this.lChild = lChild;
	}
	
	
	public String getS() {
		return s;
	}
	
	public HuffmanNode getrChild() {
		return rChild;
	}


	public HuffmanNode getlChild() {
		return lChild;
	}

	public int getCount()
	{
		return count;
	}



	@Override
	public int compareTo(HuffmanNode o) {
		
		return count-o.getCount();
}
}
