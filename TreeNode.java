public class TreeNode<T extends Comparable> {
	
	TreeNode<T> leftChild;
	TreeNode<T> rightChild;
	T element;
	int height;

	public TreeNode(T _element, int _height) {
		element = _element;
		leftChild = null;
		rightChild = null;
		height = _height;
	}

	public void setHeight(int newHeight) {
		height = newHeight;
	}

	public void setElement(T _element) {
		element = _element;
	}

	public int getHeight() {
		return height;
	}

	public void setLeftChild(TreeNode<T> newChild) {
		leftChild = newChild;
	}

	public void setRightChild(TreeNode<T> newChild) {
		rightChild = newChild;
	}

	public T getElement() {
		return element;
	}

	public TreeNode<T> getLeftChild() {
		return leftChild;
	}

	public TreeNode<T> getRightChild() {
		return rightChild;
	}

	public String toString() {
		return element.toString();
	}
}