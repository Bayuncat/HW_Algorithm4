package org.example;


public class Main {
    public static void main(String[] args) {

   Tree tree = new Tree();

    for (int i=1; i<=20; i++){
        tree.insert(i);
   }

   tree.print();

//        System.out.println(tree.find(5));
//        System.out.println(tree.find(6));

//        HashMap map = new HashMap();
//        map.push(1, 2);
//        map.push(3, 4);
//
//        System.out.println(map.find(1));
//        System.out.println(map.find(2));
//
//        map.remove(1);
//        map.push(2,5);
//
//        System.out.println(map.find(1));
//        System.out.println(map.find(2));


}
}