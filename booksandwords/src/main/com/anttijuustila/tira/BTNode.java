package com.anttijuustila.tira;

public class BTNode {

    BTNode(String word, int number, int key) {
        this.key = key;
        data = word;
        amount = number;
        left = null;
        right = null;
        parent = null;

    }

    int key;
    String data;
    int amount = 1; // Amount of similar words can't start from zero
    BTNode left = null;
    BTNode right = null;
    BTNode parent = null;
    String[] Commonwords = new String[100];
    int[] Commonamounts = new int[100];
    BTNode root = null;
    private int size = 0;
    int[] smallestAmount = new int[2];

    // Adds new word in the tree
    public boolean add(String word, int number, int key) throws IllegalArgumentException {
        if (word == null) {
            throw new IllegalArgumentException("Can't be null");
        }
        BTNode treenode = root;
        BTNode node = new BTNode(word, number, key);
        BTNode parent = null;

        // Go right if key bigger than tree node key and left if smaller until reach
        // bottom

        while (treenode != null) {
            parent = treenode;
            if (treenode.key > node.key) {
                treenode = treenode.left;
            } else if (treenode.key < node.key) {
                treenode = treenode.right;
            } else if (treenode.key == node.key) {
                treenode.amount++;
                return true;
            }
        }
        // After bottom found add word at the bottom of tree
        node.parent = parent;
        if (root == null) {
            root = node;
        } else if (parent.key > node.key) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        size++;
        return true;
    }

    // Returns list size
    public int size() {
        return size;
    }

    // Fills top 100 list
    public void printOrder(BTNode node, int number) {
        int min = number;

        if (node != null) {
            printOrder(node.right, min);
            printOrder(node.left, min);
            // First fills the list then replaces the smallest amount word at list with a
            // word that
            // has bigger amount
            if (root.Commonwords[99] == null) {
                int i = 0;
                while (root.Commonwords[i] != null && i < 99) {
                    i++;
                }
                if (root.smallestAmount[0] > node.amount || i == 1) {
                    root.smallestAmount[0] = node.amount;
                    root.smallestAmount[1] = i;
                }
                root.Commonwords[i] = node.data;
                root.Commonamounts[i] = node.amount;

            } else {
                if (root.smallestAmount[0] < node.amount) {

                    root.Commonamounts[root.smallestAmount[1]] = node.amount;
                    root.Commonwords[root.smallestAmount[1]] = node.data;
                    root.smallestAmount[0] = 100000;
                    for (int i = 0; i <= root.Commonamounts.length - 1; i++) {
                        if (root.smallestAmount[0] >= root.Commonamounts[i]) {
                            root.smallestAmount[0] = root.Commonamounts[i];
                            root.smallestAmount[1] = i;
                        }
                    }
                }
            }
        }
    }

    // sorts top 100 list using quicksort
    public void sortList(int arr[], String words[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, words, begin, end);

            sortList(arr, words, begin, partitionIndex - 1);
            sortList(arr, words, partitionIndex + 1, end);
        }
    }

    private int partition(int arr[], String words[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] > pivot) {
                i++;
                int swapTemp = arr[i];
                String temp = words[i];
                arr[i] = arr[j];
                words[i] = words[j];
                arr[j] = swapTemp;
                words[j] = temp;
            }
        }
        int swapTemp = arr[i + 1];
        String temp = words[i + 1];
        arr[i + 1] = arr[end];
        words[i + 1] = words[end];
        arr[end] = swapTemp;
        words[end] = temp;

        return i + 1;
    }

    // prints the most common 100 words
    public void printcommons() {
        for (int i = 0; i < root.Commonamounts.length; i++) {
            if (root.Commonwords[i] != null)
                System.out.println(root.Commonwords[i] + " " + root.Commonamounts[i]);
        }
    }

    public String getOrder(int number) {
        BTNode node = root;
        int counter = 0;
        int turn = 0;
        while (node.right != null) {
            node = node.right;
        }
        while (node != null && counter != number && counter < 100) {
            counter++;
            if (node.right != null) {
                node = node.right;
                turn++;
            } else if (node.left != null) {
                node = node.left;
                turn++;
            } else {
                while (turn > 0) {
                    node = node.parent;
                }
                if (node.left != null) {
                    node = node.left;
                } else {
                    node = node.parent;
                }
            }
        }

        return node.data;
    }

    // Gets the amount of the word of the given position in top 100 list
    public int getAmount(int position) {
        return root.Commonamounts[position];
    }

    public String find(String string) throws IllegalArgumentException {
        if (string == null) {
            throw new IllegalArgumentException("Can't be null");
        }

        BTNode node = root;

        // Repeat until found of reach bottom of the tree
        while (node != null && node.key != string.hashCode()) {
            if (node.key > string.hashCode()) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return null;
        }
        return node.data;

    }

    // Gets word on a top 100 lit at the given position
    public String GetWordOnListAt(int position) {
        return root.Commonwords[position];
    }

}
