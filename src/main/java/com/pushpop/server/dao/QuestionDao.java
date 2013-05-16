package com.pushpop.server.dao;

import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.pushpop.server.domain.Question;
import com.pushpop.server.domain.QuestionView;
import com.pushpop.shared.NotImplementedException;

public class QuestionDao extends DaoBase<Question> {

	public Long save(Question question) {
		try {
			return super.save(question);
		} catch (ConstraintViolationException constraingViolation) {
			for (ConstraintViolation<?> violation : constraingViolation
					.getConstraintViolations()) {
				System.out.println("Violation is "
						+ violation.getPropertyPath() + violation.getMessage());
			}
		}

		return null;
	}

	public List<Question> findSortedRange(int start, int max,
			String sortedColumn, String sortedBy) {
		throw new NotImplementedException();
	}
	
	
	public Question findAndIncrement(Long questionId) {
		Question question = find(questionId);
		QuestionView view = new QuestionView(question, currentUser());
		question.addView(view);
		getEntityManager().persist(question);
		return question;
	}

	/**
	 * Calls find and increment, but doesn't return the actual question.
	 * @param questionId
	 */
	public void incrementViews(Long questionId) {
		findAndIncrement(questionId);
	}
}