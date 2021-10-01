import java.sql.Connection;
import java.sql.Statement;

import java.util.Scanner;

import java.sql.ResultSet;
import java.sql.DriverManager;


public class Main {

	public static void main(String[] args) {
		Connection con = null;
		try {
			//SQLite JDBC 체크
			Class.forName("org.sqlite.JDBC");
			
			//데이터 베이스와 연결
			String dbFile = "myFirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			while(true) {
				Scanner sc = new Scanner(System.in);
				
				System.out.println("원하는 항목 번호를 입력하세요");
				System.out.println("1. 데이터 추가\n2. 데이터 수정\n3. 데이터 삭제\n4. 데이터 조회");
				System.out.print("\nCommend > " );
				int choice = sc.nextInt();
				
				if(choice == 1) {
					//데이터 추가
					System.out.println("\n**** 1. 데이터 추가 ****");
					Statement stat2 = con.createStatement();
					
					System.out.println("추가를 원하는 데이터의 내용을 입력하세요.");
					System.out.print("이름 > ");
					String name = sc.next();
					System.out.print("성별 (남성/여성) > ");
					String gender = sc.next();
					System.out.print("활동 시기 > ");
					String year = sc.next();
					System.out.print("데뷔년도 > ");
					String debut = sc.next();
					
					String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" 
							+ " values ('"+ name + "', '"+ gender + "', '" + year + "', '" + debut 
							+ "', datetime('now', 'localtime'));";
					int cnt = stat2.executeUpdate(sql2);
					if(cnt > 0)
						System.out.println("새로운 데이터가 추가되었습니다!\n");
					else 
						System.out.println("[Error] 데이터 추가 오류!");
					stat2.close();
				} 
				else if(choice == 2 ) {
					System.out.println("\n**** 2. 데이터 수정 ****");
					Statement stat3 = con.createStatement();
					
					System.out.println("수정을 원하는 데이터의 번호를 입력하세요.");
					System.out.print("수정할 항목 번호 > ");
					int num = sc.nextInt();
					
					System.out.print("수정할 카테고리 이름 (name, a_type, a_year, debut) > ");
					String category = sc.next();  
					System.out.print("수정할 내용 > ");
					sc.nextLine();
					String update = sc.nextLine();
				 	
					String sql3 = "update g_artists set " + category 
							+ " = '"+ update + "' where id="+ num + " ;";
					int cnt3 = stat3.executeUpdate(sql3);
					if(cnt3 > 0)
						System.out.println("데이터가 수정되었습니다!\n");
					else 
						System.out.println("[Error] 데이터 수정 오류!");
					stat3.close();
				} 
				else if(choice == 3) {
					System.out.println("\n**** 3. 데이터 삭제 ****");
					Statement stat4 = con.createStatement();
					
					System.out.println("삭제를 원하는 데이터의 번호를 입력하세요.");
					System.out.print("삭제할 항목 번호 > ");
					int num = sc.nextInt();
				 	
					String sql4 = "delete from g_artists where id = "+ num + " ;";
					int cnt4 = stat4.executeUpdate(sql4);
					if(cnt4 > 0)
						System.out.println("데이터가 삭제되었습니다!\n");
					else 
						System.out.println("[Error] 데이터 삭제 오류!");
					stat4.close();
				}
				else if(choice == 4) {
					//데이터 조회
					System.out.println("\n**** 데이터 조회 ****");
					Statement stat1 = con.createStatement();
					String sql1 = "select * from g_artists";
					ResultSet rs1 = stat1.executeQuery(sql1);
					
					System.out.println("번호 이름 성별 활동년도 데뷔년도");
					while(rs1.next()) {
						String id = rs1.getString("id");
						String name = rs1.getString("name");
						String gender = rs1.getString("a_type");
						String year = rs1.getString("a_year");
						String debut = rs1.getString("debut");
						System.out.println(id+ " "+name+ " "+gender+ " "+year+ " "+debut);
					}
					System.out.println();
					stat1.close();
				}
				else {
					System.out.println("종료!");
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
