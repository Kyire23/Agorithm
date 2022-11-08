package Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建一个集合，存放选择的电台
        ArrayList<String> selects = new ArrayList<String>();
        //创建一个临时集合用来遍历过程中进行比对此时的最大覆盖地区和未覆盖地区的交集
        HashSet<String> tempSet = new HashSet<String>();
        //创建一个maxKey值，用于放入最大覆盖地区对应电台key值
        String maxKey = null;

        while (allAreas.size() != 0) {
            //每进行一轮while比较，都要置空一次maxKey以便加入覆盖最多地区的电台
            maxKey = null;
            //遍历寻找最大覆盖地区的key值
            for (String key : broadcasts.keySet()) {
                //每进行一次for都要清空一个tempSet
                tempSet.clear();

                //当前key值能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                //把当前key值对应的地区全部加入tempSet中
                tempSet.addAll(areas);
                //求交集,然后重新赋值给tempSet，此处可以看看debug，其实就是考虑到allAreas是变化的
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //maxKey != null 将maxKey 加入selects
            if (maxKey != null) {
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到选择结果的电台有:" + selects);
    }
}