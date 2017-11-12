package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
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
			data = new QuadraticSpacePerfectHashing[1];
			data[0] = new QuadraticSpacePerfectHashing<AnyType>(array);
			return;
		}

		// A completer
		data = new QuadraticSpacePerfectHashing[array.size()];
		ArrayList<AnyType>[] collArr = new ArrayList[data.length];
		
		for(int i = 0; i < collArr.length; i++)
			collArr[i] = new ArrayList<AnyType>();
		
		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p);
		
		for(AnyType elem : array)
			collArr[getKey(elem)].add(elem);
		
		for(int i = 0; i < data.length; i++) 
			data[i] = new QuadraticSpacePerfectHashing<AnyType>(collArr[i]);
		
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		// A completer
		return data[key].Size() > 0;
	}
	
	public int getKey (AnyType x) {
		// A completer
		return ((a*x.hashCode() + b) % p) % data.length;
	}
	
	public boolean containsValue (AnyType x) {
		// A completer
		return data[getKey(x)].containsValue(x);
	}
	
	public void remove (AnyType x) {
		// A completer
		data[getKey(x)].remove(x);
		
	}

	public String toString () {
		String result = "";
		
		// A completer
		for (int i = 0; i < data.length; i++)
			if (data[i].Size() != 0)
				result += "[clé_" + i + "] -> " + data[i].toString() + "\n";
		
		return result; 
	}

	public void makeEmpty () {
		// A completer
		data = new QuadraticSpacePerfectHashing[data.length];
   	}
	
}
