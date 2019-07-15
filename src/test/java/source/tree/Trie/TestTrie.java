package source.tree.Trie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class TestTrie {

    private Trie trie = new Trie();


    @Before
    public void setUp(){
        trie.insert("Programming");
        trie.insert("istd");

        trie.insert("ist");
        trie.insert("is");
        trie.insert("a");
        trie.insert("way");
        trie.insert("of");
        trie.insert("life");
    }

    @Test
    public void testInsert() {

        assertTrue(trie.search("ist"));
        assertTrue(trie.search("istd"));
        assertTrue(trie.search("is"));


        assertFalse(trie.search("i"));
        assertFalse(trie.search("iste"));


    }


    @Test
    public void testDelete() {


        assertTrue(trie.search("is"));
        trie.delete("is");
        assertFalse(trie.search("is"));

        assertTrue(trie.search("ist"));
        trie.delete("ist");
        assertFalse(trie.search("ist"));

        assertTrue(trie.search("istd"));







    }

}
