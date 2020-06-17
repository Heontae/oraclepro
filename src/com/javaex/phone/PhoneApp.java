package com.javaex.phone;

import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PhoneDao phonedao = new PhoneDao();
		Scanner sc = new Scanner(System.in);
		List<PersonVo> pList = phonedao.getPhoneList();

		boolean run = true;
		int phone_id;
		String name, hp, company;

		System.out.println("***************************");
		System.out.println("*\t전화번호 관리 프로그램         *");
		System.out.println("***************************");
		while (run) {
			System.out.println("\n1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("------------------------------------");
			System.out.print(">메뉴 번호: ");
			int num = sc.nextInt();
			switch (num) {
			case 1:
				System.out.println("<1.리스트>");
				for (PersonVo vo : pList) {
					System.out.println(
							vo.getPerson_id() + "\t" + vo.getName() + "\t" + vo.getHp() + "\t" + vo.getCompany());
				}
				break;
			case 2:
				System.out.println("<2.등록>");
				sc.nextLine();// 오류를 계행을 받아 해결
				System.out.print("이름 > ");
				name = sc.nextLine();
				System.out.print("휴대전화 > ");
				hp = sc.nextLine();
				System.out.print("회사번호  > ");
				company = sc.nextLine();

				// 등록하기
				PersonVo v01 = new PersonVo(name, hp, company);
				phonedao.PersonInsert(v01);
				pList = phonedao.getPhoneList();
				System.out.println("[1건 추가되었습니다.]");
				break;

			case 3:
				System.out.println("<3.수정>");
				System.out.print("번호> ");
				phone_id = sc.nextInt();
				sc.nextLine(); // 오류를 계행을 받아 해결
				System.out.print("이름 > ");
				name = sc.nextLine();
				System.out.print("휴대전화 > ");
				hp = sc.nextLine();
				System.out.print("회사번호  > ");
				company = sc.nextLine();

				// 수정하기
				PersonVo v02 = new PersonVo(phone_id, name, hp, company);
				phonedao.PersonUpdate(v02);
				pList = phonedao.getPhoneList();
				System.out.println("1건 수정되었습니다.");
				break;

			case 4:
				System.out.println("<4.수정>");
				System.out.print("번호> ");
				phone_id = sc.nextInt();

				// 삭제하기
				PersonVo v03 = new PersonVo(phone_id);
				phonedao.PersonDelete(v03);
				pList = phonedao.getPhoneList();
				System.out.println("1건 삭제되었습니다.");
				break;

			case 5:
				// .contains(search) 검색 명령어
				System.out.println("<4.검색>");
				System.out.print("검색어: ");
				sc.nextLine();
				String search = sc.nextLine();
				//검색하기
				for (int i = 0; i < pList.size(); i++) {
					if (pList.get(i).getName().contains(search) || pList.get(i).getHp().contains(search)
							|| pList.get(i).getCompany().contains(search)) {
						System.out.println(pList.get(i).getPerson_id() + ".\t" + pList.get(i).getName() + "\t"
								+ pList.get(i).getHp() + "\t" + pList.get(i).getCompany());
					}
				}
				break;
				
			case 6:
				System.out.println("***************************");
				System.out.println("*\t        감사합니다                 *");
				System.out.println("***************************");
				run = false;
				break;
				
			default:
				System.out.println("[다시 입력해주세요.]");
				break;
				
			}

		}
	}
}
