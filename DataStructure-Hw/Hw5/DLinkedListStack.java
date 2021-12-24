package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T> {
    private DLinkedList<T> dll;

    public DLinkedListStack(){
        dll = new DLinkedList<T>();
    }
    @Override
    public void push(T t) {
        dll.insert(t);
    }

    @Override
    public T pop() {
        if(dll.isEmpty())
        {
            return null;
        }
        dll.goToEnd();
        T tempVal = dll.remove();
        return tempVal;
    }

    @Override
    public T top() {
        if(dll.isEmpty())
        {
            System.err.println("Stack is empty.");
            return null;
        }
        dll.goToEnd();
        T tempVal = dll.getCursor();
        return tempVal;
    }

    @Override
    public boolean isEmpty() {
        return dll.isEmpty();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        dll.goToEnd();
        while(dll.getCursor() != null)
        {
            sb.append(dll.getCursor());
            if(!dll.hasPrev()) {
                break;
            }
            sb.append(", ");
            dll.getPrev();
        }
        dll.goToEnd();
        sb.append("]");
        return sb.toString();
    }
}
