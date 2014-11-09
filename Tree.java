import java.util.*;

public class Tree<T extends Comparable> {
	
	TreeNode<T> root;
	int size;
	//length of the longest branch in the tree
	int maxHeight;

	public Tree() {
		root = null;
		size = 0;
		maxHeight = 0;
	}

	public void add(T newElement) {
		addHelper(null, root, newElement, 1, 0);
		size++;
	}
	//recursive function to add an element to the correct place in the tree
	//direction = 1 when the current node is the left child of the previous node
	//direction = 0 when the current node is the right child of the previous node
	//height is the height of the current node
	public void addHelper(TreeNode<T> previous, TreeNode<T> current, T newElement, int height, int direction) {
		TreeNode<T> newNode = new TreeNode<T>(newElement, height);
		//when current = null you are at the location where the element belongs in the tree
		if (current == null) {
			//this is the first element in the tree
			if (previous == null) {
				root = newNode;
			}
			else {
				//direction = 0 means the last move was to the right child
				if (direction == 0) {
					previous.setRightChild(newNode);
				}
				//direction = 1 means the last move was to the left child
				else {
					previous.setLeftChild(newNode);
				}
			}
			//update maxheight if necessary
			if (height > maxHeight) {
				maxHeight = height;
			}
			return;
		}
		//The new element is greater than the current node's element
		if (current.getElement().compareTo(newElement) <= 0) {
			//recursion with the right child
			addHelper(current, current.getRightChild(), newElement, height+1, 0);
		}
		//the new element is less than the current node's element
		else {
			//recursion with the left child
			addHelper(current, current.getLeftChild(), newElement, height+1, 1);
		}
	}
	//get height of tree
	public int checkHeight() {
		return checkHeightHelper(root);
	}

	public int checkHeightHelper(TreeNode<T> current) {
		if (current != null) {
			//recursion through the entire tree initially
			int path1 = checkHeightHelper(current.getLeftChild());
			int path2 = checkHeightHelper(current.getRightChild());
			//whichever branch is longer, take that value and add 1,
			//then return the sum to the next step of the recursion
			if (path1 >= path2) {
				return path1 + 1;
			}
			else {
				return path2 + 1;
			}
		}
		//the node is null, end of the branch
		else {
			return 0;
		}
	}

	public void remove(T doomedElement) {
		removeHelper(null, root, doomedElement);
	}

	public void removeHelper(TreeNode<T> previous, TreeNode<T> current, T doomedElement) {
		//reached an empty node without hitting the doomedElement
		if (current == null) {
			System.out.println("Element not found");
			return;
		}
		//if the doomed element is less than the current element
		else if (current.getElement().compareTo(doomedElement) > 0) {
			removeHelper(current, current.getLeftChild(), doomedElement);
		}
		//if the doomed element is greater than the current element
		else if (current.getElement().compareTo(doomedElement) < 0) {
			removeHelper(current, current.getRightChild(), doomedElement);
		}
		//found doomed element
		else if (current.getElement().equals(doomedElement)) {
			T newElement = removeHelper2(null, current.getRightChild());
			size--;
			//there is no right child
			if (newElement == null) {
				//if the root is removed, is has to be reset to something else
				if (current == root) {
					if (size == 0) {
						root = null;
					}
					else if (current.getRightChild() != null) {
						root.setElement(newElement);
					}
					//when there is no right child, the left child can replace the removed element
					else {
						root = current.getLeftChild();
					}
				}
				//we went to the left from the previous node
				else if (previous.getLeftChild() != null && previous.getLeftChild().equals(current)) {
					previous.setLeftChild(current.getLeftChild());
				}
				else {
					previous.setRightChild(current.getLeftChild());
				}
			}
			//replace element with the smallest element greater than the removed element
			else {
				current.setElement(newElement);
			}
			maxHeight = checkHeight();
		}
	}

