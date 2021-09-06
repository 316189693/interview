package com.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created By willz
 * Date : 2020/2/19
 * Time: 21:52
 */
public class UtilsTest {
    @Test
    void fetchValue() throws Exception{
        Assertions.assertEquals(Utils.fetchValue(Arrays.asList(new Integer[]{1,2,3,7,8,9})), 7);
        Assertions.assertEquals(Utils.fetchValue(Arrays.asList(new Integer[]{1,2,3,6,7,8,9})), 6);
        Assertions.assertThrows(Exception.class, ()->{Utils.fetchValue(Arrays.asList(new Integer[]{1,2,3,5,3,4}));});
    }

    @Test
    void maxStr() throws Exception{
        Assertions.assertEquals(Utils.maxStr("qqwwweeee"), "e");
    }
    @Test
    void  sortByName(){
        List<Extension> list = new ArrayList<>();
        Extension extension = new Extension();
        extension.setFirstName("ass");
        extension.setLastName("ddd");
        extension.setExt("ff");
        list.add(extension);

        Extension extension1 = new Extension();
        extension1.setFirstName("bss");
        extension1.setLastName("ddd");
        extension1.setExt("ff");
        list.add(extension1);

        Extension extension2 = new Extension();
        extension2.setFirstName("bss");
        extension2.setLastName("ddd");
        extension2.setExt("cc");
        list.add(extension2);

        Extension extension3 = new Extension();
        extension3.setFirstName("bss");
        extension3.setLastName(null);
        extension3.setExt("");
        list.add(extension3);

        Extension extension4 = new Extension();
        extension4.setFirstName("bss");
        extension4.setLastName(null);
        extension4.setExt("dd");
        list.add(extension4);

        Extension extension5 = new Extension();
        extension5.setFirstName("bss");
        extension5.setLastName(null);
        extension5.setExt("cc");
        list.add(extension5);

        Extension extension6 = new Extension();
        extension6.setFirstName("bss");
        extension6.setLastName(null);
        extension6.setExt(null);
        list.add(extension6);
        List<Extension> expectedList = new ArrayList<>(Arrays.asList(extension1, extension2, extension4, extension5, extension3, extension6, extension));

       List<Extension> sortedList = Utils.sortByName(list);
       Assertions.assertIterableEquals(expectedList, sortedList);
    }

    @Test
     void sortByExtType(){
         List<Extension> list = new ArrayList<>();
         Extension extension = new Extension();
         extension.setFirstName("ass");
         extension.setLastName("ddd");
         extension.setExt("ff");
         extension.setExtType("User");
         list.add(extension);

         Extension extension1 = new Extension();
         extension1.setFirstName("bss");
         extension1.setLastName("ddd");
         extension1.setExt("ff");
         extension1.setExtType("Dept");
         list.add(extension1);

         Extension extension2 = new Extension();
         extension2.setFirstName("bss");
         extension2.setLastName("ddd");
         extension2.setExt("cc");
         extension2.setExtType("AO");
         list.add(extension2);

         Extension extension3 = new Extension();
         extension3.setFirstName("bss");
         extension3.setLastName(null);
         extension3.setExt("");
         extension3.setExtType("TMO");
         list.add(extension3);

         Extension extension4 = new Extension();
         extension4.setFirstName("bss");
         extension4.setLastName(null);
         extension4.setExt("dd");
         extension4.setExtType("Other");
         list.add(extension4);

         Extension extension5 = new Extension();
         extension5.setFirstName("bss");
         extension5.setLastName(null);
         extension5.setExt("cc");
         list.add(extension5);

         Extension extension6 = new Extension();
         extension6.setFirstName("bss");
         extension6.setLastName(null);
         extension6.setExt(null);
         extension6.setExtType(null);
         list.add(extension6);
         List<Extension> expectedList = new ArrayList<>(Arrays.asList(extension5, extension6, extension, extension1, extension2, extension3, extension4));

         List<Extension> sortedList = Utils.sortByExtType(list);
         Assertions.assertIterableEquals(expectedList, sortedList);
    }

    @Test
    void sumByQuarter() {
         List<SaleItem> list = Stream.of(1,2,3,4,5,6,7,8,9,10,11,12)
                 .map(o -> {
                      SaleItem saleItem = new SaleItem();
                      saleItem.setMonth(o);
                      saleItem.setSaleNumbers(o * 1d);
                      return saleItem;
                 }).collect(Collectors.toList());
         List<Double> expectedList = new ArrayList<>(Arrays.asList(6d, 15d, 24d, 33d));

         List<QuarterSalesItem> quarterList = Utils.sumByQuarter(list);
         List<Double> resultList = quarterList.stream().map(o->o.getTotal()).collect(Collectors.toList());
         Assertions.assertEquals(expectedList, resultList);
    }

     @Test
     void maxByQuarter() {
          List<SaleItem> list = Stream.of(1,2,3,4,5,6,7,8,9,10,11,12)
                  .map(o -> {
                       SaleItem saleItem = new SaleItem();
                       saleItem.setMonth(o);
                       saleItem.setSaleNumbers(o * 1d);
                       return saleItem;
                  }).collect(Collectors.toList());
          List<Double> expectedList = new ArrayList<>(Arrays.asList(3d, 6d, 9d, 12d));

          List<QuarterSalesItem> quarterList = Utils.maxByQuarter(list);
          List<Double> resultList = quarterList.stream().map(o->o.getTotal()).collect(Collectors.toList());
          Assertions.assertEquals(expectedList, resultList);
     }

     @Test
     void getUnUsedKeys(){
          int[] usedKeys = new int[] {2,3,4};
          int[] allKeys = new int[] {0,1,2,3,4,5,6,7,8,9};
          int[] expectedKeys = new int[]{0,1,5,6,7,8,9};
          int[] result = Utils.getUnUsedKeys(allKeys, usedKeys);
          Assertions.assertArrayEquals(expectedKeys, result);
     }

}
