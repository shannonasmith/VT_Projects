package boundedstack;

import java.util.Iterator;

/*
 * CS 5704 (Spring 2023) boundedstack
 *
 * @author Shannon Smith (shae1223)
 */
public abstract class AbstractStack<E> implements boundedstack.Stack<E> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");  // append things to the string builder
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]:");
        sb.append(capacity());
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (! (obj instanceof boundedstack.Stack)) {
            return false;
        }
        boundedstack.Stack<?> that = (boundedstack.Stack) obj;
        if (this.capacity() != that.capacity()) {
            return false;
        }
        if (this.depth() != that.depth()) {
            return false;
        }
        Iterator<E> thisIter = this.iterator();
        Iterator<?> thatIter = that.iterator();
        while (thisIter.hasNext()) {
            E elem = thisIter.next();
            Object o = thatIter.next();
            if (!elem.equals(o)) {
                return  false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }
        result = 31 * result + capacity();
        return result;
    }

    @Override
    public boundedstack.Stack<E> copy() {
        boundedstack.Stack<E> result = this.newInstance();
        for (E element : this) {
            result.push(element);
        }
        return result;
    }

    @Override
    public void reverse() {
        boundedstack.Stack<E> copy = this.copy();
        this.clear();
        while (copy.depth() != 0) {
            E element = copy.pop();
            this.push(element);
        }
    }

}
