package lists;

public class RandomAccessList {

	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			String left = this.left == null ? "null" : this.left.toString();
			String right = this.right == null ? "null" : this.right.toString();
			return "(" + left + ", " + this.value + ", " + right + ")";
		}
	}

	public static class Tree {
		Node root;
		int size;
		Tree next;

		public Tree(Node root, int size, Tree next) {
			this.root = root;
			this.size = size;
			this.next = next;
		}

		@Override
		public String toString() {
			return "{size = " + this.size + ", root = " + this.root + "}"
				+ (this.next == null ? "" : (", " + this.next.toString()));
		}
	}

	public static Tree empty() {
		return null;
	}

	public static boolean isEmpty(Tree list) {
		return list == null;
	}

	public static Tree cons(int value, Tree head) {
		Tree tree1 = head;
		Tree tree2 = tree1 != null ? tree1.next : null;
		Tree result = null;

		if (tree1 != null && tree2 != null && tree1.size == tree2.size) {
			Node combinedRoot = new Node(value, tree1.root, tree2.root);
			int combinedSize = tree1.size + tree2.size + 1;
			result = new Tree(combinedRoot, combinedSize, tree2.next);
		} else {
			result = new Tree(new Node(value, null, null), 1, head);
		}

		return result;
	}

	public static int get(Tree list, int index) {
		if (isEmpty(list)) {
			throw new IndexOutOfBoundsException();
		}

		return forestLookUp(list, index);
	}

	private static int forestLookUp(Tree head, int index) {
		Tree tree = head;

		while (index >= tree.size) {
			index -= tree.size;
			tree = tree.next;
		}

		return treeLookUp(tree.root, tree.size, index);
	}

	private static int treeLookUp(Node root, int size, int index) {
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
		Tree list = cons(4, cons(3, cons(2, cons(1, empty()))));
		System.out.println(list);
		System.out.println(get(list, 0));
	}

}
