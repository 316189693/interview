package com.interview;


import java.util.*;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {


    //Question1
    public static List<Extension> sortByName(List<Extension> extensions) {
        Objects.requireNonNull(extensions, "extensions list is null");
        return extensions.parallelStream().sorted(
                ExtensionUtils.<Extension>compare()
                        .thenComparing((o1, o2) -> StringUtils.compare(o2.getFirstName(), o1.getFirstName()))
                        .thenComparing((o1, o2) -> StringUtils.compare(o2.getLastName(), o1.getLastName()))
                        .thenComparing((o1, o2) -> StringUtils.compare(o2.getExt(), o1.getExt()))
        ).collect(Collectors.toList());
    }

    //Question2
    public static List<Extension> sortByExtType(List<Extension> extensions) {
        Objects.requireNonNull(extensions, "extensions list is null");
        return extensions.parallelStream().sorted(Comparator.comparing((o) -> ExtTypeOrder.getOrderNumByExtType(o.getExtType())))
                .collect(Collectors.toList());
    }


    //Question3
    public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {

        Objects.requireNonNull(saleItems, "saleItems list is null");
        return saleItems.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(Utils::getQuarter,
                                                      Collectors.summingDouble(SaleItem::getSaleNumbers)
                                ),
                                (obj) -> mapToList(obj, (o) -> o)
                        )
                );
    }

    //Question4
    public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
        Objects.requireNonNull(saleItems);
        return saleItems.parallelStream()
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(Utils::getQuarter,
                                Collectors.maxBy(Comparator.comparingDouble(SaleItem::getSaleNumbers))
                        ),
                        (obj) -> mapToList(obj, (o) -> {
                            if (!o.isPresent()) {
                                return 0;
                            }
                            return o.get().getSaleNumbers();
                        })));
    }

    //Question5

    /**
     * We have all Keys: 0-9;
     * usedKeys is an array to store all used keys like :[2,3,4];
     * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
     */

    public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
        Objects.requireNonNull(allKeys);
        Objects.requireNonNull(usedKeys);
        return Arrays.stream(allKeys).filter(o -> !Arrays.stream(usedKeys).anyMatch(t -> t == o)).toArray();
    }

    private static int getQuarter(SaleItem saleItem) {
        return Quarter.INSTANCE.getQuarter(saleItem.getMonth());
    }

    private static <T> List<QuarterSalesItem> mapToList(Map<Integer, T> map, ToDoubleFunction<T> mapper) {
        return map.entrySet().stream().map(o -> {
            QuarterSalesItem quarterSalesItem = new QuarterSalesItem();
            quarterSalesItem.setQuarter(o.getKey());
            quarterSalesItem.setTotal(mapper.applyAsDouble(o.getValue()));
            return quarterSalesItem;
        }).collect(Collectors.toList());
    }


    // found first value from list [3,5,6,7,8], expected is big or equal than 6

    public static int fetchValue(List<Integer> numList) throws Exception{
       return numList.stream().filter(item->item >= 6).findFirst().orElseThrow(()-> new Exception("Not Fund"));
    }
   // found string 'ddddhhhhhfffkkkk' max char
    public static String maxStr(String testStr) {
        return Stream.of(testStr.split("")).collect(
                              Collectors.groupingBy((item)->item,
                                      Collectors.counting())
        ).entrySet().stream().max((a,b)-> a.getValue().intValue() - b.getValue().intValue()).get().getKey();

    }
}
