package probleme2;

public class DoubleHashingTable<AnyType> 
{
        private static final int DEFAULT_TABLE_SIZE = 11;
        private HashEntry<AnyType> [] array;
        private int currentSize;
        private int currentTableSize;
        
        //OK
	public DoubleHashingTable(){
            this(DEFAULT_TABLE_SIZE);
        }
        
        //OK
        public DoubleHashingTable(int size){
            //currentTableSize = DEFAULT_TABLE_SIZE;
            AllocateArray(size);
            makeEmpty();
        }
        
        //OK
        public void makeEmpty(){
            currentSize=0;
            for(int i=0;i<array.length;i++){
                array[i] = null;
            }
        }
        
        //OK
        public boolean contains(AnyType x){
            int currentPos = findPos(x);
            return isActive(currentPos);
        }
        
        //OK
        public int nbElement(){
            return currentSize;
        }
        
        //OK
        public AnyType get(AnyType x){
            int currentPos = findPos(x);
            if(array[currentPos]!=null)
                return array[currentPos].element;
            return null;
        }
        
        //OK
        public void insert(AnyType x){
            int currentPos = findPos(x);
            if(isActive(currentPos))
                return;
            
            array[currentPos] = new HashEntry<AnyType>(x,true);
            
            if(++currentSize > array.length/2)
                rehash();
        }
        
        //OK
        public void remove(AnyType x){
            int currentPos = findPos(x);
            if(isActive(currentPos)) 
                array[currentPos].isActive = false;
            
        }
        
        //OK
        private static class HashEntry<AnyType>{
            public AnyType element;
            public boolean isActive;
            
            public HashEntry(AnyType e){
                element=e;
                isActive=true;
            }
            
            public HashEntry(AnyType e, boolean i){
                element = e;
                isActive = i;
            }
        }
        

        
        //OK
        private void AllocateArray(int arraySize){
            array = new HashEntry[nextPrime(arraySize)];
        }
        
        //OK
        private boolean isActive(int currentPos){
            return array[currentPos] != null && array[currentPos].isActive;
        }
        
        private int findPos(AnyType x){
            int offset = 0;
            int currentPos = myhash(x);
            while(array[currentPos]!=null && !array[currentPos].element.equals(x)){
                currentPos += ((x.hashCode() % array.length) + (offset++ * (currentTableSize - (x.hashCode()%currentTableSize)))) % array.length;
                if(currentPos >= array.length)
                    currentPos -= array.length;
            }
            
            return currentPos;
        }
        
        private void rehash(){
            HashEntry<AnyType>[] oldArray = array;
            
            AllocateArray(nextPrime(2*oldArray.length));
            currentSize = 0;
            
            for(int i=0; i < oldArray.length;i++){
                if(oldArray[i]!=null && oldArray[i].isActive)
                    insert(oldArray[i].element);
            }
        }
        
        private int myhash(AnyType x){
            int hashVal = x.hashCode();
            hashVal %= array.length;
            if(hashVal < 0)
                hashVal+=array.length;
            return hashVal;
        }
        
        private static int nextPrime(int n){
            if(n%2 == 0)
                n++;
            for(;!isPrime(n); n+=2);
            return n;
        }
        
        private static boolean isPrime(int n){
            if(n==2 || n==3)
                return true;
            if(n==1 || n%2 ==0)
                return false;
            for(int i=3;i*i<n;i+=1)
                if(n%i==0)
                    return false;
            return true;
        }
}
