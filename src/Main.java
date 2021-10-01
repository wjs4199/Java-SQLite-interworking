import java.sql.Connection;
import java.sql.Statement;

import java.util.Scanner;

import java.sql.ResultSet;
import java.sql.DriverManager;


public class Main {

	public static void main(String[] args) {
		Connection con = null;
		try {
			//SQLite JDBC üũ
			Class.forName("org.sqlite.JDBC");
			
			//������ ���̽��� ����
			String dbFile = "myFirst.db";
			con = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
			
			while(true) {
				Scanner sc = new Scanner(System.in);
				
				System.out.println("���ϴ� �׸� ��ȣ�� �Է��ϼ���");
				System.out.println("1. ������ �߰�\n2. ������ ����\n3. ������ ����\n4. ������ ��ȸ");
				System.out.print("\nCommend > " );
				int choice = sc.nextInt();
				
				if(choice == 1) {
					//������ �߰�
					System.out.println("\n**** 1. ������ �߰� ****");
					Statement stat2 = con.createStatement();
					
					System.out.println("�߰��� ���ϴ� �������� ������ �Է��ϼ���.");
					System.out.print("�̸� > ");
					String name = sc.next();
					System.out.print("���� (����/����) > ");
					String gender = sc.next();
					System.out.print("Ȱ�� �ñ� > ");
					String year = sc.next();
					System.out.print("���߳⵵ > ");
					String debut = sc.next();
					
					String sql2 = "insert into g_artists (name, a_type, a_year, debut, regdate)" 
							+ " values ('"+ name + "', '"+ gender + "', '" + year + "', '" + debut 
							+ "', datetime('now', 'localtime'));";
					int cnt = stat2.executeUpdate(sql2);
					if(cnt > 0)
						System.out.println("���ο� �����Ͱ� �߰��Ǿ����ϴ�!\n");
					else 
						System.out.println("[Error] ������ �߰� ����!");
					stat2.close();
				} 
				else if(choice == 2 ) {
					System.out.println("\n**** 2. ������ ���� ****");
					Statement stat3 = con.createStatement();
					
					System.out.println("������ ���ϴ� �������� ��ȣ�� �Է��ϼ���.");
					System.out.print("������ �׸� ��ȣ > ");
					int num = sc.nextInt();
					
					System.out.print("������ ī�װ� �̸� (name, a_type, a_year, debut) > ");
					String category = sc.next();  
					System.out.print("������ ���� > ");
					sc.nextLine();
					String update = sc.nextLine();
				 	
					String sql3 = "update g_artists set " + category 
							+ " = '"+ update + "' where id="+ num + " ;";
					int cnt3 = stat3.executeUpdate(sql3);
					if(cnt3 > 0)
						System.out.println("�����Ͱ� �����Ǿ����ϴ�!\n");
					else 
						System.out.println("[Error] ������ ���� ����!");
					stat3.close();
				} 
				else if(choice == 3) {
					System.out.println("\n**** 3. ������ ���� ****");
					Statement stat4 = con.createStatement();
					
					System.out.println("������ ���ϴ� �������� ��ȣ�� �Է��ϼ���.");
					System.out.print("������ �׸� ��ȣ > ");
					int num = sc.nextInt();
				 	
					String sql4 = "delete from g_artists where id = "+ num + " ;";
					int cnt4 = stat4.executeUpdate(sql4);
					if(cnt4 > 0)
						System.out.println("�����Ͱ� �����Ǿ����ϴ�!\n");
					else 
						System.out.println("[Error] ������ ���� ����!");
					stat4.close();
				}
				else if(choice == 4) {
					//������ ��ȸ
					System.out.println("\n**** ������ ��ȸ ****");
					Statement stat1 = con.createStatement();
					String sql1 = "select * from g_artists";
					ResultSet rs1 = stat1.executeQuery(sql1);
					
					System.out.println("��ȣ �̸� ���� Ȱ���⵵ ���߳⵵");
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
					System.out.println("����!");
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
