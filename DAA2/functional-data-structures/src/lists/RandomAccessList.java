package lists;

import java.util.LinkedList;

public class RandomAccessList {

	public class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}

		@Override
		public String toString() {
			String left = this.left == null ? "null" : this.left.toString();
			String right = this.right == null ? "null" : this.right.toString();
			return "(" + left + ", " + this.value + ", " + right + ")";
		}
	}

	public class Tree {
		int size;
		Node root;

		public Tree(Node root, int size) {
			this.root = root;
			this.size = size;
		}

		@Override
		public String toString() {
			return "{size = " + this.size + ", root = " + this.root + "}";
		}
	}

	LinkedList<Tree> forest;

	public RandomAccessList() {
		forest = new LinkedList<Tree>();
	}

	@Override
	public String toString() {
		return forest.toString();
	}

	public void cons(int value) {
		if (forest.size() >= 2 && forest.get(0).size == forest.get(1).size) {
			Tree tree1 = forest.removeFirst();
			Tree tree2 = forest.removeFirst();

			Node newRoot = new Node(value);
			newRoot.left = tree1.root;
			newRoot.right = tree2.root;

			Tree combined = new Tree(newRoot, tree1.size + tree2.size + 1);
			forest.addFirst(combined);
		} else {
			forest.addFirst(new Tree(new Node(value), 1));
		}
	}

	public int get(int index) {
		if (forest.size() == 0) {
			throw new IndexOutOfBoundsException();
		}

		return forestLookUp(index);
	}

	private int forestLookUp(int index) {
		Tree tree = forest.get(0);
		int nextTreeIndex = 1;

		while (index >= tree.size) {
			index -= tree.size;
			tree = forest.get(nextTreeIndex);
			++nextTreeIndex;
		}

		return treeLookUp(tree.root, tree.size, index);
	}

	private int treeLookUp(Node root, int size, int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return root.value;
		} else {
			int subTreeSize = size / 2;

			if (index <= subTreeSize) {
				return treeLookUp(root.left, subTreeSize, index - 1);
			} else {
				return treeLookUp(root.right, subTreeSize, index - 1 - subTreeSize);
			}
		}
	}

	public static void main(String[] args) {
		RandomAccessList ral = new RandomAccessList();
		ral.cons(1);
		ral.cons(2);
		ral.cons(3);
		System.out.println(ral);
		System.out.println(ral.get(1));
	}

}
