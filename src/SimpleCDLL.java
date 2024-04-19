import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * Doubly-linked lists implementation using dummy node and circular linked list
 *
 * These do *not* (yet) support the Fail Fast policy.
 */
public class SimpleCDLL<T> implements SimpleList<T> {

	// +--------+------------------------------------------------------------
	// | Fields |
	// +--------+

	/*
	 * Dummy node that act like the head and tail of the list
	 */
	Node2<T> dummy;

	/*
	 * Size of the list
	 */

	int size;

	// +--------------+------------------------------------------------------
	// | Constructors |
	// +--------------+

	public SimpleCDLL() {
		this.dummy = new Node2<>(null, null, null);
		this.dummy.next = dummy;
		this.dummy.prev = dummy;
		this.size = 0;
	}

	// +-----------+---------------------------------------------------------
	// | Iterators |
	// +-----------+

	@Override
	public Iterator<T> iterator() {
		return listIterator();
	};

	@Override
	public ListIterator<T> listIterator() {
		return new ListIterator<T>() {

			// +--------+------------------------------------------------------------
			// | Fields |
			// +--------+

			/*
			 * The position in the list of the next value to be returned
			 */
			int pos = 0;

			/**
			 * The cursor is between neighboring values, so we start links
			 * to the previous and next value.
			 */
			Node2<T> prev = dummy;
			Node2<T> next = dummy.next;

			/**
			 * The node to be updated by remove or set. Has a value of
			 * null when there is no such value.
			 */
			Node2<T> update = null;

			public void add(T val) {

				Node2<T> newNode = prev.insertAfter(val);

				if (next == dummy) {
					dummy.next = newNode;
					next = newNode;
				}

				prev = newNode;

				update = null;
				size++;
				pos++;
			}

			public boolean hasNext() {
				return pos < size;
			}

			public boolean hasPrevious() {
				return pos > 0;
			}

			public T next() {
				if (!hasNext())
					throw new NoSuchElementException();
				update = next;
				prev = next;
				next = next.next;
				pos++;
				return update.value;
			}

			public int nextIndex() {
				return pos;
			}

			public int previousIndex() {
				return pos - 1;
			}

			public T previous() {
				if (!hasPrevious())
					throw new NoSuchElementException();
				update = prev;
				next = prev;
				prev = prev.prev;

				if (prev == dummy && size != 0) {
					prev = dummy.prev;
				}

				pos--;
				return update.value;
			}

			public void remove() {
				if (update == null || update == dummy)
					throw new IllegalStateException();

				Node2<T> toRemove = update;
				toRemove.remove();

				// Update the iterator's current position pointers
				if (size == 1) {
					dummy.next = dummy;
					dummy.prev = dummy;
					prev = dummy;
					next = dummy;
				} else {
					if (next == toRemove) {
						next = toRemove.next;
					}
					if (prev == toRemove || prev == dummy) {
						prev = toRemove.prev;
					}
				}

				update = null;
				size--;
				pos = (pos > 0) ? pos - 1 : pos;
			}

			public void set(T val) {
				if (update == null || update == dummy)
					throw new IllegalStateException();
				update.value = val;
				update = null;
			}

		};
	}

	public int size() {
		return this.size;
	}
}
