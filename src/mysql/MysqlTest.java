package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlTest {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/demoDB", "root", "root");
            System.out.println("接続成功！");

            Statement stmt = conn.createStatement();

            // roles テーブル
            System.out.println("== roles ==");
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM roles");
            while (rs1.next()) {
                System.out.println("権限ID: " + rs1.getInt("role_id") + ", admin/staff: " + rs1.getString("role_key") + ", 権限名: " + rs1.getString("role_name"));
            }

            // employees テーブル
            System.out.println("\n== employees ==");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM employees");
            while (rs2.next()) {
                System.out.println("従業員ID: " + rs2.getInt("employee_id") +
                				   ", 権限ID:" + rs2.getInt("role_id") +
                                   ", 氏名: " + rs2.getString("name") +
                                   ", メールアドレス: " + rs2.getString("email") +
                                   ", パスワード: " + rs2.getString("password") +
                                   ", 有効/無効: " + rs2.getBoolean("is_active") +
                                   ", 登録日時: " + rs2.getTimestamp("created_at") +
                				   ", 更新日時: " + rs2.getTimestamp("updated_at"));
            }

            // attendance_records テーブル
            System.out.println("\n== attendance_records ==");
            ResultSet rs3 = stmt.executeQuery("SELECT * FROM attendance_records");
            while (rs3.next()) {
                System.out.println("レコードID: " + rs3.getInt("record_id") +
                                   ", 従業員ID: " + rs3.getInt("employee_id") +
                                   ", 勤務日: " + rs3.getDate("work_date") +
                                   ", 出勤時刻: " + rs3.getTimestamp("clock_in") +
                                   ", 退勤時刻: " + rs3.getTimestamp("clock_out") +
                                   ", 外出時刻: " + rs3.getTimestamp("break_out") +
                                   ", 再入時刻: " + rs3.getTimestamp("break_in") +
                                   ", 休憩時間: " + rs3.getTime("break_duration") +
                                   ", 登録日時: " + rs3.getTimestamp("created_at") +
                                   ", 更新日時: " + rs3.getTimestamp("updated_at"));
            }

            // クローズ処理
            rs1.close();
            rs2.close();
            rs3.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
