package cash.model;

public class HashtagMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String content = "안녕하세요 #구디아카데미 입니다. 하반기 #자바 교육과정 시간표 공지하였습니다";
		
		/*
		 구디아카데미
		 자바
		 */
		
		
		String[] list = content.split(" ");
		int count = 0;
		for(String hash : list) {

			if(hash.startsWith("#")) {
				String hashtag = hash.replace("#", "");
				System.out.println(hashtag);
			}
		}
	}

}
