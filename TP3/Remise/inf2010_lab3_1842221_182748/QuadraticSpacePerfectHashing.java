package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		 a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		// A completer

		return items[key] != null;
	}

	public boolean containsValue(AnyType x )
	{
		if(Size() == 0)
			return false;
		return items[getKey(x)] != null && items[getKey(x)].equals(x);
	}

	public void remove (AnyType x) {
		items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		return ((a*x.hashCode() + b) % p) % items.length;
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			//System.out.println("Array is empty!");
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			items = (AnyType[]) new Object[1];
			items[0] = array.get(0);
			return;
		}
		
		int size = (int)(Math.pow(array.size(), 2));
		items = (AnyType[]) new Object[size];

		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p);

		for(AnyType item : array)
			if(!containsValue(item)) 
				items[getKey(item)] = item;
			else {
				AllocateMemory(array);
				return;
			}
	}
	
	public String toString () {
		String result = "";
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null)
				continue;
			result += "(" + i + "," + items[i] + "), ";
		}
		
		result = result.substring(0, result.length() - 2) + ".";
		
		return result; 
	}

	public void makeEmpty () {
		items = (AnyType[]) new Object[items.length];
   	}
}
