package test;

import static org.junit.Assert.assertEquals;

import listadt.ListADT;
import listadt.ListADTImpl;
import listadt.ListADTUtilities;


//import org.junit.Before;
import org.junit.Test;


/**
 * Tests the ListADT methods. Specifically higher order functions and utilities.
 * @author quaydragon
 *
 */
public class TestListAdt {
  public ListADTUtilities utilities;
  public ListADTImpl justList;
  



  @Test
  public void testToList() {
    
    String[] list = {"a", "b"};
   
    assertEquals("(a b)", utilities.toList(list).toString());
  }
  
  @Test
  public void testAddAll() {
    
    String[] list = {"a", "b"};
    ListADT nodeList = utilities.toList(list);
    utilities.addAll(nodeList, "c", "d");
    
    assertEquals("(a b c d)", nodeList.toString());
    
  }
  
  @Test
  public void testFrequency() {
    
    String[] list = {"a", "b"};
    ListADT nodeList = utilities.toList(list);
    utilities.addAll(nodeList, "b", "b");
    
    assertEquals(3, utilities.frequency(nodeList, "b"));
    
    //Doesnt exist 
    
    assertEquals(0, utilities.frequency(nodeList, "q"));
  }
  
  
  @Test
  public void testDisjoint() {
    
    String[] list1 = {"a", "b"};
    String[] list2 = {"c", "d"};
    String[] list3 = {"c"};
    String[] list4 = {"c", "d"};
    ListADT nodeList1 = utilities.toList(list1);
    ListADT nodeList2 = utilities.toList(list2);
    ListADT nodeList3 = utilities.toList(list3);
    ListADT nodeList4 = utilities.toList(list4);
    
    assertEquals(true, utilities.disjoint(nodeList1, nodeList2));
    
    assertEquals(false, utilities.disjoint(nodeList3, nodeList2));
    
  }
  
  @Test
  public void testEquals() {
    
    String[] list1 = {"a", "b"};
    String[] list2 = {"c", "d"};
    String[] list3 = {"c"};
    String[] list4 = {"c", "d"};
    ListADT nodeList1 = utilities.toList(list1);
    ListADT nodeList2 = utilities.toList(list2);
    ListADT nodeList3 = utilities.toList(list3);
    ListADT nodeList4 = utilities.toList(list4);
    
    assertEquals(true, utilities.equals(nodeList4, nodeList2));
    assertEquals(false, utilities.equals(nodeList3, nodeList2));
    assertEquals(false, utilities.equals(nodeList1, nodeList2));
    
  }
  
  
  
  @Test
  public void testFilter() {
    
    // Filtering based on an existing value
    String[] list4 = {"c", "d"};
    ListADT nodeList1 = utilities.toList(list4);
    
    ListADT newList = nodeList1.filter((i) -> i.equals("d"));
   
    assertEquals("(d)", newList.toString());
    
    //Filtering based on something that doesn't exist
    
    ListADT newList1 = nodeList1.filter((i) -> i.equals("D"));
    
    assertEquals("()", newList1.toString());
   
   
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullDisjoint() {
    ListADT nodeList1 = null;
    ListADT nodeList2 = null;
    
    utilities.disjoint(null, null);
    
    
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullEquals() {
    ListADT nodeList1 = null;
    ListADT nodeList2 = null;
    
    utilities.equals(null, null);
    
    
  }

  


}
