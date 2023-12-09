#include <iostream>
#include <queue>
#include <string>
#include <cstring>

class RedBlackTree {
   private:
    struct RBNode {
        int val;
        char color = 'r';

        RBNode* right = nullptr;
        RBNode* left = nullptr;
        RBNode* parent = nullptr;

        RBNode(int nodeVal) : val(nodeVal) {}
    };

    RBNode* root = nullptr;

    RBNode* createRoot(int val) {
        RBNode* newNode = new RBNode(val);
        newNode->color = 'b';

        this->root = newNode;
        return newNode;
    }

    RBNode* findSibling(RBNode* node) {
        RBNode* sibling = nullptr;

        if (node->parent) {
            if (node->parent->left == node) {
                // we are on left, so check right side
                sibling = node->parent->right;
            } else {
                // we are on right, so check left side
                sibling = node->parent->left;
            }
        }

        return sibling;
    }

    RBNode* createNode(RBNode* parentNode, bool isRight, int val) {
        RBNode* newNode = new RBNode(val);

        if (isRight) {
            parentNode->right = newNode;
        } else {
            parentNode->left = newNode;
        }

        newNode->parent = parentNode;

        if (parentNode->color == 'r') {
            RBNode* uncle = this->findSibling(parentNode);

            if (!uncle || uncle->color == 'b') {
                if (parentNode->parent->right == parentNode && isRight) {
                    // case 3: uncle black, right side
                    // left rotate
                } else if (parentNode->parent->left == parentNode && !isRight) {
                    // case 3: uncle black, left side
                    // right rotate
                } else {
                    // case 2: uncle black, opposite side
                }
            } else {
                // case 1: uncle red
                if (uncle->color == 'r') {
                }
            }
        }
        return newNode;
    }

    RBNode*
    insertNode(int val, RBNode* candidateParentNode) {
        if (!candidateParentNode) {
            // Case for root node
            return this->createRoot(val);
        } else if (val > candidateParentNode->val) {
            // insert in right subtree
            if (candidateParentNode->right)
                return this->insertNode(val, candidateParentNode->right);
            else
                return this->createNode(candidateParentNode, true, val);
        }

        else if (val < candidateParentNode->val) {
            // insert in left subtree
            if (candidateParentNode->left)
                this->insertNode(val, candidateParentNode->left);
            else
                return this->createNode(candidateParentNode, false, val);
        }

        // Node already present
        return nullptr;
    }

    std::vector<std::vector<RBNode*>> serializeTree() {
        std::vector<std::vector<RBNode*>> serializedTree;
        std::vector<RBNode*> serializedRow;

        std::queue<RBNode*> tovisit({this->root, nullptr});
        RBNode* current = tovisit.front();

        while (current) {
            if (current->left) tovisit.push(current->left);
            if (current->right) tovisit.push(current->right);

            serializedRow.push_back(current);
            tovisit.pop();
            current = tovisit.front();

            if (!current) {
                serializedTree.push_back(serializedRow);
                serializedRow.clear();

                tovisit.pop();
                tovisit.push(nullptr);
                current = tovisit.front();
            }
        }

        return serializedTree;
    }

   public:
    void insert(int val) {
        this->insertNode(val, this->root);
    }

    RBNode* getRoot() {
        return this->root;
    }
};

int main(int argc, char const* argv[]) {
    RedBlackTree mytree;

    std::string tmp;
    getline(std::cin, tmp);

    while (true) {
        int operation;

        std::cout << "Functions:" << std::endl;
        std::cout << "1. Insert Node" << std::endl;
        std::cout << "1. Get Root Node" << std::endl;

        std::cout << "\nEnter Operation: ";
        std::cin >> operation;

        switch (operation) {
            case 1:
                int val;
                std::cout << "Enter Value: ";
                std::cin >> val;
                mytree.insert(val);
                break;
            case 2:
                std::cout << mytree.getRoot() << std::endl;
                break;

            default:
                break;
        }
    }
    return 0;
}