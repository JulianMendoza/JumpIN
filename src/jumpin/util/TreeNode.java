package jumpin.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jumpin.model.move.MoveSet;

public class TreeNode<T> {
	
    private List<TreeNode<T>> children = new LinkedList<TreeNode<T>>();
    private TreeNode<T> parent = null;
    private T data;
    private List<TreeNode<T>> childNodes=new ArrayList<TreeNode<T>>();

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
    public boolean hasChildren() {
    	return !children.isEmpty();
    }
    public List<TreeNode<T>> getChildNodes(){

    	return this.childNodes;
    }
    public String toString() {
    	String toString = data.toString() + "\n";
    	for(TreeNode<T> child : children) {
    		toString += child.toString();
    	}
    	return toString;
    }
    public void addChildNodes(){
    	for(TreeNode<T> child : children) {
    		if(!child.hasChildren()) {
    			childNodes.add(child);
    		}
    		child.addChildNodes();
    	}
    }
}
