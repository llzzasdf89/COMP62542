/**
 * Iterator interface in Iterator Pattern;
 */
public interface DataIterator {
    abstract public boolean hasNext();
    abstract public Object next();
    abstract public void add(Object obj);
    abstract public void remove(Object obj);
}
