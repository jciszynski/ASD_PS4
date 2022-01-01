package zadanie;


import java.util.Comparator;

public abstract class dictBST<T> implements Comparator<T> {
   dictNode root;

    public dictBST() {

    }

    class dictNode<T>{
        T data; dictNode left; dictNode right; dictNode Equivalent;
        dictNode(T data, dictNode left, dictNode right, dictNode Equivalent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.Equivalent = Equivalent;
        }
        dictNode(T data){
            this.data = data;
            left = null;
            right = null;
        }
    }


    private dictNode insert(T data, dictNode node){
        if(root == null){ root = new dictNode(data); return root;}
        if(compare((T)node.data,data)==1){
            if(node.left == null){
                node.left = new dictNode(data);
                return node.left;
            }
            return insert(data, node.left);
        }
        if(compare((T)node.data,data)==-1){
            if(node.right == null){
                node.right = new dictNode(data);
                return node.right;
            }
            return insert(data, node.right);
        }
        return null;
    }

    public dictNode insert(T data){
        return insert(data,root);
    }

    private dictNode delete(T data, dictNode node) {
        if (node == null)
            return node;

        if (compare((T) node.data, data) == 1) {
            node.left = delete(data, node.left);
            return node;
        }

        if (compare((T) node.data, data) == -1) {
            node.right = delete(data, node.right);
            return node;
        }

        if (node.left == null)
            return node.right;
        if (node.right == null)
            return node.left;

        dictNode tmp = node.left;
        dictNode tmpParent = node;

        while (tmp.right != null) {
            tmpParent = tmp;
            tmp = tmp.right;
        }

        node.data = tmp.data;
        if(tmpParent != node)
            tmpParent.right = null;
        else
            tmpParent.left = null;

        return node;
    }

    public void delete(T data){
        delete(data,root);
    }

    private void printKLP(dictNode node){
        if(node!=null)
            System.out.println(node.data + ", ");
        if(node.left!=null) printKLP(node.left);
        if(node.right!=null) printKLP(node.right);
    }

    private dictNode search(T data, dictNode node){
        if(node == null) return null;
        if(compare((T)node.data, data)==1)
            return search(data, node.left);
        if(compare((T)node.data, data)==-1)
            return search(data, node.right);

        return node;
    }

    public dictNode search (T data){
        return search(data, root);
    }
    public void printKLP(){
        printKLP(root);
    }
}
