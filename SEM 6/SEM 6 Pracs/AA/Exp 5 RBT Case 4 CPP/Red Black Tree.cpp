#include <iostream>

struct RBNode {
    int val;
    char color = 'r';

    RBNode* right = nullptr;
    RBNode* left = nullptr;
    RBNode* parent = nullptr;

    RBNode(int nodeVal) : val(nodeVal) {}
};

class Case_4 {
   private:
    RBNode* node = nullptr;

    Case_4(RBNode* node) { this->node = node; }

    RBNode* findSibling() {
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

    bool isDoubleBlack() {
        return this->node->color == 'd';
    }

    bool isParentRed() {
        return this->node->parent->color == 'r';
    }

    bool isSiblingAndChildBlack() {
        RBNode* sibling = this->findSibling();

        if (sibling) {
            bool blackSibling = sibling->color == 'b';
            bool blackLeftChild = sibling->left ? sibling->left->color == 'b' : true;
            bool blackRightChild = sibling->right ? sibling->right->color == 'b' : true;
            return blackSibling && blackLeftChild && blackRightChild;
        } else {
            return true;
        }
    }

    bool validateCase() {
        return isDoubleBlack() && isParentRed() && isSiblingAndChildBlack();
    }

    void fixDoubleBlack() {
        RBNode* sibling = this->findSibling();

        this->node->parent->color = 'b';
        this->node->color = 'b';
        sibling->color = 'r';
    }

   public:
    bool handle() {
        if (this->validateCase()) {
            fixDoubleBlack();
            return true;
        } else {
            return false;
        }
    }
};

int main(int argc, char const* argv[]) {
}