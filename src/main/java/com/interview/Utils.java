package com.interview;


import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {

    //Question1
    public static List<Extension> sortByName(List<Extension> extensions) {
        Assertions.assertNotNull(extensions, "extensions list is null");
        return extensions.parallelStream().sorted(
                ExtensionUtils.<Extension>compare()
                .thenComparing((o1,o2)->o2.getFirstName().compareTo(o1.getFirstName()))
                .thenComparing((o1,o2)->Optional.ofNullable(o2.getLastName()).orElseGet(()->"").compareTo(Optional.ofNullable(o1.getLastName()).orElseGet(()->"")))
                        .thenComparing((o1,o2)->Optional.ofNullable(o2.getExt()).orElseGet(()->"").compareTo(Optional.ofNullable(o1.getExt()).orElseGet(()->"")))
        ).collect(Collectors.toList());
    }


    //Question2
    public static List<Extension> sortByExtType(List<Extension> extensions) {
        Assertions.assertNotNull(extensions, "extensions list is null");
        return extensions.parallelStream().sorted(Comparator.comparing((o)->ExtTypeOrder.getOrderNumByExtType(o.getExtType()))).collect(Collectors.toList());
    }

    //Question3
    public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
        Assertions.assertNotNull(saleItems, "saleItems list is null");
        Map<Integer, DoubleSummaryStatistics> map = saleItems.stream()
                .collect(Collectors.groupingBy(Utils::getQuarter, Collectors.summarizingDouble(SaleItem::getSaleNumbers)));
        return map.entrySet().stream().map(o->{ QuarterSalesItem quarterSalesItem = new QuarterSalesItem();
                    quarterSalesItem.setQuarter(o.getKey());
                    quarterSalesItem.setTotal(o.getValue().getSum());
                return quarterSalesItem;}).collect(Collectors.toList());
    }

    private static int getQuarter(SaleItem saleItem) {
        return saleItem.getMonth() % 3 > 0 ? (saleItem.getMonth() / 3 + 1) : (saleItem.getMonth() / 3);
    }

    //Question4
    public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
        Assertions.assertNotNull(saleItems);
        Map<Integer, Optional<SaleItem>> map = saleItems.parallelStream()
                .collect(Collectors.groupingBy(Utils::getQuarter, Collectors.maxBy(Comparator.comparingDouble(SaleItem::getSaleNumbers))));
        return map.entrySet().stream().map(o->{ QuarterSalesItem quarterSalesItem = new QuarterSalesItem();
            quarterSalesItem.setQuarter(o.getKey());
            if (o.getValue().isPresent()) {
                quarterSalesItem.setTotal(o.getValue().get().getSaleNumbers());
            }
            return quarterSalesItem;}).collect(Collectors.toList());

    }

    //Question5
    /**
     * We have all Keys: 0-9;
     * usedKeys is an array to store all used keys like :[2,3,4];
     * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
     */

    public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
        Assertions.assertNotNull(allKeys);
        Assertions.assertNotNull(usedKeys);
        return Arrays.stream(allKeys).filter(o->!Arrays.stream(usedKeys).anyMatch(t->t==o)).toArray();
    }
}