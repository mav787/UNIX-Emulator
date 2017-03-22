package test;

public class Test {
	
	static void print(ListNode head){
		for (ListNode node = head; node != null; node = node.next){
			System.out.print(node.val);
			System.out.print("->");
			
		}
		System.out.println("null");
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode head = node1;
		
		node1.next = node2;
		node2.next = node3;
		
		print(head);
		
		node1 = node2;
		//head = node2;
		
		print(head);
		
		
	}
}


class ListNode{
	int val;
	ListNode next;
	
	ListNode(int val){
		this.val = val;
	}
}