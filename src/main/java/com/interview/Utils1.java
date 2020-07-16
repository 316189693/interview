package com.chinaums.xmsmk.batch.ring;

import com.google.common.base.Strings;
import org.apache.commons.lang.ArrayUtils;

import java.util.*;

/**
 * @author yangjianfang
 * @version 2018-10-13
 */
public class Utils {

	/**
	 * Question1, sort by firstName + lastName + ext,
	 * if firstName is the same then sort by lastName and
	 * ext, please note lastName and ext can be empty
	 * string or null.
	 *
	 **/
	public static List<Extension> sortByName(List<Extension> extensions) {
		extensions.sort((o1, o2) -> {
			String str1 = o1.getFirstName();
			String str2 = o2.getFirstName();

			if(Objects.equals(str1, str2)) {
				str1 = Strings.nullToEmpty(o1.getLastName());
				str2 = Strings.nullToEmpty(o2.getLastName());
				if(Objects.equals(o1.getLastName(), o2.getLastName())) {
					str1 = Strings.nullToEmpty(o1.getExt());
					str2 = Strings.nullToEmpty(o2.getExt());
				}
			}
			return str1.compareTo(str2);
		});
		return extensions;
	}


	/**
	 * Question2, sort extType, extType is a string and can
	 * be "User", "Dept", "AO", "TMO", "Other",
	 * sort by User > Dept > AO > TMO > Other;
	 *
	 **/
	public static List<Extension> sortByExtType(List<Extension> extensions) {
		extensions.sort(Comparator.comparing(extension -> ExtType.valueOf(extension.getExtType())));
		return extensions;
	}

	private enum ExtType {
		User, Dept, AO, TMO, Other
	}

	/**
	 * Question3, sum all sales items by quarter
	 *
	 **/
	public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
		Map<Integer, Double> resultMap = new HashMap<>();
		saleItems.forEach(saleItem -> {
            //month may be illegal, if so take pick month from date
            int month = saleItem.getMonth() >= 1 && saleItem.getMonth() <= 12
					? saleItem.getMonth()
					: new Calendar.Builder().setInstant(saleItem.getDate()).build().get(Calendar.MONTH);
			int quarter = (month + 2) / 3;
			Double curSaleNum = resultMap.get(quarter) == null ? 0.0 : resultMap.get(quarter);
			resultMap.put(quarter, curSaleNum + saleItem.getSaleNumbers());
		});
		return mapToList(resultMap);
	}

	/**
	 * Question4, max all sales items by quarter
	 *
	 **/
	public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
		Map<Integer, Double> resultMap = new HashMap<>();
		saleItems.forEach(saleItem -> {
		    int month = saleItem.getMonth() >= 1 && saleItem.getMonth() <= 12
					? saleItem.getMonth()
					: new Calendar.Builder().setInstant(saleItem.getDate()).build().get(Calendar.MONTH);
			int quarter = (month + 2) / 3;
			Double curSaleNum = resultMap.get(quarter);
			if(curSaleNum == null || saleItem.getSaleNumbers() > curSaleNum) {
				resultMap.put(quarter, saleItem.getSaleNumbers());
			}
		});
		return mapToList(resultMap);
	}

	private static List<QuarterSalesItem> mapToList(Map<Integer, Double> map) {
		List<QuarterSalesItem> resultList = new ArrayList<>();
		map.forEach((k, v) -> {
			QuarterSalesItem quarterSalesItem = new QuarterSalesItem();
			quarterSalesItem.setQuarter(k);
			quarterSalesItem.setTotal(v);
			resultList.add(quarterSalesItem);
		});
		return resultList;
	}

	/**
	 * We have all Keys: 0-9;
	 * usedKeys is an array to store all used keys like :[2,3,4];
	 * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,]
	 */
	public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
	    //can use stream() of jdk8 to resolve, but take more time
		List<Integer> unusedKeysList = new ArrayList<>();
		for(int i : allKeys) {
			boolean flag = false;
			for(int j : usedKeys) {
				if(i == j) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				unusedKeysList.add(i);
			}
		}
		return ArrayUtils.toPrimitive(unusedKeysList.toArray(new Integer[0]));
	}
}
