import java.util.*;

public class Tree<T extends Comparable> {
	
	TreeNode<T> root;
	int size;
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

	public void addHelper(TreeNode<T> previous, TreeNode<T> current, T newElement, int height, int direction) {
		TreeNode<T> newNode = new TreeNode<T>(newElement, height);
		if (current == null) {
			if (previous == null) {
				root = newNode;
			}
			else {
				if (direction == 0) {
					previous.setRightChild(newNode);
				}
				else {
					previous.setLeftChild(newNode);
				}
			}
			if (height > maxHeight) {
				maxHeight = height;
			}
			return;
		}
		if (current.getElement().compareTo(newElement) <= 0) {
			addHelper(current, current.getRightChild(), newElement, height+1, 0);
		}
		else {
			addHelper(current, current.getLeftChild(), newElement, height+1, 1);
		}
	}

	public int checkHeight() {
		return checkHeightHelper(root);
	}

	public int checkHeightHelper(TreeNode<T> current) {
		if (current != null) {
			int path1 = checkHeightHelper(current.getLeftChild());
			int path2 = checkHeightHelper(current.getRightChild());
			if (path1 >= path2) {
				return path1 + 1;
			}
			else {
				return path2 + 1;
			}
		}
		else {
			return 0;
		}
	}

	public void remove(T doomedElement) {
		removeHelper(null, root, doomedElement);
	}

	public void removeHelper(TreeNode<T> previous, TreeNode<T> current, T doomedElement) {
		if (current == null) {
			System.out.println("Element not found");
			return;
		}
		else if (current.getElement().compareTo(doomedElement) > 0) {
			removeHelper(current, current.getLeftChild(), doomedElement);
		}
		else if (current.getElement().compareTo(doomedElement) < 0) {
			removeHelper(current, current.getRightChild(), doomedElement);
		}
		else if (current.getElement().equals(doomedElement)) {
			T newElement = removeHelper2(null, current.getRightChild());
			size--;
			if (newElement == null) {
				if (current == root) {
					if (size == 0) {
						root = null;
					}
					else if (current.getRightChild() != null) {
						root.setElement(newElement);
					}
					else {
						root = current.getLeftChild();
					}
				}
				else if (previous.getLeftChild() != null && previous.getLeftChild().equals(current)) {
					previous.setLeftChild(current.getLeftChild());
				}
				else {
					previous.setRightChild(current.getLeftChild());
				}
			}
			else {
				current.setElement(newElement);
			}
			maxHeight = checkHeight();
		}
	}

	public T removeHelper2(TreeNode<T> previous, TreeNode<T> current) {
		if (current == null) {
			return null;
		}
		if (current.getLeftChild() == null) {
			T newElement = current.getElement();
			current = null;
			return newElement;
		}
		else {
			return removeHelper2(current, current.getLeftChild());
		}
	}

	public boolean checkContains(T desiredElement) {
		return checkContainsHelper(root, desiredElement);
	}

	public boolean checkContainsHelper(TreeNode<T> current, T desiredElement) {
		if (current == null) {
			return false;
		}
		else if (current.getElement().compareTo(desiredElement) < 0) {
			return checkContainsHelper(current.getRightChild(), desiredElement);
		}
		else if (current.getElement().compareTo(desiredElement) > 0){
			return checkContainsHelper(current.getLeftChild(), desiredElement);
		}
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
		LinkedList<TreeNode<T>> myQueue = new LinkedList<TreeNode<T>>();
		myQueue.add(current);
		while (myQueue.peek() != null) {
			TreeNode<T> next = myQueue.remove();
			System.out.println(next.toString());
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
			System.out.println(current.getElement());
			preOrderTraversalHelper(current.getLeftChild());
			preOrderTraversalHelper(current.getRightChild());
		}
		return "";
	}

	public String preOrderTraversal() {
		return preOrderTraversalHelper(root);
	}

	public void inOrderTraversalHelper(TreeNode<T> current) {
		if (current != null) {
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
			if (current.getLeftChild() == null && current.getRightChild() == null) {
				if (current.getHeight() < maxHeight-1) {
					return false;
				}
				else {
					return true;
				}
			}
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
		else {
			return true;
		}
	}
	
	public boolean checkBalanced() {
		return checkBalancedHelper(root);
	}

	public TreeNode<T> getRoot() {
		return root;
	}
}