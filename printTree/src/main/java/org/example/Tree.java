package org.example;

class Tree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color{
        BLACK,
        RED
    }

    // Метод вставки элемента в бинарное дерево
    public void insert(int value) {
// Если рут пустой, то помещаем в него
        if (root != null) {
            insert(root, value);
            root = balance(root);
        } else {
            root = new Node();
            root.value = value;
        }
        root.color = Color.BLACK;
    }

    private void insert(Node node, int value) {
        if (node.value != value) {
            if (node.value < value) {
// В Правый узел
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
                    // добавляем цвет новой ноде
                    node.right.color = Color.RED;

                } else {
                    insert(node.right, value);
                    node.right = balance(node.right);
                }
            } else {
// В Левый узел
                if (node.left == null) {
                    node.left = new Node();
                    node.left.value = value;
                    node.right.color = Color.RED;

                } else {
                    insert(node.left, value);
                    node.left = balance(node.left);
                }
            }
        }
    }

    // Метод поиска

    public Node find(int value){
        return find(root, value);
    }

    private Node find(Node node, int value){
        if (node==null) return null;
        if (node.value==value) return node;
        if (node.value<value) return find(node.right, value);
        else return find(node.left, value);
    }

// Метод левого поворота дерева, возвращает новую ноду

    private Node leftRorate(Node node){
// Поворот
        Node cur = node.right;
        node.right = cur.left;
        cur.left = node;
// Смена цвета
        cur.color = node.color;
        node.color = Color.RED;

        return cur;
    }

// Метод правого поворота дерева
    private Node rightRorate(Node node){
// Поворот
        Node cur = node.left;
        node.left = cur.right;
        cur.right = node;
// Смена цвета
        cur.color = node.color;
        node.color = Color.RED;

        return cur;
    }

// Смена цветов у дерева
    private void swapColors(Node node){
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

// Метод балансировки и уменьшения высоты дерева
    private Node balance(Node node){
        boolean flag = true;
        Node cur = node;
        do{
            flag = false;

// поворот если правая красная вершина
            if (cur.right!=null && cur.right.color == Color.RED
                    && (cur.left == null || cur.left.color == Color.BLACK)){
               cur = leftRorate(cur);
               flag = true;
            }
// наличие левого потомка (текущей ноды) с левым потомком
//            если они оба красных, то выполняем правый поворот

            if (cur.left!=null && cur.left.color == Color.RED
                && cur.left.left!=null && cur.left.left.color == Color.RED){
                cur = rightRorate(cur);
                flag = true;
            }


// проверка цветов
            if (cur.left!=null && cur.left.color == Color.RED
                    && cur.right!=null && cur.right.color == Color.RED){
                swapColors(cur);
                flag = true;
            }

        }while (flag);
        return cur;
    }
// Печать дерева
    public void print(){
        print(root, 0);
    }

    private void print(Node node, int deep){
        if (node == null) {return;}

        print(node.left, deep + 4);

        for (int i=0; i<deep; i++){
            System.out.print(" ");}
        System.out.println("value: " + node.value + " {color: " + node.color + "}");

        print(node.right, deep + 4);
    }

}