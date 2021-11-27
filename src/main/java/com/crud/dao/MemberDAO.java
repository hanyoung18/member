package com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.crud.bean.MemberVO;
import com.crud.common.JDBCUtil;

public class MemberDAO {
	private Connection conn=null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private final String MEMBER_INSERT="INSERT INTO member (userid, password, username, email,photo,detail)"+" values (?,SHA1(?),?,?,?,?)";
	private final String MEMBER_UPDATE="UPDATE member set username=?, email=?,photo=?,detail=?" + "where sid=?";
	private final String MEMBER_DELETE ="DELETE FROM member where sid=?";
	private final String MEMBER_SELETE="SELECT * FROM member where sid=?";
	private final String MEMBER_LIST = "select * from member order by regdate desc";
	
	public int insertMember(MemberVO vo) {
		int result=0;
		conn=JDBCUtil.getConnection();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_INSERT);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getPassword());
			stmt.setString(3, vo.getUsername());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getPhoto());
			stmt.setString(6, vo.getDetail());
			stmt.executeUpdate();
			result = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public void deleteMember(MemberVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_DELETE);
			stmt.setInt(1, vo.getSid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int updateMember(MemberVO vo) {
		int result=0;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_UPDATE);
			stmt.setString(1, vo.getUsername());
			stmt.setString(2, vo.getEmail());
			stmt.setString(3, vo.getPhoto());
			stmt.setString(4, vo.getDetail());
			stmt.setInt(5, vo.getSid());
			result = stmt.executeUpdate();
			stmt.executeUpdate();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public MemberVO getMember(int sid) {
		MemberVO one = new MemberVO();
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(MEMBER_SELETE);
			stmt.setInt(1, sid);
			rs = stmt.executeQuery();
			if(rs.next()) {
				one.setSid(rs.getInt("sid"));
				one.setUserid(rs.getString("userid"));
				one.setUsername(rs.getString("username"));
				one.setEmail(rs.getString("email"));
				one.setPhoto(rs.getString("photo"));
				one.setDetail(rs.getString("detail"));
				one.setRegdate(rs.getDate("regdate"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}
	public List<MemberVO> getMemberList(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(MEMBER_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				MemberVO one = new MemberVO();
				one.setSid(rs.getInt("sid"));
				one.setUserid(rs.getString("userid"));
				one.setUsername(rs.getString("username"));
				one.setEmail(rs.getString("email"));
				one.setRegdate(rs.getDate("regdate"));
				list.add(one);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
}
