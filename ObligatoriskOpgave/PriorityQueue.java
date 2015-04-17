import java.util.ArrayList;
import java.util.HashMap;

public class PriorityQueue<T> {
	private ArrayList<T> elements = new ArrayList<T>();
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	private HashMap<T, Integer> map = new HashMap<T, Integer>();

	/**
	 * Inserts an element with a given key.
	 * @param element The element to insert.
	 * @param key The associated key.
	 */
	public void insert(T element, int key) {
		if (map.containsKey(element)) {
			throw new RuntimeException("The PriorityQueue already contains the element: " + element);
		}
		
		elements.add(element);
		keys.add(key);
		map.put(element, elements.size() - 1);
		
		bubbleUp(keys.size() - 1);
	}

	/**
	 * Returns the element with the lowest associated key.
	 * @return The element with the lowest associated key.
	 */
	public T extractMin() {
		T max = elements.get(0);
		
		swap(0, keys.size() - 1);
		
		keys.remove(keys.size() - 1);
		elements.remove(elements.size() - 1);
		
		bubbleDown(0);
		
		return max;
	}

	/**
	 * Changes the key of the element to newKey.
	 * @param element The element to change.
	 * @param newKey The new key associated to the element.
	 */
	public void changeKey(T element, int newKey) {
		int index = map.get(element);
		keys.set(index, newKey);
		bubbleUp(index);
		bubbleDown(index);
	}
	
	/**
	 * Returns whether the PriorityQueue is empty or not.
	 * @return True if the PriorityQueue is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return keys.size() == 0;
	}
	
	private void bubbleUp(int node) {
		while (node > 0 && keys.get(node) < keys.get(parent(node))) {
			swap(parent(node), node);
			node = parent(node);
		}
	}

	private void bubbleDown(int node) {		
		while (left(node) < keys.size()) {		
			int min;
			
			if (right(node) >= keys.size() || keys.get(left(node)) < keys.get(right(node)))
				min = left(node);
			else 
				min = right(node);

			if (keys.get(node) > keys.get(min)) {
				swap(node, min);			
				node = min;
			}
			else {
				break;
			}
		}
	}
	
	private int parent(int node) {
		return (node - 1) / 2;
	}
	
	private int left(int node) {
		return node * 2 + 1;
	}
	
	private int right(int node) {
		return node * 2 + 2;
	}
	
	private void swap(int i, int j) {
		int tKey = keys.get(i);
		keys.set(i, keys.get(j));
		keys.set(j, tKey);
		
		T tElement = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, tElement);

		map.put(elements.get(i), i);
		map.put(elements.get(j), j);
	}
}
