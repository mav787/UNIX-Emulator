import java.util.*;


public class SubSetTest {

	  int [] arr = {1};
	
	  ArrayList<ArrayList<Integer>> subSet(){
		  
		  ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		  //ArrayList<Integer> fai = new ArrayList<Integer>();
		  //res.add(fai);
		  
		 /* for (int i = 0; i < this.arr.length; i++){
			  subsetHelper(res,this.arr,i);
		  }
		  */
		  return res;
	  }
	  
	  
	  void subsetHelper(ArrayList<ArrayList<Integer>> res, int[] arr, int i){
		  ArrayList<ArrayList<Integer>> mid = new ArrayList<ArrayList<Integer>>();
		  for (ArrayList<Integer> unit : res){
			  mid.add(unit);
		  }
		  
		  for (ArrayList<Integer> unit : mid){
			  unit.add(arr[i]);
		  }
		  res.addAll(mid);
		  
	  }
	  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SubSetTest sst = new SubSetTest();
		System.out.println(sst.subSet());
		
	}

}
