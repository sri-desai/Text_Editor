package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author Srinivas Desai
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() 
	{				
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null)
		{
			throw new NullPointerException("Can not add null values");			
		}
		
		LLNode<E> newNode = new LLNode<E>(element);
		
		tail.prev.next = newNode;
		newNode.prev   = tail.prev;
		newNode.next   = tail;
		tail.prev      = newNode;
		
		++this.size;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		if(index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		else
		{
			LLNode<E> curNode;
			
			boolean isForward;
			
			if(index <= size / 2)
			{
				curNode = head.next;
				
				isForward = true;
			}
			else
			{
				curNode = tail.prev;
				
				isForward = false;
				
				index = size - index - 1;
			}
							
			while(index >= 0)
			{
				if(index == 0)
				{
					return curNode.data;
				}
				else
				{
					--index;
					
					if(isForward)
					{
						curNode = curNode.next;
					}
					else
					{
						curNode = curNode.prev;
					}					
				}
			}
		}
		
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null)
		{
			throw new NullPointerException("Element can not be NULL");
		}
		else
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index should between 0 and " + (size - 1));
		}
		else
		{
			LLNode<E> curNode = head.next;
			
			while(index >= 0)
			{
				if(index == 0)
				{
					LLNode<E> newNode = new LLNode(element);
					
					newNode.prev = curNode.prev;
					
					curNode.prev = newNode;
					
					newNode.next = curNode;
					
					++size;
					
					return;
				}
				else
				{
					--index;
					
					curNode = curNode.next;					
				}
			}
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index should between 0 and " + (size - 1));
		}
		else
		{
			LLNode<E> curNode = head.next;
			
			while(index >= 0)
			{
				if(index == 0)
				{									
					--size;
					
					curNode.prev.next = curNode.next;
					curNode.next.prev = curNode.prev;
					
					return curNode.data;				
				}
				else
				{
					--index;
					
					curNode = curNode.next;					
				}
			}
			
			return null;
		}
					
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index should between 0 and " + (size - 1));
		}
		else
		if(element == null)
		{
			throw new NullPointerException("Passed parameter can not be null");
		}
		else
		{
			LLNode<E> curNode = head.next;
			
			while(index >= 0)
			{
				if(index == 0)
				{									
					E data = curNode.data;
					
					curNode.data = element;
					
					return data;				
				}
				else
				{
					--index;
					
					curNode = curNode.next;					
				}
			}
			
			return null;
		}				
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
