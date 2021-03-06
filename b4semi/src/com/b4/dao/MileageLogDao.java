package com.b4.dao;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.b4.model.vo.MileageLog;
import static common.JDBCTemplate.close;

public class MileageLogDao {
	
	private Properties prop = new Properties();
	
	public MileageLogDao (){
		try {
			String fileName = MileageLogDao.class.getResource("/sql/mileage/mileage-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public int selectMileageLogCount(Connection conn, int memberSeq)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectMileageLogCount");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberSeq);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				result = rs.getInt("CN");
			}
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	
	public List<MileageLog> selectMileageLogList(Connection conn, int cPage, int numPerPage, int memberSeq)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MileageLog> list = new ArrayList<MileageLog>();
		String sql = prop.getProperty("selectMileageLogList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberSeq);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MileageLog ml = new MileageLog();
				ml.setMileageLogSeq(rs.getInt("mileageLogSeq"));
				ml.setMemberSeq(rs.getInt("memberSeq"));
				ml.setLogDate(rs.getTimestamp("logDate"));
				ml.setPreMileage(rs.getInt("preMileage"));
				ml.setNextMileage(rs.getInt("nextMileage"));
				ml.setLogTypeName(rs.getString("logTypeName"));
				list.add(ml);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		return list;
	}
	
	public List<MileageLog> selectAllMileageLogList(Connection conn, int memberSeq)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectAllMileageLogList");
		List<MileageLog> result = new ArrayList<MileageLog>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberSeq);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MileageLog ml = new MileageLog();
				ml.setMileageLogSeq(rs.getInt("mileageLogSeq"));
				ml.setMemberSeq(rs.getInt("memberSeq"));
				ml.setLogDate(rs.getTimestamp("logDate"));
				ml.setPreMileage(rs.getInt("preMileage"));
				ml.setNextMileage(rs.getInt("nextMileage"));
				result.add(ml);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
			close(rs);
		}
		return result;
	}
	
	public int createLog(Connection conn, MileageLog mlog)
	{
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("createLog");
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mlog.getMileageLogType());
			pstmt.setInt(2, mlog.getMemberSeq());
			pstmt.setTimestamp(3, mlog.getLogDate());
			pstmt.setInt(4, mlog.getPreMileage());
			pstmt.setInt(5, mlog.getNextMileage());
			result = pstmt.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(pstmt);
		}
		return result;
	}

	
/*import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.b4.model.vo.MileageLog;

public class MileageLogDao {
	
private Properties prop = new Properties();
	
	public MileageLogDao() {
		try {
			String fileName = MemberDao.class.getResource("/sql/mileageLog/mileageLog-query.properties").getPath();
			prop.load(new FileReader(fileName));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public int logCountByMember(Connection conn, int memberSeq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = prop.getProperty("logCountByMember");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberSeq);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				result = rs.getInt("CNT");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {close(rs);close(pstmt);}
		return result;
	}

	public List<MileageLog> mileageLogByMember(Connection conn, int cPage, int memberSeq) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MileageLog> list = new ArrayList<>();
		String sql = prop.getProperty("mileageLogByMember");
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberSeq);
			pstmt.setInt(2, (cPage-1)*7+1);
			pstmt.setInt(3, (cPage*7));
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				MileageLog ml = new MileageLog();
				ml.setMileageLogSeq(rs.getInt("MileageLogSeq"));
				ml.setMileageLogType(rs.getString("mileageLogType"));
				ml.setLogTypeName(rs.getString("logTypeName"));
				ml.setMemberSeq(rs.getInt("memberSeq"));
				ml.setLogDate(rs.getTimestamp("logDate"));
				ml.setPreMileage(rs.getInt("preMileage"));
				ml.setNextMileage(rs.getInt("nextMileage"));
				list.add(ml);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {close(rs);close(pstmt);}
		return list;	
	}
*/
	
}
