package huff;
import java.util.PriorityQueue;

public class Huffman {
	
	private static int[] counting;
	private static char[] charArray;
	private static int[] auxArray1;
	private static char[] auxArray2;
	private static char[] mainCharArray;
	private static int size;
	private static String[][] huffCode;
	private String inputSt;
	private static String huffmanCode = "";
	static PriorityQueue<HuffmanNode> pQueue = new PriorityQueue<HuffmanNode>();
	/*
	 * 
	 * 
	 * 
	 *    Constructor of the class
	 * 
	 * 
	 */
	public Huffman(String inputSt){
		this.inputSt = inputSt;
	}
	
	/*
	 ** 
	 * 
	 * 
	 *     Process the inputArray and change it into huffman Code
	 * 
	 * 
	 */

	public void initialize()
		{	
			charArray = inputSt.toCharArray();
			size=0;
			counting = new int[charArray.length];
			mainCharArray = new char[charArray.length];
			
			for(int i=0; i<charArray.length; i++)
			{
				int count = 0;
				for(int j=0; j<charArray.length; j++)
				{
					if(charArray[i] == mainCharArray[j])
						count++;
				}
				if(count == 0 && charArray[i]!= ' ')
				{
					mainCharArray[size] = charArray[i];
					size++;
				}
			}

			for(int i=0; i<charArray.length; i++)
			{
				for(int j=0; j<charArray.length; j++)
				{
					if(mainCharArray[i]==charArray[j])
						counting[i]++;
				}	
			}
			
			auxArray1 = new int[counting.length];
			auxArray2 = new char[counting.length];
			huffCode = new String[size][2];
			
			
			sortByCount();
			buildQueue();
			System.out.println("character\tFrequency\thuffCode");
			printTree(pQueue.peek(), "");
			
			int digits = getHuffmanCode();
			System.out.print("the huffmancode is: " + huffmanCode + " \ndigits: " + digits);
			
			
		}
	/*
	 * 
	 * 
	 * 
	 *      Find each minimum frequency character
	 * 
	 * 
	 */
		
	private static int findIndexOfMin()
		{
			int index = 0, min = counting[0];
			for(int i=0; i<counting.length; i++)
			{
				if(counting[i] < min && counting[i] != 0)
				{
					index = i;
					min = counting[i];
				}			
			}
			return index;
		}
		
	/*
	 * 
	 * 
	 * 
	 *      Sort the array via frequency of letters
	 * 
	 * 	
	 */
		
		
		private static void sortByCount()
		{
			int p = 0;
			while(p<counting.length){
				int index = findIndexOfMin();
				auxArray1[p] = counting[index];
				counting [index] = 100;
				auxArray2[p] = mainCharArray[index];
				p++;
			}
			mainCharArray = auxArray2;
			counting = auxArray1;
		}
		
		
		/*
		 * 
		 * 
		 *    Build the priority Queue and put the huffmanNodes there,
		 * 
		 * 
		 */
		
		 private static void buildQueue()
		    {
			 	for(int i=0; i<size; i++)
			 	{
			 		HuffmanNode h = new HuffmanNode(Character.toString(mainCharArray[i]), counting[i]);
			 		pQueue.add(h);
			 	}
			 	
		    	while(pQueue.size()>1)
		    	{
		       		HuffmanNode left = pQueue.poll(), right = pQueue.poll();
		    		HuffmanNode hn1 = new HuffmanNode("new node", right.getCount()+left.getCount());
		    		hn1.setRLChild(right, left);
		    		pQueue.add(hn1);
		    	}
		    	
		    	
		    }

		 
		 /*
		  * 
		  * 
		  *    Print the huffmanNode tree.
		  * 
		  * 
		  */
		 
		 private static int i=0;
		    private static void printTree(HuffmanNode a, String prefix)
		    {
		    	if(a.getlChild() == null || a.getrChild()== null)
		    	{
		    		huffCode[i][1] = prefix;
		    		huffCode[i][0] = a.getS();
		    		System.out.println(a.getS()+ "\t" + a.getCount() + "\t" + huffCode[i][1]);		    		
		    		prefix = "";
		    		i++;
		    	}
		    	else{
		    	prefix = prefix.concat("0");
		    	printTree(a.getlChild(),prefix);
		        prefix = prefix.substring(0, prefix.length() - 1);
		    	
		    	if(a.getrChild() != null)
		    		prefix = prefix.concat("1");
		    	printTree(a.getrChild(),prefix);
		        prefix = prefix.substring(0, prefix.length() - 1);
		    	}
		    }
		    
		    /*
		     * 
		     * 
		     *   put huffmanCode into an array and return the digits
		     * 
		     * 
		     * 
		     */
		    private int getHuffmanCode()
		    {
		    	String[] st = inputSt.split("");
		    	int index=0;
		    	for(int i=1; i<st.length; i++)
		    	{
		    		index=0;
		    		for(int j=0; j<huffCode.length; j++)
		    		{
		    			if(st[i].equals((huffCode[j][0])))
		    			{
		    				index =j;
		    				break;
		    			}
		    		}
		    		huffmanCode = huffmanCode.concat(huffCode[index][1]);
		    	}
		    	String[] digits = huffmanCode.split("");
		    	return digits.length-1;
		    }
}
