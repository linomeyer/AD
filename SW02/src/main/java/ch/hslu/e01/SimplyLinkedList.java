package ch.hslu.e01;

public class SimplyLinkedList<E> {
    Node<E> head;

    public void addFirst(E item) {
        head = new Node<>(item, head);
    }

    public E getFirst() {
        return head.item;
    }

    public boolean contains(E item) {
        if (head != null) {
            ListIterator<E> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public E pop() {
        E firstItem = head.item;
        head = head.next;

        return firstItem;
    }

    public boolean remove(E itemToRemove) {
        ListIterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E next = iterator.next();
            if (next.equals(itemToRemove)) {
                iterator.remove();
                // otherwise it doesn't work if the head item is removed
                if (next.equals(head.item)) {
                    head = head.next;
                }
                return true;
            }
        }
        return false;
    }

    public ListIterator<E> iterator() {
        return new ListIteratorImpl<>(head);
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    private static class ListIteratorImpl<E> implements ListIterator<E> {
        Node<E> currentNode;
        Node<E> previousNode;
        int index;

        public ListIteratorImpl(Node<E> head) {
            this.currentNode = head;
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index != 0 ? currentNode.next != null : currentNode.item != null;
        }

        @Override
        public E next() {
            E nextItem;
            if (index == 0) {
                nextItem = currentNode.item;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next;
                nextItem = currentNode == null ? null : currentNode.item;
            }
            index++;
            return nextItem;
        }

        @Override
        public void remove() {
            if (previousNode != null) {
                previousNode.next = currentNode.next;
            }
            currentNode = null;
        }

        public int getIndex() {
            return index;
        }
    }
}