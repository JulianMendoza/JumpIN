package jumpin.util;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {
	
    private List<TreeNode<T>> children = new LinkedList<TreeNode<T>>();
    private TreeNode<T> parent = null;
    private T data;

    public TreeNode(T data, TreeNode<T> parent) {
        this.data = data;
        this.parent = parent;
    }
    
    public List<TreeNode<T>> children() {
    	return children;
    }
    
    public T data() {
    	return data;
    }
    
    public TreeNode<T> parent() {
    	return parent;
    }
    
    public void addChild(TreeNode<T> t) {
    	children.add(t);
    }
    
    public String toString() {
    	String toString = data.toString() + "\n";
    	for(TreeNode<T> child : children) {
    		toString += child.toString();
    	}
    	return toString;
    }
}
