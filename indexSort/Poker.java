package indexSort;

import java.util.*;

public class Poker {
	//	使用双列集合，键是序号，值是牌
	private static HashMap<Integer, String> pokerMap = new HashMap<>();
	//	使用单列集合，用来存储序号
	private static ArrayList<Integer> indexList = new ArrayList<>();

	static {
		//	准备牌
		//	准备俩个数组，一个记录牌的花色，一个记录大小
		String[] Color = {"♠", "♣", "♥", "♦"};
		String[] Number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
		//	初始化定义牌的开始序号为1
		int firstIndex = 1;
		//	将牌放入容器
		//	外循环：循环遍历数字	按照规律："♠3", "♣3", "♥3", "♦3"，"♠4", "♣4", "♥4", "♦4"
		for (String num : Number) {
			//	内循环：循环遍历花色   按照规律："♠3", "♣3", "♥3", "♦3"
			for (String color : Color) {
				//	将花色牌添加到双列集合
				pokerMap.put(firstIndex, num+color);
				//	将牌对应的序号添加到单列集合
				indexList.add(firstIndex);
				//	每循环一次，序号+1
				firstIndex++;
			}
		}
		//	添加大小王
		pokerMap.put(firstIndex,"joker");
		indexList.add(firstIndex);
		firstIndex++;
		pokerMap.put(firstIndex,"JOKER");
		indexList.add(firstIndex);
	}

	public Poker() {
		//	洗牌
		Collections.shuffle(indexList);

		//	发牌
		//	分别创建底牌及三名玩家的手牌
		TreeSet<Integer> lode = new TreeSet<>();
		TreeSet<Integer> player1 = new TreeSet<>();
		TreeSet<Integer> player2 = new TreeSet<>();
		TreeSet<Integer> player3 = new TreeSet<>();

		//	循环遍历索引集合，并将他们分给玩家和底牌
		for (int i = 0; i < indexList.size(); i++) {
			//	分给底牌
			if (i<=2){
				lode.add(indexList.get(i));
				continue;
			}
			//	分给玩家
			if (i%3==0){
				player1.add(indexList.get(i));
			}else if (i%3==1){
				player2.add(indexList.get(i));
			}else {
				player3.add(indexList.get(i));
			}
		}
		//	看牌
		setPoker("底牌",lode);
		setPoker("李国豪",player1);
		setPoker("陈兆瑞",player2);
		setPoker("陶李缘",player3);
	}

	public void setPoker(String name, TreeSet<Integer> pokers){
		System.out.print(name+"：");
		//	遍历传入的底牌或玩家手牌
		for (Integer index : pokers) {
			//	将传入的底牌或玩家手牌的索引（键）与双列集合中的牌进行互换（值）
			String poker = pokerMap.get(index);
			System.out.print(poker+" ");
		}
		System.out.println();
	}
}
