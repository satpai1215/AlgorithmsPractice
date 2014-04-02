import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MaxSumPyramid {
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("triangle.txt"));
		
		//pyramid is an arrayList where each row is represented by a String[]
		ArrayList<String[]> pyramid = new ArrayList<String[]>();
		
		String rowString = reader.readLine();		
		
		//0. read file, populate pyramid
		while (rowString != null) {
			pyramid.add(rowString.split(" "));
			rowString = reader.readLine();
		}
		reader.close();
		
		//1. Start at second to last row of pyramid (rowTop)
		//2. For each num in rowTop, check left and right child in rowBottom, add max to self
		//3. Delete last row (not needed)
		//4. Continue upward until you reach first row.
		
		int size = pyramid.size() - 1;
		for(int i = size; i > 0; i-- ) {
			String[] rowTop = pyramid.get(i-1);
			String[] rowBottom = pyramid.get(i);
			
			for(int x = 0; x < rowTop.length ; x++) {
				int parent = Integer.parseInt(rowTop[x]);
				int leftChild = Integer.parseInt(rowBottom[x]); 
				int rightChild = Integer.parseInt(rowBottom[x+1]);
				
				rowTop[x] = String.valueOf(parent + Math.max(leftChild, rightChild));
			}
			pyramid.remove(i);
		}
		System.out.println(pyramid.get(0)[0]);
	}

}
