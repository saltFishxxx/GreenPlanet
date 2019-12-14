package cn.xyh.tree.dao;

import cn.xyh.tree.domain.NewsImg;

import java.util.List;

public interface NewsImgDao {
    public int upload(NewsImg newsImg); //图片上传,单个上传
    public List<NewsImg> downLoad(int nid); //图片下载,多个下载
}
