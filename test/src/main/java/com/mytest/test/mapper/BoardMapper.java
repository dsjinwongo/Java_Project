package com.mytest.test.mapper;

import java.util.List;

import com.mytest.test.domain.AmericaVO;
import com.mytest.test.domain.BoardVO;
import com.mytest.test.domain.ChickenVO;
import com.mytest.test.domain.ChinaVO;
import com.mytest.test.domain.DessertVO;
import com.mytest.test.domain.JapanVO;
import com.mytest.test.domain.KoreaVO;
import com.mytest.test.domain.SnackVO;

public interface BoardMapper {
	public List<BoardVO> viewAll();
	public List<KoreaVO> viewKorea();
	public List<DessertVO> viewDessert();
	public List<AmericaVO> viewAmerica();
	public List<ChickenVO> viewChicken();
	public List<JapanVO> viewJapan();
	public List<SnackVO> viewSnack();
	public List<ChinaVO> viewChina();
	public BoardVO viewDetail(int seq);
	public boolean plusCnt(int seq);
	public int insertBoard(BoardVO vo);
}