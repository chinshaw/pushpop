package com.pushpop.server.dao;

import java.util.List;


import com.pushpop.server.domain.Question;
import com.pushpop.shared.NotImplementedException;

public class QuestionDao extends DaoBase<Question> {
    
    
    public Long save(Question question) {
        return super.save(question);
    }
    
    public List<Question> findSortedRange(int start, int max, String sortedColumn, String sortedBy) {
        throw new NotImplementedException();
    }
}
