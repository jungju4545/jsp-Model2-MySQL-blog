package com.cos.utill;

import java.security.MessageDigest;

//256비트의 길이의 암호 =해시 : 복호화가 안됨
public class SHA256 {
	// password를 암호화 한 후 return해줄 거다.

	// 매개변수 2개 필요
	public static String getEncypt(String rawPassword, String salt) {

		String result = "";// 초기화

		// password 바이트화 시킨다. >> 바이트 배열에 저장
		
		byte[] b = (rawPassword+salt).getBytes();
		System.out.println(b);

		try {
			// 암호화 시킬 수 있는 함수
			// MessageDigest md = MessageDigest.getInstance("암호화 방식");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b);// password와 salt를 넣은 문자 : 바이트 타입이라서 바이트화 시켜서 넣어야 한다.
			// MessageDigest 가 SHA-256d으로 암호화 된 값을 가지고 있다.
			
			byte[] bresult = md.digest(); //
			System.out.println(bresult);
           //for(byte data : bresult) {
           //System.out.print(data+" ");
           //}						
			StringBuffer sb = new StringBuffer(); // 바이트 담을 상자
			for (byte data : bresult) {
				sb.append(Integer.toString(data & 0xff, 16));
			}
			result = sb.toString();
			System.out.println(result);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		getEncypt("zzzz", "cos");
	}
}
