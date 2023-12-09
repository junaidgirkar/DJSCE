// Online C compiler to run C program online
#include <stdio.h>
#include <stdlib.h>

struct node {
    int value;
    // 1: Red | 0: Black
    int color;
    // pointers to parent, right child and left child
    struct node* parent;
    struct node* rightChild;
    struct node* leftChild;
};

// initially the root node will be empty
struct node* root = NULL;

// insert a node according to BST logic (Recursive)
// trav: points to a node of the existing RB tree, initially root of the tree
// newNode: points to the node that we want inserted
struct node* bst(struct node* traverse, struct node* newNode)
{
    // Check if the tree is empty -> traverse is NULL initially
    // If yes -> then the new node becomes the root of the tree
    if (traverse == NULL) {
        return newNode;
    }
   
    // if the value of newNode is more than that of the root\
    // we will insert newNode in the right subtree of the BST
    if (traverse->value < newNode->value) {
        // we take a snapshot of the right subtree
        // the newNode will be inserted there
        traverse->rightChild = bst(traverse->rightChild, newNode);
        // update the parent once insertion of newNode is done in right sub tree
        traverse->rightChild->parent = traverse;
    }
    // if the value of newNode is  less than that of the root
    // we will insert newNode in the left subtree of the BST
    else if (traverse->value > newNode->value) {
        // we take a snapshot of the left subtree
        // the newNode will be inserted there
        traverse->leftChild = bst(traverse->leftChild, newNode);
        // update the parent once insertion of newNode is done in right sub tree
        traverse->leftChild->parent = traverse;
    }
   
    return traverse;
}

// perform left rotation
void leftrotate(struct node* temp)
{
    struct node* right = temp->rightChild;
    temp->rightChild = right->leftChild;
    if (temp->rightChild)
        temp->rightChild->parent = temp;
    right->parent = temp->parent;
    if (!temp->parent)
        root = right;
    else if (temp == temp->parent->leftChild)
        temp->parent->leftChild = right;
    else
        temp->parent->rightChild = right;
    right->leftChild = temp;
    temp->parent = right;
}

// function to perform right rotate on the given node
void rightrotate(struct node* temp)
{
    // store the node containing the leftChid of rotation node
    struct node* left = temp->leftChild;
   
    // The leftChild of the rotaion node will be
    // the rightChild of it's leftChild
    temp->leftChild = left->rightChild;
   
    // Check if the rotation node's leftChild had a rightChild
    // if yes -> set the parent of that as the rotation node
    if (temp->leftChild)
        temp->leftChild->parent = temp;
       
    // The parent of roation node's leftChild will be rotations Node's parent
    // left is lifted in RR
    left->parent = temp->parent;
   
    // check if rotation Node has a parnt at all
    // if it does not, the left will become the new root node
    // means rotation node was the OG root
    if (!temp->parent)
        root = left;
       
    // Check if rotation Node was the rightChild or leftChild of its parent
    // (basically replace rotation node in parent's eyes)
    // if yes, then left will be the new leftChild
    else if (temp == temp->parent->leftChild)
        temp->parent->leftChild = left;
    // else left will be the new rightChild
    else
        temp->parent->rightChild = left;
   
    // rotation node will be rightChild of left since left was lifted
    left->rightChild = temp;
    temp->parent = left;
}

// Function to print inorder traversal of a tree
void inorder(struct node* trav)
{
    if (trav == NULL)
        return;
    inorder(trav->leftChild);
    printf("%d ", trav->value);
    inorder(trav->rightChild);
}

// function to fix the the RB tree
// void fixup(struct node* root, struct node* pt) {
//     // case 1
//     if (root->)
// }




int main() {
    // Write C code here
    int n = 7;
    int a[7] = { 7, 6, 5, 4, 3, 2, 1 };
 
    for (int i = 0; i < n; i++) {
        // allocating memory to the node and initializing:
        // 1. color as red
        // 2. parent, left and right pointers as NULL
        // 3. data as i-th value in the array
        struct node* temp = (struct node*)malloc(sizeof(struct node));
        temp->rightChild = NULL;
        temp->leftChild = NULL;
        temp->parent = NULL;
        temp->value = a[i];
        temp->color = 1;
 
        // calling function that performs bst insertion of
        // this newly created node
        root = bst(root, temp);

    }
 
    printf("Inorder Traversal of Created Tree\n");
    inorder(root);
    printf("\nHello world");
   
    return 0;
}