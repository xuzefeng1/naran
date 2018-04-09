package com.naran.core.service.impl.points;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naran.core.dao.points.LevelDao;
import com.naran.core.entity.points.Level;
import com.naran.dubbo.service.points.ILevelService;
import com.naran.foundation.mybatis.page.Page;

/**
 * 
 * @author zefeng.xu
 */
@Transactional
@Service("levelService")
public class LevelServiceImpl implements ILevelService {

    @Autowired
    private LevelDao levelDao;

    @Override
    public Long addLevel(Level level) {
	if (level == null) {
	    return null;
	}
	return levelDao.addLevel(level);
    }

    @Override
    public void updateLevel(Level level) {
	if (level == null || level.getId() == null) {
	    return;
	}
	levelDao.updateLevel(level);

    }

    @Override
    public void deleteLevel(Long id) {
	if (id == null) {
	    return;
	}
	levelDao.deleteLevel(id);

    }

    @Override
    public Level findLevelById(Long id) {
	if (id == null) {
	    return null;
	}
	return levelDao.findLevelById(id);
    }

    @Override
    public Page<Level> findLevelByPage(int pageNum, int pageSize) {

	return levelDao.findLevelByPage(pageNum, pageSize);
    }

}
