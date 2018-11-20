import java.util.Arrays;
import java.util.Comparator;

public class BoxStackingP3 {

//function takes array of Boxes and its size as input, implements LIS algorithm to find out maximum possible height of the stacks.
	static int maxStackHeight( Box array[], int size){ 
		
		Box[] instance = new Box[size*3]; 
		
		//----------------------Creates 3 instances of the same box----------------------------
		for(int i = 0;i < size;i++)
		{ Box box = array[i]; 				
			instance[3*i] = new Box(box.height, Math.max(box.width,box.depth),Math.min(box.width,box.depth)); 
			instance[3*i+1] = new Box(box.width, Math.max(box.height,box.depth),Math.min(box.height,box.depth));
			instance[3*i+2] = new Box(box.depth, Math.max(box.width,box.height), Math.min(box.width,box.height)); 
			} 
		
		//-----------Calculate the area for newly created array of box
		for(int i = 0; i < instance.length; i++) 
			instance[i].area = instance[i].width * instance[i].depth; 
	
		//-----sorted this newly created array according to their area by descending order
		Arrays.sort(instance, new BoxComparator()); 
		
		//Below commented function is used to verify newly sorted triple size array of boxes
		/*for(int i = 0;i < instance.length;i++)
		{ 
			System.out.println("instace area: "+ instance[i].area +" instance height: "+instance[i].height+ "instance depth:" +instance[i].depth+"instance width:"+instance[i].width);
		} */
		
		//-------------  new integer array 	to store the heights of boxes to implement LIS algorithm
		int[]stackheight = new int[3 * size]; 
		for (int i = 0; i < stackheight.length; i++ ) stackheight[i] = instance[i].height; 
		
		//--------Implement the LIS algorithm--------compares the boxes as per their width and depth and calculates the possible maximum height
		for(int i = 0; i < stackheight.length; i++){ 
			stackheight[i] = 0; 
			Box b = instance[i]; 
			int resultant_height = 0; 
			for(int j = 0; j < i; j++){ 
				Box b_prev = instance[j]; 
				if(b.width < b_prev.width && b.depth < b_prev.depth){ 
					resultant_height = Math.max(resultant_height, stackheight[j]); 
				} } 
			stackheight[i] = resultant_height + b.height; } 
		
		int max_height = 0; 
		
		//-------find out the final maximum height from the array stackheight  array
		for(int i = 0; i < stackheight.length; i++)
			max_height = Math.max(max_height, stackheight[i]); 
			
		//--------returns the largest possible height
		return max_height; 
	} //-- end of function 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box[] arr = new Box[10]; 
		/*arr[0] = new Box(1, 5, 1);
		arr[1] = new Box(2, 2, 5); 
		arr[2] = new Box(4, 4, 4);	*/	
		
		
		arr[0] = new Box(24, 25, 26);
		arr[1] = new Box(1, 2, 3); 
		arr[2] = new Box(4, 5, 6); 
		arr[3] = new Box(10, 12, 32);
		arr[4] = new Box(14, 16, 17); 
		arr[5] = new Box(11, 12, 13); 
		arr[6] = new Box(14, 15, 16); 
		arr[7] = new Box(20, 22, 32);
		arr[8] = new Box(24, 26, 27); 
		arr[9] = new Box(21, 22, 23); 
		
		  long startTime = System.nanoTime();
		  
		  // actual function call for maxStackHeight
		  int box_stack_heihght=maxStackHeight(arr,arr.length); 
		  
		  long endTime = System.nanoTime();
		  long timeDifference = endTime - startTime;
		  System.out.println("Number of Boxes : " +arr.length+ " Time required : " +timeDifference +"ns");
		  System.out.println("The maximum possible "+ "height of stack is " +box_stack_heihght);
	}
}

// To represent the 3D box with width,depth and height
class Box{ 
	int height, width, depth, area; 
	public Box(int h, int w, int d) { 
		height = h; 
		width = w; 
		depth = d; } 
}

// Comparator implementation to compare the boxes in descending order as per their areas
class BoxComparator implements Comparator<Box>{
	@Override
	public int compare(Box o1, Box o2) {
		// TODO Auto-generated method stub
		return o2.area-o1.area;
	}


}
