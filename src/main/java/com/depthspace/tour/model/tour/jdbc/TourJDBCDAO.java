package com.depthspace.tour.model.tour.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.depthspace.tour.model.tour.TourVO;
import com.depthspace.utils.DBUtil;

public class TourJDBCDAO implements TourDAO_Interface {
	private static final String INSERT_STMT = "INSERT INTO TOUR (MEM_ID, TOUR_NAME, TOUR_TYPE_ID, ALL_DAYS, TOUR_DESCRIPTION, TOUR_IMG) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE TOUR SET MEM_ID=? , TOUR_NAME=? , TOUR_TYPE_ID=? , ALL_DAYS=? , TOUR_DESCRIPTION=? , TOUR_IMG=? WHERE TOUR_ID=?";
	private static final String DELETE_STMT = "DELETE FROM TOUR WHERE TOUR_ID=?";
	private static final String GET_ONE_STMT = "SELECT TOUR_ID , MEM_ID, TOUR_NAME, TOUR_TYPE_ID, ALL_DAYS, TOUR_DESCRIPTION, TOUR_IMG FROM TOUR WHERE TOUR_ID=?";
	private static final String GET_ALL_STMT = "SELECT TOUR_ID , MEM_ID, TOUR_NAME, TOUR_TYPE_ID, ALL_DAYS, TOUR_DESCRIPTION, TOUR_IMG FROM TOUR ORDER BY TOUR_ID ";

	@Override
	public void insert(TourVO TourVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, TourVO.getMemId());
			pstmt.setString(2, TourVO.getTourName());
			pstmt.setInt(3, TourVO.getTourTypeId());
			pstmt.setInt(4, TourVO.getAllDays());
			pstmt.setString(5, TourVO.getTourDescription());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}

	@Override
	public void update(TourVO TourVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, TourVO.getTourId());
			pstmt.setInt(2, TourVO.getMemId());
			pstmt.setString(3, TourVO.getTourName());
			pstmt.setInt(4, TourVO.getTourTypeId());
			pstmt.setInt(5, TourVO.getAllDays());
			pstmt.setString(6, TourVO.getTourDescription());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer tourId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, tourId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, null);
		}
	}

	@Override
	public TourVO findByPrimaryKey(Integer tourId) {
		TourVO tourVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tourId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				tourVO = new TourVO();
				tourVO.setTourId(rs.getInt("TOUR_ID"));
				tourVO.setMemId(rs.getInt("MEM_ID"));
				tourVO.setTourName(rs.getString("TOUR_NAME"));
				tourVO.setTourTypeId(rs.getInt("TOUR_TYPE_ID"));
				tourVO.setAllDays(rs.getInt("ALL_DAYS"));
				tourVO.setTourDescription(rs.getString("TOUR_DESCRIPTION"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return tourVO;
	}

	@Override
	public List<TourVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<TourVO> tourList = new ArrayList<>();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TourVO tourVO = new TourVO();
				tourVO.setTourId(rs.getInt("TOUR_ID"));
				tourVO.setMemId(rs.getInt("MEM_ID"));
				tourVO.setTourName(rs.getString("TOUR_NAME"));
				tourVO.setTourTypeId(rs.getInt("TOUR_TYPE_ID"));
				tourVO.setAllDays(rs.getInt("ALL_DAYS"));
				tourVO.setTourDescription(rs.getString("TOUR_DESCRIPTION"));
				tourList.add(tourVO);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(con, pstmt, rs);
		}
		return tourList;
	}
}
