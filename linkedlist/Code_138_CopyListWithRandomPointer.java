package linkedlist;

public class Code_138_CopyListWithRandomPointer {
    
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // Step 1: Create new nodes and insert them next to original nodes
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next;
        }

        // Step 2: Assign random pointers for the new nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // Step 3: Separate the two lists
        Node dummyHead = new Node(0);
        Node copyCurrent = dummyHead;
        current = head;

        while (current != null) {
            copyCurrent.next = current.next;
            copyCurrent = copyCurrent.next;

            current.next = current.next.next;
            current = current.next;
        }

        return dummyHead.next;
    }
}
