import java.util.List;

public class TrieYearExample {

    private List<List<List<List<Integer>>>> trieYear;

    public static void main(String[] args) {
        trieTest();
    }

    private static void trieTest(){

        TrieYears trie = new TrieYears();
        trie.add(2003);
        trie.add(2012);
        trie.add(2109);
        trie.add(2150);
        trie.add(1995);
        trie.add(1993);

        System.out.println("2007 next "+trie.next(2007));
        System.out.println("2018 next "+trie.next(2018));
        System.out.println("2001 next "+trie.next(2001));

        System.out.println("2007 previous "+trie.previous(2007));
        System.out.println("2018 previous "+trie.previous(2018));
        System.out.println("2001 previous "+trie.previous(2001));

        System.out.println("removed : "+trie.remove(1995));
        System.out.println("removed : "+trie.remove(2109));

        System.out.println("2007 next "+trie.next(2007));
        System.out.println("2018 next "+trie.next(2018));
        System.out.println("2001 next "+trie.next(2001));

        System.out.println("2007 previous "+trie.previous(2007));
        System.out.println("2018 previous "+trie.previous(2018));
        System.out.println("2001 previous "+trie.previous(2001));
    }

}
