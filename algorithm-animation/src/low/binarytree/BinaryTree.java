package low.binarytree;

public class BinaryTree <E extends Comparable<E>>{
		private TreeNode<E> root;
		private int size = 0;
		public BinaryTree(){
			
		}
		
		public BinaryTree(E[] object){
			for(E k : object)
				insert(k);
		}
		
		public TreeNode<E> creatTreeNode(E e){
			return new TreeNode<E>(e);
		}
		
		public boolean search(E e){
			TreeNode<E> current = root;
			while (current != null) {
				if(e.compareTo(current.element) < 0){
					current = current.left;
				}else if(e.compareTo(current.element) > 0){
					current = current.right;
				}else {
					return true;
				}
			}
			return false;
		}
		
		public boolean insert(E e){
			if(root == null){
				root = creatTreeNode(e);
			}else{
				TreeNode<E> parent = null;
				TreeNode<E> current = root;
				while(current != null){
					if(e.compareTo(current.element) < 0){
						parent = current;
						current = current.left;
					}else if(e.compareTo(current.element) > 0){
						parent = current;
						current = current.right;
					}else{
						return false;
					}
				}
				if(e.compareTo(parent.element) < 0){
					parent.left = creatTreeNode(e);
				}
				if(e.compareTo(parent.element) > 0){
					parent.right = creatTreeNode(e);
				}
			}
			size++;
			return true;
		}
		
		public int getSize(){
			return size;
		}

		public TreeNode<E> getRoot(){
			return root;
		}

		
		public boolean delete(E e){
			System.out.println("delete somthing" + e.toString());
			
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while(current != null){
				if(e.compareTo(current.element) < 0){
					parent = current;
					current = current.left;
				}else if(e.compareTo(current.element) > 0){
					parent = current;
					current = current.right;
				}else {
					break;
				}
			}
			
			if(current == null){
				return false;
			}
			if(current.left == null){
				if(parent == null){
					root = current.right;
				}else{
					if(e.compareTo(parent.element) < 0){
						parent.left = current.right;
					}else{
						parent.right = current.right;
					}
				}
			}else{
				TreeNode<E> parentOfRightMost = current;
				TreeNode<E> rightMost = current.left;
				while(rightMost != null){
					parentOfRightMost = rightMost;
					rightMost = rightMost.right;
				}
				current.element = rightMost.element;
				if(parentOfRightMost.right == rightMost){
					parentOfRightMost.right = rightMost.left;
				}else{
					parentOfRightMost.left = rightMost.left;
				}
			}
			size--;
			return true;
		}
		
		
}
