package listadt;



import java.util.List;
import java.util.function.Predicate;



/**
 * Implementation of utilities for the ListADT list.
 */
public class ListADTUtilities {

  /**
   * Create a new list that contains all of the specified elements in the same
   * order as they appeared in the specified array.
   * 
   * @param <T>  the type of element in the list.
   * @param data the data to store in the list
   * @return a new list that contains all of the specified elements
   */
  public static <T> ListADT<T> toList(T[] data) {
    ListADT<T> list = new ListADTImpl<>();
    List<T> array = List.of(data);    
    array.stream().forEach(x -> list.addBack(x));
  
    return list;
  }
 
  /**
   * Adds all of the specified elements to the specified list. Elements to be
   * added may be specified individually or as an array and should be added to the
   * end of the list.
   * 
   * @param <T>      the type of elements to add to the list
   * @param list     the list to insert the elements into
   * @param elements the elements to insert into list
   * @throws IllegalArgumentException if elements contains one or more null values
   */
  @SafeVarargs
  public static <T> void addAll(ListADT<T> list, T... elements) {
    
    System.out.println(elements);
    List<T> array = List.of(elements);    
    array.stream().forEach(x -> 
            list.addBack(x));
    
  }

  /**
   * Returns the number of elements in the specified list equal to the specified
   * element. More formally, returns the number of elements in the list such that
   * <code>(o == null ? e == null : o.equals(e))</code>.
   * 
   * @param <T>     the type of elements in the list
   * @param list    the list to search
   * @param element the element to search for
   * @return the number of elements in the list
   */
  public static <T> int frequency(ListADT<T> list, T element) {

    Predicate<T> equalsElement = (i) -> i.equals(element);
    ListADT<T> subList = list.filter(equalsElement);
    
    return subList.getSize();
  }

  /**
   * Returns true if the two lists have no elements in common.
   * 
   * @param <T> the type of elements in the lists.
   * @param one the first list
   * @param two the second list
   * @return if the two lists have no elements in common
   * @throws IllegalArgumentException if either list is null or if either list
   *                                  contains a null element
   */
  public static <T> boolean disjoint(ListADT<T> one, ListADT<T> two) {
    if (nullPointer(one) || nullPointer(two)) {
      throw new IllegalArgumentException("The list or at least one of its values are null");
    }

    ListADT newList = one.map((i) -> frequency(two, i));
    ListADT filtered = newList.filter((i) -> !i.equals(0));
    
    System.out.println(filtered.toString());
    
    
    return (filtered.getSize() == 0);
  }

  /**
   * Returns true if the two lists are equal. Two lists are equal if they have the
   * same elements in the same order.
   * 
   * @param <T> the type of elements in the lists.
   * @param one the first list
   * @param two the second list
   * @return true if the two lists have the same elements in the same order
   * @throws IllegalArgumentException if either list is null or if either
   *                                  collection contains a null element
   */
  public static <T> boolean equals(ListADT<T> one, ListADT<T> two) {
    if (nullPointer(one) || nullPointer(two)) {
      throw new IllegalArgumentException("The list or at least one of its values are null");
    }

    
    if (one.getSize() == two.getSize() && one.getSize() > 0 && two.getSize() > 0) {
      if (one.get(0).equals(two.get(0))) {
        equals(one.filter((i) -> i != one.get(0)), two.filter((i) -> i != two.get(0)));
      } else {
        return false;
      }
    } else {
      return false;
    }
    return true;
  }
  
  /**
   * Aids in throwing an illegal argument exception for a null list or null value. 
   * 
   * @param <T> Generic Object
   * @param list of generic objects
   * @return true if null retruns false if not
   */
  private static <T> boolean nullPointer(ListADT<T> list) {
    if (list == null) {
      return true;
    } else {
      return (frequency(list, null) > 0);

    }
    
  }
  
}
