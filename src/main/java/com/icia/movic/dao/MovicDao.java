package com.icia.movic.dao;

import com.icia.movic.dto.BookMarkDto;
import com.icia.movic.dto.MovicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MovicDao {
    List<MovicDto> selectMovicList(Map<String, Object> map);
    List<MovicDto> selectMovicListByGenre(Map<String, Object> map);

    int selectMovicCnt(Map<String, Object> map);

    void insertMovic(MovicDto movic);

    MovicDto selectMovieDetail(Integer mCode);

    void deleteMovie(Integer m_code);

    void insertBookMark(BookMarkDto bookmark);

    List<BookMarkDto> selectBookmarkList(Map<String, Object> map);

    List<MovicDto> selectMovicListPopular(Map<String, Object> map);

    int selectMovicPopularCnt(Map<String, Object> map);

    int selectMovicBookmarkCnt(Map<String, Object> map);

    void deleteBookMark(Integer b_code);
}