	public T removeHelper2(TreeNode<T> previous, TreeNode<T> current) {
		//the removed element has no right child
		if (current == null) {
			return null;
		}
		//this is the smallers element greater than the removed element
		if (current.getLeftChild() == null) {
			T newElement = current.getElement();
			//remove this node so that it is not in two places when we move it up
			current = null;
			return newElement;
		}
		//recursively find the smallest element larger than the removed element
		else {
			return removeHelper2(current, current.getLeftChild());
		}
	}

	public boolean checkContains(T desiredElement) {
		return checkContainsHelper(root, desiredElement);
	}

	public boolean checkContainsHelper(TreeNode<T> current, T desiredElement) {
		//the element was not found
		if (current == null) {
			return false;
		}
		//element > current element
		else if (current.getElement().compareTo(desiredElement) < 0) {
			return checkContainsHelper(current.getRightChild(), desiredElement);
		}
		//element < current element
		else if (current.getElement().compareTo(desiredElement) > 0){
			return checkContainsHelper(current.getLeftChild(), desiredElement);
		}
		//element = current element
		else {
			return true;
		}
	}

	public boolean checkEmpty() {
		if (root == null) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getSize() {
		return size;
	}

	public void levelOrderTraversal() {
		TreeNode<T> current = root;
		//create a queue
		LinkedList<TreeNode<T>> myQueue = new LinkedList<TreeNode<T>>();
		//start at the root
		myQueue.add(current);
		//check that myQueue is not empty
		while (myQueue.peek() != null) {
			//remove first item in myQueue
			TreeNode<T> next = myQueue.remove();
			System.out.println(next.toString());
			//add children of the node to the queue
			if (next.getLeftChild() != null) {
				myQueue.add(next.getLeftChild());
			}
			if (next.getRightChild() != null) {
				myQueue.add(next.getRightChild());
			}
		}
	}

	public String preOrderTraversalHelper(TreeNode<T> current) {
		if (current != null) {
			//prints going down the left then across
			System.out.println(current.getElement());
			preOrderTraversalHelper(current.getLeftChild());
			preOrderTraversalHelper(current.getRightChild());
		}
		return "";
	}

	public String preOrderTraversal() {
		//start at the root
		return preOrderTraversalHelper(root);
	}

	public void inOrderTraversalHelper(TreeNode<T> current) {
		if (current != null) {
			//prints going from least to greatest
			inOrderTraversalHelper(current.getLeftChild());
			System.out.println(current.getElement());
			inOrderTraversalHelper(current.getRightChild());
		}
	}

	public void inOrderTraversal() {
		inOrderTraversalHelper(root);
	}

	public String postOrderTraversalHelper(TreeNode<T> current) {
		if (current != null) {
			postOrderTraversalHelper(current.getLeftChild());
			postOrderTraversalHelper(current.getRightChild());
			System.out.println(current.getElement());
		}
		return "";
	}

	public String postOrderTraversal() {
		return postOrderTraversalHelper(root);
	}

	public int getHeight() {
		return maxHeight;
	}

	public boolean checkBalancedHelper(TreeNode<T> current) {
		if (current != null) {
			//is this the end of a branch?
			if (current.getLeftChild() == null && current.getRightChild() == null) {
				//is this branch 2 nodes shorter than the max height of the tree?
				if (current.getHeight() < maxHeight-1) {
					return false;
				}
				else {
					return true;
				}
			}
			//check both child nodes if this is not the end of a branch
			else {
				boolean path1 = checkBalancedHelper(current.getLeftChild());
				boolean path2 = checkBalancedHelper(current.getRightChild());
				if (path1 == false || path2 == false) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		//if there is nothing in the tree it is balanced
		else {
			return true;
		}
	}
	//make sure no branch is 2 nodes longer than any other branch
	public boolean checkBalanced() {
		return checkBalancedHelper(root);
	}

	public TreeNode<T> getRoot() {
		return root;
	}
}