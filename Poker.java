package Text;

import java.util.ArrayList;
import java.util.Collections;

public class Poker {
	/*
	使用集合来存储。
	private：不想让其他的类使用该集合
	static：在静态代码块中的代码如果需要使用成员位置的数据，该数据需要被 static 进行修饰
	*/
	private static ArrayList<String> pokerList = new ArrayList<>();

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
		pokerList.add("joker");
		pokerList.add("JOKER");
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
			if (i <= 2) {
				lord.add(pokerList.get(i));
			} else if (i % 3 == 0) {
				playGamer1.add(pokerList.get(i));
			} else if (i % 3 == 1) {
				playGamer2.add(pokerList.get(i));
			} else {
				playGamer3.add(pokerList.get(i));
			}
		}

		//	看牌
		getPoker("底牌", lord);
		getPoker("李国豪", playGamer1);
		getPoker("陈兆瑞", playGamer2);
		getPoker("陶李缘", playGamer3);
	}

	public static void getPoker(String name, ArrayList<String> playerPoker) {
		System.out.print("\n"+name + ": ");
		for (String s : playerPoker) {
			System.out.print(s + " ");
		}
	}
}
