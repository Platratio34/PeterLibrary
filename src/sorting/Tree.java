package sorting;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

	protected List<TreeNode<T>> nodes;
	
	public Tree() {
		nodes = new ArrayList<TreeNode<T>>();
	}
	
	public TreeNode<T> getTop() {
		TreeNode<T> top = nodes.get(0);
		sortDown();
		
		return top;
	}
	
	public void sortDown() {
		
	}
	
	protected void moveUp(int index) {
		TreeNode<T> t = nodes.get(index);
		TreeNode<T> p = t.parent;
		
	}
}
