class MyHashSet {
    private List<List<Integer>> container = null;
    private int size = 1000;
    private double loadFactor = 0.75;
    private int count = 0;
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        container = new ArrayList<List<Integer>>(size);
        
        for(int i = 0; i < size; i++) {
            container.add(null);
        }
    }
    
    private int getIndexUsingHashFunction(int key) {
        return key % size;
    }
    
    public void add(int key) {
        
        if( contains(key) ) {
            return;
        }
        
        if( count == loadFactor * size ) {
            rehash();
        }
        
        int index = getIndexUsingHashFunction(key);
        List childList = container.get(index);
        
        if(childList == null) {
            List<Integer> list = new LinkedList<Integer>();
            list.add(key);
            
            container.set(index, list);
        } else {
            childList.add(key);
        }
        
        ++count;
    }
    
    private void rehash() {
        List<List<Integer>> oldContainer = new ArrayList<List<Integer>>(container);
        count = 0;
        size *= 2;
        
        container = new ArrayList<List<Integer>>(size);
        
        for(int i = 0; i < size; i++) {
            container.add(null);
        }
        
        for(List<Integer> innerList : oldContainer) {
            
            if( innerList == null ) {
                continue;
            }
            
            for(Integer key : innerList) {
                add(key);
            }
        }
    }
    
    public void remove(int key) {
        int index = getIndexUsingHashFunction(key);
        List childList = container.get(index);
        
        // print();
        
        if( childList != null ) {
            childList.remove( Integer.valueOf(key) );
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = getIndexUsingHashFunction(key);
        List childList = container.get(index);
        
        if( childList != null && childList.contains(key) ) {
            return true;
        }
        
        return false;
    }
    
    private void print() {
        int i = 0;
        
        for(List<Integer> innerList : container) {
            ++i;
            
            if( innerList == null ) {
                continue;
            }
            
            System.out.print(i - 1 + " ===> ");
            
            for(Integer key : innerList) {
                System.out.print(key + " -> ");
            }
            
            System.out.println("");
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */