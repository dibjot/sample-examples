import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TrieYears {

    private List<List<List<List<Integer>>>> trieYear;

    public void add(int year){
        Stack<Integer> digits = getYearDigits(year);
        add(year, trieYear,digits);
    }

    private void add(int year, List parent, Stack<Integer> digits){
        trieYear = addNode(year, parent!=null?parent:initArrayList(10), digits, 3);
    }

    public Integer next(int year){
        if(trieYear==null){
            return null;
        }
        Stack<Integer> digits = getYearDigits(year);
        return nextNode(digits,trieYear,false,3);
    }

    public Integer previous(int year){
        if(trieYear==null){
            return null;
        }
        Stack<Integer> digits = getYearDigits(year);
        return previousNode(digits,trieYear,false,3);
    }

    public Integer remove(int year){
        if(trieYear==null){
            return null;
        }
        Stack<Integer> digits = getYearDigits(year);
        return removeNode(digits,trieYear,3);
    }

    private Integer removeNode(Stack<Integer> digits, List parent, int depth){
        Integer removedNode = null;
        int index = digits.pop();
        if(depth==0){
            Integer removed = (Integer) parent.set(index, null);
            return removed;
        }
        List child = (List) parent.get(index);
        if(child!=null){
            removedNode = removeNode(digits,child,depth-1);
            if(removedNode!=null){
                checkForEmpty(parent,index,child);
            }
        }
        return removedNode;
    }

    private void checkForEmpty(List parent, int index, List child){
        if(child!=null){
            for(int i=0;i<10;i++){
                if(child.get(i)!=null){
                    // non null elements are found. return
                    return;
                }
            }
            // all elements are null. remove the list from parent
            parent.set(index, null);
        }
    }

    private Integer nextNode(Stack<Integer> digits, List parent, boolean traversed, int depth){
        Integer next = null;
        int index = digits.pop();
        int traversingIndex = index;
        do{
            Object child = traversed? traverseToNext(parent) :parent.get(traversingIndex);
            if(child!=null){
                if(depth==0){
                    return (Integer) child;
                }else{
                    // we reached this after traversal
                    if(traversingIndex != index){
                        traversed = true;
                    }
                    next = nextNode(digits, (List)child,traversed,depth-1);
                }
            }
            traversingIndex +=1;
        }while(next==null && traversingIndex<10);

        // if node not found even after traversal push back the digit and go back to upper node
        if(next==null){
            digits.push(index);
        }


        return next;
    }

    private Integer previousNode(Stack<Integer> digits, List parent, boolean traversed, int depth){
        Integer next = null;
        int index = digits.pop();
        int traversingIndex = index;
        do{
            Object child = traversed? traverseToPrevious(parent) :parent.get(traversingIndex);
            if(child!=null){
                if(depth==0){
                    return (Integer) child;
                }else{
                    // we reached this after traversal
                    if(traversingIndex != index){
                        traversed = true;
                    }
                    next = previousNode(digits, (List)child,traversed,depth-1);
                }
            }
            traversingIndex -=1;
        }while(next==null && traversingIndex>=0);

        // if node not found even after traversal push back the digit and go back to upper node
        if(next==null){
            digits.push(index);
        }


        return next;
    }

    private Object traverseToNext(List parent) {
        Object node = null;
        //traverse from beginning to end
        for (int i = 0; i < 10; i++) {
            node = parent.get(i);
            if (node != null) {
                break;
            }
        }
        return node;
    }

    private Object traverseToPrevious(List parent) {
        Object node = null;
        //traverse from beginning to end
        for (int i = 9; i >= 0; i--) {
            node = parent.get(i);
            if (node != null) {
                break;
            }
        }
        return node;
    }

    private List addNode(int year, List parent, Stack<Integer> index, int depth){
        if(depth==0){
            return addYearToList(parent,index.pop(), year);
        }
        int idx = index.pop();
        List child = parent.get(idx)!=null? (List) parent.get(idx) :initArrayList(10);
        parent.set(idx, addNode(year, child, index, depth-1));
        return parent;
    }

    private List addYearToList(List parent, int index, Integer year){
        if(parent==null || parent.isEmpty()){
            parent = initArrayList(10);
        }
        parent.set(index, year);

        return parent;
    }

    private ArrayList initArrayList(int capacity){
        ArrayList l = new ArrayList(capacity);
        for(int i=0;i<capacity;i++){
            l.add(null);
        }
        return l;
    }

    private Stack<Integer> getYearDigits(int year){
        Stack<Integer> stack = new Stack<>();
        do{
            int digit = year%10;
            stack.push(digit);
            year = year/10;
        }while(year>0);

        return stack;
    }

    @Override
    public String toString() {
        return "TrieYears{" +
                "trieYear=" + trieYear +
                '}';
    }
}
