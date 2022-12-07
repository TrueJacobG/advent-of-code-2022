package solution.day7;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String dirName = "";
    int size = 0;

    Node parent;
    List<Node> children;

    public Node(String dirName) {
        this.dirName = dirName;
        this.size = 0;

        this.children = new ArrayList<>();
        this.parent = null;
    }

    public void addChild(String dirName) {
        Node node = new Node(dirName);
        node.parent = this;
        this.children.add(node);
    }

    public boolean hasChild(String dirName) {
        for (Node node : children) {
            if (node.dirName.equals(dirName)) {
                return true;
            }
        }

        return false;
    }

    public Node changeToChild(String dirName) {
        for (Node node : children) {
            if (node.dirName.equals(dirName)) {
                return node;
            }
        }
        return null;
    }

    public void increaseParentsSizes(int size) {
        Node node = this;
        while (node.parent != null) {
            node.size += size;
            node = node.parent;
        }
        node.size += size;
    }

    public Node goToTop() {
        Node node = this;
        while (node.parent != null) {
            node = node.parent;
        }
        return node;
    }
}
