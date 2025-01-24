package com.korit.servlet_study.dao;

import com.korit.servlet_study.config.DBConnectionMgr;
import com.korit.servlet_study.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static UserDao userDao = null; // 싱글톤 패턴

    private UserDao() {}

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public User findByid(int id) {
        User foundUser = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                    select
                        user_id,
                        username,
                        password,
                        name,
                        email
                    from
                        user_tb
                    where
                        user_id=?
                    """;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                foundUser = User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnectionMgr.getInstance().freeConnection(con, ps, rs);
        }

        return foundUser;
    }


    // 문제
    public List<User> findAllBySearchValue(String searchValue) {
        List<User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBConnectionMgr.getInstance().getConnection(); // 데이터베이스 연결 객체 (Connection)를 얻는 코드
            String sql = """
                select 
                    user_id,
                    username,
                    password,
                    name,
                    email
                from 
                    user_tb
                where
                    username like concat('%',?,'%')          
            """;
            ps = con.prepareStatement(sql);
            ps.setString(1, searchValue); // ? 에 대입. 1개 뿐이니 1 에서 끝
            rs = ps.executeQuery();

            while (rs.next()) {
                users.add(User.builder()
                        .userId(rs.getInt("user_id"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>(); // 조회된 User 객체들을 저장할 리스트
        Connection con = null; // 데이터베이스 연결을 위한 객체
        PreparedStatement ps = null; // 쿼리 실행을 준비하는 객체
        ResultSet rs = null; // 쿼리 실행 결과를 담는 객체

        try {
            // DBConnectionMgr.getInstance()으로 싱글톤 객체를 가져오고 .getConnection()를 호출
            // 데이터베이스 연결을 획득
            con = DBConnectionMgr.getInstance().getConnection();
            String sql = """
                select 
                    user_id,
                    username,
                    password,
                    name,
                    email
                from 
                    user_tb
            """;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                users.add(User.builder()
                        .userId(rs.getInt(1))
                        .username(rs.getString(2))
                        .password(rs.getString(3))
                        .name(rs.getString(4))
                        .email(rs.getString(5))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }


    public Optional<User> save(User user) {
        Connection con = null; // 데이터베이스 연결을 위한 Connection
        PreparedStatement ps = null; // SQL 쿼리 실행을 위한 PreparedStatement
        // ps.get~ or set~ 가능

        try {
        // DBConnectionMgr.getInstance()으로 싱글톤 객체를 가져오고 .getConnection()를 호출
        // 데이터베이스 연결을 획득
        con = DBConnectionMgr.getInstance().getConnection();

        // jdk 16 은 이렇게 sql문을 """ """ 으로 관리 가능
        // user_tb 테이블에 데이터를 삽입하기 위한 INSERT 문
        String sql = """ 
            insert into user_tb 
            values(default, ?, ?, ?, ?);
        """;

        // Java에서 JDBC (Java Database Connectivity)를 사용하여 데이터베이스에 쿼리를 실행하기 위해 준비된 문(prepared statement)을 생성
        ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // JDBC에서 자동 생성된 키 값을 가져오기 위해 PreparedStatement를 생성할 때 사용하는 코드
        ps.setString(1, user.getUsername()); // ps는 prepareStatement로 쿼리를 실행할 수 있기 때문에 set타입()...등 이 가능
        ps.setString(2, user.getPassword()); // set타입() 을 하면 저기 ?에 알아서 ""를 씌우거나 안씌워서 사용함
        ps.setString(3, user.getName());
        ps.setString(4, user.getEmail());
        // execute() : 쿼리 실행. true, false 반환(됐는지 안됐는지), executeUpdate() : 성공 횟수에 대해 int 횟수를 return
        ps.executeUpdate(); // 현재 values는 위에 row1개. 즉 1이 나와야 함. insert, update, delete
        ResultSet keyRs = ps.getGeneratedKeys(); // ResultSet 은 빈 테이블형태, ResultSet은 기본적으로 하나의 행에 생성된 키 값이 포함
        keyRs.next(); // ResultSet의 커서를 첫 번째 행으로 이동
        int userId = keyRs.getInt(1); // 1 번째 컬럼(생성된 키)의 값을 int로 가져옴
        user.setUserId(userId); // 가져온 userId를 user에 저장

        } catch (Exception e) {
            e.printStackTrace();
        } finally { // 예외가 터지든 말든
            DBConnectionMgr.getInstance().freeConnection(con, ps); // freeConnection() : 연결을 해제. 객체를 반납?
        }

        return Optional.ofNullable(user);
    }
}
/*
제너레이드키를 가져오는 이유 : insert 하자마자 키값을 추가해야 할 때
안쓰면 insert하고 그걸 찾으려면 또 select를 써서 그 키 값을 찾아야함. 그럴빠에 insert할 때 키 값을 넣어서 바로 동시에 가능하게 하는 것
*/