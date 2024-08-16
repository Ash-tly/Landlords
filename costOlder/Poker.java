package costOlder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Poker {
	/*
	使用集合来存储。
	private：不想让其他的类使用该集合
	static：在静态代码块中的代码如果需要使用成员位置的数据，该数据需要被 static 进行修饰
	*/
	//	使用一个单列集合，用来存放所有牌
	private static ArrayList<String> pokerList = new ArrayList<>();
	//	用一个集合用来存放牌及该牌的价值
	private static HashMap<String, Integer> pokerMap = new HashMap<>();

	//	静态代码块：随着类的加载而加载，只加载一次
	static {
		//	准备牌
		//	准备俩个数组，一个记录牌的花色，一个记录大小
		String[] Color = {"♠", "♣", "♥", "♦"};
		String[] Number = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		//	向牌堆添加不同花色的牌
		for (String color : Color) {
			for (String num : Number) {
				pokerList.add(color + num);
			}
		}
		//	向牌堆添加大小王
		pokerList.add(" joker");
		pokerList.add(" JOKER");

		//	给牌定义价值
		//	当牌在双列集合中时，按照规定的价值进行排序
		//	当牌不在双列集合中时，牌本身的值就是其的价值
		pokerMap.put("J", 11);
		pokerMap.put("Q", 12);
		pokerMap.put("K", 13);
		pokerMap.put("A", 14);
		pokerMap.put("2", 15);
		pokerMap.put("joker", 16);
		pokerMap.put("JOKER", 17);
	}

	public Poker() {
		//	洗牌
		Collections.shuffle(pokerList);

		//	发牌
		ArrayList<String> lord = new ArrayList<>();
		ArrayList<String> playGamer1 = new ArrayList<>();
		ArrayList<String> playGamer2 = new ArrayList<>();
		ArrayList<String> playGamer3 = new ArrayList<>();

		for (int i = 0; i < pokerList.size(); i++) {
			//	放入底牌
			if (i <= 2) {
				lord.add(pokerList.get(i));
				continue;
			}
			//	给玩家的牌
			if (i % 3 == 0) {
				playGamer1.add(pokerList.get(i));
			} else if (i % 3 == 1) {
				playGamer2.add(pokerList.get(i));
			} else {
				playGamer3.add(pokerList.get(i));
			}
		}

		//	排序
		rostPoker(lord);
		rostPoker(playGamer1);
		rostPoker(playGamer2);
		rostPoker(playGamer3);

		//	看牌
		getPoker("底牌", lord);
		getPoker("李国豪", playGamer1);
		getPoker("陈兆瑞", playGamer2);
		getPoker("陶李缘", playGamer3);
	}

	//	按照价值对牌进行排序
	public void rostPoker(ArrayList<String> list) {
		list.sort((o1, o2) -> {
			//	获取o1的花色
			String color = o1.substring(0, 1);
			//	获取o1的值
			int valuePoker1 = valuePoker(o1);
			//	获取o1的花色
			String number = o2.substring(0, 1);
			//	获取o1的值
			int valuePoker2 = valuePoker(o2);
			//	计算o1的值减去o2的值，当两个值相等时，比较两张牌的花色
			int cost = valuePoker1 - valuePoker2;
			return cost == 0 ? color.compareTo(number) : cost;
		});
	}

	//	判断牌时使用定义的价值还是其自身时价值
	public int valuePoker(String poker) {
		//	对传入的字符串进行截取
		String data = poker.substring(1);
		//	判断截取的部分在双列集合是否存在
		if (pokerMap.containsKey(data)) {
			//	存在：使用定义的价值
			return pokerMap.get(data);
		} else {
			//	不存在：本身就是牌的价值 -> 类型转换，将截取到的字符变为其对应的数字
			return Integer.parseInt(data);
		}
	}

	public static void getPoker(String name, ArrayList<String> playerPoker) {
		System.out.print(name + ": ");
		for (String s : playerPoker) {
			System.out.print(s + " ");
		}
		System.out.println();
	}
}
